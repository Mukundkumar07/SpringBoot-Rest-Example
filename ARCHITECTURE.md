# Books API - Architecture & Design Patterns

Comprehensive documentation of the architecture, design patterns, and technical decisions.

## Table of Contents

1. [Architecture Overview](#architecture-overview)
2. [Design Patterns](#design-patterns)
3. [Controller Organization](#controller-organization)
4. [Data Models](#data-models)
5. [Request/Response Flow](#requestresponse-flow)
6. [HTTP Method Strategy](#http-method-strategy)
7. [Error Handling](#error-handling)
8. [Future Improvements](#future-improvements)

## Architecture Overview

### Layered Architecture

```
┌─────────────────────────────────────┐
│   Client (Postman, Browser, App)    │
└────────────────┬────────────────────┘
                 │
┌────────────────▼────────────────────┐
│       REST API Layer (HTTP)          │
│   • Request validation               │
│   • Parameter parsing                │
└────────────────┬────────────────────┘
                 │
┌────────────────▼────────────────────┐
│     Controller Layer (Current)       │
│   • Route requests                   │
│   • Business logic (temporary)       │
│   • Response generation              │
└────────────────┬────────────────────┘
                 │
┌────────────────▼────────────────────┐
│   Data Layer (Future - In-Memory)    │
│   • Data storage                     │
│   • Data retrieval                   │
│   • Persistence (future: Database)   │
└─────────────────────────────────────┘
```

### Component Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    Spring Boot Application                  │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              Controller Layer                        │  │
│  │  ┌──────────────┐  ┌──────────────┐  ┌────────────┐ │  │
│  │  │BooksConntroll│ │BooksPostCtrl │ │BooksPutCtrl│ │  │
│  │  │(GET)         │ │(POST)        │ │(PUT)       │ │  │
│  │  └──────────────┘  └──────────────┘  └────────────┘ │  │
│  │  ┌──────────────┐  ┌──────────────┐                 │  │
│  │  │BooksDeleteCtr│ │BooksPatchCtrl │                 │  │
│  │  │(DELETE)      │ │(PATCH)        │                 │  │
│  │  └──────────────┘  └──────────────┘                 │  │
│  └──────────────────────────────────────────────────────┘  │
│                          │                                  │
│  ┌──────────────────────▼──────────────────────────────┐  │
│  │            Entity Models (Data Objects)             │  │
│  │  ┌──────────────┐  ┌──────────────┐                │  │
│  │  │Book Entity   │  │MyBook Entity │                │  │
│  │  │-title        │  │-id           │                │  │
│  │  │-author       │  │-name         │                │  │
│  │  │-category     │  │-description  │                │  │
│  │  └──────────────┘  └──────────────┘                │  │
│  └──────────────────────────────────────────────────────┘  │
│                          │                                  │
│  ┌──────────────────────▼──────────────────────────────┐  │
│  │         In-Memory Data Storage (ArrayList)          │  │
│  │    private List<Book> books = new ArrayList<>();   │  │
│  └──────────────────────────────────────────────────────┘  │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

## Design Patterns

### 1. Controller Per HTTP Method Pattern

**Purpose**: Organize controllers by HTTP method (GET, POST, PUT, PATCH, DELETE)

**Benefits**:
- Clear separation of concerns
- Easy to understand which controller handles which operations
- Scalable for large APIs
- Reduces code duplication

**Structure**:
```
BooksConntroller      → Handles all GET (READ) operations
BooksPostController   → Handles all POST (CREATE) operations
BooksPutController    → Handles all PUT (UPDATE) operations
BooksDeleteController → Handles all DELETE (REMOVE) operations
BooksPatchController  → Handles all PATCH (PARTIAL UPDATE) operations
```

**Example**:
```
/api/books - BooksConntroller.getBooks()
/api/books/title/{title} - BooksConntroller.getBookByTitle()
/api/books/create - BooksPostController.createBook()
/api/books/update/{title} - BooksPutController.updateBook()
/api/books/delete/{title} - BooksDeleteController.deleteBook()
/api/books/partial/{title} - BooksPatchController.partialUpdateBook()
```

### 2. Multiple Query Pattern Design

**Purpose**: Provide multiple ways to query the same data

**Patterns**:
- **Path Variables**: `/api/books/title/{title}` - specific resource identification
- **Query Parameters**: `/api/books?category=Science` - filtering
- **Multiple Parameters**: `/api/books/title/{title}/category/{category}` - complex queries
- **Stream API**: `/api/books/stream/title/{title}` - functional approach

**Benefits**:
- Flexibility for different use cases
- Familiar to API consumers
- Easy migration from other systems

### 3. Deletion Strategy Pattern

**Purpose**: Implement multiple deletion patterns for different scenarios

**Strategies**:

1. **Hard Delete** (Immediate Removal)
   ```
   DELETE /api/books/{title}
   → Permanently removes from database
   → Use for non-critical data
   ```

2. **Soft Delete** (Mark as Deleted)
   ```
   DELETE /api/books/soft/{title}
   → Marks as deleted, preserves data
   → Use for audit trails and recovery
   ```

3. **Batch Delete** (Multiple Deletions)
   ```
   DELETE /api/books/batch/delete
   → Delete multiple books in one request
   → Efficient for bulk operations
   ```

4. **Conditional Delete** (Delete by Category)
   ```
   DELETE /api/books/category/{category}
   → Delete all items matching condition
   → Use for cascading deletions
   ```

### 4. Response Pattern Design

**Purpose**: Provide different response patterns for various scenarios

**Patterns**:

1. **Data Only** (Direct Return)
   ```java
   @PostMapping("/create")
   public Book createBook(@RequestBody Book book) {
       return book;  // Status: 201 Created
   }
   ```

2. **Confirmation Response**
   ```java
   @DeleteMapping("/{title}")
   public ResponseEntity<Map<String, String>> deleteBook(@PathVariable String title) {
       return ResponseEntity.ok(Map.of("message", "Deleted"));
   }
   ```

3. **Status Only** (204 No Content)
   ```java
   @DeleteMapping("/delete/{title}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteBook(@PathVariable String title) { }
   ```

4. **Error Response**
   ```java
   if (book == null) {
       return ResponseEntity.notFound().build();
   }
   ```

### 5. Validation Pattern

**Purpose**: Validate input data before processing

**Implementation**:
```java
if (book.getTitle() == null || book.getTitle().isEmpty()) {
    throw new IllegalArgumentException("Title cannot be empty");
}
```

**Best Practices**:
- Validate early (in controller)
- Provide clear error messages
- Use @Valid annotation for automatic validation
- Fail fast on invalid input

## Controller Organization

### By HTTP Method

```
Controller Class        HTTP Method    Purpose
─────────────────────────────────────────────────
BooksConntroller       GET            Read/Query
BooksPostController    POST           Create
BooksPutController     PUT            Full Update
BooksDeleteController  DELETE         Delete
BooksPatchController   PATCH          Partial Update
```

### By Data Flow

```
Request
   ↓
┌─────────────────────────────┐
│ Controller Method Selection │
│ (Based on HTTP method)      │
└──────────────┬──────────────┘
               ↓
┌─────────────────────────────┐
│ Parameter Extraction        │
│ • Path: @PathVariable       │
│ • Query: @RequestParam      │
│ • Body: @RequestBody        │
└──────────────┬──────────────┘
               ↓
┌─────────────────────────────┐
│ Input Validation            │
│ • Check null                │
│ • Check empty               │
│ • Check format              │
└──────────────┬──────────────┘
               ↓
┌─────────────────────────────┐
│ Business Logic              │
│ • Find data                 │
│ • Modify data               │
│ • Validate rules            │
└──────────────┬──────────────┘
               ↓
┌─────────────────────────────┐
│ Response Generation         │
│ • Set status code           │
│ • Format data               │
│ • Add headers               │
└──────────────┬──────────────┘
               ↓
Response
```

## Data Models

### Book Entity

```java
public class Book {
    private String title;      // Book title (unique identifier)
    private String author;     // Book author name
    private String category;   // Book category/genre
    
    // Constructors
    // Getters & Setters
}
```

**Characteristics**:
- Simple POJO (Plain Old Java Object)
- Used in all controllers
- Serialized to JSON automatically by Jackson
- Stored in-memory in ArrayList

**Future Enhancement**:
```java
public class Book {
    private Long id;                    // Database ID
    private String title;
    private String author;
    private String category;
    private LocalDateTime createdAt;    // Timestamp
    private LocalDateTime updatedAt;    // Timestamp
    private boolean isDeleted;          // Soft delete flag
    private String isbn;                // ISBN code
    private int yearPublished;          // Year
}
```

## Request/Response Flow

### GET Request Flow

```
Client Request
│
├─ GET /api/books/title/Hamlet
│
▼
┌─────────────────────┐
│ BooksConntroller    │
│ getBookByTitle()    │
└────────┬────────────┘
         │
         ├─ Extract @PathVariable("title")
         │
         ├─ Iterate through books list
         │
         ├─ Find matching book
         │
         └─ Return Book object
            │
            ▼
         Jackson Serialization
            │
            ├─ Convert Java object to JSON
            │
            └─ {
                 "title": "Hamlet",
                 "author": "Shakespeare",
                 "category": "Drama"
               }
            │
            ▼
      HTTP Response 200 OK
            │
            ▼
      Client Receives JSON
```

### POST Request Flow

```
Client Request
│
├─ POST /api/books/create
│  Content-Type: application/json
│  Body: {"title": "New Book", ...}
│
▼
┌───────────────────────────┐
│ Spring Framework (Tomcat)  │
│ Receive Request            │
└────────┬──────────────────┘
         │
         ├─ Parse JSON body
         │
         ├─ Invoke Jackson deserialization
         │  • Create new Book() instance
         │  • Set properties from JSON
         │  • Return Book object
         │
         ▼
┌───────────────────────────┐
│ BooksPostController       │
│ createBook()              │
└────────┬──────────────────┘
         │
         ├─ Receive @RequestBody Book
         │
         ├─ Validate fields
         │  ├─ title != null && !empty
         │  ├─ author != null && !empty
         │  └─ category != null && !empty
         │
         ├─ Add to books list
         │  books.add(book)
         │
         └─ Return created Book
            │
            ▼
         Jackson Serialization
            │
            └─ Convert to JSON
            │
            ▼
      HTTP Response 201 Created
            │
            ▼
      Client Receives JSON
```

### PUT Request Flow (Full Update)

```
Request: PUT /api/books/update/Hamlet
Body: {"title": "...", "author": "...", "category": "..."}
        │
        ▼
┌──────────────────────────┐
│ BooksPutController       │
│ updateBook()             │
└────────┬─────────────────┘
         │
         ├─ Extract @PathVariable title
         │
         ├─ Receive @RequestBody (new book data)
         │
         ├─ Find existing book by title
         │
         ├─ If found:
         │  ├─ Replace all fields
         │  └─ Return updated book
         │
         └─ If not found:
            └─ Return 404 Not Found
            │
            ▼
      HTTP Response 200/404
```

### PATCH Request Flow (Partial Update)

```
Request: PATCH /api/books/partial/Hamlet
Body: {"title": "New Title"}  ← Only changed field
        │
        ▼
┌──────────────────────────┐
│ BooksPatchController     │
│ partialUpdateBook()      │
└────────┬─────────────────┘
         │
         ├─ Extract @PathVariable title
         │
         ├─ Receive @RequestBody Map<String, String>
         │
         ├─ Find existing book
         │
         ├─ If found:
         │  ├─ IF map contains "title"
         │  │  └─ update only title
         │  │
         │  ├─ IF map contains "author"
         │  │  └─ update only author
         │  │
         │  ├─ IF map contains "category"
         │  │  └─ update only category
         │  │
         │  └─ Return updated book (other fields unchanged)
         │
         └─ If not found:
            └─ Return 404 Not Found
            │
            ▼
      HTTP Response 200/404
      
Note: Only provided fields are updated
      Other fields remain exactly as before
```

### DELETE Request Flow

```
Request: DELETE /api/books/delete/Hamlet
        │
        ▼
┌────────────────────────────┐
│ BooksDeleteController      │
│ deleteBook()               │
└────────┬───────────────────┘
         │
         ├─ Extract @PathVariable title
         │
         ├─ Find book by title
         │
         ├─ If found:
         │  ├─ books.remove(book)
         │  ├─ Log deletion
         │  └─ Return 204 No Content
         │
         └─ If not found:
            └─ Return 404 Not Found
            │
            ▼
      HTTP Response 204/404
```

## HTTP Method Strategy

### GET - Retrieval Strategy

| Aspect | Detail |
|--------|--------|
| **Idempotent** | ✅ Yes |
| **Safe** | ✅ Yes |
| **Cacheable** | ✅ Yes |
| **Has Body** | ❌ No |
| **Status** | 200 OK, 404 Not Found |

**Use Cases**:
- Retrieve single resource: `/api/books/title/{title}`
- Retrieve multiple: `/api/books/category/{category}`
- Search with filters: `/api/books?category=Science`
- List all: `/api/books`

### POST - Creation Strategy

| Aspect | Detail |
|--------|--------|
| **Idempotent** | ❌ No |
| **Safe** | ❌ No |
| **Cacheable** | ❌ No |
| **Has Body** | ✅ Yes |
| **Status** | 201 Created, 400 Bad Request |

**Use Cases**:
- Create single: `/api/books/create`
- Batch create: `/api/books/createMultiple`
- Form submission
- File upload

### PUT - Full Update Strategy

| Aspect | Detail |
|--------|--------|
| **Idempotent** | ✅ Yes |
| **Safe** | ❌ No |
| **Cacheable** | ❌ No |
| **Has Body** | ✅ Yes |
| **Status** | 200 OK, 204 No Content, 404 Not Found |

**Characteristics**:
- Must include ALL fields
- Replaces entire resource
- Same request body = same result (idempotent)

**Use Cases**:
- Update entire resource: `/api/books/update/{title}`
- Batch updates: `/api/books/batch/update`
- Replace data

### PATCH - Partial Update Strategy

| Aspect | Detail |
|--------|--------|
| **Idempotent** | ✅ Yes |
| **Safe** | ❌ No |
| **Cacheable** | ❌ No |
| **Has Body** | ✅ Yes |
| **Status** | 200 OK, 404 Not Found |

**Characteristics**:
- Only include changed fields
- Other fields unchanged
- Idempotent
- More efficient than PUT

**Use Cases**:
- Partial update: `/api/books/partial/{title}`
- Update single field: `/api/books/{title}/title`
- Selective updates

### DELETE - Removal Strategy

| Aspect | Detail |
|--------|--------|
| **Idempotent** | ✅ Yes |
| **Safe** | ❌ No |
| **Cacheable** | ❌ No |
| **Has Body** | ❌ No |
| **Status** | 204 No Content, 200 OK, 404 Not Found |

**Strategies**:
1. Hard Delete: Permanent removal
2. Soft Delete: Mark as deleted
3. Batch Delete: Multiple deletions
4. Conditional Delete: Delete by criteria

**Use Cases**:
- Delete single: `/api/books/delete/{title}`
- Batch delete: `/api/books/batch/delete`
- Delete by category: `/api/books/category/{category}`
- Soft delete: `/api/books/soft/{title}`

## Error Handling

### Current Implementation

```java
try {
    // Business logic
    for (Book book : books) {
        if (book.getTitle().equalsIgnoreCase(title)) {
            return book;
        }
    }
    return null;  // Not found
} catch (Exception e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
    return null;  // Default response
}
```

**Issues**:
- Broad exception catching
- Limited error information
- No structured error response
- Difficult to debug

### Recommended Implementation

```java
@RestController
public class BooksController {
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable String id) {
        try {
            Book book = findBook(id);
            if (book == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(book);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal server error"));
        }
    }
}

@ExceptionHandler(BookNotFoundException.class)
public ResponseEntity<ErrorResponse> handleNotFound(BookNotFoundException e) {
    return ResponseEntity.notFound().build();
}

@ExceptionHandler(ValidationException.class)
public ResponseEntity<ErrorResponse> handleValidation(ValidationException e) {
    return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
}
```

### Error Response Model

```java
public class ErrorResponse {
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    
    // Getters, Setters, Constructors
}
```

## Future Improvements

### 1. Service Layer Implementation

```
Controller Layer
    ↓
Service Layer (NEW)
├─ BooksService
│  ├─ findAll()
│  ├─ findById()
│  ├─ create()
│  ├─ update()
│  └─ delete()
    ↓
Repository Layer (NEW)
    ↓
Database Layer
```

### 2. Database Integration

**Options**:
- **H2**: In-memory (development)
- **PostgreSQL**: Production database
- **MySQL**: Alternative production
- **MongoDB**: NoSQL option

**With JPA/Hibernate**:
```java
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    List<Book> findByCategory(String category);
}

@Service
public class BooksService {
    @Autowired
    private BookRepository repository;
    
    public List<Book> findAll() {
        return repository.findAll();
    }
}
```

### 3. Authentication & Security

```
Add Security Layer:
├─ JWT Tokens
├─ Spring Security
├─ Role-Based Access Control (RBAC)
└─ API Key management
```

### 4. API Versioning

```
/api/v1/books
/api/v2/books
/api/v3/books
```

### 5. Caching Strategy

```
├─ HTTP caching (ETag, Cache-Control)
├─ Spring Cache abstraction
├─ Redis cache
└─ Client-side caching
```

### 6. Pagination & Sorting

```
/api/books?page=1&size=10&sort=title,asc
```

### 7. Comprehensive Logging

```
├─ Request/Response logging
├─ Execution time tracking
├─ Error tracking
└─ Audit trails
```

### 8. Unit & Integration Tests

```
├─ Controller tests
├─ Service tests
├─ Integration tests
├─ Test coverage: >80%
└─ CI/CD pipeline
```

---

**Last Updated**: April 2, 2026
**Version**: 1.0.0

