package com.love2code.books.documentation;

/**
 * ╔════════════════════════════════════════════════════════════════════════════════════════╗
 * ║                    COMPLETE GUIDE TO SPRING ANNOTATIONS USED IN THIS PROJECT           ║
 * ║                                                                                          ║
 * ║  This document explains all annotations used in the Books API project:                 ║
 * ║  1. @RestController - REST API Controller                                              ║
 * ║  2. @RequestMapping - Base URL mapping                                                 ║
 * ║  3. @GetMapping - HTTP GET method                                                      ║
 * ║  4. @PostMapping - HTTP POST method                                                    ║
 * ║  5. @PutMapping - HTTP PUT method                                                      ║
 * ║  6. @DeleteMapping - HTTP DELETE method                                                ║
 * ║  7. @PathVariable - URL path parameters                                                ║
 * ║  8. @RequestParam - Query string parameters                                            ║
 * ║  9. @RequestBody - Request body mapping                                                ║
 * ║  10. @ResponseStatus - HTTP response status code                                       ║
 * ║  11. Other important annotations                                                       ║
 * ╚════════════════════════════════════════════════════════════════════════════════════════╝
 */

public class SpringAnnotationsGuide {

    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 1. @RestController
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @RestController - REST API CONTROLLER ANNOTATION                                    │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @RestController?
     * ────────────────────────
     * @RestController is a Spring annotation that marks a class as a REST API controller.
     * It combines @Controller and @ResponseBody annotations.
     * Every method in the class automatically returns JSON/XML instead of views.
     *
     * IMPORT STATEMENT:
     * ─────────────────
     * import org.springframework.web.bind.annotation.RestController;
     *
     * SYNTAX:
     * ───────
     * @RestController
     * public class BooksController {
     *     // All methods return JSON automatically
     * }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. REST API handler: Automatically serializes return values to JSON/XML
     * 2. Combines annotations: @Controller + @ResponseBody
     * 3. No view resolution: Doesn't look for JSP/HTML templates
     * 4. JSON response: By default returns application/json content-type
     * 5. Spring Bean: Registered as a Spring bean automatically
     * 6. Component scanning: Detected and registered by component scan
     * 7. Request handling: Handles HTTP requests and returns data
     *
     * DETAILED EXPLANATION:
     * ────────────────────
     * When you use @RestController, Spring automatically:
     * 1. Creates a Spring Bean of the class
     * 2. Registers it to handle HTTP requests
     * 3. Serializes method return values to JSON (or other formats)
     * 4. Sets response content-type to application/json
     * 5. Handles serialization/deserialization
     *
     * EXAMPLE USAGE:
     * ──────────────
     */

    // Example 1: Basic @RestController
    /*
    @RestController
    public class BooksConntroller {
        @GetMapping("/api/books")
        public List<Book> getBooks() {
            return books;  // Automatically converted to JSON
        }
    }
    */

    // Example 2: @RestController with @RequestMapping
    /*
    @RestController
    @RequestMapping("/api/books")
    public class BooksController {
        @GetMapping
        public List<Book> getBooks() {
            // GET /api/books -> Returns JSON array
        }

        @PostMapping
        public Book createBook(@RequestBody Book book) {
            // POST /api/books -> Returns JSON object
        }
    }
    */

    /**
     * ADVANTAGES:
     * ───────────
     * ✓ Automatic JSON serialization
     * ✓ RESTful API friendly
     * ✓ Less boilerplate code
     * ✓ Cleaner than @Controller + @ResponseBody
     * ✓ Built-in Spring support
     * ✓ Works with content negotiation (JSON, XML, etc.)
     * ✓ Modern API development standard
     *
     * DISADVANTAGES:
     * ──────────────
     * ✗ Cannot return views (HTML templates)
     * ✗ Not suitable for traditional web applications
     * ✗ Limited to API responses
     *
     * WHEN TO USE:
     * ────────────
     * ✓ Building REST APIs
     * ✓ Microservices
     * ✓ Mobile app backends
     * ✓ Web service APIs
     * ✗ NOT for traditional MVC web applications with views
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Return values are serialized to response body (usually JSON)
     * • No need for ResponseEntity in most cases (though optional)
     * • Jackson library handles serialization
     * • Works with Spring Boot automatically
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 2. @RequestMapping
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @RequestMapping - CLASS/METHOD LEVEL URL MAPPING                                    │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @RequestMapping?
     * ────────────────────────
     * @RequestMapping is a Spring annotation that maps HTTP requests to handler methods.
     * It can be used at class level (base path) or method level (specific path).
     *
     * IMPORT STATEMENT:
     * ─────────────────
     * import org.springframework.web.bind.annotation.RequestMapping;
     *
     * SYNTAX:
     * ───────
     * @RequestMapping(value = "/api/books", method = RequestMethod.GET)
     * public List<Book> getBooks() { }
     *
     * // Or with shorthand:
     * @RequestMapping("/api/books")  // Maps all HTTP methods
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. Flexible mapping: Maps any HTTP method by default
     * 2. Class level: Can define base path for all methods in class
     * 3. Method level: Can define specific path for individual methods
     * 4. Multiple patterns: Supports multiple URL patterns
     * 5. Path variables: Supports {variable} syntax
     * 6. Produces/Consumes: Control request/response content types
     * 7. Headers: Can match on request headers
     * 8. Parameters: Can match on query parameters
     *
     * DETAILED EXPLANATION:
     * ────────────────────
     * @RequestMapping is the parent annotation for @GetMapping, @PostMapping, etc.
     * It provides more control and flexibility.
     *
     * EXAMPLE USAGE:
     * ──────────────
     */

    // Example 1: Class level @RequestMapping
    /*
    @RestController
    @RequestMapping("/api/books")
    public class BooksController {
        // All methods inherit /api/books base path
        
        @RequestMapping  // GET /api/books
        public List<Book> getBooks() { }
        
        @RequestMapping(method = RequestMethod.POST)  // POST /api/books
        public Book createBook(@RequestBody Book book) { }
    }
    */

