#!/bin/bash

set -euo pipefail

kubectl create namespace argocd --dry-run=client -o yaml | kubectl apply -f -

kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

echo "Waiting for ArgoCD..."
kubectl wait --for=condition=available deployment/argocd-server -n argocd --timeout=300s

kubectl port-forward svc/argocd-server -n argocd 8080:443 &
echo "Access ArgoCD: https://localhost:8080"

kubectl -n argocd get secret argocd-initial-admin-secret \
-o jsonpath="{.data.password}" | base64 -d && echo
