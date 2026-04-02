package com.love2code.books.documentation;

/**
 * ╔════════════════════════════════════════════════════════════════════════════════════════╗
 * ║                     COMPLETE GUIDE TO HTTP METHODS & REST API CONCEPTS                  ║
 * ║                                                                                          ║
 * ║  This document explains:                                                                ║
 * ║  1. HTTP GET Method - Retrieve Data                                                     ║
 * ║  2. HTTP POST Method - Create Data                                                      ║
 * ║  3. @PathVariable - URL Path Parameters                                                 ║
 * ║  4. @RequestParam - Query String Parameters                                             ║
 * ║  5. Differences, Advantages, Disadvantages & Usage                                      ║
 * ╚════════════════════════════════════════════════════════════════════════════════════════╝
 */

public class HTTPMethodsGuide {

    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 1. HTTP GET METHOD - RETRIEVE DATA
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ HTTP GET METHOD COMPLETE EXPLANATION                                                │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS GET?
     * ───────────
     * GET is an HTTP method used to RETRIEVE data from the server.
     * It requests data without modifying it.
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. Safe Method: Does not modify server data
     * 2. Idempotent: Multiple requests produce same result
     * 3. Cacheable: Responses can be cached by browsers
     * 4. No Request Body: Data passed through URL only
     * 5. Visible Parameters: Parameters visible in URL (less secure for sensitive data)
     * 6. Limited Data Size: Browser URL length limits (typically 2000-8000 characters)
     * 7. Bookmarkable: URLs can be bookmarked
     *
     * EXAMPLES:
     * ────────
     */

    // Example 1: Simple GET - Retrieve all books
    // @GetMapping("/api/books")
    // public List<Book> getAllBooks() {
    //     return books;
    // }
    // 
    // URL: http://localhost:8080/api/books
    // Response: JSON array of all books

    // Example 2: GET with @PathVariable - Retrieve book by title
    // @GetMapping("/api/books/title/{title}")
    // public Book getBookByTitle(@PathVariable String title) {
    //     // Find and return book with matching title
    // }
    //
    // URL: http://localhost:8080/api/books/title/The%20Great%20Gatsby
    // Path Variable: title = "The Great Gatsby"
    // Response: Single book object

    // Example 3: GET with @RequestParam - Retrieve books with filters
    // @GetMapping("/api/books/search")
    // public List<Book> searchBooks(
    //     @RequestParam(required = false) String category,
    //     @RequestParam(required = false) String author
    // ) {
    //     // Filter books by category and/or author
    // }
    //
    // URL: http://localhost:8080/api/books/search?category=Science&author=Einstein
    // Query Parameters: category=Science, author=Einstein
    // Response: Filtered list of books

    /**
     * WHEN TO USE GET:
     * ───────────────
     * ✓ Retrieve existing data (read-only operations)
     * ✓ Fetch a list of items
     * ✓ Get a single item by ID or identifier
     * ✓ Search and filter data
     * ✓ When data doesn't need to be modified
     *
     * ADVANTAGES OF GET:
     * ──────────────────
     * ✓ Simple and straightforward to implement
     * ✓ Responses can be cached by browsers/proxies
     * ✓ URLs can be bookmarked and shared
     * ✓ No request body needed
     * ✓ Safer for sensitive data when using HTTPS
     * ✓ Better browser support and history
     * ✓ Follows REST convention for read operations
     * ✓ Better for SEO (URLs are indexed)
     *
     * DISADVANTAGES OF GET:
     * ─────────────────────
     * ✗ Limited data transmission (URL length limit)
     * ✗ Parameters visible in URL (not good for passwords/tokens)
     * ✗ Cannot send complex objects (limited to simple parameters)
     * ✗ No request body support
     * ✗ Performance issues with many parameters
     * ✗ All data visible in browser history
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 1.5. HTTP PUT METHOD - UPDATE ENTIRE RESOURCE
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ HTTP PUT METHOD COMPLETE EXPLANATION                                                │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS PUT?
     * ────────────
     * PUT is an HTTP method used to UPDATE/REPLACE entire resources on the server.
     * It replaces the entire resource with new data.
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. Idempotent: Multiple requests produce same result
     * 2. Full update: Replaces entire resource (all fields)
     * 3. Resource ID: Requires identifier in URL path
     * 4. Request body: Complete updated data in body
     * 5. NOT safe: Modifies server state
     * 6. Complete replacement: Must provide all fields
     * 7. Atomic: Entire resource replaced at once
     * 8. Status code: Usually 200 OK or 204 No Content
     *
     * IDEMPOTENCY EXPLANATION:
     * ───────────────────────
     * An operation is idempotent if multiple requests produce the same result.
     * 
     * Example:
     * PUT /api/books/1 with {"title": "New Title", ...}
     * Request 1: Updates book -> Result: Book updated
     * Request 2: Updates book with same data -> Result: Book updated (same as before)
     * Request 3: Updates book with same data -> Result: Book updated (same as before)
     * 
     * No matter how many times you send the same PUT request,
     * the result is always the same.
     *
     * FULL UPDATE vs PARTIAL UPDATE:
     * ──────────────────────────────
     * PUT:   Full update - Replace entire resource
     *        Must provide ALL fields
     *        Example: PUT /api/books/1 {"title": "...", "author": "...", "category": "..."}
     *
     * PATCH: Partial update - Update only specific fields
     *        Can provide ONLY changed fields
     *        Example: PATCH /api/books/1 {"title": "..."}
     *
     * EXAMPLES:
     * ────────
     */

    // Example 1: Simple PUT - Update entire book
    // @PutMapping("/api/books/{id}")
    // public Book updateBook(
    //     @PathVariable String id,
    //     @RequestBody Book updatedBook
    // ) {
    //     // Replace entire book with new data
    //     Book book = findBook(id);
    //     book.setTitle(updatedBook.getTitle());
    //     book.setAuthor(updatedBook.getAuthor());
    //     book.setCategory(updatedBook.getCategory());
    //     return book;
    // }
    //
    // URL: PUT http://localhost:8080/api/books/123
    // Request Body:
    // {
    //     "title": "Updated Title",
    //     "author": "Updated Author",
    //     "category": "Updated Category"
    // }
    // Response: Updated book object

    // Example 2: PUT with ResponseEntity and status codes
    // @PutMapping("/api/books/{id}")
    // public ResponseEntity<Book> updateBook(
    //     @PathVariable String id,
    //     @RequestBody Book updatedBook
    // ) {
    //     Book book = findBook(id);
    //     if (book == null) {
    //         return ResponseEntity.notFound().build();  // 404
    //     }
    //     book.setTitle(updatedBook.getTitle());
    //     book.setAuthor(updatedBook.getAuthor());
    //     return ResponseEntity.ok(book);  // 200 OK
    // }

