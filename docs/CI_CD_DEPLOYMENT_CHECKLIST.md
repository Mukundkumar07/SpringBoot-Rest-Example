# CI/CD Deployment Checklist

## Pre-Deployment Checklist

### Local Setup

- [ ] Clone repository: `git clone https://github.com/Mukundkumar07/SpringBoot-Rest-Example.git`
- [ ] Switch branch: `git checkout SpringBoot-Rest-Complete`
- [ ] Verify files exist:
  - [ ] `.github/workflows/ci-cd-pipeline.yml`
  - [ ] `Dockerfile`
  - [ ] `k8s/namespace.yaml`
  - [ ] `k8s/staging/deployment.yaml`
  - [ ] `k8s/production/deployment.yaml`
  - [ ] `docker-compose.yml`

### Docker Setup

- [ ] Docker installed: `docker --version`
- [ ] Docker daemon running: `docker ps`
- [ ] Can build image: `docker build -t books-api:test .`

### Kubernetes Setup

- [ ] kubectl installed: `kubectl version --client`
- [ ] Staging cluster accessible: `kubectl cluster-info`
- [ ] Production cluster accessible: `kubectl config use-context production && kubectl cluster-info`
- [ ] Back to staging: `kubectl config use-context staging`

### GitHub Setup

- [ ] Push access to repository
- [ ] Repository settings accessible
- [ ] Can access Actions tab

---

## Step 1: Test Locally (Optional but Recommended)

```bash
# Navigate to project
cd /Users/mukundkumar/books

# Start all services
docker-compose up -d

# Wait 30 seconds for services to start
sleep 30

# Check if API is running
curl http://localhost:8080/actuator/health

# Expected output:
# {"status":"UP"}

# Check databases
docker-compose ps

# All services should be up:
# ✓ books-api
# ✓ postgres
# ✓ prometheus
# ✓ grafana
# ✓ jaeger
# ✓ nginx

# View Grafana dashboards
# http://localhost:3000 (admin/admin)

# Stop services when done
docker-compose down
```

- [ ] API responds to health check
- [ ] All Docker containers running
- [ ] Grafana accessible
- [ ] Prometheus accessible

---

## Step 2: Configure GitHub Secrets

### Get Kubeconfig

```bash
# For Staging Cluster
cat ~/.kube/config | base64

# Copy the entire base64 output
# It will look like: YXBpVmVyc2lvbjogdjEKY2x1c3RlcnM6...
```

### Add to GitHub

1. Go to repository on GitHub
2. Click **Settings** (top right)
3. Left sidebar → **Secrets and variables** → **Actions**
4. Click **New repository secret**

**Secret 1: Staging Kubeconfig**
```
Name: KUBE_CONFIG_STAGING
Value: <paste-base64-kubeconfig-here>
```

5. Click **Add secret**
6. Repeat for **KUBE_CONFIG_PRODUCTION** if you have a production cluster

- [ ] KUBE_CONFIG_STAGING added
- [ ] KUBE_CONFIG_PRODUCTION added (optional)
- [ ] Secrets are visible in settings

---

## Step 3: Verify Kubernetes Resources

### Create Namespaces

```bash
# Apply namespace manifest
kubectl apply -f k8s/namespace.yaml

# Verify namespaces created
kubectl get namespaces | grep books
# Expected output:
# books-production   Active   10s
# books-staging      Active   10s
```

- [ ] books-staging namespace exists
- [ ] books-production namespace exists

### Verify RBAC

```bash
# Check service accounts
kubectl get sa -n books-staging
# Expected: books-api-sa

kubectl get sa -n books-production
# Expected: books-api-sa
```

- [ ] Service accounts created
- [ ] Role bindings created

---

## Step 4: Commit & Push Changes

```bash
# Navigate to project
cd /Users/mukundkumar/books

# Stage all files
git add .

# Verify files staged
git status

# Should show:
# New files:
# - .github/workflows/ci-cd-pipeline.yml
# - Dockerfile
# - docker-compose.yml
# - k8s/namespace.yaml
# - k8s/staging/deployment.yaml
# - k8s/production/deployment.yaml
# - monitoring/prometheus.yml
# - monitoring/alert_rules.yml
# - nginx/nginx.conf
# - docs/CI_CD_PIPELINE_GUIDE.md
# - docs/CI_CD_QUICK_START.md

# Commit
git commit -m "ci: add complete CI/CD pipeline with Kubernetes and Docker

- Add GitHub Actions workflow with 7 stages
- Create multi-stage Dockerfile for optimized image
- Add Kubernetes manifests for staging and production
- Include Docker Compose for local testing
- Setup Prometheus, Grafana, and Jaeger monitoring
- Configure Nginx reverse proxy
- Add comprehensive CI/CD documentation"

# Push to GitHub
git push origin SpringBoot-Rest-Complete
```

