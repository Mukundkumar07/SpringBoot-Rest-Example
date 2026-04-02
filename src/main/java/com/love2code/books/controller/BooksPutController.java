package com.love2code.books.controller;

import com.love2code.books.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * BooksPutController - Handles HTTP PUT requests for Books API
 * 
 * HTTP PUT Method Overview:
 * ────────────────────────
 * PUT is used to UPDATE entire resources on the server.
 * It is an IDEMPOTENT operation (multiple requests = same result).
 * 
 * Key Characteristics:
 * • Replaces entire resource (full update)
 * • Requires resource ID/identifier
 * • Requires @RequestBody with complete updated data
 * • Returns updated resource
 * • Idempotent: Calling multiple times = same result
 * • NOT safe: Modifies server state
 * • Usually returns 200 OK or 204 No Content
 * 
 * PUT vs PATCH:
 * ─────────────
 * PUT:   Replaces entire resource (all fields)
 * PATCH: Partial update (only changed fields)
 * 
 * When to Use:
 * • Update entire book record
 * • Replace all book fields
 * • When you have complete updated data
 * • When partial updates are not needed
 */
@RestController
@RequestMapping("/api/books")
public class BooksPutController {

    private final List<Book> books = new ArrayList<>();

    public BooksPutController() {
        initializeBooks();
    }

    private void initializeBooks() {
        books.addAll(List.of(
                new Book("Title One", "Author One", "Science"),
                new Book("Title Two", "Author Two", "Math"),
                new Book("Title Three", "Author Three", "Science"),
                new Book("Title Four", "Author Four", "Biology")
        ));
    }

    /**
     * UPDATE a book by title (Full Update)
     * 
     * HTTP Method: PUT
     * URL: PUT /api/books/update/{title}
     * 
     * Description:
     * ────────────
     * Updates the entire book record with the provided data.
     * All fields will be replaced with new values.
     * 
     * Path Variable:
     * ──────────────
     * @PathVariable String title - The title of the book to update
     * 
     * Request Body (JSON):
     * ────────────────────
     * {
     *     "title": "Updated Title",
     *     "author": "Updated Author",
     *     "category": "Updated Category"
     * }
     * 
     * cURL Example:
     * ──────────────
     * curl -X PUT http://localhost:8080/api/books/update/Title%20One \
     *   -H "Content-Type: application/json" \
     *   -d '{
     *     "title": "New Title One",
     *     "author": "New Author",
     *     "category": "Physics"
     *   }'
     * 
     * Response:
     * ─────────
     * Status: 200 OK
     * Body:
     * {
     *     "title": "New Title One",
     *     "author": "New Author",
     *     "category": "Physics"
     * }
     * 
     * Possible Responses:
     * ───────────────────
     * 200 OK           - Successfully updated book
     * 400 Bad Request  - Invalid request body
     * 404 Not Found    - Book not found
     * 500 Server Error - Internal error
     */
    @PutMapping("/update/{title}")
    public ResponseEntity<Book> updateBook(
            @PathVariable String title,
            @RequestBody Book updatedBook) {
        try {
            // Validate request body
            if (updatedBook == null) {
                return ResponseEntity.badRequest().build();
            }

            // Find book by title
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    // Update all fields (full update)
                    if (updatedBook.getTitle() != null && !updatedBook.getTitle().isEmpty()) {
                        book.setTitle(updatedBook.getTitle());
                    }
                    if (updatedBook.getAuthor() != null && !updatedBook.getAuthor().isEmpty()) {
                        book.setAuthor(updatedBook.getAuthor());
                    }
                    if (updatedBook.getCategory() != null && !updatedBook.getCategory().isEmpty()) {
                        book.setCategory(updatedBook.getCategory());
                    }

                    System.out.println("Book updated successfully: " + title);
                    System.out.println("New title: " + book.getTitle());
                    System.out.println("New author: " + book.getAuthor());
                    System.out.println("New category: " + book.getCategory());

                    // Return 200 OK with updated book
                    return ResponseEntity.ok(book);
                }
            }

