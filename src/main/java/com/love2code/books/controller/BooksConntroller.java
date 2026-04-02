package com.love2code.books.controller;

import com.love2code.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BooksConntroller {

   private final List<Book> books= new ArrayList<> ();
   
   /**
    * API Endpoints:
    * 1. GET /api/books - Retrieve all books
    * 2. GET /api/books/title/{title} - Retrieve a book by title
    * 3. GET /api/books/category/{category} - Retrieve all books by category
    * 
    * IMPORTANT NOTE - AMBIGUOUS HANDLER ISSUE:
    * ============================================
    * DO NOT use the same URL pattern for different methods!
    * 
    * WRONG (causes 500 error - Ambiguous handler methods):
    * ------
    *   @GetMapping("/api/books/{title}")
    *   public Book getBookByTitle(@PathVariable String title) { }
    *   
    *   @GetMapping("/api/books/{category}")
    *   public List<Book> getBookByCategory(@PathVariable String category) { }
    * 
    * When Spring receives /api/books/science, it cannot determine which method to call
    * because both patterns match. This results in:
    * "IllegalStateException: Ambiguous handler methods mapped for '/api/books/science'"
    * 
    * CORRECT (use distinct URL paths):
    * ----------
    *   @GetMapping("/api/books/title/{title}")
    *   public Book getBookByTitle(@PathVariable String title) { }
    *   
    *   @GetMapping("/api/books/category/{category}")
    *   public List<Book> getBookByCategory(@PathVariable String category) { }
    * 
    * Now Spring can clearly distinguish:
    * - /api/books/title/Title%20One -> getBookByTitle()
    * - /api/books/category/science -> getBookByCategory()
    */
   
   public BooksConntroller() {
       initializeBooks ();
   }
    private void initializeBooks() {
        books.addAll (List.of(
             new Book("Title One","Author One","Science"),
             new Book ("Title Two","Author Two","Math"),
             new Book("Title Three", "Author Three","Science"),
             new Book ("Title Four","Author Four", "Biology")
        ));
    }

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return books;
    }

    
   //PathVariable is a request parameter annotation that binds a method parameter to a URI template variable.
   //It is used to extract values from the URL and pass them as arguments to the controller methods.
   // For example, in the method getBookByTitle(@PathVariable String title),
   // the {title} part of the URL will be extracted and passed as the title parameter to the method.
   // This allows us to retrieve specific books based on their title or category from the URL.

    /**
     * Get book by title
     * URL: GET /api/books/title/{title}
     * Example: /api/books/title/Title%20One
     */
    @GetMapping("/api/books/title/{title}")
    public Book getBookByTitle(@PathVariable String title){
       try {
           for(Book book:books){
               if(book.getTitle ().equalsIgnoreCase (title)){
                   return book;
               }
           }
           return null;
       } catch (Exception e) {
           System.err.println("Error in getBookByTitle: " + e.getMessage());
           e.printStackTrace();
           return null;
       }
    }

    /**
     * Get all books by category
     * URL: GET /api/books/category/{category}
     * Example: /api/books/category/science
     */
    @GetMapping("/api/books/category/{category}")
    public List<Book> getBookByCategory(@PathVariable String category){
       try {
           List<Book> result = new ArrayList<>();
           System.out.println("Searching for category: " + category);
           System.out.println("Available books: " + books.size());
           for(Book book:books){
               System.out.println("Comparing: " + book.getCategory() + " with " + category);
               if(book.getCategory() != null && book.getCategory().equalsIgnoreCase(category)){
                   result.add(book);
               }
           }
           System.out.println("Found " + result.size() + " books");
           return result;
       } catch (Exception e) {
           System.err.println("Error in getBookByCategory: " + e.getMessage());
           e.printStackTrace();
           return new ArrayList<>();
       }
    }
    //get book by author
    @GetMapping("/api/books/author/{author}")
    public List<Book> getBookByAuthor(@PathVariable String author){
        try{
            List<Book> result= new ArrayList<>();
            System.out.println("Searching for author: " + author);
            System.out.println("Available books: " + books.size());
            for(Book book: books){
                System.out.println("comparing: " + book.getAuthor() + " with " + author);
                if(book.getAuthor()!=null && book.getAuthor().equalsIgnoreCase(author)){
                    result.add(book);
                }
            }
            return result;
        } catch (Exception e) {
            System.err.println("Error in getBookByAuthor: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //get book by title and category
    @GetMapping("/api/books/title/{title}/category/{category}")
    public Book getBookByTitleAndCategory(@PathVariable String title, @PathVariable String category){
        try {
            for(Book book:books){
                if(book.getTitle().equalsIgnoreCase(title) && book.getCategory().equalsIgnoreCase(category)){
                    return book;
                }
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error in getBookByTitleAndCategory: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    
    }

    //get  bok by title using streams
    @GetMapping("/api/books/stream/title/{title}")
    public Book getBookByTitleUsingStreams(@PathVariable String title){
        try {
            return books.stream()
            .filter(book -> book.getTitle().equalsIgnoreCase(title))
            .findFirst()
            .orElse(null);
        } catch (Exception e) {
            System.err.println("Error in getBookByTitleUsingStreams: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    //RequestParam is a springboot annotation used to bind HTTP request parameters to method parameters 
    //in controller methods. It allows you to extract query parameters from the URL and use them as arguments in 
    //your methods. For example, if you have a method like getBooksByAuthor(@RequestParam String author), 
    //you can call it with a URL like /api/books?author=Author%20One, and the value "Author One" 
    //will be passed to the method as the author parameter. This is useful for filtering or searching for books 
    //based on specific criteria provided in the query string of the URL.

    @GetMapping("/api/books/getBooksByCategory")
    public List<Book> getBooksByCategory(@RequestParam String category){
        try{

            List<Book> result = new ArrayList<>();
            for(Book book:books){
                if(book.getCategory().equalsIgnoreCase(category)){
                    result.add(book);
                }
            }
            return result;
        }catch (Exception e){
            System.err.println("Error in getBooksByCategory: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //using stream to filter books by category
    @GetMapping("/api/books/stream/getBooksByCategory")
    public List<Book> getBooksByCategoryUsingStream(@RequestParam String category) {
        try{
            return books.stream().filter(book->book.getCategory().equalsIgnoreCase(category))
            .toList();
        }
        catch (Exception e){
            System.err.println("Error in getBooksByCategoryUsingStream: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    
    }

    //as @queryParam/@RequstPatam is mendatory or by default it is required=true, if we want to make it optional then we can set required=false and also provide default value using defaultValue attribute
    @GetMapping("/api/books/author/getBooksByAuthor")
    public List<Book> getBooksByAuthor(@RequestParam(required = false) String author) {
        if(author == null || author.isEmpty()) {
            System.out.println("Author parameter is missing or empty. Returning all books.");
            return books;
        }
        try {
            return books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).toList();
        } catch (Exception e) {
            System.err.println("Error in getBooksByAuthor: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * NOTE: POST endpoints have been moved to BooksPostController
     * This controller focuses only on GET operations
     * 
     * For creating books, use BooksPostController:
     * - POST /api/books/create - Create single book
     * - POST /api/books/createMultiple - Create multiple books
     */

    @PostMapping("/api/books")
    public void createNewBook(@RequestBody Book newBook){
        try{
            for(Book book:books){
                if(book.getTitle().equalsIgnoreCase(newBook.getTitle())){
                    return;
                }
            }
            books.add(newBook);
        }catch (Exception e){
            System.err.println("Error in createNewBook: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //POSTMAPING ,using stream
@PostMapping("/api/stream/books")
    public void createBooksUsingStreams(@RequestBody List<Book> newBooks){
        try{
            boolean isNewBook= books.stream().noneMatch (book->book.getTitle ().equalsIgnoreCase(newBooks.get(0).getTitle()));
            if(isNewBook){
                books.addAll(newBooks);
            }
        }catch (Exception e){
            System.err.println("Error in createBooksUsingStreams: " + e.getMessage());
            e.printStackTrace();
        }
    }
}