    // Example 3: PUT with @ResponseStatus
    // @PutMapping("/api/books/{id}")
    // @ResponseStatus(HttpStatus.OK)
    // public Book updateBook(
    //     @PathVariable String id,
    //     @RequestBody Book updatedBook
    // ) {
    //     // Update and return book
    // }

    /**
     * WHEN TO USE PUT:
     * ────────────────
     * ✓ Update entire resource/object
     * ✓ Replace all fields of a resource
     * ✓ You have complete updated data
     * ✓ Full replacement of resource
     * ✓ When idempotency is important
     * ✓ Update user profile (all fields)
     * ✓ Update product details (complete info)
     * ✗ NOT for partial updates (use PATCH)
     * ✗ NOT when you only have some fields to update
     *
     * ADVANTAGES OF PUT:
     * ──────────────────
     * ✓ Idempotent: Safe to retry
     * ✓ Clear semantics: Full update intent
     * ✓ Atomic: Entire resource replaced at once
     * ✓ No ambiguity: All fields provided
     * ✓ RESTful: Standard for full updates
     * ✓ Easy to understand
     * ✓ State is well-defined
     *
     * DISADVANTAGES OF PUT:
     * ─────────────────────
     * ✗ Must provide ALL fields (even unchanged ones)
     * ✗ Inefficient for partial updates
     * ✗ Client must know all fields
     * ✗ Large payloads for small changes
     * ✗ Not ideal for sparse updates
     * ✗ Risk of overwriting with incomplete data
     *
     * HTTP STATUS CODES FOR PUT:
     * ──────────────────────────
     * 200 OK              - Successfully updated, returns updated resource
     * 204 No Content      - Successfully updated, no body returned
     * 400 Bad Request     - Invalid request body
     * 404 Not Found       - Resource not found
     * 409 Conflict        - Resource conflict
     * 500 Server Error    - Internal error
     *
     * PUT vs POST vs PATCH:
     * ─────────────────────
     * POST:  Create new resource (non-idempotent)
     *        Multiple requests = multiple resources
     *        URL: POST /api/books (no ID)
     *
     * PUT:   Full update (idempotent)
     *        Multiple requests = same result
     *        URL: PUT /api/books/123 (with ID)
     *        Must provide ALL fields
     *
     * PATCH: Partial update (may or may not be idempotent)
     *        Update only specific fields
     *        URL: PATCH /api/books/123 (with ID)
     *        Only changed fields provided
     *
     * REAL-WORLD EXAMPLES:
     * ────────────────────
     * Update entire user profile:
     * PUT /api/users/123
     * {"name": "John", "email": "john@example.com", "age": 30, "address": "123 Main St"}
     *
     * Update book details:
     * PUT /api/books/456
     * {"title": "New Title", "author": "New Author", "category": "New Category", "price": 19.99}
     *
     * Update product inventory:
     * PUT /api/products/789
     * {"name": "Product", "price": 100, "quantity": 50, "description": "..."}
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Idempotent: Multiple PUTs = same result (important!)
     * • Different from PATCH (partial updates)
     * • Requires complete object in request body
     * • Use @PathVariable to identify resource
     * • Use @RequestBody to receive updated data
     * • Return 200 OK or 204 No Content
     * • Consider what happens if fields are missing
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 1.6. HTTP PATCH METHOD - PARTIAL UPDATE
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ HTTP PATCH METHOD COMPLETE EXPLANATION                                              │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS PATCH?
     * ──────────────
     * PATCH is an HTTP method used to PARTIALLY UPDATE resources on the server.
     * It updates only the fields that are provided, leaving others unchanged.
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. Partial update: Updates only specific fields
     * 2. Selective data: Only changed fields in request body
     * 3. Resource ID: Requires identifier in URL path
     * 4. Efficient: Smaller payloads than PUT
     * 5. NOT idempotent: Multiple requests may have different results*
     * 6. NOT safe: Modifies server state
     * 7. Status code: Usually 200 OK
     * 8. Preserves data: Unmodified fields remain unchanged
     *
     * *Note: PATCH can be made idempotent depending on implementation
     *
     * PUT vs PATCH - KEY DIFFERENCES:
     * ───────────────────────────────
     * PUT:
     *   • Full update: Replace entire resource
     *   • All fields required: Must provide complete object
     *   • Idempotent: Multiple requests = same result
     *   • Semantics: Complete replacement
     *
     * PATCH:
     *   • Partial update: Update only specific fields
     *   • Only changed fields: Provide only what changed
     *   • May not be idempotent: Depends on implementation
     *   • Semantics: Partial modification
     *
     * EXAMPLE COMPARISON:
     * ───────────────────
     * Original Book:
     * {
     *     "title": "Old Title",
     *     "author": "Old Author",
     *     "category": "Old Category"
     * }
     *
     * PUT Request (must include all fields):
     * PUT /api/books/1
     * {
     *     "title": "New Title",
     *     "author": "New Author",
     *     "category": "New Category"
     * }
     * Result: All fields replaced
     *
     * PATCH Request (only changed fields):
     * PATCH /api/books/1
     * {
     *     "title": "New Title"
     * }
     * Result:
     * {
     *     "title": "New Title",          ← Changed
     *     "author": "Old Author",        ← Unchanged
     *     "category": "Old Category"     ← Unchanged
     * }
     *
     * EXAMPLES:
     * ────────
     */

    // Example 1: Partial update with selective fields
    // @PatchMapping("/api/books/{id}")
    // public Book patchBook(
    //     @PathVariable String id,
    //     @RequestBody Map<String, String> updates
    // ) {
    //     Book book = findBook(id);
    //     if (updates.containsKey("title")) {
    //         book.setTitle(updates.get("title"));
    //     }
    //     if (updates.containsKey("author")) {
    //         book.setAuthor(updates.get("author"));
    //     }
    //     return book;
    // }
    //
    // URL: PATCH http://localhost:8080/api/books/123
    // Request Body: {"title": "New Title"}
    // Response: Updated book (other fields unchanged)

    // Example 2: Update single field
    // @PatchMapping("/api/books/{id}/category")
    // public ResponseEntity<Book> updateCategory(
    //     @PathVariable String id,
    //     @RequestBody Map<String, String> request
    // ) {
    //     Book book = findBook(id);
    //     if (book != null) {
    //         book.setCategory(request.get("value"));
    //         return ResponseEntity.ok(book);
    //     }
    //     return ResponseEntity.notFound().build();
    // }