    // Example 2: Specific HTTP method
    /*
    @RequestMapping(value = "/api/books/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable String id) { }
    */

    // Example 3: Multiple URL patterns
    /*
    @RequestMapping(value = {"/api/books", "/api/library/books"})
    public List<Book> getBooks() { }
    */

    // Example 4: With content type
    /*
    @RequestMapping(
        value = "/api/books",
        method = RequestMethod.POST,
        consumes = "application/json",
        produces = "application/json"
    )
    public Book createBook(@RequestBody Book book) { }
    */

    /**
     * COMPARISON WITH SHORTHAND ANNOTATIONS:
     * ──────────────────────────────────────
     * These are equivalent:
     *
     * @RequestMapping(value = "/books", method = RequestMethod.GET)
     * @GetMapping("/books")
     *
     * @RequestMapping(value = "/books", method = RequestMethod.POST)
     * @PostMapping("/books")
     *
     * @RequestMapping(value = "/books", method = RequestMethod.PUT)
     * @PutMapping("/books")
     *
     * @RequestMapping(value = "/books", method = RequestMethod.DELETE)
     * @DeleteMapping("/books")
     *
     * ADVANTAGES:
     * ───────────
     * ✓ More flexible and powerful than shorthand annotations
     * ✓ Can handle multiple HTTP methods in one annotation
     * ✓ Fine-grained control over request matching
     * ✓ Can match on headers, content types, parameters
     * ✓ More concise for multiple similar mappings
     *
     * DISADVANTAGES:
     * ──────────────
     * ✗ More verbose than shorthand annotations
     * ✗ Overkill for simple GET/POST mappings
     * ✗ Less readable for common use cases
     *
     * WHEN TO USE:
     * ────────────
     * ✓ Class level: Define common base path
     * ✓ Multiple HTTP methods: Map same path to different methods
     * ✓ Advanced routing: Custom header/parameter matching
     * ✗ Simple cases: Use @GetMapping, @PostMapping instead
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Used at class level to define base path for all methods
     * • Can be combined with method-level annotations
     * • Default method matches all HTTP verbs (GET, POST, PUT, DELETE, etc.)
     * • Shorthand annotations (@GetMapping, etc.) are preferred for clarity
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 3. @GetMapping
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @GetMapping - HTTP GET REQUEST MAPPING                                              │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @GetMapping?
     * ────────────────────
     * @GetMapping is a shorthand annotation for @RequestMapping with method = GET.
     * It maps HTTP GET requests to handler methods.
     *
     * IMPORT STATEMENT:
     * ─────────────────
     * import org.springframework.web.bind.annotation.GetMapping;
     *
     * SYNTAX:
     * ───────
     * @GetMapping("/api/books")
     * public List<Book> getBooks() { }
     *
     * EQUIVALENT TO:
     * ──────────────
     * @RequestMapping(value = "/api/books", method = RequestMethod.GET)
     * public List<Book> getBooks() { }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. HTTP GET only: Only handles GET requests
     * 2. Shorthand: Cleaner than @RequestMapping
     * 3. Data retrieval: Used for fetching data without modification
     * 4. Safe method: Does not modify server state
     * 5. Cacheable: Responses can be cached
     * 6. Idempotent: Multiple calls produce same result
     * 7. No request body: Data passed through URL (path variables, query params)
     *
     * EXAMPLE USAGE:
     * ──────────────
     */

    // Example 1: Simple GET
    /*
    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        return books;
    }
    // URL: GET http://localhost:8080/api/books
    // Response: JSON array of all books
    */

    // Example 2: GET with @PathVariable
    /*
    @GetMapping("/api/books/{id}")
    public Book getBookById(@PathVariable String id) {
        return findBook(id);
    }
    // URL: GET http://localhost:8080/api/books/123
    // Response: Single book JSON object
    */

    // Example 3: GET with @RequestParam
    /*
    @GetMapping("/api/books/search")
    public List<Book> searchBooks(
        @RequestParam String category,
        @RequestParam(required = false) String author
    ) {
        return filterBooks(category, author);
    }
    // URL: GET http://localhost:8080/api/books/search?category=Science&author=Einstein
    */

    // Example 4: GET with multiple path variables
    /*
    @GetMapping("/api/library/{libId}/books/{bookId}")
    public Book getBookFromLibrary(
        @PathVariable String libId,
        @PathVariable String bookId
    ) {
        return findBookInLibrary(libId, bookId);
    }
    // URL: GET http://localhost:8080/api/library/456/books/123
    */

    /**
     * ADVANTAGES:
     * ───────────
     * ✓ Clear intent: Obvious it's a GET request
     * ✓ Concise: Shorter than @RequestMapping
     * ✓ Safe: Safe method (no side effects)
     * ✓ Cacheable: Browser can cache responses
     * ✓ Idempotent: Safe to retry multiple times
     * ✓ Shareable: URLs can be shared and bookmarked
     *
     * DISADVANTAGES:
     * ──────────────
     * ✗ Cannot send request body
     * ✗ URL length limitations
     * ✗ Parameters visible in URL (security consideration)
     *
     * WHEN TO USE:
     * ────────────
     * ✓ Retrieve data from server
     * ✓ Fetch list of items
     * ✓ Get single item by ID
     * ✓ Search and filter operations
     * ✓ Read-only operations
     * ✗ NOT for creating, updating, or deleting data
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • No @RequestBody support in method signature
     * • Data passed through @PathVariable and @RequestParam
     * • Responses automatically serialized to JSON
     * • Spring automatically sets content-type to application/json
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 4. @PostMapping
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @PostMapping - HTTP POST REQUEST MAPPING                                            │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @PostMapping?
     * ─────────────────────
     * @PostMapping is a shorthand annotation for @RequestMapping with method = POST.
     * It maps HTTP POST requests to handler methods.
     *
     * IMPORT STATEMENT:
     * ─────────────────
     * import org.springframework.web.bind.annotation.PostMapping;
     *
     * SYNTAX:
     * ───────
     * @PostMapping("/api/books")
     * public Book createBook(@RequestBody Book book) { }
     *
     * EQUIVALENT TO:
     * ──────────────
     * @RequestMapping(value = "/api/books", method = RequestMethod.POST)
     * public Book createBook(@RequestBody Book book) { }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. HTTP POST only: Only handles POST requests
     * 2. Data creation: Used for creating new resources
     * 3. Request body: Data sent in request body
     * 4. NOT Safe: Can modify server state
     * 5. NOT Idempotent: Multiple calls may create multiple resources
     * 6. Complex objects: Can send complex nested objects
     * 7. No size limit: Can send large payloads
     * 8. Sensitive data: Good for passwords and tokens
     *
     * EXAMPLE USAGE:
     * ──────────────
     */

