# 🚀 CI/CD Pipeline - Quick Start

## What Has Been Set Up

Your project now has a **complete, production-ready CI/CD pipeline** with:

✅ **GitHub Actions** - Automated build and deploy
✅ **Docker** - Containerization
✅ **Kubernetes** - Production orchestration
✅ **Monitoring** - Prometheus + Grafana
✅ **Security** - Trivy scanning, RBAC, Network policies
✅ **Documentation** - Comprehensive guides

---

## Files Created

```
.github/workflows/
├── ci-cd-pipeline.yml ........... GitHub Actions workflow (7 stages)

Dockerfile ...................... Multi-stage Docker build

k8s/
├── namespace.yaml ............... Create namespaces
├── staging/
│   └── deployment.yaml ......... Staging deployment (2 replicas)
└── production/
    └── deployment.yaml ......... Production deployment (3+ replicas)

docker-compose.yml .............. Local testing with all services

monitoring/
├── prometheus.yml .............. Prometheus configuration
├── alert_rules.yml ............. Alert rules
└── nginx/nginx.conf ............ Nginx reverse proxy

docs/
└── CI_CD_PIPELINE_GUIDE.md .... Complete documentation
```

---

## 5-Minute Quick Start

### 1️⃣ Add GitHub Secrets (Required for deployment)

Go to: **GitHub Repo → Settings → Secrets and variables → Actions**

Add this secret:

```
Name: KUBE_CONFIG_STAGING
Value: <your-kubeconfig-file-content-base64-encoded>
```

To get base64 kubeconfig:
```bash
cat ~/.kube/config | base64
```

### 2️⃣ Test Locally (Optional)

```bash
# Start all services
docker-compose up -d

# Test API
curl http://localhost:8080/actuator/health

# View dashboards
# Grafana: http://localhost:3000
# Prometheus: http://localhost:9090

# Stop when done
docker-compose down
```

### 3️⃣ Push to GitHub

```bash
git add .
git commit -m "ci: add complete CI/CD pipeline with K8s and Docker"
git push origin SpringBoot-Rest-Complete
```

GitHub Actions will automatically:
- ✅ Build your code
- ✅ Run tests
- ✅ Build Docker image
- ✅ Scan for vulnerabilities
- ✅ Deploy to staging (if kubeconfig added)
- ✅ Run integration tests
- ✅ Deploy to production (if kubeconfig added)

---

## Viewing Pipeline Status

### In GitHub

1. Go to repository
2. Click **Actions** tab
3. View running workflows
4. Click workflow for details

### Pipeline Stages

```
📦 BUILD & TEST
   ↓
🐳 BUILD DOCKER IMAGE
   ↓
🔒 SECURITY SCANNING
   ↓
🎯 DEPLOY TO STAGING
   ↓
✅ INTEGRATION TESTS
   ↓
⭐ DEPLOY TO PRODUCTION
   ↓
🔔 NOTIFICATIONS
```

---

## Key Commands

### View Deployments

```bash
# Staging
kubectl get pods -n books-staging
kubectl describe deployment books-api -n books-staging

# Production
kubectl get pods -n books-production
kubectl describe deployment books-api -n books-production
```

### View Logs

```bash
# Stream logs
kubectl logs -f deployment/books-api -n books-staging
```

### Monitor

```bash
# Prometheus: http://localhost:9090
# Grafana: http://localhost:3000
# Jaeger: http://localhost:16686
```

### Scale

```bash
# Manual scaling
kubectl scale deployment books-api -n books-staging --replicas=5

# Auto-scaling (already configured)
kubectl get hpa -n books-staging
```

---

## What Happens on Push

1. **GitHub Actions triggered**
   - Code is checked out
   - Java 17 & Maven set up

2. **Build & Test**
   - Maven compiles code
   - Unit tests run
   - JAR artifact created

3. **Docker Image**
   - Multi-stage build (optimized)
   - Image pushed to GitHub Container Registry
   - Size: ~150-200MB (optimized)

4. **Security**
   - Trivy scans for vulnerabilities
   - Results shown in GitHub Security tab

5. **Deploy to Staging**
   - 2 replicas deployed
   - Rolling update (no downtime)
   - Health checks verify status

6. **Integration Tests**
   - API endpoints tested
   - Reports generated

7. **Deploy to Production**
   - 3+ replicas deployed
   - Auto-scaling enabled (3-10 replicas)
   - High availability setup

---

## Monitoring Dashboard

### Grafana Access

```
URL: http://localhost:3000
Username: admin
Password: admin
```

**Pre-built dashboards show:**
- CPU & Memory usage
- Request count & latency
- Error rates
- JVM metrics
- Pod information

### Prometheus Queries

```
# CPU usage
process_cpu_usage

# Error rate
rate(http_server_requests_seconds_count{status=~"5.."}[5m])

# Response time (p95)
histogram_quantile(0.95, rate(http_server_requests_seconds_bucket[5m]))
```

---

## Security Features

✅ **Container Security**
- Non-root user (UID: 1000)
- Read-only root filesystem
- No privileged capabilities

✅ **Kubernetes Security**
- Network policies (ingress/egress)
- RBAC (Role-Based Access Control)
- Resource limits enforced
- Pod security context

✅ **CI/CD Security**
- Trivy vulnerability scanning
- GitHub Actions runners isolated
- Secrets encrypted
- Base64 encoded kubeconfig

---

## Troubleshooting

### Pipeline failing?

1. Check **Actions** tab in GitHub
2. Click failed job
3. Scroll to see error message
4. Common issues:
   - Missing GitHub secrets
   - Kubeconfig invalid
   - Kubernetes cluster not ready

### Deployment stuck?

```bash
# Check deployment status
kubectl describe deployment books-api -n books-staging

# View events
kubectl get events -n books-staging

# Check pod logs
kubectl logs <pod-name> -n books-staging
```

### Can't access service?

```bash
# Port forward to test
kubectl port-forward svc/books-api-service 8080:80 -n books-staging

# Test locally
curl http://localhost:8080/actuator/health
```

---

## Next Steps

1. ✅ **Commit & Push** your code
2. ⏱️ **Wait** for GitHub Actions (5-10 minutes)
3. 📊 **Monitor** deployment in Actions tab
4. 🎯 **Check** your running pods: `kubectl get pods -n books-staging`
5. 📈 **View** Grafana dashboards: `http://localhost:3000`

---

## Complete Documentation

For detailed information, see: **docs/CI_CD_PIPELINE_GUIDE.md**

Includes:
- Architecture diagrams
- Setup instructions
- Kubernetes commands
- Monitoring setup
- Troubleshooting guide
- Security best practices

---

## Summary

Your pipeline is **completely automated**:

- **Triggered by:** Push to GitHub
- **Runs on:** GitHub Actions (free tier)
- **Deploys to:** Your Kubernetes cluster
- **Monitors:** Prometheus + Grafana
- **Secures:** Trivy scanning + RBAC
- **Notifies:** Slack (optional)

**All you need to do is push your code!** 🚀

---

Questions? Check the main CI/CD guide or GitHub Actions logs.
