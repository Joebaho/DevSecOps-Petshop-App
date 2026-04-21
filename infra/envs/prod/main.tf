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
  region = "us-west-2"
}

module "vpc" {
  source          = "../../modules/vpc"
  name            = "petshop-prod-vpc"
  cidr            = "10.2.0.0/16"
  azs             = ["us-west-2a", "us-west-2b"]
  private_subnets = ["10.2.1.0/24", "10.2.2.0/24"]
  public_subnets  = ["10.2.101.0/24", "10.2.102.0/24"]
  tags = {
    Environment = "prod"
    Project     = "petshop"
  }
}

module "eks" {
  source          = "../../modules/eks"
  cluster_name    = "petshop-prod-cluster"
  vpc_id          = module.vpc.vpc_id
  private_subnets = module.vpc.private_subnets
  env             = "prod"
}
