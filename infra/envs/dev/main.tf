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
  name            = "petshop-dev-vpc"
  cidr            = "10.0.0.0/16"
  azs             = ["us-west-2a", "us-west-2b"]
  private_subnets = ["10.0.1.0/24", "10.0.2.0/24"]
  public_subnets  = ["10.0.101.0/24", "10.0.102.0/24"]
  tags = {
    Environment = "dev"
    Project     = "petshop"
  }
}

module "eks" {
  source          = "../../modules/eks"
  cluster_name    = "petshop-dev-cluster"
  vpc_id          = module.vpc.vpc_id
  private_subnets = module.vpc.private_subnets
  env             = "dev"
}