    // Example 1: Simple POST - Create book
    /*
    @PostMapping("/api/books")
    public Book createBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }
    // URL: POST http://localhost:8080/api/books
    // Request Body: {"title": "...", "author": "...", "category": "..."}
    // Response: Created book JSON object
    */

    // Example 2: POST with multiple objects
    /*
    @PostMapping("/api/books/batch")
    public List<Book> createMultipleBooks(@RequestBody List<Book> bookList) {
        books.addAll(bookList);
        return bookList;
    }
    // URL: POST http://localhost:8080/api/books/batch
    // Request Body: JSON array of books
    */

    // Example 3: POST with @ResponseStatus
    /*
    @PostMapping("/api/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }
    // Returns 201 Created instead of 200 OK
    */

    // Example 4: POST with @PathVariable and @RequestBody
    /*
    @PostMapping("/api/library/{libId}/books")
    public Book addBookToLibrary(
        @PathVariable String libId,
        @RequestBody Book book
    ) {
        libraries.get(libId).addBook(book);
        return book;
    }
    // URL: POST http://localhost:8080/api/library/456/books
    // Request Body: book JSON
    */

    /**
     * ADVANTAGES:
     * ───────────
     * ✓ No data size limitation
     * ✓ Can send complex objects
     * ✓ Sensitive data in body (not visible in URL)
     * ✓ Better semantics for creation
     * ✓ Supports nested objects
     * ✓ File upload support
     * ✓ Form submission support
     *
     * DISADVANTAGES:
     * ──────────────
     * ✗ Cannot be bookmarked
     * ✗ Not cacheable by default
     * ✗ Cannot be retried without side effects
     * ✗ Browser back button triggers "Resubmit?" dialog
     * ✗ More complex to test
     *
     * WHEN TO USE:
     * ────────────
     * ✓ Create new resources
     * ✓ Submit form data
     * ✓ Upload files
     * ✓ Send sensitive information
     * ✓ Send large payloads
     * ✓ Create nested/complex objects
     * ✗ NOT for retrieving data (use GET)
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Use @RequestBody to receive JSON data
     * • Use @ResponseStatus(HttpStatus.CREATED) for 201 response
     * • Can combine with @PathVariable for nested resources
     * • Jackson automatically deserializes JSON to objects
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 5. @PutMapping
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @PutMapping - HTTP PUT REQUEST MAPPING                                              │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @PutMapping?
     * ────────────────────
     * @PutMapping is a shorthand annotation for @RequestMapping with method = PUT.
     * It maps HTTP PUT requests to handler methods.
     *
     * IMPORT STATEMENT:
     * ─────────────────
     * import org.springframework.web.bind.annotation.PutMapping;
     *
     * SYNTAX:
     * ───────
     * @PutMapping("/api/books/{id}")
     * public Book updateBook(@PathVariable String id, @RequestBody Book book) { }
     *
     * EQUIVALENT TO:
     * ──────────────
     * @RequestMapping(value = "/api/books/{id}", method = RequestMethod.PUT)
     * public Book updateBook(@PathVariable String id, @RequestBody Book book) { }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. HTTP PUT only: Only handles PUT requests
     * 2. Full update: Replaces entire resource
     * 3. Idempotent: Multiple calls produce same result
     * 4. Resource ID: Usually requires resource identifier
     * 5. Request body: Updated data sent in body
     * 6. Replaces data: Complete replacement, not partial
     * 7. NOT safe: Modifies server state
     *
     * EXAMPLE USAGE:
     * ──────────────
     */

    // Example 1: Full resource update
    /*
    @PutMapping("/api/books/{id}")
    public Book updateBook(
        @PathVariable String id,
        @RequestBody Book updatedBook
    ) {
        Book book = findBook(id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setCategory(updatedBook.getCategory());
        return book;
    }
    // URL: PUT http://localhost:8080/api/books/123
    // Request Body: Complete book JSON object (all fields)
    // Response: Updated book object
    */

    // Example 2: PUT with nested resources
    /*
    @PutMapping("/api/library/{libId}/books/{bookId}")
    public Book updateLibraryBook(
        @PathVariable String libId,
        @PathVariable String bookId,
        @RequestBody Book updatedBook
    ) {
        // Update book in library
    }
    */

