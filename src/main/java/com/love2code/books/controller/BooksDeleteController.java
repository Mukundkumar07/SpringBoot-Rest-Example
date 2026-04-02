package com.love2code.books.controller;

import com.love2code.books.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BooksDeleteController - Handles HTTP DELETE requests for Books API
 * 
 * HTTP DELETE Method Overview:
 * ────────────────────────────
 * DELETE is used to REMOVE/DELETE resources from the server.
 * It is an IDEMPOTENT operation (multiple requests = same result).
 * 
 * Key Characteristics:
 * • Removes resource from server
 * • Requires resource ID/identifier
 * • No request body needed
 * • Usually returns 204 No Content (no response body)
 * • Or returns 200 OK with deletion confirmation
 * • Idempotent: Calling multiple times = same result
 * • NOT safe: Modifies/removes server state
 * • Destructive: Permanently deletes data
 * 
 * Important Considerations:
 * • Cannot be undone (unless backup exists)
 * • Consider soft delete (mark as deleted) instead
 * • Implement proper authorization/authentication
 * • Log deletion for audit trail
 * • Consider cascade deletion of related data
 * 
 * When to Use:
 * • Delete book by ID
 * • Remove user account
 * • Delete specific resource
 * • Cleanup operations
 */
@RestController
@RequestMapping("/api/books")
public class BooksDeleteController {

    private final List<Book> books = new ArrayList<>();