    /**
     * WHEN TO USE PATCH:
     * ──────────────────
     * ✓ Update specific field only
     * ✓ Don't have complete object data
     * ✓ Want efficient partial updates
     * ✓ Preserve existing field values
     * ✓ Only title changes, keep other fields
     * ✓ Client doesn't know all fields
     * ✓ Reduce bandwidth usage
     * ✗ NOT for full resource replacement (use PUT)
     *
     * ADVANTAGES OF PATCH:
     * ────────────────────
     * ✓ Efficient: Only send changed data
     * ✓ Flexible: Can update any combination of fields
     * ✓ Selective: Control exactly what changes
     * ✓ Bandwidth: Smaller payloads
     * ✓ User-friendly: Don't need full object
     * ✓ Preserves: Unspecified fields unchanged
     * ✓ Modern: Used in modern APIs
     *
     * DISADVANTAGES OF PATCH:
     * ───────────────────────
     * ✗ Not idempotent by default
     * ✗ More complex implementation
     * ✗ Partial update semantics can be ambiguous
     * ✗ May not be supported by all clients
     * ✗ Testing complexity
     *
     * HTTP STATUS CODES FOR PATCH:
     * ──────────────────────────────
     * 200 OK              - Successfully updated, returns updated resource
     * 204 No Content      - Successfully updated, no body
     * 400 Bad Request     - Invalid request
     * 404 Not Found       - Resource not found
     * 409 Conflict        - Conflict with current state
     * 500 Server Error    - Internal error
     *
     * REAL-WORLD EXAMPLES:
     * ────────────────────
     * Update only book title:
     * PATCH /api/books/1
     * {"title": "New Title"}
     *
     * Update only category:
     * PATCH /api/books/1
     * {"category": "Physics"}
     *
     * Update title and category (keep author):
     * PATCH /api/books/1
     * {"title": "New Title", "category": "Physics"}
     *
     * Update user profile (only some fields):
     * PATCH /api/users/123
     * {"email": "newemail@example.com", "phone": "555-1234"}
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Partial update: Only provided fields are modified
     * • Preserves data: Unspecified fields keep existing values
     * • Use Map<String, String> for flexible updates
     * • Return 200 OK with updated resource
     * • More efficient than PUT for small changes
     * • Better client experience (don't need all data)
     * • RFC 7386 defines JSON Merge Patch
     * • RFC 6902 defines JSON Patch (more complex)
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 1.7. HTTP DELETE METHOD - REMOVE RESOURCES
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ HTTP DELETE METHOD COMPLETE EXPLANATION                                             │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS DELETE?
     * ───────────────
     * DELETE is an HTTP method used to REMOVE/DELETE resources from the server.
     * It permanently removes data from the system.
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. Idempotent: Multiple requests produce same result
     * 2. Resource removal: Permanently deletes resource
     * 3. Resource ID: Requires identifier in URL path
     * 4. No request body: Data passed through URL only
     * 5. NOT safe: Modifies/removes server state
     * 6. Destructive: Cannot be undone (unless backup exists)
     * 7. Status code: Usually 204 No Content or 200 OK
     * 8. Irreversible: Permanent deletion (consider soft delete)
     *
     * IDEMPOTENCY FOR DELETE:
     * ──────────────────────
     * DELETE is idempotent: Multiple calls = same result
     *
     * Example:
     * DELETE /api/books/1
     * Request 1: Deletes book -> Result: Book removed
     * Request 2: Deletes same book -> Result: Already deleted (same state)
     * Request 3: Deletes same book -> Result: Already deleted (same state)
     *
     * No matter how many times you send the same DELETE request,
     * the final state is always: resource deleted.
     *
     * PERMANENT DELETE vs SOFT DELETE:
     * ────────────────────────────────
     * Hard Delete: Permanently remove from database
     *             Cannot be recovered
     *             Example: DELETE /api/books/1
     *
     * Soft Delete: Mark as deleted (not actually removed)
     *             Can be recovered/undeleted
     *             Example: UPDATE books SET deleted = true WHERE id = 1
     *
     * EXAMPLES:
     * ────────
     */

    // Example 1: Simple DELETE by ID
    // @DeleteMapping("/api/books/{id}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    // public void deleteBook(@PathVariable String id) {
    //     books.removeIf(b -> b.getId().equals(id));
    // }
    //
    // URL: DELETE http://localhost:8080/api/books/123
    // Response: 204 No Content (no body)

    // Example 2: DELETE with response body
    // @DeleteMapping("/api/books/{id}")
    // public ResponseEntity<Map<String, String>> deleteBook(@PathVariable String id) {
    //     Book book = findBook(id);
    //     if (book != null) {
    //         books.remove(book);
    //         return ResponseEntity.ok(Map.of("message", "Deleted"));
    //     }
    //     return ResponseEntity.notFound().build();
    // }

    // Example 3: DELETE multiple items
    // @DeleteMapping("/api/books/batch")
    // public ResponseEntity<Map<String, Object>> deleteMultiple(@RequestBody List<String> ids) {
    //     int deleted = 0;
    //     for (String id : ids) {
    //         if (books.removeIf(b -> b.getId().equals(id))) {
    //             deleted++;
    //         }
    //     }
    //     return ResponseEntity.ok(Map.of("deleted", deleted));
    // }

    // Example 4: Soft DELETE (mark as deleted)
    // @DeleteMapping("/api/books/{id}")
    // public ResponseEntity<String> softDeleteBook(@PathVariable String id) {
    //     Book book = findBook(id);
    //     if (book != null) {
    //         book.setDeleted(true);  // Mark as deleted, don't remove
    //         return ResponseEntity.ok("Book marked as deleted");
    //     }
    //     return ResponseEntity.notFound().build();
    // }