    /**
     * PUT vs PATCH:
     * ─────────────
     * PUT: Replaces entire resource (all fields)
     * PATCH: Partial update (only changed fields)
     *
     * Example:
     * Original: {"id": 1, "title": "Book", "author": "Author", "category": "Science"}
     *
     * PUT request: {"id": 1, "title": "New Title", "author": "New Author", "category": "New"}
     * Result: Entire resource replaced
     *
     * PATCH request: {"title": "New Title"}
     * Result: Only title updated, others unchanged
     *
     * ADVANTAGES:
     * ───────────
     * ✓ Idempotent: Safe to retry
     * ✓ Clear semantics: Full update intent
     * ✓ Replaces entire resource
     * ✓ Atomic operation
     *
     * DISADVANTAGES:
     * ──────────────
     * ✗ Must provide all fields (even unchanged ones)
     * ✗ Inefficient for partial updates
     * ✗ Client must know all fields
     *
     * WHEN TO USE:
     * ────────────
     * ✓ Replace entire resource
     * ✓ Full object updates
     * ✓ When all fields are provided
     * ✗ NOT for partial updates (use PATCH)
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Idempotent: Multiple PUT requests = same result
     * • Different from PATCH (partial updates)
     * • Use @PathVariable to identify resource to update
     * • Use @RequestBody to receive updated data
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 6. @DeleteMapping
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @DeleteMapping - HTTP DELETE REQUEST MAPPING                                        │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @DeleteMapping?
     * ───────────────────────
     * @DeleteMapping is a shorthand annotation for @RequestMapping with method = DELETE.
     * It maps HTTP DELETE requests to handler methods.
     *
     * IMPORT STATEMENT:
     * ─────────────────
     * import org.springframework.web.bind.annotation.DeleteMapping;
     *
     * SYNTAX:
     * ───────
     * @DeleteMapping("/api/books/{id}")
     * public void deleteBook(@PathVariable String id) { }
     *
     * EQUIVALENT TO:
     * ──────────────
     * @RequestMapping(value = "/api/books/{id}", method = RequestMethod.DELETE)
     * public void deleteBook(@PathVariable String id) { }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. HTTP DELETE only: Only handles DELETE requests
     * 2. Resource deletion: Removes resources from server
     * 3. Resource ID: Usually requires resource identifier
     * 4. Idempotent: Multiple calls produce same result
     * 5. No request body: Data passed through URL
     * 6. Status codes: Usually returns 204 No Content or 200 OK
     * 7. NOT safe: Modifies server state (removes data)
     *
     * EXAMPLE USAGE:
     * ──────────────
     */

    // Example 1: Simple DELETE by ID
    /*
    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable String id) {
        books.removeIf(b -> b.getId().equals(id));
    }
    // URL: DELETE http://localhost:8080/api/books/123
    // Response: 204 No Content (usually no body)
    */

    // Example 2: DELETE with response
    /*
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        Book book = findBook(id);
        if (book != null) {
            books.remove(book);
            return ResponseEntity.ok("Book deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
    */

    // Example 3: DELETE with @ResponseStatus
    /*
    @DeleteMapping("/api/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String id) {
        books.removeIf(b -> b.getId().equals(id));
    }
    // Returns 204 No Content
    */

    // Example 4: DELETE nested resource
    /*
    @DeleteMapping("/api/library/{libId}/books/{bookId}")
    public void deleteBookFromLibrary(
        @PathVariable String libId,
        @PathVariable String bookId
    ) {
        libraries.get(libId).removeBook(bookId);
    }
    */

    /**
     * ADVANTAGES:
     * ───────────
     * ✓ Idempotent: Safe to retry
     * ✓ Clear intent: Obviously deletes resource
     * ✓ RESTful: Follows REST conventions
     * ✓ Simple: No request body needed
     *
     * DISADVANTAGES:
     * ──────────────
     * ✗ Not reversible: Deletes data permanently
     * ✗ Destructive: Removes data from server
     * ✗ No request body: Limited data transmission
     *
     * WHEN TO USE:
     * ────────────
     * ✓ Remove resources
     * ✓ Delete items by ID
     * ✓ Remove nested resources
     * ✓ Cleanup operations
     * ✗ NOT for non-destructive operations
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Idempotent: DELETE same ID multiple times = same result
     * • Usually returns 204 No Content
     * • Use @PathVariable to identify resource to delete
     * • Be careful: Deletion is permanent
     * • Consider soft deletes (marking as deleted)
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 7. @PathVariable
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @PathVariable - URL PATH PARAMETER EXTRACTION                                       │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @PathVariable?
     * ──────────────────────
     * @PathVariable extracts variable values from the request URL path.
     * It binds URI template variables to controller method parameters.
     *
     * IMPORT STATEMENT:
     * ─────────────────
     * import org.springframework.web.bind.annotation.PathVariable;
     *
     * SYNTAX:
     * ───────
     * @GetMapping("/api/books/{id}")
     * public Book getBook(@PathVariable String id) { }
     *
     * ALTERNATIVE SYNTAX (explicit name):
     * ──────────────────────────────────
     * @GetMapping("/api/books/{bookId}")
     * public Book getBook(@PathVariable("bookId") String id) { }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. Part of URL: Value is in URL path
     * 2. Mandatory: Must be provided (cannot be null)
     * 3. Type conversion: Automatically converts types
     * 4. Single value: One value per path variable
     * 5. URL encoded: Handles URL encoding/decoding
     * 6. Hierarchical: Good for nested resources
     * 7. RESTful: Standard REST convention
     *
     * EXAMPLE USAGE:
     * ──────────────
     */

    // Example 1: Single @PathVariable
    /*
    @GetMapping("/api/books/{id}")
    public Book getBook(@PathVariable String id) {
        return findBook(id);
    }
    // URL: GET http://localhost:8080/api/books/123
    // pathVariable: id = "123"
    */

    // Example 2: Type conversion (String to Long)
    /*
    @GetMapping("/api/books/{id}")
    public Book getBook(@PathVariable Long id) {
        // id is automatically converted to Long
        return findBook(id);
    }
    // URL: GET http://localhost:8080/api/books/789
    // pathVariable: id = 789L (Long type)
    */

    // Example 3: Multiple @PathVariable
    /*
    @GetMapping("/api/library/{libId}/books/{bookId}")
    public Book getBook(
        @PathVariable String libId,
        @PathVariable String bookId
    ) {
        return findBookInLibrary(libId, bookId);
    }
    // URL: GET http://localhost:8080/api/library/456/books/123
    // pathVariables: libId = "456", bookId = "123"
    */