    public BooksDeleteController() {
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
     * DELETE a book by title
     * 
     * HTTP Method: DELETE
     * URL: DELETE /api/books/delete/{title}
     * 
     * Description:
     * ────────────
     * Permanently removes a book from the database by title.
     * Returns 204 No Content if successful.
     * 
     * Path Variable:
     * ──────────────
     * @PathVariable String title - The title of the book to delete
     * 
     * cURL Example:
     * ──────────────
     * curl -X DELETE http://localhost:8080/api/books/delete/Title%20One
     * 
     * Response:
     * ─────────
     * Status: 204 No Content (no response body)
     * 
     * Possible Responses:
     * ───────────────────
     * 204 No Content   - Successfully deleted
     * 404 Not Found    - Book not found
     * 500 Server Error - Internal error
     * 
     * Idempotency:
     * ────────────
     * Request 1: DELETE /api/books/delete/Title%20One -> 204 Deleted
     * Request 2: DELETE /api/books/delete/Title%20One -> 204 Deleted (idempotent)
     * Request 3: DELETE /api/books/delete/Title%20One -> 204 Deleted (same result)
     * 
     * Important: Calling delete multiple times produces same result (idempotent)
     */
    @DeleteMapping("/delete/{title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String title) {
        try {
            boolean removed = false;
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                    Book deletedBook = books.remove(i);
                    System.out.println("Book deleted successfully: " + deletedBook.getTitle());
                    System.out.println("Author: " + deletedBook.getAuthor());
                    System.out.println("Category: " + deletedBook.getCategory());
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                System.out.println("Book not found for deletion: " + title);
            }

        } catch (Exception e) {
            System.err.println("Error deleting book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * DELETE a book by title with response body
     * 
     * HTTP Method: DELETE
     * URL: DELETE /api/books/{title}
     * 
     * Description:
     * ────────────
     * Deletes a book and returns response body with status/confirmation.
     * Uses ResponseEntity for fine-grained control.
     * 
     * Path Variable:
     * ──────────────
     * @PathVariable String title - The title of the book to delete
     * 
     * cURL Example:
     * ──────────────
     * curl -X DELETE http://localhost:8080/api/books/Title%20Two
     * 
     * Response on Success:
     * ────────────────────
     * Status: 200 OK
     * Body:
     * {
     *     "message": "Book deleted successfully",
     *     "title": "Title Two",
     *     "author": "Author Two",
     *     "category": "Math"
     * }
     * 
     * Response on Not Found:
     * ──────────────────────
     * Status: 404 Not Found
     * Body: {}
     */
    @DeleteMapping("/{title}")
    public ResponseEntity<Map<String, String>> deleteBookWithResponse(
            @PathVariable String title) {
        try {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                    Book deletedBook = books.remove(i);
                    
                    Map<String, String> response = Map.of(
                            "message", "Book deleted successfully",
                            "title", deletedBook.getTitle(),
                            "author", deletedBook.getAuthor(),
                            "category", deletedBook.getCategory()
                    );

                    System.out.println("Deleted book: " + deletedBook.getTitle());
                    return ResponseEntity.ok(response);
                }
            }

            System.out.println("Book not found: " + title);
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            System.err.println("Error deleting book: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * DELETE multiple books at once
     * 
     * HTTP Method: DELETE
     * URL: DELETE /api/books/batch/delete
     * 
     * Description:
     * ────────────
     * Deletes multiple books in a single request.
     * Accepts a list of book titles to delete.
     * 
     * Request Body (JSON Array):
     * ───────────────────────────
     * {
     *     "titles": ["Title One", "Title Two", "Title Three"]
     * }
     * 
     * cURL Example:
     * ──────────────
     * curl -X DELETE http://localhost:8080/api/books/batch/delete \
     *   -H "Content-Type: application/json" \
     *   -d '{
     *     "titles": ["Title One", "Title Two"]
     *   }'
     * 
     * Response:
     * ─────────
     * Status: 200 OK
     * Body:
     * {
     *     "message": "Batch deletion completed",
     *     "deleted_count": 2,
     *     "deleted_titles": ["Title One", "Title Two"],
     *     "remaining_books": 2
     * }
     */
    @DeleteMapping("/batch/delete")
    public ResponseEntity<Map<String, Object>> deleteMultipleBooks(
            @RequestBody Map<String, List<String>> request) {
        try {
            List<String> titles = request.get("titles");
            if (titles == null || titles.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            List<String> deletedTitles = new ArrayList<>();

            for (String title : titles) {
                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                        Book deletedBook = books.remove(i);
                        deletedTitles.add(deletedBook.getTitle());
                        System.out.println("Deleted: " + deletedBook.getTitle());
                        break;
                    }
                }
            }

            Map<String, Object> response = Map.of(
                    "message", "Batch deletion completed",
                    "deleted_count", deletedTitles.size(),
                    "deleted_titles", deletedTitles,
                    "remaining_books", books.size()
            );

            System.out.println("Total deleted: " + deletedTitles.size());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("Error in batch delete: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * DELETE all books from specific category
     * 
     * HTTP Method: DELETE
     * URL: DELETE /api/books/category/{category}
     * 
     * Description:
     * ────────────
     * Deletes all books belonging to a specific category.
     * Example of conditional deletion.
     * 
     * Path Variable:
     * ──────────────
     * @PathVariable String category - The category to delete
     * 
     * cURL Example:
     * ──────────────
     * curl -X DELETE http://localhost:8080/api/books/category/Science
     * 
     * Response:
     * ─────────
     * Status: 200 OK
     * Body:
     * {
     *     "message": "All books in category deleted",
     *     "category": "Science",
     *     "deleted_count": 2,
     *     "remaining_books": 2
     * }
     */
    @DeleteMapping("/category/{category}")
    public ResponseEntity<Map<String, Object>> deleteByCategory(
            @PathVariable String category) {
        try {
            int deleted = 0;

            for (int i = books.size() - 1; i >= 0; i--) {
                if (books.get(i).getCategory().equalsIgnoreCase(category)) {
                    Book removedBook = books.remove(i);
                    deleted++;
                    System.out.println("Deleted from category: " + removedBook.getTitle());
                }
            }

            Map<String, Object> response = Map.of(
                    "message", "All books in category deleted",
                    "category", category,
                    "deleted_count", deleted,
                    "remaining_books", books.size()
            );

            System.out.println("Total deleted from category " + category + ": " + deleted);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("Error deleting category: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * DELETE all books (DANGEROUS - use with caution!)
     * 
     * HTTP Method: DELETE
     * URL: DELETE /api/books/all
     * 
     * Description:
     * ────────────
     * CAUTION: Deletes ALL books from the system.
     * This is a destructive operation - use only for testing or cleanup.
     * In production, consider implementing authorization checks.
     * 
     * cURL Example:
     * ──────────────
     * curl -X DELETE http://localhost:8080/api/books/all
     * 
     * Response:
     * ─────────
     * Status: 200 OK
     * Body:
     * {
     *     "message": "All books deleted",
     *     "deleted_count": 4,
     *     "remaining_books": 0
     * }
     * 
     * Security Note:
     * ───────────────
     * In production, implement:
     * • Authentication checks
     * • Authorization verification
     * • Admin-only access
     * • Confirmation requirement
     * • Audit logging
     */
    @DeleteMapping("/all")
    public ResponseEntity<Map<String, Object>> deleteAllBooks() {
        try {
            int deleted = books.size();
            System.out.println("WARNING: Deleting all books. Count: " + deleted);
            
            // Log all deleted books
            for (Book book : books) {
                System.out.println("Deleting: " + book.getTitle());
            }
            
            books.clear();

            Map<String, Object> response = Map.of(
                    "message", "All books deleted",
                    "deleted_count", deleted,
                    "remaining_books", books.size()
            );

            System.out.println("All books deleted successfully");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("Error deleting all books: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * SOFT DELETE - Mark book as deleted (not permanent)
     * 
     * HTTP Method: DELETE
     * URL: DELETE /api/books/soft/{title}
     * 
     * Description:
     * ────────────
     * Instead of permanently deleting, marks book as deleted.
     * Allows recovery and maintains data integrity.
     * Better for production systems.
     * 
     * Note: This example uses a flag (in real system, add 'deleted' boolean field)
     * 
     * cURL Example:
     * ──────────────
     * curl -X DELETE http://localhost:8080/api/books/soft/Title%20One
     * 
     * Response:
     * ─────────
     * Status: 200 OK
     * Body:
     * {
     *     "message": "Book marked as deleted (soft delete)",
     *     "title": "Title One",
     *     "status": "deleted"
     * }
     * 
     * Advantages of Soft Delete:
     * ──────────────────────────
     * • Data can be recovered
     * • Maintains referential integrity
     * • Audit trail is preserved
     * • Undo operations possible
     * • Better for compliance/regulations
     */
    @DeleteMapping("/soft/{title}")
    public ResponseEntity<Map<String, String>> softDeleteBook(
            @PathVariable String title) {
        try {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    // In real implementation, add 'deleted' boolean field to Book class
                    // book.setDeleted(true);
                    
                    System.out.println("Soft delete: " + title + " (marked as deleted)");
                    
                    Map<String, String> response = Map.of(
                            "message", "Book marked as deleted (soft delete)",
                            "title", title,
                            "status", "deleted"
                    );
                    
                    return ResponseEntity.ok(response);
                }
            }

            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            System.err.println("Error in soft delete: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * NOTE: Helper GET methods have been moved to BooksConntroller
     * This controller focuses only on DELETE (REMOVAL) operations
     * 
     * For viewing/reading books, use:
     * - BooksConntroller for all GET operations
     * - GET /api/books - all books
     * - GET /api/books/title/{title} - find by title
     * - GET /api/books/category/{category} - find by category
     */

}
