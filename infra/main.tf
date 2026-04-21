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