    // Example 4: Explicit path variable name
    /*
    @GetMapping("/api/books/{id}")
    public Book getBook(@PathVariable("id") String bookIdentifier) {
        // bookIdentifier receives value from {id} in URL
        return findBook(bookIdentifier);
    }
    */

    // Example 5: Type mismatch handling
    /*
    @GetMapping("/api/books/{id}")
    public Book getBook(@PathVariable Long id) {
        // URL: /api/books/abc -> 400 Bad Request
        // URL: /api/books/123 -> 200 OK
    }
    */

    /**
     * ADVANTAGES:
     * ───────────
     * ✓ Clean, RESTful URLs
     * ✓ Resource identification
     * ✓ Hierarchical paths
     * ✓ URL is self-descriptive
     * ✓ Mandatory by nature (required)
     * ✓ Type-safe (type conversion)
     * ✓ Easier to cache (different URLs)
     *
     * DISADVANTAGES:
     * ──────────────
     * ✗ Cannot be optional
     * ✗ Limited to simple values
     * ✗ URL length limitations
     * ✗ Cannot send complex objects
     * ✗ Clutters URLs with many variables
     *
     * WHEN TO USE:
     * ────────────
     * ✓ Resource IDs (GET /api/books/123)
     * ✓ Hierarchical resources (GET /api/library/456/books/123)
     * ✓ Required parameters
     * ✓ Resource identification
     * ✓ RESTful design
     *
     * COMMON PATTERNS:
     * ────────────────
     * /api/books/{id}                           - Single resource
     * /api/library/{libId}/books/{bookId}       - Nested resource
     * /api/users/{userId}/posts/{postId}        - Deep nesting
     * /api/books/{category}/{subcategory}       - Hierarchical
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Variables wrapped in curly braces: {id}
     * • Name matching: Method param name must match or use @PathVariable("name")
     * • Type conversion: Automatic for String, Long, int, etc.
     * • Required: Cannot be optional
     * • URL encoding: Automatically handles encoded values
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 8. @RequestParam
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @RequestParam - QUERY STRING PARAMETER EXTRACTION                                   │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @RequestParam?
     * ──────────────────────
     * @RequestParam extracts query string parameters from the request URL.
     * It binds request parameters to controller method parameters.
     *
     * IMPORT STATEMENT:
     * ─────────────────
     * import org.springframework.web.bind.annotation.RequestParam;
     *
     * SYNTAX (Required):
     * ──────────────────
     * @GetMapping("/api/books")
     * public List<Book> getBooks(@RequestParam String category) { }
     *
     * SYNTAX (Optional):
     * ──────────────────
     * @GetMapping("/api/books")
     * public List<Book> getBooks(@RequestParam(required = false) String category) { }
     *
     * SYNTAX (With Default):
     * ──────────────────────
     * @GetMapping("/api/books")
     * public List<Book> getBooks(@RequestParam(defaultValue = "10") int pageSize) { }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. Query string: Value after ? in URL
     * 2. Optional by default: Not required unless specified
     * 3. Multiple values: Can have same parameter multiple times
     * 4. Default values: Can provide defaults if not supplied
     * 5. Type conversion: Automatically converts types
     * 6. Flexible: Good for filters and options
     * 7. Visible in URL: Parameters visible in address bar
     *
     * EXAMPLE USAGE:
     * ──────────────
     */

    // Example 1: Required @RequestParam
    /*
    @GetMapping("/api/books")
    public List<Book> getBooks(@RequestParam String category) {
        return filterByCategory(category);
    }
    // URL: GET http://localhost:8080/api/books?category=Science
    // URL without param: 400 Bad Request (required)
    */

    // Example 2: Optional @RequestParam
    /*
    @GetMapping("/api/books")
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category != null) {
            return filterByCategory(category);
        }
        return allBooks;
    }
    // URL: GET http://localhost:8080/api/books?category=Science
    // URL: GET http://localhost:8080/api/books (category = null)
    */

    // Example 3: @RequestParam with default value
    /*
    @GetMapping("/api/books")
    public List<Book> getBooks(
        @RequestParam(defaultValue = "Science") String category,
        @RequestParam(defaultValue = "10") int pageSize
    ) {
        return getPaginatedBooks(category, pageSize);
    }
    // URL: GET http://localhost:8080/api/books
    // Uses default values: category="Science", pageSize=10
    // URL: GET http://localhost:8080/api/books?category=Math&pageSize=20
    */

    // Example 4: Multiple @RequestParam
    /*
    @GetMapping("/api/books/search")
    public List<Book> search(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String category,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        return searchBooks(title, author, category, page, size);
    }
    // URL: GET /api/books/search?author=Einstein&category=Science&size=25
    */

    // Example 5: Multiple values for same parameter
    /*
    @GetMapping("/api/books")
    public List<Book> getBooks(@RequestParam List<String> categories) {
        // categories is a list of all values provided
        return filterByCategories(categories);
    }
    // URL: GET /api/books?categories=Science&categories=Math&categories=Physics
    // categories = ["Science", "Math", "Physics"]
    */

    // Example 6: Type conversion
    /*
    @GetMapping("/api/books")
    public List<Book> getBooks(
        @RequestParam int pageSize,
        @RequestParam double minPrice,
        @RequestParam boolean available
    ) {
        // Types automatically converted
    }
    // URL: /api/books?pageSize=20&minPrice=10.5&available=true
    */

