output "repository_url" {
  value = module.ecr.repository_url
}

output "github_actions_role_arn" {
  value = module.github_actions_oidc.role_arn
}

output "github_actions_oidc_provider_arn" {
  value = module.github_actions_oidc.oidc_provider_arn
}
