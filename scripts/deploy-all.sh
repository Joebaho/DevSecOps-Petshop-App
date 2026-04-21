#!/bin/bash

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
AWS_REGION="${AWS_REGION:-us-west-2}"
DEPLOY_STAGE_AND_PROD_APPS="${DEPLOY_STAGE_AND_PROD_APPS:-false}"

echo "Starting DevSecOps Petshop deployment..."
echo "Project root: ${ROOT_DIR}"
echo "AWS region: ${AWS_REGION}"

require_command() {
  local cmd="$1"
  if ! command -v "${cmd}" >/dev/null 2>&1; then
    echo "Required command not found: ${cmd}"
    exit 1
  fi
}

apply_stack() {
  local stack_dir="$1"
  echo "Initializing Terraform stack: ${stack_dir}"
  terraform -chdir="${stack_dir}" init
  echo "Applying Terraform stack: ${stack_dir}"
  terraform -chdir="${stack_dir}" apply -auto-approve
}

configure_cluster() {
  local env="$1"
  local cluster_name="petshop-${env}-cluster"

  echo "Updating kubeconfig for cluster: ${cluster_name}"
  aws eks update-kubeconfig --region "${AWS_REGION}" --name "${cluster_name}" --alias "${cluster_name}"
  kubectl config use-context "${cluster_name}"

  echo "Installing ArgoCD on ${cluster_name}"
  INSTALL_ARGOCD_PORT_FORWARD=false INSTALL_ARGOCD_PRINT_PASSWORD=false \
    "${ROOT_DIR}/scripts/install-argocd.sh"
}

apply_gitops_app() {
  local env="$1"
  echo "Applying GitOps application for ${env}"
  kubectl apply -f "${ROOT_DIR}/gitops/petshop-${env}/app.yaml"
}

require_command terraform
require_command kubectl
require_command aws

apply_stack "${ROOT_DIR}/infra"

GITHUB_ACTIONS_ROLE_ARN="$(terraform -chdir="${ROOT_DIR}/infra" output -raw github_actions_role_arn)"
ECR_REPOSITORY_URL="$(terraform -chdir="${ROOT_DIR}/infra" output -raw repository_url)"

echo
echo "Terraform created the GitHub Actions role:"
echo "  ${GITHUB_ACTIONS_ROLE_ARN}"
echo "Add it in GitHub repository secrets as AWS_ROLE_ARN."
echo "Also add/update GitHub repository variables:"
echo "  AWS_REGION=${AWS_REGION}"
echo "  ECR_REPOSITORY=$(basename "${ECR_REPOSITORY_URL}")"
echo

for env in dev stage prod; do
  apply_stack "${ROOT_DIR}/infra/envs/${env}"
  configure_cluster "${env}"
done

kubectl config use-context "petshop-dev-cluster"
apply_gitops_app "dev"

if [ "${DEPLOY_STAGE_AND_PROD_APPS}" = "true" ]; then
  kubectl config use-context "petshop-stage-cluster"
  apply_gitops_app "stage"

  kubectl config use-context "petshop-prod-cluster"
  apply_gitops_app "prod"
else
  echo "Skipping stage and prod app registration for now."
  echo "After you promote a valid image tag, you can register them with:"
  echo "  kubectl config use-context petshop-stage-cluster && kubectl apply -f ${ROOT_DIR}/gitops/petshop-stage/app.yaml"
  echo "  kubectl config use-context petshop-prod-cluster && kubectl apply -f ${ROOT_DIR}/gitops/petshop-prod/app.yaml"
fi

echo
echo "Deployment automation complete."
echo "Next step: push to main so GitHub Actions builds the image and updates dev GitOps values."