    /**
     * ADVANTAGES:
     * ───────────
     * ✓ Optional by default
     * ✓ Can have default values
     * ✓ Multiple parameters easily managed
     * ✓ Can have multiple values for same param
     * ✓ Good for filtering and sorting
     * ✓ Flexible configuration
     * ✓ Standard query string format
     *
     * DISADVANTAGES:
     * ──────────────
     * ✗ URLs become cluttered
     * ✗ Less RESTful than @PathVariable
     * ✗ Parameters visible in URL
     * ✗ URL length limitations
     * ✗ Not good for resource identification
     *
     * WHEN TO USE:
     * ────────────
     * ✓ Filtering results
     * ✓ Sorting and pagination
     * ✓ Search queries
     * ✓ Optional parameters
     * ✓ Configuration options
     * ✓ Multiple optional filters
     * ✗ NOT for required resource identification (use @PathVariable)
     *
     * COMMON PATTERNS:
     * ────────────────
     * /api/books?category=Science                          - Single filter
     * /api/books?category=Science&author=Einstein          - Multiple filters
     * /api/books?page=2&size=20                            - Pagination
     * /api/books?sort=title&order=asc                      - Sorting
     * /api/books?categories=Science&categories=Math        - Multiple values
     * /api/books?title=Great&author=Fitzgerald&year=1925   - Complex search
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Optional by default (use required = true to make required)
     * • Supports defaultValue for when parameter not provided
     * • Can bind to List for multiple values
     * • Type conversion automatic for common types
     * • Parameter name matches by default (or use @RequestParam("name"))
     * • Great for filtering, sorting, pagination
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 9. @RequestBody
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @RequestBody - HTTP REQUEST BODY MAPPING                                            │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @RequestBody?
     * ────────────────────
     * @RequestBody binds HTTP request body to method parameter.
     * It deserializes JSON/XML from request body to Java object.
     *
     * IMPORT STATEMENT:
     * ─────────────────
     * import org.springframework.web.bind.annotation.RequestBody;
     *
     * SYNTAX:
     * ───────
     * @PostMapping("/api/books")
     * public Book createBook(@RequestBody Book book) { }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. Request body: Data in request body (not URL)
     * 2. JSON/XML: Deserializes JSON or XML to object
     * 3. Complex objects: Supports nested structures
     * 4. Jackson mapping: Automatic deserialization
     * 5. Type-safe: Converts to Java object
     * 6. Required by default: Must be provided
     * 7. Content type: Usually application/json
     * 8. No size limit: Can send large payloads
     *
     * EXAMPLE USAGE:
     * ──────────────
     */

    // Example 1: Simple @RequestBody
    /*
    @PostMapping("/api/books")
    public Book createBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }
    // URL: POST http://localhost:8080/api/books
    // Request Header: Content-Type: application/json
    // Request Body:
    // {
    //     "title": "New Book",
    //     "author": "John Doe",
    //     "category": "Fiction"
    // }
    // Response: Created book JSON object
    */

    // Example 2: @RequestBody with List
    /*
    @PostMapping("/api/books/batch")
    public List<Book> createMultipleBooks(@RequestBody List<Book> bookList) {
        books.addAll(bookList);
        return bookList;
    }
    // URL: POST http://localhost:8080/api/books/batch
    // Request Body: JSON array of books
    // [
    //     {"title": "Book 1", ...},
    //     {"title": "Book 2", ...}
    // ]
    */

    // Example 3: @RequestBody with nested objects
    /*
    @PostMapping("/api/library")
    public Library createLibrary(@RequestBody Library library) {
        // library has nested Book objects
        libraries.add(library);
        return library;
    }
    // Request Body can be complex nested JSON
    */

    // Example 4: @RequestBody with @PathVariable
    /*
    @PutMapping("/api/books/{id}")
    public Book updateBook(
        @PathVariable String id,
        @RequestBody Book updatedBook
    ) {
        Book book = findBook(id);
        book.setTitle(updatedBook.getTitle());
        return book;
    }
    // URL: PUT http://localhost:8080/api/books/123
    // Request Body: Updated book JSON
    */

    // Example 5: @RequestBody with validation
    /*
    @PostMapping("/api/books")
    public Book createBook(@RequestBody @Valid Book book) {
        // book properties are validated using @Valid
        books.add(book);
        return book;
    }
    // Validates all @NotNull, @NotEmpty, etc. annotations in Book class
    */

    // Example 6: @RequestBody with Map for flexible field updates
    /*
    @PatchMapping("/api/books/{id}")
    public ResponseEntity<Book> partialUpdate(
        @PathVariable String id,
        @RequestBody Map<String, String> updates
    ) {
        Book book = findBook(id);
        if (updates.containsKey("title")) {
            book.setTitle(updates.get("title"));
        }
        if (updates.containsKey("author")) {
            book.setAuthor(updates.get("author"));
        }
        return ResponseEntity.ok(book);
    }
    // URL: PATCH http://localhost:8080/api/books/1
    // Request Body: {"title": "New Title"}  // Only send fields to update
    // Response: Updated book with partial changes
    // ADVANTAGE: Only updates provided fields, leaves others unchanged
    */

    // Example 7: @RequestBody with List of objects
    /*
    @PostMapping("/api/books/batch")
    public ResponseEntity<List<Book>> createMultipleBooks(
        @RequestBody List<Book> bookList
    ) {
        books.addAll(bookList);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookList);
    }
    // URL: POST http://localhost:8080/api/books/batch
    // Request Body:
    // [
    //     {"title": "Book 1", "author": "Author 1", "category": "Fiction"},
    //     {"title": "Book 2", "author": "Author 2", "category": "Science"}
    // ]
    // Response: 201 Created with array of created books
    */

    // Example 8: @RequestBody with complex nested structure
    /*
    @PostMapping("/api/library")
    public ResponseEntity<Library> createLibrary(
        @RequestBody Library library
    ) {
        // Library class contains List<Book> and other nested objects
        libraries.add(library);
        return ResponseEntity.status(HttpStatus.CREATED).body(library);
    }
    // Request Body can be deeply nested JSON structures
    // Jackson automatically deserializes all nested objects
    */

