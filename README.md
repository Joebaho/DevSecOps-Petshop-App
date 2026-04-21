# DevSecOps 3-Tier Application

## Stack
- Terraform (AWS ECR, VPC, EKS)
- Docker
- Kubernetes
- Helm
- ArgoCD (GitOps)
- GitHub Actions (CI/CD)
- Trivy (Security)
- SonarQube (Code Quality)

## Flow
1. Developer pushes code
2. CI builds, scans, and pushes the container image to ECR
3. CI updates `helm/petshop/values-dev.yaml` with the new image tag
4. ArgoCD detects the Git change
5. ArgoCD deploys to the target Kubernetes namespace
6. Manual promotion workflow updates `stage` or `prod` values files

## Layout
- `app/`: Java application and Docker build
- `.github/workflows/`: CI build and promotion workflows
- `helm/petshop/`: Helm chart plus environment-specific values
- `gitops/`: ArgoCD applications for `dev`, `stage`, and `prod`
- `infra/`: Terraform for ECR plus per-environment VPC and EKS stacks

## Prerequisites
- A GitHub repository for this project
- AWS account access
- GitHub Actions secret: `AWS_ROLE_ARN`
- GitHub Actions variables:
  - `AWS_REGION`
  - `ECR_REPOSITORY`

## Region
- Default AWS region is `us-west-2`
- The Terraform environment stacks use `us-west-2a` and `us-west-2b`

## Repository
- GitHub repository: `https://github.com/Joebaho/DevSecOps-Petshop-App.git`

## Before First Deployment
1. Create the ECR repository:
   - `terraform -chdir=infra init`
   - `terraform -chdir=infra apply`
2. Create the EKS environments you want:
   - `terraform -chdir=infra/envs/dev init && terraform -chdir=infra/envs/dev apply`
   - `terraform -chdir=infra/envs/stage init && terraform -chdir=infra/envs/stage apply`
   - `terraform -chdir=infra/envs/prod init && terraform -chdir=infra/envs/prod apply`
3. Install ArgoCD:
   - `./scripts/install-argocd.sh`
4. Apply the ArgoCD applications:
   - `kubectl apply -f gitops/petshop-dev/app.yaml`
   - `kubectl apply -f gitops/petshop-stage/app.yaml`
   - `kubectl apply -f gitops/petshop-prod/app.yaml`

## Promotion
- Push to `main` to build, scan, push, and deploy to `dev`
- Run the `Promote` GitHub Actions workflow to move an existing image tag to `stage` or `prod`