    /**
     * WHEN TO USE DELETE:
     * ───────────────────
     * ✓ Remove resource by ID
     * ✓ Delete user account
     * ✓ Remove data no longer needed
     * ✓ Cleanup operations
     * ✓ Cascade delete related items
     * ✓ Data cleanup scripts
     * ✗ NOT for archiving (use soft delete)
     * ✗ NOT for reversible operations
     * ✗ NOT for temporary deactivation
     *
     * ADVANTAGES OF DELETE:
     * ──────────────────────
     * ✓ Idempotent: Safe to retry
     * ✓ Clear intent: Obviously removes resource
     * ✓ RESTful: Standard for removal
     * ✓ Simple: No request body needed
     * ✓ Storage: Frees up database space
     * ✓ Privacy: Complete data removal
     *
     * DISADVANTAGES OF DELETE:
     * ────────────────────────
     * ✗ Irreversible: Cannot recover deleted data
     * ✗ Risk of data loss: Permanent removal
     * ✗ Audit issues: Hard to maintain history
     * ✗ Referential integrity: May break relationships
     * ✗ Compliance: May violate data retention rules
     * ✗ User error: Accidental deletion costly
     *
     * HTTP STATUS CODES FOR DELETE:
     * ──────────────────────────────
     * 200 OK              - Successfully deleted, returns confirmation
     * 204 No Content      - Successfully deleted, no body (preferred)
     * 202 Accepted        - Deletion queued for async processing
     * 400 Bad Request     - Invalid request
     * 404 Not Found       - Resource not found
     * 409 Conflict        - Cannot delete (dependent resources exist)
     * 500 Server Error    - Internal error
     *
     * HARD DELETE vs SOFT DELETE vs ARCHIVE:
     * ───────────────────────────────────────
     * Hard Delete:
     *   • Permanently removes from database
     *   • Cannot be recovered
     *   • Used for testing, cleanup
     *   • Example: DELETE FROM books WHERE id = 1
     *   • Risk: Data loss
     *
     * Soft Delete:
     *   • Marks as deleted (added 'deleted' flag)
     *   • Can be recovered
     *   • Better for production
     *   • Example: UPDATE books SET deleted = true WHERE id = 1
     *   • Advantage: Data recovery possible
     *
     * Archive:
     *   • Moves to separate archive table
     *   • Keeps historical data
     *   • Can search archive
     *   • Example: INSERT INTO books_archive SELECT * FROM books WHERE id = 1
     *   • Best for compliance
     *
     * REAL-WORLD EXAMPLES:
     * ────────────────────
     * Delete book by ID:
     * DELETE /api/books/123
     * Response: 204 No Content
     *
     * Delete user account:
     * DELETE /api/users/456
     * Response: 204 No Content or 200 OK with confirmation
     *
     * Delete multiple items:
     * DELETE /api/books/batch
     * Body: {"ids": [1, 2, 3]}
     * Response: 200 OK with count of deleted items
     *
     * Delete by category:
     * DELETE /api/books/category/Science
     * Response: 200 OK with deleted count
     *
     * IMPORTANT NOTES:
     * ────────────────
     * • Idempotent: Multiple DELETEs = same result (important!)
     * • Usually returns 204 No Content (empty body)
     * • Or 200 OK with deletion confirmation
     * • Use @PathVariable to identify resource
     * • Consider soft delete for production systems
     * • Implement proper authorization (admin-only)
     * • Log deletions for audit trail
     * • Handle cascading deletes carefully
     * • Consider backup strategy
     * • Implement undo functionality where possible
     * • For sensitive data: consider encryption before deletion
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 6. COMPREHENSIVE HTTP METHODS COMPARISON
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌──────────────────────────────────────────────────────────────────────────────────────┐
     * │                  ALL HTTP METHODS COMPLETE COMPARISON TABLE                          │
     * └──────────────────────────────────────────────────────────────────────────────────────┘
     *
     * HTTP Method │ Purpose          │ Idempotent │ Safe │ Request Body │ Cacheable
     * ─────────────────────────────────────────────────────────────────────────────
     * GET         │ Retrieve data    │ YES        │ YES  │ NO           │ YES
     * POST        │ Create resource  │ NO         │ NO   │ YES          │ NO
     * PUT         │ Full update      │ YES        │ NO   │ YES          │ NO
     * PATCH       │ Partial update   │ NO         │ NO   │ YES          │ NO
     * DELETE      │ Remove resource  │ YES        │ NO   │ NO           │ NO
     * HEAD        │ Like GET (no body)│ YES       │ YES  │ NO           │ YES
     * OPTIONS     │ Get methods      │ YES        │ YES  │ NO           │ YES
     *
     * IDEMPOTENT EXPLANATION:
     * ───────────────────────
     * GET:    ✓ Multiple requests return same data
     * POST:   ✗ Multiple requests create multiple resources
     * PUT:    ✓ Multiple requests update to same state
     * PATCH:  ✗ May not be idempotent (depends on implementation)
     * DELETE: ✓ Multiple requests result in same state (deleted)
     *
     * SAFE EXPLANATION:
     * ──────────────────
     * Safe = Does not modify server state
     * GET:     ✓ Read-only, no side effects
     * POST:    ✗ Creates new data
     * PUT:     ✗ Modifies existing data
     * PATCH:   ✗ Modifies existing data
     * DELETE:  ✗ Removes data
     *
     * USE CASES:
     * ──────────
     * GET /api/books                      → Retrieve all books
     * GET /api/books/123                  → Retrieve specific book
     * POST /api/books                     → Create new book
     * PUT /api/books/123                  → Replace entire book
     * PATCH /api/books/123                → Update specific field
     * DELETE /api/books/123               → Delete book
     *
     * STATUS CODE GUIDE:
     * ──────────────────
     * 2XX Success:
     *     200 OK              → Request successful
     *     201 Created         → Resource created (POST)
     *     204 No Content      → Successful with no response body
     *     202 Accepted        → Request accepted for processing
     *
     * 4XX Client Error:
     *     400 Bad Request     → Invalid request
     *     401 Unauthorized    → Authentication required
     *     403 Forbidden       → Access denied
     *     404 Not Found       → Resource not found
     *     409 Conflict        → Request conflicts with state
     *
     * 5XX Server Error:
     *     500 Internal Error  → Server error
     *     503 Unavailable     → Service unavailable
     *
     * DECISION FLOWCHART:
     * ───────────────────
     * Want to retrieve data?
     *   → YES: Use GET
     *   → NO: Continue
     *
     * Want to create new resource?
     *   → YES: Use POST
     *   → NO: Continue
     *
     * Want to update resource?
     *   → Full update (all fields): Use PUT
     *   → Partial update (some fields): Use PATCH
     *   → NO: Continue
     *
     * Want to delete resource?
     *   → YES: Use DELETE
     *   → NO: Invalid operation
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 2. HTTP POST METHOD - CREATE/SEND DATA
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ HTTP POST METHOD COMPLETE EXPLANATION                                               │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS POST?
     * ─────────────
     * POST is an HTTP method used to SEND/CREATE data on the server.
     * It typically creates new resources or submits data for processing.
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. NOT Safe Method: Can modify server data
     * 2. NOT Idempotent: Multiple requests may produce different results
     * 3. Not Cacheable: Responses typically not cached (by default)
     * 4. Has Request Body: Data sent in request body (JSON, XML, form data, etc.)
     * 5. Hidden Parameters: Parameters hidden in request body (more secure)
     * 6. Unlimited Data Size: Can send large amounts of data
     * 7. Not Bookmarkable: URLs cannot be easily bookmarked
     * 8. Server Resource Intensive: Each request processed separately
     *
     * EXAMPLES:
     * ────────
     */

