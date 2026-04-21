variable "github_owner" {
  type = string
}

variable "github_repo" {
  type = string
}

variable "github_branch" {
  type    = string
  default = "main"
}

variable "role_name" {
  type = string
}

variable "ecr_repository_arn" {
  type = string
}

variable "tags" {
  type    = map(string)
  default = {}
}