    /**
     * HOW JACKSON DESERIALIZATION WORKS:
     * ──────────────────────────────────
     * 1. Spring receives HTTP request with JSON body
     * 2. Jackson library reads JSON string
     * 3. Jackson maps JSON fields to object properties
     * 4. Requires no-arg constructor in class (Book())
     * 5. Uses setters or direct field mapping
     * 6. Returns Java object
     * 7. Works recursively for nested objects and lists
     *
     * Example - Simple Object:
     * JSON: {"title": "Great Gatsby", "author": "Fitzgerald", "category": "Fiction"}
     * ↓
     * Book book = new Book();
     * book.setTitle("Great Gatsby");
     * book.setAuthor("Fitzgerald");
     * book.setCategory("Fiction");
     * ↓
     * Returns: Book object ready to use
     *
     * Example - List of Objects:
     * JSON: [{"title": "Book1"...}, {"title": "Book2"...}]
     * ↓
     * List<Book> books = new ArrayList<>();
     * books.add(new Book("Book1"...));
     * books.add(new Book("Book2"...));
     * ↓
     * Returns: List<Book>
     *
     * Example - Map for flexible data:
     * JSON: {"title": "New Title", "author": "New Author"}
     * ↓
     * Map<String, String> map = new HashMap<>();
     * map.put("title", "New Title");
     * map.put("author", "New Author");
     * ↓
     * Returns: Map with flexible key-value pairs
     *
     * ADVANTAGES:
     * ───────────
     * ✓ No data size limitation
     * ✓ Can send complex nested objects
     * ✓ Automatic deserialization
     * ✓ Type-safe (mapped to Java object)
     * ✓ Security: Not visible in URL
     * ✓ Supports collections and nested objects
     * ✓ Easy to validate input
     * ✓ Works with Map for flexible/dynamic fields
     * ✓ Better for partial updates (using Map)
     * ✓ Scalable for evolving data structures
     * ✓ Automatic handling of complex nested structures
     *
     * DISADVANTAGES:
     * ──────────────
     * ✗ Requires no-arg constructor in classes
     * ✗ Can be complex to debug JSON mapping issues
     * ✗ Sensitive data still in body (use HTTPS)
     * ✗ Large payloads can impact performance
     * ✗ Requires correct JSON structure match
     * ✗ May be overkill for simple string parameters
     *
     * WHEN TO USE:
     * ────────────
     * ✓ Create new resources (POST)
     * ✓ Update resources (PUT/PATCH)
     * ✓ Send complex objects
     * ✓ Send sensitive data (use with HTTPS)
     * ✓ Form submissions
     * ✓ Batch operations (sending lists)
     * ✓ Partial updates (using Map or custom DTO)
     * ✓ Flexible/dynamic field structures
     * ✓ Nested object creation
     * ✗ NOT for simple single values (use @RequestParam)
     * ✗ NOT for form-data (use @RequestParam or MultipartFile)
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Requires class to have no-arg constructor (Book())
     * • Jackson automatically deserializes JSON to object
     * • Content-Type MUST be application/json
     * • Works with @Valid for validation
     * • Can combine with @PathVariable and @RequestParam
     * • Error if JSON structure doesn't match class
     * • Use Map<String, String> for partial/flexible updates
     * • Use List<T> for batch operations
     * • Jackson ignores extra JSON fields (by default)
     * • Missing JSON fields use default values or stay null
     *
     * COMPARISON WITH @RequestParam:
     * ──────────────────────────────
     * @RequestBody:
     *   • Data location: HTTP request body
     *   • Format: JSON/XML
     *   • Content-Type: application/json
     *   • Data complexity: Complex objects, nested
     *   • Use case: CREATE, UPDATE with full data
     *   • Size: No practical limit
     *   • Visibility: Not in URL (private)
     *
     * @RequestParam:
     *   • Data location: Query string (?param=value)
     *   • Format: Simple key-value pairs
     *   • Content-Type: Not needed
     *   • Data complexity: Simple strings only
     *   • Use case: Filtering, pagination, simple options
     *   • Size: Limited (URL length limits)
     *   • Visibility: Visible in URL
     *
     * REAL-WORLD USAGE FROM THIS PROJECT:
     * ────────────────────────────────────
     *
     * 1. POST - Create single book (BooksPostController)
     *    @PostMapping("/create")
     *    public Book createBook(@RequestBody Book book) { }
     *    Receives: {"title": "...", "author": "...", "category": "..."}
     *
     * 2. POST - Create batch (BooksPostController)
     *    @PostMapping("/createMultiple")
     *    public List<Book> createMultipleBooks(@RequestBody List<Book> bookList) { }
     *    Receives: [{"title": "Book1"...}, {"title": "Book2"...}]
     *
     * 3. PUT - Full update (BooksPutController)
     *    @PutMapping("/update/{title}")
     *    public ResponseEntity<Book> updateBook(
     *        @PathVariable String title,
     *        @RequestBody Book updated
     *    ) { }
     *    Receives: {"title": "...", "author": "...", "category": "..."}
     *
     * 4. PATCH - Partial update with Map (BooksPatchController)
     *    @PatchMapping("/partial/{title}")
     *    public ResponseEntity<Book> partialUpdateBook(
     *        @PathVariable String title,
     *        @RequestBody Map<String, String> updates
     *    ) { }
     *    Receives: {"title": "New Title"}  // Only changed fields
     *    Advantage: Only provided fields are updated, others preserved
     *
     * 5. DELETE - Batch delete with request body (BooksDeleteController)
     *    @DeleteMapping("/batch/delete")
     *    public ResponseEntity<Map<String, String>> deleteMultipleBooks(
     *        @RequestBody Map<String, List<String>> request
     *    ) { }
     *    Receives: {"titles": ["Book1", "Book2", "Book3"]}
     *    Advantage: Send multiple identifiers in single request
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 10. @ResponseStatus
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @ResponseStatus - HTTP RESPONSE STATUS CODE                                         │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @ResponseStatus?
     * ────────────────────────
     * @ResponseStatus sets the HTTP status code for the response.
     * It overrides the default 200 OK status.
     *
     * IMPORT STATEMENT:
     * ─────────────────
     * import org.springframework.http.HttpStatus;
     * import org.springframework.web.bind.annotation.ResponseStatus;
     *
     * SYNTAX:
     * ───────
     * @ResponseStatus(HttpStatus.CREATED)
     * @PostMapping("/api/books")
     * public Book createBook(@RequestBody Book book) { }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. HTTP status code: Sets response status (201, 204, 400, etc.)
     * 2. Method level: Applied to handler method
     * 3. Class level: Can be applied to exception classes
     * 4. Overrides default: Changes from default 200 OK
     * 5. Semantically correct: Proper HTTP status for operation
     * 6. Client-friendly: Clients understand operation result
     * 7. RESTful: Follows REST conventions
     *
     * COMMON HTTP STATUS CODES:
     * ──────────────────────────
     * 200 OK              - Success (default for GET, POST responses)
     * 201 Created         - Resource created (use for POST)
     * 204 No Content      - Success but no body (use for DELETE)
     * 400 Bad Request     - Invalid request
     * 401 Unauthorized    - Authentication required
     * 403 Forbidden       - Access denied
     * 404 Not Found       - Resource not found
     * 500 Internal Error  - Server error
     *
     * EXAMPLE USAGE:
     * ──────────────
     */

