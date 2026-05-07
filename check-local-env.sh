#!/usr/bin/env bash
# check-local-env.sh — Vérifie l'environnement local pour gestionBiblio
set -euo pipefail

GREEN='\033[0;32m'; RED='\033[0;31m'; YELLOW='\033[1;33m'; NC='\033[0m'
ok()   { echo -e "  ${GREEN}[OK]${NC}    $1"; }
fail() { echo -e "  ${RED}[ERREUR]${NC} $1"; }
warn() { echo -e "  ${YELLOW}[WARN]${NC}   $1"; }

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "  gestionBiblio — Vérification env local"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

# Java
if command -v java &>/dev/null; then
  VER=$(java -version 2>&1 | awk -F '"' '/version/{print $2}' | cut -d. -f1)
  if [ "$VER" -ge 17 ]; then ok "Java $VER (requis: 17+)"; else fail "Java $VER — requis 17+"; fi
else fail "Java non installé — brew install openjdk@17"; fi

# Maven
if command -v mvn &>/dev/null; then
  ok "Maven $(mvn -q --version 2>&1 | head -1 | awk '{print $3}')";
else fail "Maven non installé — brew install maven"; fi

# Docker
if command -v docker &>/dev/null && docker info &>/dev/null 2>&1; then
  ok "Docker $(docker --version | awk '{print $3}' | tr -d ',') — daemon actif"
else
  if command -v docker &>/dev/null; then fail "Docker installé mais daemon inactif — lance Docker Desktop"
  else fail "Docker non installé"; fi
fi

# kubectl
if command -v kubectl &>/dev/null; then ok "kubectl $(kubectl version --client -o json 2>/dev/null | grep gitVersion | head -1 | tr -d ' ",' | cut -d: -f2)"
else warn "kubectl non installé (optionnel si Docker Desktop K8s activé)"; fi

# Helm
if command -v helm &>/dev/null; then ok "Helm $(helm version --short 2>/dev/null)"
else fail "Helm non installé — brew install helm"; fi

# act
if command -v act &>/dev/null; then ok "act $(act --version 2>/dev/null)"
else warn "act non installé (optionnel) — brew install act"; fi

# minikube
if command -v minikube &>/dev/null; then
  STATUS=$(minikube status --format='{{.Host}}' 2>/dev/null || echo "Stopped")
  ok "minikube installé — statut: $STATUS"
else warn "minikube non installé (optionnel) — brew install minikube"; fi

# ArgoCD CLI
if command -v argocd &>/dev/null; then ok "ArgoCD CLI $(argocd version --client -o json 2>/dev/null | grep BuildDate | head -1 || echo 'installé')"
else warn "ArgoCD CLI non installé (optionnel) — brew install argocd"; fi

# Port 8000 libre
if ! lsof -i:8000 &>/dev/null 2>&1; then ok "Port 8000 libre"
else warn "Port 8000 occupé — l'app Spring Boot ne pourra pas démarrer"; fi

# Fichier .github/workflows/ci.yml
if [ -f ".github/workflows/ci.yml" ]; then
  LINES=$(wc -l < .github/workflows/ci.yml)
  if [ "$LINES" -gt 10 ]; then ok ".github/workflows/ci.yml présent et non vide"
  else warn ".github/workflows/ci.yml présent mais presque vide ($LINES lignes) — à compléter"; fi
else fail ".github/workflows/ci.yml manquant"; fi

# Dockerfile
if [ -f "Dockerfile" ]; then ok "Dockerfile présent"
else fail "Dockerfile manquant — voir Section 6 de la documentation"; fi

# Helm chart
if [ -d "helm" ]; then ok "Dossier helm/ présent"
else fail "Dossier helm/ manquant — voir Section 8 de la documentation"; fi

# pom.xml
if [ -f "pom.xml" ]; then ok "pom.xml présent"
else fail "pom.xml manquant"; fi

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "  Terminé. Corrige les [ERREUR] avant de continuer."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
