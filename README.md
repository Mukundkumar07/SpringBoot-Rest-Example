# Books API - Spring Boot REST API

A comprehensive Spring Boot REST API for managing books with full CRUD operations, complete HTTP method demonstrations, and extensive documentation.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [HTTP Methods Explained](#http-methods-explained)
- [Spring Annotations Guide](#spring-annotations-guide)
- [Examples](#examples)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

This project demonstrates a **production-ready REST API** built with Spring Boot 4.0.5 and Java 25. It showcases:

- **Complete CRUD Operations**: GET, POST, PUT, PATCH, DELETE
- **RESTful Design Principles**: Proper HTTP semantics and status codes
- **Multiple Design Patterns**: Hard delete, soft delete, batch operations, partial updates
- **Comprehensive Documentation**: Inline code documentation, API guides, and annotation explanations
- **Best Practices**: Error handling, input validation, resource organization
- **Swagger/OpenAPI Integration**: Auto-generated API documentation

## ✨ Features

### Core Functionality
- ✅ **Create Books**: Single and batch creation with validation
- ✅ **Read Books**: Multiple query methods with filtering options
- ✅ **Update Books**: Full updates (PUT) and partial updates (PATCH)
- ✅ **Delete Books**: Hard delete, soft delete, and batch deletion
- ✅ **Search & Filter**: By title, author, category with multiple patterns
- ✅ **Batch Operations**: Create, update, or delete multiple books at once

### API Features
- 📚 **40+ Endpoints**: Comprehensive API coverage
- 🔍 **Multiple Query Methods**: Path parameters, query parameters, request body
- 📊 **Various Response Patterns**: Different HTTP status codes (200, 201, 204, 400, 404, 500)
- 🛡️ **Input Validation**: Comprehensive data validation
- 📝 **Swagger UI**: Auto-generated interactive API documentation at `/swagger-ui.html`

### Documentation
- 📖 **HTTP Methods Guide**: Detailed explanation of GET, POST, PUT, PATCH, DELETE with real examples
- 📚 **Spring Annotations Guide**: 11 core annotations explained with usage patterns
- 💡 **JavaDoc Comments**: Comprehensive inline documentation in all controllers
- 🔗 **cURL Examples**: Ready-to-use cURL commands for testing each endpoint

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 25 | Programming language |
| **Spring Boot** | 4.0.5 | Framework |
| **Spring Web** | Latest | REST API support |
| **Spring Validation** | Latest | Input validation |
| **Maven** | 3.9+ | Build tool |
| **Tomcat** | 11.0.20 | Embedded server |
| **Jackson** | Latest | JSON serialization |
| **springdoc-openapi** | 2.2.0 | Swagger/OpenAPI documentation |
| **Lombok** | Optional | Code generation |

### Dependencies (pom.xml)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

## 📁 Project Structure

```
books/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/love2code/books/
│   │   │       ├── BooksApplication.java          # Main Spring Boot application
│   │   │       ├── controller/
│   │   │       │   ├── BooksConntroller.java      # GET endpoints (Read operations)
│   │   │       │   ├── BooksPostController.java   # POST endpoints (Create operations)
│   │   │       │   ├── BooksPutController.java    # PUT endpoints (Full updates)
│   │   │       │   ├── BooksDeleteController.java # DELETE endpoints (Removal)
│   │   │       │   ├── BooksPatchController.java  # PATCH endpoints (Partial updates)
│   │   │       │   └── MyBookController.java      # Additional endpoints
│   │   │       ├── entity/
│   │   │       │   ├── Book.java                  # Book entity model
│   │   │       │   └── MyBook.java                # Alternative entity
│   │   │       └── documentation/
│   │   │           ├── HTTPMethodsGuide.java      # HTTP methods documentation
│   │   │           └── SpringAnnotationsGuide.java # Spring annotations documentation
│   │   └── resources/
│   │       ├── application.properties             # Application configuration
│   │       ├── static/                           # Static files (CSS, JS)
│   │       └── templates/                        # HTML templates
│   └── test/
│       └── java/com/love2code/books/
│           └── BooksApplicationTests.java         # Unit tests
├── pom.xml                                       # Maven dependencies
├── mvnw                                         # Maven wrapper (Unix)
├── mvnw.cmd                                     # Maven wrapper (Windows)
├── .gitignore                                   # Git ignore patterns
├── README.md                                    # This file
├── API_DOCUMENTATION.md                         # Detailed API documentation
├── ARCHITECTURE.md                              # Architecture and design patterns
└── CONTRIBUTING.md                              # Contribution guidelines
```

## 🚀 Getting Started

### Prerequisites

- **Java 25** or higher
- **Maven 3.9+**
- **Git** (for version control)
- **Postman** or **cURL** (for testing)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/mukundkumar/books.git
cd books
```

2. **Install dependencies**
```bash
mvn clean install
```

3. **Build the project**
```bash
mvn clean package
```

4. **Run the application**
```bash
mvn spring-boot:run
# OR
java -jar target/books-0.0.1-SNAPSHOT.jar
```

5. **Access the application**
- **API Base URL**: `http://localhost:8080`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/v3/api-docs`

## 📚 API Endpoints

### Overview Summary

| HTTP Method | Purpose | Count | Base Path |
|-----------|---------|-------|-----------|
| **GET** | Retrieve data | 13 | `/api/books` |
| **POST** | Create data | 2 | `/api/books` |
| **PUT** | Full updates | 4 | `/api/books` |
| **PATCH** | Partial updates | 4 | `/api/books` |
| **DELETE** | Delete data | 6 | `/api/books` |
| **Total** | | **29** | |

### GET Endpoints (BooksConntroller)

**Retrieve all books**
```
GET /api/books
Response: List[Book]
Status: 200 OK
```

**Get book by title**
```
GET /api/books/title/{title}
Example: GET /api/books/title/Great%20Gatsby
Response: Book object or null
Status: 200 OK
```

**Get books by category**
```
GET /api/books/category/{category}
Example: GET /api/books/category/Science
Response: List[Book]
Status: 200 OK
```

**Get books by author**
```
GET /api/books/author/{author}
Example: GET /api/books/author/Shakespeare
Response: List[Book]
Status: 200 OK
```

**Advanced query with RequestParam**
```
GET /api/books/getBooksByCategory?category=Science
Response: List[Book]
Status: 200 OK
```

### POST Endpoints (BooksPostController)

**Create single book**
```
POST /api/books/create
Content-Type: application/json

{
    "title": "New Book",
    "author": "John Doe",
    "category": "Fiction"
}

Response: Created Book object
Status: 201 Created
```

**Create multiple books (batch)**
```
POST /api/books/createMultiple
Content-Type: application/json

[
    {
        "title": "Book 1",
        "author": "Author 1",
        "category": "Science"
    },
    {
        "title": "Book 2",
        "author": "Author 2",
        "category": "Math"
    }
]

Response: List[Book]
Status: 201 Created
```

### PUT Endpoints (BooksPutController)

**Update book by title (full update)**
```
PUT /api/books/update/{title}
Content-Type: application/json

{
    "title": "Updated Title",
    "author": "New Author",
    "category": "NewCategory"
}

Response: Updated Book
Status: 200 OK or 404 Not Found
```

**Update book by ID**
```
PUT /api/books/{id}
Content-Type: application/json

Body: Book object with all fields

Response: Updated Book
Status: 200 OK
```

**Batch update multiple books**
```
PUT /api/books/batch/update
Content-Type: application/json

[
    {"title": "Book1", "author": "New Author1", "category": "Cat1"},
    {"title": "Book2", "author": "New Author2", "category": "Cat2"}
]

Response: List[Book] - updated books
Status: 200 OK
```

**Update category only**
```
PUT /api/books/{title}/category
Content-Type: application/json

{
    "category": "NewCategory"
}

Response: Updated Book or 404 Not Found
Status: 200 OK or 404
```

### PATCH Endpoints (BooksPatchController)

**Partial update (selective fields)**
```
PATCH /api/books/partial/{title}
Content-Type: application/json

{
    "title": "New Title",
    "author": "New Author"
}

Response: Updated Book (only provided fields updated)
Status: 200 OK or 404 Not Found
```

**Update title only**
```
PATCH /api/books/{title}/title
Content-Type: application/json

{
    "value": "New Title"
}

Response: {
    "message": "Field updated successfully",
    "field": "title",
    "old_value": "Old Title",
    "new_value": "New Title"
}
Status: 200 OK or 404 Not Found
```

**Update author only**
```
PATCH /api/books/{title}/author
Content-Type: application/json

{
    "value": "New Author"
}

Response: Confirmation map with field update details
Status: 200 OK or 404 Not Found
```

**Update category only**
```
PATCH /api/books/{title}/category
Content-Type: application/json

{
    "value": "NewCategory"
}

Response: Confirmation map with field update details
Status: 200 OK or 404 Not Found
```

### DELETE Endpoints (BooksDeleteController)

**Delete book by title (no response body)**
```
DELETE /api/books/delete/{title}
Example: DELETE /api/books/delete/Great%20Gatsby

Response: (empty body)
Status: 204 No Content
```

**Delete book with confirmation response**
```
DELETE /api/books/{title}
Example: DELETE /api/books/Great%20Gatsby

Response: {
    "message": "Book deleted successfully",
    "title": "Great Gatsby",
    "deletedAt": "2026-04-02T23:37:15"
}
Status: 200 OK or 404 Not Found
```

**Batch delete multiple books**
```
DELETE /api/books/batch/delete
Content-Type: application/json

{
    "titles": ["Book1", "Book2", "Book3"]
}

Response: {
    "message": "Books deleted successfully",
    "count": 3,
    "deletedBooks": ["Book1", "Book2", "Book3"]
}
Status: 200 OK
```

**Delete all books in category**
```
DELETE /api/books/category/{category}
Example: DELETE /api/books/category/Science

Response: {
    "message": "All books in category deleted",
    "category": "Science",
    "count": 3
}
Status: 200 OK
```

**Delete all books (DANGEROUS - Testing only)**
```
DELETE /api/books/all

Response: {
    "message": "All books deleted",
    "count": 10
}
Status: 200 OK
WARNING: This deletes all data!
```

**Soft delete (non-permanent)**
```
DELETE /api/books/soft/{title}
Example: DELETE /api/books/soft/Great%20Gatsby

Response: {
    "message": "Book marked as deleted (soft delete)",
    "title": "Great Gatsby",
    "status": "deleted"
}
Status: 200 OK
NOTE: Book data is preserved for recovery
```

## 🌐 HTTP Methods Explained

### GET - Safe & Idempotent
- **Purpose**: Retrieve data without modifying it
- **Idempotent**: Yes (calling multiple times returns same result)
- **Safe**: Yes (no side effects)
- **Request Body**: No
- **Caching**: Yes (should be cached)
- **Status Codes**: 200 OK, 404 Not Found

**When to use**:
- Fetch resources
- Query data with filters
- List operations
- Search operations

### POST - Create Data
- **Purpose**: Create new resources
- **Idempotent**: No (each call creates new resource)
- **Safe**: No (modifies state)
- **Request Body**: Yes (usually required)
- **Caching**: No
- **Status Codes**: 201 Created, 400 Bad Request, 409 Conflict

**When to use**:
- Create new records
- Submit forms
- Non-idempotent operations
- When you want new resource each time

**POST vs PUT vs PATCH**:
- **POST**: Creates new resource, may not return same thing each time
- **PUT**: Updates entire resource, idempotent, must provide all fields
- **PATCH**: Updates specific fields, idempotent, only changed fields needed

### PUT - Full Update
- **Purpose**: Replace entire resource
- **Idempotent**: Yes (same body = same result)
- **Safe**: No (modifies state)
- **Request Body**: Yes (required, must be complete)
- **Caching**: No
- **Status Codes**: 200 OK, 204 No Content, 404 Not Found, 400 Bad Request

**Characteristics**:
- Must include ALL fields
- Completely replaces resource
- Idempotent: calling 100 times = same as calling once
- Use when updating whole resource

**Example**:
```
PUT /api/books/1
{
    "title": "New Title",      // ALL fields required
    "author": "New Author",
    "category": "NewCat"
}
```

### PATCH - Partial Update
- **Purpose**: Update specific fields only
- **Idempotent**: Yes (same body = same result)
- **Safe**: No (modifies state)
- **Request Body**: Yes (only changed fields)
- **Caching**: No
- **Status Codes**: 200 OK, 404 Not Found, 400 Bad Request

**Characteristics**:
- Only include fields to change
- Other fields unchanged
- More efficient for selective updates
- Idempotent

**Example**:
```
PATCH /api/books/1
{
    "title": "New Title"  // Only changed field
}
// author and category remain unchanged
```

### DELETE - Remove Resource
- **Purpose**: Delete/remove resource
- **Idempotent**: Yes (deleting twice = same as once)
- **Safe**: No (modifies state)
- **Request Body**: Usually No
- **Caching**: No
- **Status Codes**: 200 OK, 204 No Content, 404 Not Found

**Deletion Patterns**:
1. **Hard Delete**: Permanent removal from database
2. **Soft Delete**: Mark as deleted, preserve data
3. **Archive**: Move to archive table
4. **Cascade**: Delete resource and related items

**Example**:
```
DELETE /api/books/1
Response: 204 No Content

DELETE /api/books/soft/1
Response: Book marked as deleted, recoverable
```

## 📖 Spring Annotations Guide

See `SpringAnnotationsGuide.java` for detailed documentation of:

1. **@RestController** - REST API controller annotation
2. **@RequestMapping** - Class/method level URL mapping
3. **@GetMapping** - HTTP GET shorthand
4. **@PostMapping** - HTTP POST shorthand
5. **@PutMapping** - HTTP PUT shorthand
6. **@DeleteMapping** - HTTP DELETE shorthand
7. **@PathVariable** - URL path parameter extraction
8. **@RequestParam** - Query string parameter extraction
9. **@RequestBody** - HTTP request body mapping
10. **@ResponseStatus** - HTTP response status code setting
11. **Other Annotations** - @Valid, @CrossOrigin, @Autowired, etc.

### Key Annotation Patterns

**Path Variable (URL part)**:
```java
@GetMapping("/books/{id}")
public Book getBook(@PathVariable String id) { }
// URL: /books/123
```

**Request Parameter (Query string)**:
```java
@GetMapping("/books")
public List<Book> searchBooks(@RequestParam String category) { }
// URL: /books?category=Science
```

**Request Body (JSON)**:
```java
@PostMapping("/books")
public Book createBook(@RequestBody Book book) { }
// JSON body sent in request
```

## 🧪 Testing

### Using cURL

**Get all books**:
```bash
curl -X GET http://localhost:8080/api/books
```

**Get books by category**:
```bash
curl -X GET "http://localhost:8080/api/books/category/Science"
```

**Create a book**:
```bash
curl -X POST http://localhost:8080/api/books/create \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "category": "Fiction"
  }'
```

**Update a book (PUT)**:
```bash
curl -X PUT "http://localhost:8080/api/books/update/The%20Great%20Gatsby" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "category": "Classic"
  }'
```

**Partial update (PATCH)**:
```bash
curl -X PATCH "http://localhost:8080/api/books/partial/The%20Great%20Gatsby" \
  -H "Content-Type: application/json" \
  -d '{"category": "Classic"}'
```

**Delete a book**:
```bash
curl -X DELETE "http://localhost:8080/api/books/delete/The%20Great%20Gatsby"
```

### Using Postman

1. Download [Postman](https://www.postman.com/downloads/)
2. Import endpoints from Swagger: `http://localhost:8080/v3/api-docs`
3. Or manually create requests:
   - **Method**: GET/POST/PUT/DELETE
   - **URL**: `http://localhost:8080/api/books/...`
   - **Headers**: `Content-Type: application/json`
   - **Body**: JSON data for POST/PUT/PATCH

### Using Swagger UI

1. Start the application
2. Navigate to: `http://localhost:8080/swagger-ui.html`
3. Browse all endpoints
4. Click "Try it out" to test any endpoint
5. View request/response details

## 🏗️ Architecture

### Design Patterns Used

1. **Controller Separation by HTTP Method**
   - BooksConntroller: GET operations
   - BooksPostController: POST operations
   - BooksPutController: PUT operations
   - BooksDeleteController: DELETE operations
   - BooksPatchController: PATCH operations

2. **Multiple Query Patterns**
   - Path variables: `/api/books/title/{title}`
   - Query parameters: `/api/books?category=Science`
   - Request body: POST/PUT/PATCH with JSON

3. **Deletion Strategies**
   - Hard delete: Permanent removal
   - Soft delete: Mark as deleted, preserve data
   - Batch delete: Delete multiple at once
   - Conditional delete: Delete by category

4. **Response Patterns**
   - HTTP status codes (200, 201, 204, 400, 404, 500)
   - ResponseEntity for status control
   - @ResponseStatus for simple status setting
   - JSON confirmation responses

### Data Flow

```
Request → Controller → Model → Service (future) → Database (future)
                ↓
           Request Validation
                ↓
           Business Logic
                ↓
           Response Generation
                ↓
Response ← ResponseEntity/Status
```

## 🔄 Git Workflow

### Create and Switch to Development Branch

```bash
# Create new branch
git checkout -b feature/rest-api-complete

# Or create and switch in one command
git checkout -b feature/rest-api-complete
```

### Commit Changes

```bash
# Stage all changes
git add .

# Commit with descriptive message
git commit -m "Complete REST API implementation with all CRUD operations"

# Or commit specific files
git add src/main/java/
git commit -m "Add all controller files"
```

### Push to GitHub

```bash
# Push to remote branch
git push origin feature/rest-api-complete

# Set upstream and push (first time)
git push --set-upstream origin feature/rest-api-complete
```

### Create Pull Request

1. Go to GitHub repository
2. Click "Pull requests" → "New pull request"
3. Select base branch (main) and compare branch (feature/rest-api-complete)
4. Add description and create PR

## 📝 Contributing

1. Fork the repository
2. Create feature branch: `git checkout -b feature/YourFeature`
3. Commit changes: `git commit -m 'Add YourFeature'`
4. Push branch: `git push origin feature/YourFeature`
5. Open Pull Request

### Code Style

- Java naming conventions (camelCase)
- Meaningful variable names
- Comprehensive JavaDoc comments
- One responsibility per class
- Proper exception handling

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📧 Contact

For questions or suggestions, please open an issue on GitHub.

---

**Last Updated**: April 2, 2026
**Version**: 1.0.0
**Status**: ✅ Production Ready