- [ ] All files staged
- [ ] Commit message clear
- [ ] Push successful

---

## Step 5: Monitor GitHub Actions

1. Go to repository on GitHub
2. Click **Actions** tab
3. Should see workflow running: **CI/CD Pipeline - Build & Deploy to Kubernetes**

### Watch Workflow Stages

```
⏳ BUILD & TEST
   └─ Java build
   └─ Unit tests
   └─ Upload artifacts
   └─ Duration: 3-5 minutes

⏳ BUILD DOCKER IMAGE
   └─ Docker build
   └─ Push to GHCR
   └─ Duration: 2-3 minutes

⏳ SECURITY SCANNING
   └─ Trivy scan
   └─ Upload results
   └─ Duration: 1-2 minutes

⏳ DEPLOY TO STAGING
   └─ Configure kubectl
   └─ Apply manifests
   └─ Health checks
   └─ Duration: 3-5 minutes

⏳ INTEGRATION TESTS
   └─ Run tests
   └─ Generate reports
   └─ Duration: 2-3 minutes

⏳ DEPLOY TO PRODUCTION
   └─ Configure kubectl
   └─ Apply manifests
   └─ Verify deployment
   └─ Duration: 3-5 minutes

⏳ NOTIFICATIONS
   └─ Slack message (optional)
   └─ Generate summary
   └─ Duration: 1 minute

TOTAL TIME: 15-25 minutes
```

- [ ] Workflow started
- [ ] All stages running
- [ ] Check logs for errors

---

## Step 6: Verify Deployment

### Check Staging Deployment

```bash
# Set context to staging cluster
kubectl config use-context staging

# Get deployments
kubectl get deployments -n books-staging
# Expected:
# NAME        READY   UP-TO-DATE   AVAILABLE   AGE
# books-api   2/2     2            2           XX

# Get pods
kubectl get pods -n books-staging
# Expected: 2 pods (books-api-xxx-yyy, books-api-zzz-aaa)

# Check service
kubectl get svc -n books-staging
# Expected: books-api-service with LoadBalancer IP

# Get service details
kubectl describe svc books-api-service -n books-staging
# Note the LoadBalancer IP or hostname
```

- [ ] Deployment shows 2/2 ready
- [ ] 2 pods running
- [ ] Service has external IP/hostname

### Test Staging API

```bash
# Get service IP
STAGING_IP=$(kubectl get svc books-api-service -n books-staging -o jsonpath='{.status.loadBalancer.ingress[0].hostname}')
echo $STAGING_IP

# Test health endpoint
curl http://$STAGING_IP:8080/actuator/health
# Expected: {"status":"UP"}

# Test API endpoint
curl http://$STAGING_IP:8080/books
# Expected: Books data or empty array
```

- [ ] Health endpoint responds
- [ ] API endpoints accessible
- [ ] No connection errors

### Check Production Deployment (if configured)

```bash
# Set context to production cluster
kubectl config use-context production

# Verify deployment
kubectl get deployments -n books-production
# Expected: 3 or more replicas

# Check pods
kubectl get pods -n books-production
# Expected: 3+ pods running
```

- [ ] 3+ replicas running
- [ ] All pods in Running state
- [ ] Service accessible

---

## Step 7: Verify Monitoring

### Local Testing (if using docker-compose)

```bash
# Start stack
docker-compose up -d

# Access Prometheus
# http://localhost:9090

# Access Grafana
# http://localhost:3000 (admin/admin)

# Check metrics
curl http://localhost:8080/actuator/prometheus
```

- [ ] Prometheus accessible
- [ ] Grafana accessible
- [ ] Metrics visible

### Kubernetes Monitoring