    // Example 1: Simple POST - Create a new book
    // @PostMapping("/api/books/create")
    // public Book createBook(@RequestBody Book book) {
    //     // Validate and save the book
    //     books.add(book);
    //     return book;
    // }
    //
    // URL: http://localhost:8080/api/books/create
    // Request Body (JSON):
    // {
    //     "title": "New Book Title",
    //     "author": "John Doe",
    //     "category": "Fiction"
    // }
    // Response: Created book object

    // Example 2: POST - Create multiple books
    // @PostMapping("/api/books/createMultiple")
    // public List<Book> createMultipleBooks(@RequestBody List<Book> bookList) {
    //     // Validate and save multiple books
    //     books.addAll(bookList);
    //     return bookList;
    // }
    //
    // URL: http://localhost:8080/api/books/createMultiple
    // Request Body (JSON Array):
    // [
    //     {
    //         "title": "Book 1",
    //         "author": "Author 1",
    //         "category": "Science"
    //     },
    //     {
    //         "title": "Book 2",
    //         "author": "Author 2",
    //         "category": "Math"
    //     }
    // ]
    // Response: List of created books

    /**
     * WHEN TO USE POST:
     * ────────────────
     * ✓ Create new resources
     * ✓ Submit form data
     * ✓ Send sensitive information (passwords, tokens, etc.)
     * ✓ Upload files
     * ✓ Send large amounts of data
     * ✓ Create nested/complex objects
     * ✓ When data should NOT be cached
     * ✓ When data modification is required
     *
     * ADVANTAGES OF POST:
     * ───────────────────
     * ✓ No data size limitation (can send large payloads)
     * ✓ Parameters hidden in request body (more secure for sensitive data)
     * ✓ Can send complex objects (JSON, nested structures)
     * ✓ Can include request body
     * ✓ Better for form submissions
     * ✓ Multiple large file uploads supported
     * ✓ Data not visible in browser history
     * ✓ Follows REST convention for create operations
     * ✓ Proper semantics for resource creation
     *
     * DISADVANTAGES OF POST:
     * ──────────────────────
     * ✗ Cannot be bookmarked
     * ✗ Responses not cached by default
     * ✗ More complex to implement
     * ✗ Not idempotent (multiple requests = multiple resources)
     * ✗ More server resources required
     * ✗ Browser back button may cause "Confirm Resubmission" dialog
     * ✗ Cannot be used for simple data retrieval
     * ✗ Not suitable for read-only operations
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 3. @PathVariable - URL PATH PARAMETERS
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @PathVariable COMPLETE EXPLANATION                                                  │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @PathVariable?
     * ──────────────────────
     * @PathVariable is a Spring annotation that extracts values from the URL path.
     * It maps URL segments to method parameters.
     *
     * SYNTAX:
     * ───────
     * @GetMapping("/api/books/{id}")
     * public Book getBook(@PathVariable String id) {
     *     // id will contain the value from URL
     * }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. Part of URL path: Value is part of the URL itself
     * 2. Mandatory by default: Must be provided in URL
     * 3. Single value: One value per path variable
     * 4. Type conversion: Automatically converts to specified type (String, int, Long, etc.)
     * 5. REST-friendly: Standard for RESTful APIs
     * 6. URL encoded: Values are URL encoded
     * 7. Visible in URL: Part of the URL (visible in browser address bar)
     *
     * EXAMPLES:
     * ────────
     */

    // Example 1: Single @PathVariable
    // @GetMapping("/api/books/{bookId}")
    // public Book getBook(@PathVariable String bookId) {
    //     // Find book with bookId
    // }
    // URL: http://localhost:8080/api/books/123
    // pathVariable: bookId = "123"

    // Example 2: Multiple @PathVariable
    // @GetMapping("/api/library/{libraryId}/books/{bookId}")
    // public Book getBook(
    //     @PathVariable String libraryId,
    //     @PathVariable String bookId
    // ) {
    //     // Find book by libraryId and bookId
    // }
    // URL: http://localhost:8080/api/library/456/books/123
    // pathVariables: libraryId = "456", bookId = "123"

    // Example 3: @PathVariable with type conversion
    // @GetMapping("/api/books/{bookId}")
    // public Book getBook(@PathVariable Long bookId) {
    //     // bookId is automatically converted to Long
    // }
    // URL: http://localhost:8080/api/books/789
    // pathVariable: bookId = 789L (Long type)

    // Example 4: @PathVariable with custom name
    // @GetMapping("/api/books/{id}")
    // public Book getBook(@PathVariable("id") String bookIdentifier) {
    //     // bookIdentifier receives value from {id} placeholder
    // }
    // URL: http://localhost:8080/api/books/abc123
    // pathVariable: bookIdentifier = "abc123"

