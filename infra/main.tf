terraform {
  required_version = ">= 1.5.0"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.region
}

module "ecr" {
  source          = "./modules/ecr"
  repository_name = var.repository_name
  tags = {
    Project = "petshop"
  }
}

module "github_actions_oidc" {
  source             = "./modules/github_actions_oidc"
  github_owner       = var.github_owner
  github_repo        = var.github_repo
  github_branch      = var.github_branch
  role_name          = var.github_actions_role_name
  ecr_repository_arn = module.ecr.repository_arn
  tags = {
    Project = "petshop"
  }
}