            // Book not found
            System.out.println("Book not found for update: " + title);
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            System.err.println("Error updating book: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * UPDATE a book by ID with full object replacement
     * 
     * HTTP Method: PUT
     * URL: PUT /api/books/{id}
     * 
     * Description:
     * ────────────
     * Updates the entire book identified by ID.
     * Complete replacement of all book properties.
     * 
     * Path Variable:
     * ──────────────
     * @PathVariable String id - The ID of the book to update
     * 
     * Request Body (JSON):
     * ────────────────────
     * {
     *     "title": "Completely New Title",
     *     "author": "Completely New Author",
     *     "category": "Completely New Category"
     * }
     * 
     * Idempotency Example:
     * ────────────────────
     * Request 1: PUT /api/books/1 with body X -> Updated
     * Request 2: PUT /api/books/1 with body X -> Same result (idempotent)
     * Request 3: PUT /api/books/1 with body X -> Same result
     * 
     * cURL Example:
     * ──────────────
     * curl -X PUT http://localhost:8080/api/books/1 \
     *   -H "Content-Type: application/json" \
     *   -d '{
     *     "title": "New Title",
     *     "author": "New Author Name",
     *     "category": "New Category"
     *   }'
     * 
     * Response:
     * ─────────
     * Status: 200 OK
     * Body: Updated book JSON object
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBookById(
            @PathVariable String id,
            @RequestBody Book updatedBook) {
        try {
            System.out.println("Updating book with ID: " + id);
            
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(id)) {
                    // Update all fields
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setCategory(updatedBook.getCategory());

                    System.out.println("Book updated successfully");
                    return book;
                }
            }

            System.out.println("Book not found: " + id);
            return null;

        } catch (Exception e) {
            System.err.println("Error in updateBookById: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * UPDATE multiple books at once
     * 
     * HTTP Method: PUT
     * URL: PUT /api/books/batch/update
     * 
     * Description:
     * ────────────
     * Updates multiple books in a single request.
     * Each book in the array is updated completely.
     * 
     * Request Body (JSON Array):
     * ───────────────────────────
     * [
     *     {
     *         "title": "Book 1 Updated Title",
     *         "author": "Book 1 Updated Author",
     *         "category": "Science"
     *     },
     *     {
     *         "title": "Book 2 Updated Title",
     *         "author": "Book 2 Updated Author",
     *         "category": "Math"
     *     }
     * ]
     * 
     * cURL Example:
     * ──────────────
     * curl -X PUT http://localhost:8080/api/books/batch/update \
     *   -H "Content-Type: application/json" \
     *   -d '[
     *     {"title": "Title A", "author": "Author A", "category": "Science"},
     *     {"title": "Title B", "author": "Author B", "category": "Math"}
     *   ]'
     * 
     * Response:
     * ─────────
     * Status: 200 OK
     * Body: Array of updated books
     */
    @PutMapping("/batch/update")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> updateMultipleBooks(@RequestBody List<Book> updatedBooks) {
        try {
            if (updatedBooks == null || updatedBooks.isEmpty()) {
                System.out.println("No books provided for update");
                return new ArrayList<>();
            }

            List<Book> updated = new ArrayList<>();

            // Update each book
            for (Book updatedBook : updatedBooks) {
                for (Book existingBook : books) {
                    if (existingBook.getTitle().equalsIgnoreCase(updatedBook.getTitle())) {
                        existingBook.setAuthor(updatedBook.getAuthor());
                        existingBook.setCategory(updatedBook.getCategory());
                        updated.add(existingBook);
                        System.out.println("Updated: " + updatedBook.getTitle());
                        break;
                    }
                }
            }

            System.out.println("Total books updated: " + updated.size());
            return updated;

        } catch (Exception e) {
            System.err.println("Error in updateMultipleBooks: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * UPDATE book category only (but still use PUT semantics)
     * 
     * HTTP Method: PUT
     * URL: PUT /api/books/{title}/category
     * 
     * Description:
     * ────────────
     * Updates specific field but returns entire book.
     * Demonstrates nested resource update.
     * 
     * Request Body (JSON):
     * ────────────────────
     * {
     *     "category": "New Category"
     * }
     * 
     * cURL Example:
     * ──────────────
     * curl -X PUT http://localhost:8080/api/books/Title%20One/category \
     *   -H "Content-Type: application/json" \
     *   -d '{"category": "Physics"}'
     * 
     * Response:
     * ─────────
     * Status: 200 OK
     * Body: Entire updated book object
     */
    @PutMapping("/{title}/category")
    public ResponseEntity<Book> updateBookCategory(
            @PathVariable String title,
            @RequestBody Book categoryUpdate) {
        try {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    if (categoryUpdate.getCategory() != null && !categoryUpdate.getCategory().isEmpty()) {
                        book.setCategory(categoryUpdate.getCategory());
                        System.out.println("Updated category for: " + title + 
                                         " to: " + categoryUpdate.getCategory());
                        return ResponseEntity.ok(book);
                    }
                }
            }
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            System.err.println("Error updating category: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * GET all books (for testing/reference)
     * 
     * NOTE: Use BooksConntroller for viewing all books or finding specific books
     * This controller focuses only on PUT (UPDATE) operations
     */

}