    /**
     * WHEN TO USE @PathVariable:
     * ───────────────────────────
     * ✓ Identify specific resources (GET /api/books/123)
     * ✓ Hierarchical resource access
     * ✓ RESTful API design for resource identification
     * ✓ When value is REQUIRED (mandatory)
     * ✓ When value is essential to identify the resource
     * ✓ For IDs, names, or unique identifiers
     * ✓ Nested resource access
     *
     * ADVANTAGES OF @PathVariable:
     * ──────────────────────────────
     * ✓ Clean, RESTful URL design
     * ✓ Mandatory parameters (required by default)
     * ✓ Clear resource identification
     * ✓ URL is self-descriptive
     * ✓ Better for hierarchical resources
     * ✓ Easier to read and understand
     * ✓ Standard REST convention
     * ✓ Works well with caching
     *
     * DISADVANTAGES OF @PathVariable:
     * ─────────────────────────────────
     * ✗ Limited to simple values (usually IDs/names)
     * ✗ Cannot be optional (must be in URL)
     * ✗ Cannot send complex objects
     * ✗ Cannot send multiple values for same parameter
     * ✗ Clutters URL with many parameters
     * ✗ Not ideal for many optional filters
     * ✗ URL length limitations
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 4. @RequestParam - QUERY STRING PARAMETERS
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌─────────────────────────────────────────────────────────────────────────────────────┐
     * │ @RequestParam COMPLETE EXPLANATION                                                  │
     * └─────────────────────────────────────────────────────────────────────────────────────┘
     *
     * WHAT IS @RequestParam?
     * ──────────────────────
     * @RequestParam is a Spring annotation that extracts query string parameters from the URL.
     * It maps query parameters (?key=value) to method parameters.
     *
     * SYNTAX:
     * ───────
     * @GetMapping("/api/books")
     * public List<Book> searchBooks(@RequestParam String category) {
     *     // category will contain the value from query string
     * }
     *
     * KEY CHARACTERISTICS:
     * ───────────────────
     * 1. Part of query string: Value follows ? in URL
     * 2. Optional by default: Can be made required explicitly
     * 3. Multiple values: Can have same parameter multiple times
     * 4. Type conversion: Automatically converts to specified type
     * 5. Default values: Can provide default if not supplied
     * 6. Visible in URL: Part of query string (visible in URL)
     * 7. Flexible: Good for optional filters and sorting
     *
     * EXAMPLES:
     * ────────
     */

    // Example 1: Single @RequestParam (required)
    // @GetMapping("/api/books")
    // public List<Book> getBooks(@RequestParam String category) {
    //     // category is REQUIRED
    // }
    // URL: http://localhost:8080/api/books?category=Science
    // requestParam: category = "Science"
    // URL without param: ERROR (required parameter missing)

    // Example 2: Single @RequestParam (optional)
    // @GetMapping("/api/books")
    // public List<Book> getBooks(@RequestParam(required = false) String category) {
    //     // category is OPTIONAL
    // }
    // URL: http://localhost:8080/api/books?category=Science
    // URL: http://localhost:8080/api/books (category will be null)

    // Example 3: @RequestParam with default value
    // @GetMapping("/api/books")
    // public List<Book> getBooks(
    //     @RequestParam(defaultValue = "Science") String category,
    //     @RequestParam(defaultValue = "10") int pageSize
    // ) {
    //     // If not provided, uses default values
    // }
    // URL: http://localhost:8080/api/books
    // requestParams: category = "Science" (default), pageSize = 10 (default)
    // URL: http://localhost:8080/api/books?category=Math&pageSize=20
    // requestParams: category = "Math", pageSize = 20

    // Example 4: Multiple @RequestParam
    // @GetMapping("/api/books")
    // public List<Book> searchBooks(
    //     @RequestParam(required = false) String category,
    //     @RequestParam(required = false) String author,
    //     @RequestParam(required = false) String title,
    //     @RequestParam(defaultValue = "1") int page,
    //     @RequestParam(defaultValue = "10") int size
    // ) {
    //     // Multiple optional filters
    // }
    // URL: http://localhost:8080/api/books?category=Science&author=Einstein&page=2&size=25

    // Example 5: Multiple values for same parameter
    // @GetMapping("/api/books")
    // public List<Book> getBooks(@RequestParam List<String> categories) {
    //     // categories list contains multiple values
    // }
    // URL: http://localhost:8080/api/books?categories=Science&categories=Math&categories=Physics
    // requestParam: categories = ["Science", "Math", "Physics"]

