package com.love2code.books.controller;

import com.love2code.books.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BooksPatchController - Handles HTTP PATCH requests for Books API
 * 
 * HTTP PATCH Method Overview:
 * ────────────────────────────
 * PATCH is used to PARTIALLY UPDATE resources on the server.
 * It updates only the fields that are provided.
 * 
 * Key Characteristics:
 * • Partial update: Update only specific fields
 * • Resource ID: Requires identifier in URL path
 * • Selective fields: Only changed fields in request body
 * • More efficient: Smaller payloads than PUT
 * • NOT idempotent: Multiple requests may have different results
 * • NOT safe: Modifies server state
 * • Usually returns 200 OK
 * 
 * PUT vs PATCH:
 * ─────────────
 * PUT:   Full update - Replace entire resource (all fields required)
 *        Example: PUT /api/books/1 {"title": "...", "author": "...", "category": "..."}
 *        All fields must be provided
 *
 * PATCH: Partial update - Update only specific fields
 *        Example: PATCH /api/books/1 {"title": "..."}
 *        Only changed fields needed
 * 
 * When to Use:
 * • Update only specific field (title, category, etc.)
 * • Don't have complete object data
 * • Want efficient partial updates
 * • Client doesn't want to provide all fields
 * • Preserve existing field values not mentioned
 */
@RestController
@RequestMapping("/api/books")
public class BooksPatchController {

    private final List<Book> books = new ArrayList<>();

    public BooksPatchController() {
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
     * PARTIALLY UPDATE a book (selective fields)
     * 
     * HTTP Method: PATCH
     * URL: PATCH /api/books/partial/{title}
     * 
     * Description:
     * ────────────
     * Updates only the fields provided in request body.
     * Preserves other fields with existing values.
     * 
     * Path Variable:
     * ──────────────
     * @PathVariable String title - The title of the book to update
     * 
     * Request Body (JSON) - Only provide fields to update:
     * ────────────────────────────────────────────────────
     * Option 1 - Update only title:
     * {
     *     "title": "New Title Only"
     * }
     * 
     * Option 2 - Update only category:
     * {
     *     "category": "Physics"
     * }
     * 
     * Option 3 - Update title and category (keep author):
     * {
     *     "title": "New Title",
     *     "category": "Physics"
     * }
     * 
     * cURL Examples:
     * ───────────────
     * # Update only title
     * curl -X PATCH http://localhost:8080/api/books/partial/Title%20One \
     *   -H "Content-Type: application/json" \
     *   -d '{"title": "New Title"}'
     * 
     * # Update only category
     * curl -X PATCH http://localhost:8080/api/books/partial/Title%20One \
     *   -H "Content-Type: application/json" \
     *   -d '{"category": "Physics"}'
     * 
     * Response:
     * ─────────
     * Status: 200 OK
     * Body:
     * {
     *     "title": "New Title",
     *     "author": "Author One",      ← Unchanged
     *     "category": "Physics"
     * }
     */
    @PatchMapping("/partial/{title}")
    public ResponseEntity<Book> partialUpdateBook(
            @PathVariable String title,
            @RequestBody Map<String, String> updates) {
        try {
            // Find the book
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    // Update only provided fields
                    if (updates.containsKey("title") && !updates.get("title").isEmpty()) {
                        String oldTitle = book.getTitle();
                        book.setTitle(updates.get("title"));
                        System.out.println("Updated title: " + oldTitle + " → " + book.getTitle());
                    }

                    if (updates.containsKey("author") && !updates.get("author").isEmpty()) {
                        String oldAuthor = book.getAuthor();
                        book.setAuthor(updates.get("author"));
                        System.out.println("Updated author: " + oldAuthor + " → " + book.getAuthor());
                    }

                    if (updates.containsKey("category") && !updates.get("category").isEmpty()) {
                        String oldCategory = book.getCategory();
                        book.setCategory(updates.get("category"));
                        System.out.println("Updated category: " + oldCategory + " → " + book.getCategory());
                    }

                    System.out.println("Partial update completed for: " + title);
                    return ResponseEntity.ok(book);
                }
            }

            System.out.println("Book not found for partial update: " + title);
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            System.err.println("Error in partial update: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * UPDATE single field of a book
     * 
     * HTTP Method: PATCH
     * URL: PATCH /api/books/{title}/title
     *      PATCH /api/books/{title}/author
     *      PATCH /api/books/{title}/category
     * 
     * Description:
     * ────────────
     * Updates a single specific field using nested route.
     * More explicit control over which field to update.
     * 
     * cURL Examples:
     * ───────────────
     * # Update only title
     * curl -X PATCH http://localhost:8080/api/books/Title%20One/title \
     *   -H "Content-Type: application/json" \
     *   -d '{"value": "Brand New Title"}'
     * 
     * # Update only author
     * curl -X PATCH http://localhost:8080/api/books/Title%20One/author \
     *   -H "Content-Type: application/json" \
     *   -d '{"value": "New Author Name"}'
     * 
     * # Update only category
     * curl -X PATCH http://localhost:8080/api/books/Title%20One/category \
     *   -H "Content-Type: application/json" \
     *   -d '{"value": "Physics"}'
     * 
     * Response:
     * ─────────
     * Status: 200 OK
     * Body:
     * {
     *     "message": "Field updated successfully",
     *     "field": "title",
     *     "old_value": "Title One",
     *     "new_value": "Brand New Title"
     * }
     */
    @PatchMapping("/{title}/title")
    public ResponseEntity<Map<String, String>> updateTitle(
            @PathVariable String title,
            @RequestBody Map<String, String> request) {
        try {
            String newValue = request.get("value");
            if (newValue == null || newValue.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    String oldValue = book.getTitle();
                    book.setTitle(newValue);

                    Map<String, String> response = Map.of(
                            "message", "Field updated successfully",
                            "field", "title",
                            "old_value", oldValue,
                            "new_value", newValue
                    );

                    System.out.println("Updated title: " + oldValue + " → " + newValue);
                    return ResponseEntity.ok(response);
                }
            }

            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            System.err.println("Error updating title: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{title}/author")
    public ResponseEntity<Map<String, String>> updateAuthor(
            @PathVariable String title,
            @RequestBody Map<String, String> request) {
        try {
            String newValue = request.get("value");
            if (newValue == null || newValue.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    String oldValue = book.getAuthor();
                    book.setAuthor(newValue);

                    Map<String, String> response = Map.of(
                            "message", "Field updated successfully",
                            "field", "author",
                            "old_value", oldValue,
                            "new_value", newValue
                    );

                    System.out.println("Updated author: " + oldValue + " → " + newValue);
                    return ResponseEntity.ok(response);
                }
            }

            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            System.err.println("Error updating author: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{title}/category")
    public ResponseEntity<Map<String, String>> updateCategory(
            @PathVariable String title,
            @RequestBody Map<String, String> request) {
        try {
            String newValue = request.get("value");
            if (newValue == null || newValue.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    String oldValue = book.getCategory();
                    book.setCategory(newValue);

                    Map<String, String> response = Map.of(
                            "message", "Field updated successfully",
                            "field", "category",
                            "old_value", oldValue,
                            "new_value", newValue
                    );

                    System.out.println("Updated category: " + oldValue + " → " + newValue);
                    return ResponseEntity.ok(response);
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
     * NOTE: Use BooksConntroller for viewing all books or finding specific books
     * This controller focuses only on PATCH (PARTIAL UPDATE) operations
     */

}
