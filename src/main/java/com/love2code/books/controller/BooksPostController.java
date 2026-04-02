package com.love2code.books.controller;

import com.love2code.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * BooksPostController - Handles HTTP POST requests for Books API
 * 
 * This controller manages CREATE operations for books using HTTP POST method
 */
@RestController
@RequestMapping("/api/books")
public class BooksPostController {

    private final List<Book> books = new ArrayList<>();

    public BooksPostController() {
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
     * Create a new book
     * HTTP Method: POST
     * URL: POST /api/books/create
     * 
     * Request Body (JSON):
     * {
     *     "title": "New Book Title",
     *     "author": "Author Name",
     *     "category": "Category Name"
     * }
     * 
     * Response:
     * - 201 Created: Returns the created book
     * - 400 Bad Request: If required fields are missing
     */
    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        try {
            // Validate input
            if (book.getTitle() == null || book.getTitle().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
                throw new IllegalArgumentException("Author cannot be empty");
            }
            if (book.getCategory() == null || book.getCategory().isEmpty()) {
                throw new IllegalArgumentException("Category cannot be empty");
            }

            // Add the new book to the list
            books.add(book);
            System.out.println("Book created successfully: " + book.getTitle());
            return book;
        } catch (Exception e) {
            System.err.println("Error creating book: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add multiple books at once
     * HTTP Method: POST
     * URL: POST /api/books/createMultiple
     * 
     * Request Body (JSON Array):
     * [
     *     {
     *         "title": "Book 1",
     *         "author": "Author 1",
     *         "category": "Science"
     *     },
     *     {
     *         "title": "Book 2",
     *         "author": "Author 2",
     *         "category": "Math"
     *     }
     * ]
     * 
     * Response:
     * - Returns list of created books
     */
    @PostMapping("/createMultiple")
    public List<Book> createMultipleBooks(@RequestBody List<Book> bookList) {
        try {
            if (bookList == null || bookList.isEmpty()) {
                throw new IllegalArgumentException("Book list cannot be empty");
            }

            // Add all books to the list
            for (Book book : bookList) {
                if (book.getTitle() != null && !book.getTitle().isEmpty() &&
                    book.getAuthor() != null && !book.getAuthor().isEmpty() &&
                    book.getCategory() != null && !book.getCategory().isEmpty()) {
                    books.add(book);
                }
            }

            System.out.println("Successfully added " + bookList.size() + " books");
            return bookList;
        } catch (Exception e) {
            System.err.println("Error creating multiple books: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