    /**
     * WHEN TO USE @RequestParam:
     * ──────────────────────────
     * ✓ Filter results (category, author, etc.)
     * ✓ Sorting and pagination
     * ✓ Search queries
     * ✓ Optional parameters
     * ✓ Multiple optional filters
     * ✓ When values are not required for resource identification
     * ✓ Configuration parameters (page, size, sort order)
     * ✓ Multiple values for same parameter
     *
     * ADVANTAGES OF @RequestParam:
     * ──────────────────────────────
     * ✓ Optional by default (can be made required)
     * ✓ Can have default values
     * ✓ Multiple parameters easily managed
     * ✓ Good for filtering and sorting
     * ✓ Flexible and optional nature
     * ✓ Can have multiple values for same parameter
     * ✓ Better for complex search criteria
     * ✓ Caching friendly (same endpoint, different params)
     * ✓ Standard for query parameters
     *
     * DISADVANTAGES OF @RequestParam:
     * ─────────────────────────────────
     * ✗ URL becomes cluttered with many parameters
     * ✗ Less RESTful than @PathVariable for identification
     * ✗ Parameters visible in URL (less secure for sensitive data)
     * ✗ URL length limitations with many parameters
     * ✗ Cannot send complex objects directly
     * ✗ Not ideal for mandatory resource identification
     * ✗ Can make URLs long and hard to read
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 5. COMPARISON TABLE: @PathVariable vs @RequestParam
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌──────────────────────────────────────────────────────────────────────────────────────┐
     * │                    @PathVariable vs @RequestParam COMPARISON                         │
     * ├──────────────────────────┬────────────────────────┬──────────────────────────────────┤
     * │ Feature                  │ @PathVariable          │ @RequestParam                    │
     * ├──────────────────────────┼────────────────────────┼──────────────────────────────────┤
     * │ Location                 │ URL path               │ Query string (?key=value)        │
     * │ Mandatory by default      │ YES (required)         │ NO (optional)                    │
     * │ Can be optional           │ NO                     │ YES                              │
     * │ Can have default value    │ NO                     │ YES                              │
     * │ Multiple values           │ NO (one per position)  │ YES (List support)              │
     * │ Type conversion           │ Supported              │ Supported                        │
     * │ REST convention           │ For resource ID        │ For filters/options              │
     * │ URL example               │ /api/books/123         │ /api/books?size=10&page=1       │
     * │ Use case                  │ Identify resources     │ Filter/sort/configure           │
     * │ URL readability           │ Very clean             │ Can get cluttered                │
     * │ Security                  │ Visible in URL         │ Visible in URL                   │
     * │ Complexity                │ Simple values          │ Can be complex                   │
     * │ Caching                   │ Different URLs cached  │ Same URL, diff params            │
     * └──────────────────────────┴────────────────────────┴──────────────────────────────────┘
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 6. PRACTICAL EXAMPLES: WHEN TO USE WHAT
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌──────────────────────────────────────────────────────────────────────────────────────┐
     * │                    DECISION GUIDE: WHICH METHOD/ANNOTATION TO USE                    │
     * └──────────────────────────────────────────────────────────────────────────────────────┘
     *
     * SCENARIO 1: Get a specific book by ID
     * ────────────────────────────────────────
     * Use: GET with @PathVariable
     *
     * @GetMapping("/api/books/{bookId}")
     * public Book getBook(@PathVariable String bookId) { }
     *
     * URL: http://localhost:8080/api/books/123
     *
     * Why: The bookId is essential to identify the resource.
     *      RESTful design. Must be provided. Mandatory parameter.
     *
     *
     * SCENARIO 2: Get all books with optional filters
     * ─────────────────────────────────────────────────
     * Use: GET with @RequestParam
     *
     * @GetMapping("/api/books")
     * public List<Book> getBooks(
     *     @RequestParam(required = false) String category,
     *     @RequestParam(defaultValue = "10") int pageSize
     * ) { }
     *
     * URL: http://localhost:8080/api/books?category=Science&pageSize=20
     * URL: http://localhost:8080/api/books (uses defaults)
     *
     * Why: Category is optional (not required). Multiple filters.
     *      Default values make sense. User may not provide all params.
     *
     *
     * SCENARIO 3: Create a new book
     * ──────────────────────────────
     * Use: POST with @RequestBody
     *
     * @PostMapping("/api/books")
     * public Book createBook(@RequestBody Book book) { }
     *
     * URL: http://localhost:8080/api/books
     * Request Body: {"title": "...", "author": "...", "category": "..."}
     *
     * Why: Creating new resource. POST semantics. Can send complex object.
     *      No params in URL. Sensitive data in body.
     *
     *
     * SCENARIO 4: Get books by category from a specific library
     * ────────────────────────────────────────────────────────────
     * Use: GET with @PathVariable (for library) + @RequestParam (for category)
     *
     * @GetMapping("/api/library/{libraryId}/books")
     * public List<Book> getBooks(
     *     @PathVariable String libraryId,
     *     @RequestParam(required = false) String category,
     *     @RequestParam(defaultValue = "1") int page
     * ) { }
     *
     * URL: http://localhost:8080/api/library/456/books?category=Science&page=2
     *
     * Why: libraryId is essential (resource hierarchy), category is optional filter.
     *      Mix of both for clear semantics.
     *
     *
     * SCENARIO 5: Update user password
     * ──────────────────────────────────
     * Use: PUT/POST with @RequestBody
     *
     * @PostMapping("/api/users/{userId}/changePassword")
     * public Map<String, String> changePassword(
     *     @PathVariable String userId,
     *     @RequestBody PasswordChangeRequest request
     * ) { }
     *
     * URL: http://localhost:8080/api/users/123/changePassword
     * Request Body: {"oldPassword": "...", "newPassword": "..."}
     *
     * Why: userId identifies the user (path). Passwords in body (secure).
     *      Sensitive data should NOT be in URL.
     *
     *
     * SCENARIO 6: Search books with multiple criteria
     * ────────────────────────────────────────────────
     * Use: GET with multiple @RequestParam
     *
     * @GetMapping("/api/books/search")
     * public List<Book> searchBooks(
     *     @RequestParam(required = false) String title,
     *     @RequestParam(required = false) String author,
     *     @RequestParam(required = false) String category,
     *     @RequestParam(defaultValue = "relevance") String sortBy,
     *     @RequestParam(defaultValue = "1") int page,
     *     @RequestParam(defaultValue = "20") int size
     * ) { }
     *
     * URL: http://localhost:8080/api/books/search?title=Great&author=Fitzgerald&sortBy=year&size=50
     *
     * Why: Multiple optional filters. Not identifying specific resource.
     *      All are optional. Search functionality.
     *
     *
     * SCENARIO 7: Upload multiple files
     * ──────────────────────────────────
     * Use: POST with @RequestParam (for file) or @RequestBody
     *
     * @PostMapping("/api/upload")
     * public Map<String, String> uploadFiles(@RequestParam("files") List<MultipartFile> files) { }
     *
     * URL: http://localhost:8080/api/upload
     * Form Data: files = [file1.pdf, file2.pdf, file3.pdf]
     *
     * Why: POST for data creation. Form data with files.
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 7. BEST PRACTICES & RECOMMENDATIONS
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌──────────────────────────────────────────────────────────────────────────────────────┐
     * │                          BEST PRACTICES & RECOMMENDATIONS                            │
     * └──────────────────────────────────────────────────────────────────────────────────────┘
     *
     * 1. USE CORRECT HTTP METHOD:
     *    ────────────────────────
     *    ✓ GET for reading data (safe, idempotent)
     *    ✓ POST for creating new resources
     *    ✓ PUT for full updates
     *    ✓ PATCH for partial updates
     *    ✓ DELETE for removing resources
     *    ✗ Don't use GET for modifying data (security issue)
     *    ✗ Don't use POST for simple data retrieval
     *
     *
     * 2. USE @PathVariable FOR RESOURCE IDENTIFICATION:
     *    ──────────────────────────────────────────────
     *    ✓ /api/books/123 - Get specific book
     *    ✓ /api/library/456/books - Books from library
     *    ✗ /api/books?bookId=123 - Bad practice (should be in path)
     *
     *
     * 3. USE @RequestParam FOR FILTERING/CONFIGURATION:
     *    ───────────────────────────────────────────────
     *    ✓ /api/books?category=Science&page=2 - Filters
     *    ✓ /api/search?query=java&sort=date - Search with options
     *    ✗ /api/books/Science - Category as path variable
     *
     *
     * 4. NEVER PUT SENSITIVE DATA IN URL:
     *    ──────────────────────────────────
     *    ✓ POST /api/users/login
     *      Body: {"username": "john", "password": "***"}
     *    ✗ GET /api/users/login?username=john&password=*** (NEVER!)
     *
     *
     * 5. USE @RequestBody FOR COMPLEX DATA:
     *    ────────────────────────────────────
     *    ✓ POST /api/books
     *      Body: {"title": "...", "author": "...", "category": "..."}
     *    ✗ POST /api/books?title=...&author=...&category=... (Bad for complex)
     *
     *
     * 6. PROVIDE DEFAULT VALUES FOR OPTIONAL PARAMS:
     *    ──────────────────────────────────────────
     *    ✓ @RequestParam(defaultValue = "10") int pageSize
     *    ✓ @RequestParam(defaultValue = "asc") String order
     *    ✗ @RequestParam int pageSize (might be null)
     *
     *
     * 7. BE CONSISTENT WITH NAMING:
     *    ──────────────────────────
     *    ✓ /api/v1/books/{id} (consistent version, naming)
     *    ✓ /api/users/{userId}/posts/{postId}
     *    ✗ /api/books/{book_id} (mix of camelCase and snake_case)
     *
     *
     * 8. USE MEANINGFUL PARAMETER NAMES:
     *    ────────────────────────────────
     *    ✓ /api/books?category=Science&sortBy=title
     *    ✗ /api/books?c=Science&s=title (too cryptic)
     *
     *
     * 9. DOCUMENT YOUR ENDPOINTS:
     *    ──────────────────────────
     *    ✓ Use @GetMapping with descriptive paths
     *    ✓ Include JavaDoc comments
     *    ✓ Use @RequestParam descriptions
     *    ✗ Vague or unclear endpoint names
     *
     *
     * 10. VALIDATE INPUT:
     *     ────────────────
     *     ✓ Check if required params are provided
     *     ✓ Validate data types and formats
     *     ✓ Throw appropriate exceptions
     *     ✗ Assume all input is valid
     */