```bash
# Check Prometheus pod
kubectl get pods -n books-staging

# Port forward Prometheus
kubectl port-forward svc/prometheus 9090:9090 -n books-staging

# Port forward Grafana
kubectl port-forward svc/grafana 3000:3000 -n books-staging

# Access locally:
# http://localhost:9090 (Prometheus)
# http://localhost:3000 (Grafana - admin/admin)
```

- [ ] Prometheus collecting metrics
- [ ] Grafana showing dashboards
- [ ] Application metrics visible

---

## Step 8: Verify Security

### Check Security Scanning Results

```bash
# Go to GitHub → Security tab
# Should see "Trivy scan results"
```

- [ ] Security scan completed
- [ ] No critical vulnerabilities
- [ ] Results visible in GitHub Security

### Verify RBAC

```bash
kubectl get roles -n books-staging
# Expected: books-api-role

kubectl get rolebindings -n books-staging
# Expected: books-api-rolebinding

kubectl get networkpolicies -n books-staging
# Expected: books-api-netpolicy
```

- [ ] Roles created
- [ ] Role bindings created
- [ ] Network policies applied

---

## Post-Deployment Verification

### Check Auto-Scaling

```bash
# View HPA
kubectl get hpa -n books-staging
# Expected: books-api-hpa

# Check HPA details
kubectl describe hpa books-api-hpa -n books-staging
# Should show metrics and current replicas
```

- [ ] HPA created
- [ ] Min/max replicas configured
- [ ] Metrics being collected

### Check Pod Resources

```bash
# View resource usage
kubectl top pods -n books-staging

# View node resources
kubectl top nodes
```

- [ ] Pods showing CPU/Memory usage
- [ ] No resource warnings
- [ ] Sufficient node capacity

### Check Logs

```bash
# Stream application logs
kubectl logs -f deployment/books-api -n books-staging

# Check for any errors
# Should see Spring Boot startup messages
```

- [ ] Application started successfully
- [ ] No error messages
- [ ] Application ready

---

## Troubleshooting

### Pod not ready?

```bash
# Describe pod
kubectl describe pod <pod-name> -n books-staging

# Check logs
kubectl logs <pod-name> -n books-staging

# Check events
kubectl get events -n books-staging
```

### Deployment not rolling?

```bash
# Check rollout status
kubectl rollout status deployment/books-api -n books-staging

# Describe deployment
kubectl describe deployment books-api -n books-staging

# Rollback if needed
kubectl rollout undo deployment/books-api -n books-staging
```

### Service not accessible?

```bash
# Check service
kubectl get svc -n books-staging

# Check endpoints
kubectl get endpoints -n books-staging

# Port forward to test
kubectl port-forward svc/books-api-service 8080:80 -n books-staging

# Test
curl http://localhost:8080/actuator/health
```

---

## Success Criteria

✅ **All of the following must be true:**

- [ ] GitHub Actions workflow completed successfully
- [ ] All 7 stages passed (Build, Docker, Security, Deploy Staging, Tests, Deploy Prod, Notify)
- [ ] Docker image pushed to GitHub Container Registry
- [ ] 2 pods running in staging namespace
- [ ] 3+ pods running in production namespace
- [ ] API responds to health checks
- [ ] Prometheus collecting metrics
- [ ] Grafana dashboards accessible
- [ ] Network policies applied
- [ ] RBAC configured
- [ ] No security vulnerabilities found
- [ ] All services in Running state
- [ ] No restarts or errors in logs

---

## When Everything is Complete

🎉 **Your CI/CD pipeline is live!**

### What happens now:

1. **On every push to GitHub:**
   - Automatic build and test
   - Docker image created and pushed
   - Security scanning
   - Automatic deployment to staging
   - Automatic deployment to production (if configured)

2. **Continuous monitoring:**
   - Prometheus collects metrics
   - Grafana displays dashboards
   - Alerts trigger on anomalies
   - Jaeger traces requests

3. **Auto-scaling:**
   - Staging: 2-5 replicas
   - Production: 3-10 replicas
   - Based on CPU and memory

---

## Next Steps

1. **Make changes** to your code
2. **Commit and push** to GitHub
3. **Watch** the workflow in Actions tab
4. **Verify** deployment in Kubernetes
5. **Monitor** with Prometheus/Grafana
6. **Repeat!**

---

**Checklist Complete!** Your CI/CD pipeline is ready for production use. 🚀
