# ============================================================================
# MULTI-STAGE BUILD - Optimized Docker Image for Spring Boot
# ============================================================================

# Stage 1: Build Application
FROM maven:3.9.11-eclipse-temurin-17 AS builder

WORKDIR /build

# Copy Maven files
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn

# Download dependencies (cached layer)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application
RUN mvn clean package -DskipTests -q

# ============================================================================
# Stage 2: Runtime Image (Optimized)
# ============================================================================
FROM eclipse-temurin:17-jre-alpine

LABEL maintainer="Mukund Kumar <mukundkumar@example.com>"
LABEL description="Books REST API - Spring Boot 4.0.5"
LABEL version="1.0.0"

# Set working directory
WORKDIR /app

# Create non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy built JAR from builder stage
COPY --from=builder /build/target/books-api-*.jar app.jar

# Change ownership to non-root user
RUN chown -R appuser:appgroup /app

# Switch to non-root user
USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["--server.port=8080"]
