#!/bin/bash

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

echo "Starting DevSecOps Petshop teardown..."
echo "Project root: ${ROOT_DIR}"

delete_argocd_app() {
  local manifest="$1"
  if [ -f "${manifest}" ]; then
    echo "Deleting ArgoCD application manifest: ${manifest}"
    kubectl delete -f "${manifest}" --ignore-not-found=true || true
  fi
}

destroy_stack() {
  local stack_dir="$1"
  echo "Destroying Terraform stack: ${stack_dir}"
  terraform -chdir="${stack_dir}" destroy -auto-approve
}

delete_argocd_app "${ROOT_DIR}/gitops/petshop-prod/app.yaml"
delete_argocd_app "${ROOT_DIR}/gitops/petshop-stage/app.yaml"
delete_argocd_app "${ROOT_DIR}/gitops/petshop-dev/app.yaml"

destroy_stack "${ROOT_DIR}/infra/envs/prod"
destroy_stack "${ROOT_DIR}/infra/envs/stage"
destroy_stack "${ROOT_DIR}/infra/envs/dev"
destroy_stack "${ROOT_DIR}/infra"

echo "Teardown complete."