    // Example 1: 201 Created for POST
    /*
    @PostMapping("/api/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }
    // Response: 201 Created with book JSON in body
    */

    // Example 2: 204 No Content for DELETE
    /*
    @DeleteMapping("/api/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String id) {
        books.removeIf(b -> b.getId().equals(id));
    }
    // Response: 204 No Content (no response body)
    */

    // Example 3: 202 Accepted for async operations
    /*
    @PostMapping("/api/books/process")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void processBooks(@RequestBody List<Book> books) {
        // Process books asynchronously
    }
    // Response: 202 Accepted
    */

    /**
     * HTTP STATUS CODE GUIDE:
     * ───────────────────────
     * 2XX - Success:
     *   200 OK              - Successful GET, PUT, POST (with body)
     *   201 Created         - Successful resource creation (POST)
     *   202 Accepted        - Request accepted for async processing
     *   204 No Content      - Successful DELETE, or operation with no body
     *
     * 4XX - Client Error:
     *   400 Bad Request     - Invalid request (validation failed)
     *   401 Unauthorized    - Authentication required
     *   403 Forbidden       - Authenticated but not authorized
     *   404 Not Found       - Resource doesn't exist
     *   409 Conflict        - Request conflicts with current state
     *
     * 5XX - Server Error:
     *   500 Internal Error  - Server error
     *   503 Service Unavailable - Service temporarily unavailable
     *
     * BEST PRACTICES:
     * ───────────────
     * ✓ Use 201 Created for successful POST (resource creation)
     * ✓ Use 204 No Content for DELETE
     * ✓ Use 200 OK for GET and successful updates
     * ✓ Use 400 for validation errors
     * ✓ Use 404 for resource not found
     * ✓ Use 500 only for unexpected server errors
     *
     * ADVANTAGES:
     * ───────────
     * ✓ RESTful: Proper HTTP semantics
     * ✓ Client-friendly: Clients know operation result
     * ✓ Standard: Follows HTTP conventions
     * ✓ Proper caching: Clients can cache correctly
     * ✓ Monitoring: Easier to track API usage
     *
     * WHEN TO USE:
     * ────────────
     * ✓ 201 Created: After successful POST (resource creation)
     * ✓ 204 No Content: After successful DELETE
     * ✓ 200 OK: Default for successful operations
     * ✓ 400 Bad Request: Validation errors
     * ✓ 404 Not Found: Resource not found
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Applied at method level with @ResponseStatus
     * • Overrides default 200 OK response
     * • Should be semantically correct for the operation
     * • RESTful APIs should use proper status codes
     * • Can be combined with error responses
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 11. OTHER IMPORTANT ANNOTATIONS
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ OTHER IMPORTANT SPRING ANNOTATIONS FOR FUTURE USE                                   │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * @CrossOrigin
     * ────────────
     * Enables CORS (Cross-Origin Resource Sharing) for API access from different origins.
     *
     * @CrossOrigin(origins = "http://localhost:3000")
     * @GetMapping("/api/books")
     * public List<Book> getBooks() { }
     *
     * @Autowired
     * ────────
     * Injects dependencies into Spring beans.
     *
     * @Autowired
     * private BookService bookService;
     *
     * @Service
     * ────────
     * Marks class as a service layer component.
     *
     * @Service
     * public class BookService {
     *     // Business logic
     * }
     *
     * @Repository
     * ──────────
     * Marks class as a data access object (DAO).
     *
     * @Repository
     * public class BookRepository {
     *     // Database operations
     * }
     *
     * @Valid
     * ──────
     * Triggers validation on request body using validation annotations.
     *
     * @PostMapping("/api/books")
     * public Book createBook(@RequestBody @Valid Book book) { }
     *
     * @ExceptionHandler
     * ────────────────
     * Handles exceptions thrown by handler methods.
     *
     * @ExceptionHandler(EntityNotFoundException.class)
     * public ResponseEntity<String> handleNotFound() {
     *     return ResponseEntity.notFound().build();
     * }
     *
     * @ControllerAdvice
     * ─────────────────
     * Global exception handler for all controllers.
     *
     * @ControllerAdvice
     * public class GlobalExceptionHandler {
     *     @ExceptionHandler(Exception.class)
     *     public ResponseEntity<String> handleException(Exception e) { }
     * }
     */

}
