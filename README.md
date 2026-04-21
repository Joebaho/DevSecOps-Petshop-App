# DevSecOps Petshop Application

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
- GitHub Actions variables:
  - `AWS_REGION`
  - `ECR_REPOSITORY`

## Region
- Default AWS region is `us-west-2`
- The Terraform environment stacks use `us-west-2a` and `us-west-2b`

## Repository
- GitHub repository: `https://github.com/Joebaho/DevSecOps-Petshop-App.git`

## Before First Deployment
1. Run the automated deployment bootstrap:
   - `./scripts/deploy-infra-and-dev.sh`
2. Copy the `github_actions_role_arn` Terraform output shown by the script into the GitHub Actions secret `AWS_ROLE_ARN`
3. Confirm the GitHub repository variables exist:
   - `AWS_REGION=us-west-2`
   - `ECR_REPOSITORY=petshop-app`
4. Push to `main` so CI builds the first image and updates `helm/petshop/values-dev.yaml`
5. Promote the image to `stage` and `prod` when you are ready

## Promotion
- Push to `main` to build, scan, push, and deploy to `dev`
- Run the `Promote` GitHub Actions workflow to move an existing image tag to `stage` or `prod`

## Deploy Infrastructure And Dev
- Recommended command:
  - `./scripts/deploy-infra-and-dev.sh`
- Backward-compatible alias:
  - `./scripts/deploy-all.sh`
- The script:
  - creates the shared root infrastructure in `infra/`
  - creates all three EKS environments
  - installs ArgoCD in each cluster
  - applies the `dev` ArgoCD application by default
  - prints the GitHub Actions IAM role ARN you need for the `AWS_ROLE_ARN` secret
- By default it does not register `stage` and `prod` apps immediately, because those environments depend on a promoted image tag.
- If you want it to register all three apps right away, run:
  - `DEPLOY_STAGE_AND_PROD_APPS=true ./scripts/deploy-infra-and-dev.sh`

## GitHub Actions AWS Access
- Terraform creates an AWS OIDC provider for GitHub Actions and an IAM role scoped to `Joebaho/DevSecOps-Petshop-App` on the `main` branch.
- The CI workflow in `.github/workflows/ci.yaml` assumes that role using the `AWS_ROLE_ARN` GitHub secret.
- For the current pipeline, that role only needs ECR permissions because GitHub Actions pushes the image and commits the Helm values update back to GitHub.
- ArgoCD handles the Kubernetes deployment after the Git change, so GitHub Actions does not currently need direct EKS permissions.

## Destroy Everything
- To tear down the full project in the correct order, run:
  - `./scripts/destroy-all.sh`
- The script:
  - deletes the ArgoCD applications
  - destroys `prod`, then `stage`, then `dev`
  - destroys the shared root infrastructure in `infra/`
- Make sure your AWS credentials and `kubectl` context are still valid before running it.
- If ECR contains images, Terraform may require the repository to be emptied before deletion completes.
