# 📚 Books REST API

A production-ready Spring Boot REST API for managing books with complete CRUD operations, comprehensive documentation, and configured for Maven Central publishing.

## ⚡ Quick Start (2 minutes)

```bash
# Clone repository
git clone https://github.com/Mukundkumar07/SpringBoot-Rest-Example.git
cd SpringBoot-Rest-Example

# Build project
mvn clean package

# Run application
mvn spring-boot:run

# API available at: http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui.html
```

## 📖 Documentation

All documentation is organized in the `docs/` folder:

### Getting Started
- **[API Documentation](docs/API_DOCUMENTATION.md)** - All 40+ endpoints explained
- **[Quick Reference](docs/QUICK_REFERENCE.md)** - One-page cheat sheet
- **[Architecture](docs/ARCHITECTURE.md)** - Design patterns and structure

### Development & Deployment
- **[Maven Central Deployment](docs/MAVEN_CENTRAL_DEPLOYMENT.md)** - Complete deployment guide
- **[Maven Quick Checklist](docs/MAVEN_QUICK_CHECKLIST.md)** - Action items
- **[Git Setup](docs/GIT_SETUP.md)** - Git workflow

### Learning Resources
- **[HTTP Methods Guide](docs/HTTPMethodsGuide.java)** - 1800+ lines of HTTP tutorial
- **[Spring Annotations Guide](docs/SpringAnnotationsGuide.java)** - 1500+ lines of annotations
- **[Final Status](docs/FINAL_STATUS.md)** - Complete project summary

### Contributing
- **[Contributing](docs/CONTRIBUTING.md)** - How to contribute
- **[Commit Guidelines](docs/HUMAN_LIKE_COMMITS.md)** - Writing good commits

## 🚀 Features

- ✅ **40+ REST Endpoints** - Complete CRUD operations
- ✅ **6 Controllers** - Organized by HTTP method
- ✅ **Full Validation** - Input validation and error handling
- ✅ **Swagger/OpenAPI** - Auto-generated API documentation
- ✅ **Maven Central Ready** - Fully configured for publishing
- ✅ **Professional Documentation** - 12,500+ lines of guides

## � Installation & Running

```bash
# Clone & setup
git clone https://github.com/Mukundkumar07/SpringBoot-Rest-Example.git
cd SpringBoot-Rest-Example

# Build
mvn clean package

# Run
mvn spring-boot:run

# Access
API: http://localhost:8080/api/books
Swagger UI: http://localhost:8080/swagger-ui.html
```

## � Technology Stack

- **Framework**: Spring Boot 4.0.5
- **Java**: 17+
- **Build Tool**: Maven 3.6+
- **License**: Apache 2.0
- **Repository**: https://github.com/Mukundkumar07/SpringBoot-Rest-Example

## 🚀 Maven Central Publishing

This project is configured and ready for Maven Central deployment.

**To Deploy:**
1. Generate GPG key: `gpg --full-generate-key`
2. Create Sonatype account: https://issues.sonatype.org/
3. Deploy: `mvn clean deploy -P gpg-sign`

See `docs/MAVEN_CENTRAL_DEPLOYMENT.md` for detailed instructions.

## 📄 License

Apache License 2.0 - see LICENSE file for details

For questions or suggestions, please open an issue on GitHub.

---

**Last Updated**: April 2, 2026
**Version**: 1.0.0
**Status**: ✅ Production Ready