    // ═══════════════════════════════════════════════════════════════════════════════════════
    // 8. COMMON MISTAKES TO AVOID
    // ═══════════════════════════════════════════════════════════════════════════════════════

    /**
     * ┌──────────────────────────────────────────────────────────────────────────────────────┐
     * │                            COMMON MISTAKES TO AVOID                                  │
     * └──────────────────────────────────────────────────────────────────────────────────────┘
     *
     * MISTAKE 1: Ambiguous URL Mappings
     * ──────────────────────────────────
     * ✗ WRONG:
     *   @GetMapping("/api/books/{title}")
     *   public Book getByTitle(@PathVariable String title) { }
     *
     *   @GetMapping("/api/books/{category}")
     *   public List<Book> getByCategory(@PathVariable String category) { }
     *
     *   // ERROR: Spring can't distinguish /api/books/science
     *
     * ✓ CORRECT:
     *   @GetMapping("/api/books/title/{title}")
     *   public Book getByTitle(@PathVariable String title) { }
     *
     *   @GetMapping("/api/books/category/{category}")
     *   public List<Book> getByCategory(@PathVariable String category) { }
     *
     *
     * MISTAKE 2: Using GET for data modification
     * ────────────────────────────────────────────
     * ✗ WRONG:
     *   @GetMapping("/api/books/delete/{id}")
     *   public boolean deleteBook(@PathVariable String id) { }
     *   // Security issue! Anyone can click link and delete.
     *
     * ✓ CORRECT:
     *   @DeleteMapping("/api/books/{id}")
     *   public boolean deleteBook(@PathVariable String id) { }
     *
     *
     * MISTAKE 3: Sending sensitive data in URL
     * ──────────────────────────────────────────
     * ✗ WRONG:
     *   GET /api/transfer?fromAccount=123&toAccount=456&amount=1000
     *
     * ✓ CORRECT:
     *   POST /api/transfer
     *   Body: {"fromAccount": 123, "toAccount": 456, "amount": 1000}
     *
     *
     * MISTAKE 4: Not handling null or missing parameters
     * ───────────────────────────────────────────────────
     * ✗ WRONG:
     *   @GetMapping("/api/books")
     *   public List<Book> getBooks(@RequestParam String category) {
     *       if (category.equalsIgnoreCase("Science")) { } // NPE if missing!
     *   }
     *
     * ✓ CORRECT:
     *   @GetMapping("/api/books")
     *   public List<Book> getBooks(@RequestParam(required = false) String category) {
     *       if (category != null && category.equalsIgnoreCase("Science")) { }
     *   }
     *
     *
     * MISTAKE 5: Using @PathVariable for optional values
     * ───────────────────────────────────────────────────
     * ✗ WRONG:
     *   @GetMapping("/api/books/{category}")
     *   // Error if category not provided
     *   public List<Book> getBooks(@PathVariable(required = false) String category) { }
     *   // URL must be /api/books/something - can't be /api/books
     *
     * ✓ CORRECT:
     *   @GetMapping("/api/books")
     *   public List<Book> getBooks(@RequestParam(required = false) String category) { }
     *   // Can access /api/books or /api/books?category=Science
     *
     *
     * MISTAKE 6: Type mismatch in @PathVariable
     * ──────────────────────────────────────────
     * ✗ WRONG:
     *   @GetMapping("/api/books/{id}")
     *   public Book getBook(@PathVariable Long id) {
     *       // If id is not numeric: /api/books/abc -> ERROR
     *   }
     *
     * ✓ CORRECT:
     *   @GetMapping("/api/books/{id}")
     *   public Book getBook(@PathVariable String id) {
     *       // Then convert if needed or handle gracefully
     *   }
     *
     *
     * MISTAKE 7: Not using appropriate HTTP status codes
     * ───────────────────────────────────────────────────
     * ✗ WRONG:
     *   @PostMapping("/api/books")
     *   public Book createBook(@RequestBody Book book) {
     *       return book; // Returns 200 OK for creation
     *   }
     *
     * ✓ CORRECT:
     *   @PostMapping("/api/books")
     *   @ResponseStatus(HttpStatus.CREATED)
     *   public Book createBook(@RequestBody Book book) {
     *       return book; // Returns 201 Created
     *   }
     */

    /**
     * ╔════════════════════════════════════════════════════════════════════════════════════════╗
     * ║                                   SUMMARY TABLE                                        ║
     * ╠════════════════════════════════════════════════════════════════════════════════════════╣
     * ║                                                                                        ║
     * ║  HTTP METHOD │ PURPOSE           │ WHEN TO USE        │ IDEMPOTENT │ SAFE             ║
     * ║  ───────────────────────────────────────────────────────────────────────────────────  ║
     * ║  GET         │ Retrieve data     │ Read operations    │ YES        │ YES              ║
     * ║  POST        │ Create data       │ Create operations  │ NO         │ NO               ║
     * ║  PUT         │ Update data       │ Full update        │ YES        │ NO               ║
     * ║  PATCH       │ Partial update    │ Partial update     │ NO         │ NO               ║
     * ║  DELETE      │ Delete data       │ Remove resources   │ YES        │ NO               ║
     * ║                                                                                        ║
     * ║  ANNOTATION  │ LOCATION          │ MANDATORY │ DEFAULT │ MULTIPLE VALUES            ║
     * ║  ──────────────────────────────────────────────────────────────────────────────────   ║
     * ║  PathVariable│ URL path          │ YES       │ NO      │ NO                         ║
     * ║  RequestParam│ Query string      │ NO        │ YES     │ YES (List)                 ║
     * ║                                                                                        ║
     * ╚════════════════════════════════════════════════════════════════════════════════════════╝
     */

}
