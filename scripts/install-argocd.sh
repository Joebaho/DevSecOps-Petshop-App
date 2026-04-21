#!/bin/bash

set -euo pipefail

PORT_FORWARD="${INSTALL_ARGOCD_PORT_FORWARD:-true}"
PRINT_PASSWORD="${INSTALL_ARGOCD_PRINT_PASSWORD:-true}"

kubectl create namespace argocd --dry-run=client -o yaml | kubectl apply -f -

kubectl apply --server-side=true --force-conflicts -n argocd \
  -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

echo "Waiting for ArgoCD..."
kubectl wait --for=condition=available deployment/argocd-server -n argocd --timeout=300s

if [ "${PORT_FORWARD}" = "true" ]; then
  kubectl port-forward svc/argocd-server -n argocd 8080:443 &
  echo "Access ArgoCD: https://localhost:8080"
fi

if [ "${PRINT_PASSWORD}" = "true" ]; then
  kubectl -n argocd get secret argocd-initial-admin-secret \
  -o jsonpath="{.data.password}" | base64 -d && echo
fi
