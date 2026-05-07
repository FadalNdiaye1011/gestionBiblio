# check-local-env.ps1 — Vérifie l'environnement local pour gestionBiblio (Windows)
$OK    = "[OK]   "
$FAIL  = "[ERREUR]"
$WARN  = "[WARN] "

function ok($msg)   { Write-Host "  $OK    $msg" -ForegroundColor Green }
function fail($msg) { Write-Host "  $FAIL $msg" -ForegroundColor Red }
function warn($msg) { Write-Host "  $WARN  $msg" -ForegroundColor Yellow }

Write-Host ""
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
Write-Host "  gestionBiblio — Vérification env local"
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
Write-Host ""

# Java
$java = Get-Command java -ErrorAction SilentlyContinue
if ($java) {
    $ver = (java -version 2>&1)[0] -replace '.*"(\d+).*".*','$1'
    if ([int]$ver -ge 17) { ok "Java $ver (requis: 17+)" } else { fail "Java $ver — requis 17+" }
} else { fail "Java non installé — winget install Microsoft.OpenJDK.17" }

# Maven
$mvn = Get-Command mvn -ErrorAction SilentlyContinue
if ($mvn) { ok "Maven installé — $(mvn -q --version 2>&1 | Select-Object -First 1)" } else { fail "Maven non installé" }

# Docker
$docker = Get-Command docker -ErrorAction SilentlyContinue
if ($docker) {
    try { docker info 2>&1 | Out-Null; ok "Docker $(docker --version) — daemon actif" }
    catch { fail "Docker installé mais daemon inactif — lance Docker Desktop" }
} else { fail "Docker non installé — https://docs.docker.com/desktop/windows/" }

# kubectl
$kubectl = Get-Command kubectl -ErrorAction SilentlyContinue
if ($kubectl) { ok "kubectl installé" } else { warn "kubectl non installé (optionnel)" }

# Helm
$helm = Get-Command helm -ErrorAction SilentlyContinue
if ($helm) { ok "Helm $(helm version --short 2>$null)" } else { fail "Helm non installé — winget install Helm.Helm" }

# act
$act = Get-Command act -ErrorAction SilentlyContinue
if ($act) { ok "act installé" } else { warn "act non installé (optionnel) — https://github.com/nektos/act" }

# minikube
$minikube = Get-Command minikube -ErrorAction SilentlyContinue
if ($minikube) { ok "minikube installé" } else { warn "minikube non installé (optionnel) — winget install Kubernetes.minikube" }

# Port 8000
$port = Get-NetTCPConnection -LocalPort 8000 -ErrorAction SilentlyContinue
if (-not $port) { ok "Port 8000 libre" } else { warn "Port 8000 occupé — l'app ne pourra pas démarrer" }

# Fichiers clés
if (Test-Path ".github/workflows/ci.yml") {
    $lines = (Get-Content ".github/workflows/ci.yml").Count
    if ($lines -gt 10) { ok ".github/workflows/ci.yml présent" } else { warn ".github/workflows/ci.yml présent mais presque vide ($lines lignes)" }
} else { fail ".github/workflows/ci.yml manquant" }

if (Test-Path "Dockerfile")  { ok "Dockerfile présent" }  else { fail "Dockerfile manquant — voir Section 6" }
if (Test-Path "helm")        { ok "Dossier helm/ présent" } else { fail "Dossier helm/ manquant — voir Section 8" }
if (Test-Path "pom.xml")     { ok "pom.xml présent" }      else { fail "pom.xml manquant" }

Write-Host ""
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
Write-Host "  Terminé. Corrige les [ERREUR] avant de continuer."
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
Write-Host ""
