# CI/CD Pipeline Complete Guide

## 📋 Table of Contents
1. [Overview](#overview)
2. [Architecture](#architecture)
3. [Setup Instructions](#setup-instructions)
4. [GitHub Actions Workflow](#github-actions-workflow)
5. [Local Testing with Docker](#local-testing)
6. [Kubernetes Deployment](#kubernetes-deployment)
7. [Monitoring & Logging](#monitoring)
8. [Troubleshooting](#troubleshooting)

---

## Overview

This project implements a **complete CI/CD pipeline** with:

✅ **GitHub Actions** - Automated build, test, and deploy
✅ **Docker** - Containerization for consistency
✅ **Kubernetes** - Production-ready orchestration
✅ **Prometheus + Grafana** - Monitoring and visualization
✅ **Nginx** - Reverse proxy and load balancing
✅ **Security** - RBAC, Network Policies, Non-root users

### Pipeline Stages

```
Push to GitHub
    ↓
[1] BUILD & TEST
    • Checkout code
    • Set up Java 17
    • Build with Maven
    • Run unit tests
    ↓
[2] BUILD DOCKER IMAGE
    • Multi-stage build
    • Push to GitHub Container Registry (GHCR)
    ↓
[3] SECURITY SCANNING
    • Trivy vulnerability scan
    • Upload to GitHub Security
    ↓
[4] DEPLOY TO STAGING
    • Apply K8s manifests
    • Rolling update strategy
    • Health checks
    ↓
[5] INTEGRATION TESTS
    • API endpoint testing
    • Load testing
    ↓
[6] DEPLOY TO PRODUCTION
    • High availability setup
    • 3 replicas minimum
    • Auto-scaling enabled
    ↓
[7] NOTIFICATIONS
    • Slack alerts
    • GitHub Actions summary
```

---

## Architecture

### Components

```
┌─────────────────────────────────────────────────────────────┐
│                     GitHub Repository                        │
│  (SpringBoot-Rest-Example - SpringBoot-Rest-Complete)        │
└────────────────────────┬────────────────────────────────────┘
                         │
                    On Push Event
                         │
         ┌───────────────┴───────────────┐
         ↓                               ↓
    GitHub Actions CI/CD          Docker Image Build
         │                               │
         ├─Build & Test                 ├─Multi-stage
         ├─Security Scan                ├─Push to GHCR
         ├─Deploy to Staging            └─Cache optimization
         └─Deploy to Production
                         │
         ┌───────────────┴───────────────┐
         ↓                               ↓
    Kubernetes Cluster (Staging)    Kubernetes Cluster (Production)
         │                               │
         ├─2 replicas                    ├─3+ replicas
         ├─Auto-scaling 2-5              ├─Auto-scaling 3-10
         ├─Rolling updates               ├─Pod anti-affinity
         └─Health checks                 └─Network policies
                         │
         ┌───────────────┴───────────────┐
         ↓                               ↓
    Prometheus + Grafana           Jaeger Distributed Tracing
    Metrics Collection              Request Tracing
```

---

## Setup Instructions

### Prerequisites

- **GitHub Account** with repository access
- **Kubernetes Cluster** (AWS EKS, Azure AKS, GCP GKE, or on-premises)
- **kubectl** configured locally
- **Docker** installed locally (for testing)
- **Docker Hub or GHCR** account

### Step 1: Set Up GitHub Secrets

1. Go to your GitHub repository settings
2. Navigate to **Secrets and variables → Actions**
3. Add the following secrets:

#### Required Secrets

```
KUBE_CONFIG_STAGING
  └─ Base64 encoded kubeconfig for staging cluster
     Command: cat ~/.kube/config | base64

KUBE_CONFIG_PRODUCTION
  └─ Base64 encoded kubeconfig for production cluster
     Command: cat ~/.kube/config | base64

SLACK_WEBHOOK_URL (Optional)
  └─ Slack webhook for notifications
     Get from: Slack App → Incoming Webhooks
```

**How to add secrets:**

```bash
# Get your kubeconfig
cat ~/.kube/config | base64

# Copy the output and add to GitHub:
# Settings → Secrets and variables → Actions → New repository secret
# Name: KUBE_CONFIG_STAGING
# Value: <paste base64 output>
```

### Step 2: Configure Kubernetes Clusters

#### Create namespaces:

```bash
# Staging cluster
kubectl apply -f k8s/namespace.yaml

# Production cluster (repeat for production cluster context)
kubectl config use-context production-cluster
kubectl apply -f k8s/namespace.yaml
```

#### Verify namespaces:

```bash
kubectl get namespaces | grep books
# Expected output:
# books-production   Active   XXs
# books-staging      Active   XXs
```

### Step 3: Test Locally with Docker Compose

```bash
# Build and start services
docker-compose up --build

# Verify services are running
docker-compose ps

# Test API
curl http://localhost:8080/actuator/health

# Access dashboards
# - Grafana: http://localhost:3000 (admin/admin)
# - Prometheus: http://localhost:9090
# - Jaeger: http://localhost:16686
```

---

## GitHub Actions Workflow

### Workflow File

Location: `.github/workflows/ci-cd-pipeline.yml`

### Stages Explained

#### Stage 1: Build & Test

```yaml
- Checkout code from GitHub
- Set up Java 17 & Maven
- Build application with: mvn clean package
- Run tests with: mvn test
- Upload JAR artifacts
```

**Expected Output:**
```
✓ BUILD SUCCESS
✓ Tests passed: XX/XX
✓ JAR uploaded to artifacts
```

#### Stage 2: Build Docker Image

```yaml
- Set up Docker Buildx (for multi-platform builds)
- Log in to GHCR (GitHub Container Registry)
- Extract image metadata (tags, version info)
- Build & push with layer caching
```

**Image Location:**
```
ghcr.io/mukundkumar07/springboot-rest-example:SpringBoot-Rest-Complete
ghcr.io/mukundkumar07/springboot-rest-example:latest
ghcr.io/mukundkumar07/springboot-rest-example:sha-abc123
```

#### Stage 3: Security Scanning

```yaml
- Run Trivy vulnerability scanner
- Scan filesystem for CVEs
- Upload results to GitHub Security tab
```

#### Stage 4: Deploy to Staging

```yaml
- Configure kubectl with staging kubeconfig
- Apply namespace and K8s manifests
- Wait for rollout (5 min timeout)
- Run health checks
```

**Commands Run:**
```bash
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/staging/ -n books-staging
kubectl rollout status deployment/books-api -n books-staging
```

#### Stage 5: Integration Tests

```yaml
- Run integration tests: mvn verify
- Upload test reports
- Generate test summary
```

#### Stage 6: Deploy to Production

```yaml
- Configure kubectl with production kubeconfig
- Apply production-grade K8s manifests
- High availability setup (3 replicas)
- Stricter health checks
```

#### Stage 7: Notifications

```yaml
- Send Slack notifications on success/failure
- Generate GitHub Actions summary
- Include deployment details
```

---

## Local Testing

### Docker Compose

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f books-api

# Stop services
docker-compose down

# Clean up volumes
docker-compose down -v
```

### Testing the API

```bash
# Health check
curl http://localhost:8080/actuator/health

# Get API info
curl http://localhost:8080/actuator/info

# View metrics
curl http://localhost:8080/actuator/prometheus

# Get books (example)
curl http://localhost:8080/books
```

### Monitoring

```bash
# Prometheus (metrics)
http://localhost:9090

# Grafana (dashboards)
http://localhost:3000
Login: admin / admin

# Jaeger (tracing)
http://localhost:16686
```

---

## Kubernetes Deployment

### Manual Deployment

```bash
# Set context to staging cluster
kubectl config use-context staging

# Deploy to staging
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/staging/deployment.yaml

# Verify deployment
kubectl get pods -n books-staging
kubectl get svc -n books-staging
kubectl logs -f deployment/books-api -n books-staging

# Check deployment status
kubectl describe deployment books-api -n books-staging
```

### Production Deployment

```bash
# Set context to production cluster
kubectl config use-context production

# Deploy to production
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/production/deployment.yaml

# Verify deployment
kubectl get pods -n books-production
kubectl get svc -n books-production
kubectl logs -f deployment/books-api -n books-production
```

### Scaling

```bash
# Manual scaling
kubectl scale deployment books-api -n books-staging --replicas=5

# Auto-scaling status
kubectl get hpa -n books-staging
kubectl describe hpa books-api-hpa -n books-staging
```

### Rolling Updates

```bash
# Update image
kubectl set image deployment/books-api \
  books-api=ghcr.io/mukundkumar07/springboot-rest-example:latest \
  -n books-staging

# Monitor rollout
kubectl rollout status deployment/books-api -n books-staging

# Rollback if needed
kubectl rollout undo deployment/books-api -n books-staging
```

---

## Monitoring & Logging

### Prometheus Queries

```
# CPU usage
process_cpu_usage

# Memory usage
process_resident_memory_bytes / 1024 / 1024

# HTTP requests
rate(http_server_requests_seconds_count[5m])

# Error rate
rate(http_server_requests_seconds_count{status=~"5.."}[5m])

# Response time (95th percentile)
histogram_quantile(0.95, rate(http_server_requests_seconds_bucket[5m]))
```

### Grafana Dashboards

1. **System Metrics**
   - CPU usage
   - Memory usage
   - JVM metrics

2. **Application Metrics**
   - Request count
   - Error rate
   - Response time

3. **Kubernetes Metrics**
   - Pod CPU/Memory
   - Network I/O
   - Pod restarts

### Logs

```bash
# View pod logs
kubectl logs deployment/books-api -n books-staging

# Stream logs
kubectl logs -f deployment/books-api -n books-staging

# View previous logs (if crashed)
kubectl logs deployment/books-api -n books-staging --previous

# All pods logs
kubectl logs -l app=books-api -n books-staging --all-containers=true
```

---

## Troubleshooting

### Pod not starting

```bash
# Check pod status
kubectl describe pod <pod-name> -n books-staging

# View logs
kubectl logs <pod-name> -n books-staging

# Check resource requests
kubectl top pods -n books-staging
```

### Deployment stuck in pending

```bash
# Check events
kubectl get events -n books-staging

# Check node resources
kubectl top nodes

# Check resource requests vs available
kubectl describe deployment books-api -n books-staging
```

### Service not accessible

```bash
# Check service
kubectl get svc -n books-staging

# Check endpoints
kubectl get endpoints -n books-staging

# Port forward to test
kubectl port-forward svc/books-api-service 8080:80 -n books-staging

# Test locally
curl http://localhost:8080/actuator/health
```

### Rollout issues

```bash
# Check rollout status
kubectl rollout status deployment/books-api -n books-staging

# Describe deployment
kubectl describe deployment books-api -n books-staging

# View replica sets
kubectl get rs -n books-staging

# Rollback
kubectl rollout undo deployment/books-api -n books-staging
```

### Scaling issues

```bash
# Check HPA status
kubectl describe hpa books-api-hpa -n books-staging

# View metrics used by HPA
kubectl get hpa -n books-staging -o wide

# Check if metrics are available
kubectl get --raw /apis/custom.metrics.k8s.io/v1beta1
```

---

## Security Best Practices

✅ **Implemented:**
- Non-root user in container
- Read-only root filesystem
- Network policies (ingress/egress)
- RBAC (Role-Based Access Control)
- Pod security policies
- Health checks and liveness probes
- Resource limits and requests

✅ **Recommended Additions:**
- Enable Pod Security Policy
- Use private image registry
- Implement network segmentation
- Add WAF (Web Application Firewall)
- Enable encryption in transit (TLS)
- Regular security scanning

---

## Next Steps

1. **Configure Kubernetes Cluster**
   - Add kubeconfig to GitHub Secrets
   - Create both staging and production clusters

2. **Test Locally**
   - Run `docker-compose up`
   - Verify API and monitoring dashboards

3. **Push to GitHub**
   - Git push to SpringBoot-Rest-Complete branch
   - Watch GitHub Actions workflow

4. **Monitor**
   - Check Prometheus metrics
   - View Grafana dashboards
   - Review logs in kubectl

5. **Iterate**
   - Adjust replicas based on load
   - Fine-tune resource requests
   - Optimize CI/CD timing

---

## Additional Resources

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Kubernetes Official Docs](https://kubernetes.io/docs/)
- [Docker Best Practices](https://docs.docker.com/develop/dev-best-practices/)
- [Prometheus Documentation](https://prometheus.io/docs/)
- [Grafana Documentation](https://grafana.com/docs/)

---

**Questions or Issues?** Check the troubleshooting section or refer to official documentation.
