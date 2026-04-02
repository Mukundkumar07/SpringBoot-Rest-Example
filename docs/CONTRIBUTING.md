# Contributing to Books API

Thank you for your interest in contributing! This document provides guidelines and instructions for contributing to the Books API project.

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Workflow](#development-workflow)
- [Coding Standards](#coding-standards)
- [Commit Guidelines](#commit-guidelines)
- [Pull Request Process](#pull-request-process)
- [Testing Guidelines](#testing-guidelines)
- [Documentation](#documentation)

## Code of Conduct

### Our Pledge

We are committed to providing a welcoming and inspiring community for all. Please read and adhere to our Code of Conduct:

- Be respectful and inclusive
- Welcome diverse perspectives
- Assume good intentions
- Focus on constructive feedback
- Report inappropriate behavior

## Getting Started

### Prerequisites

- Java 25+
- Maven 3.9+
- Git
- IDE (VS Code, IntelliJ IDEA, or Eclipse)

### Fork & Clone

1. **Fork the repository** on GitHub
2. **Clone your fork**:
   ```bash
   git clone https://github.com/YOUR_USERNAME/books.git
   cd books
   ```

3. **Add upstream remote**:
   ```bash
   git remote add upstream https://github.com/ORIGINAL_OWNER/books.git
   ```

4. **Keep your fork updated**:
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```

### Setup Development Environment

1. **Build the project**:
   ```bash
   mvn clean install
   ```

2. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

3. **Access the application**:
   - API: http://localhost:8080
   - Swagger: http://localhost:8080/swagger-ui.html

## Development Workflow

### Branch Naming Convention

Create branches with descriptive names:

```
feature/description       → New features
bugfix/description       → Bug fixes
hotfix/description       → Urgent fixes
refactor/description     → Code refactoring
docs/description        → Documentation
test/description        → Tests
```

### Examples

```bash
# Feature branch
git checkout -b feature/add-user-authentication

# Bug fix branch
git checkout -b bugfix/fix-null-pointer-exception

# Refactoring branch
git checkout -b refactor/improve-error-handling

# Documentation branch
git checkout -b docs/add-migration-guide
```

### Creating a Feature Branch

```bash
# Make sure you're on main
git checkout main

# Sync with upstream
git fetch upstream
git rebase upstream/main

# Create new feature branch
git checkout -b feature/your-feature-name

# Push to your fork
git push origin feature/your-feature-name
```

## Coding Standards

### Java Code Style

#### Naming Conventions

```java
// Classes: PascalCase
public class BooksController { }
public class BookService { }

// Methods: camelCase
public void getBook() { }
public List<Book> findAllBooks() { }

// Variables: camelCase
private String bookTitle;
private List<Book> allBooks;

// Constants: UPPER_SNAKE_CASE
private static final int MAX_PAGE_SIZE = 100;
private static final String API_BASE_URL = "/api/books";

// Interfaces: Start with 'I' (optional but recommended)
public interface IBookRepository { }
public interface IBookService { }
```

#### Formatting

```java
// Method formatting
public ResponseEntity<Book> createBook(
        @RequestBody Book book,
        @RequestParam String category) {
    
    // Method body with 4-space indentation
    if (book == null) {
        return ResponseEntity.badRequest().build();
    }
    
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(book);
}

// Class formatting
@RestController
@RequestMapping("/api/books")
public class BooksController {
    
    private final List<Book> books = new ArrayList<>();
    
    // Methods in logical order:
    // 1. Constructors
    // 2. Initialization
    // 3. HTTP Endpoints (GET, POST, PUT, DELETE, PATCH)
    // 4. Private helper methods
    // 5. Overridden methods
}
```

#### Documentation

```java
/**
 * Get all books from the system
 *
 * HTTP Method: GET
 * URL: GET /api/books
 *
 * @return List of all books
 * @throws Exception if database operation fails
 * 
 * Example:
 * curl -X GET http://localhost:8080/api/books
 *
 * Response: [{"title": "..."}, ...]
 * Status: 200 OK
 */
@GetMapping
public List<Book> getAllBooks() {
    return books;
}

/**
 * Create a new book with validation
 *
 * @param book the book to create (title, author, category required)
 * @return created book or error message
 * 
 * Validation:
 * - title cannot be null or empty
 * - author cannot be null or empty
 * - category cannot be null or empty
 */
@PostMapping("/create")
public Book createBook(@RequestBody Book book) {
    // Implementation
}
```

### Code Organization

```java
@RestController
@RequestMapping("/api/books")
public class BooksController {
    
    // 1. Fields
    private final List<Book> books = new ArrayList<>();
    
    // 2. Constructors
    public BooksController() {
        initializeBooks();
    }
    
    // 3. Initialization Methods
    private void initializeBooks() {
        // Initialize default data
    }
    
    // 4. HTTP Methods (organized by method type)
    // GET endpoints
    @GetMapping
    public List<Book> getBooks() { }
    
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id) { }
    
    // POST endpoints
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) { }
    
    // PUT endpoints
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) { }
    
    // DELETE endpoints
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) { }
    
    // PATCH endpoints
    @PatchMapping("/{id}")
    public ResponseEntity<Book> partialUpdate(@PathVariable String id, @RequestBody Map<String, String> updates) { }
    
    // 5. Private Helper Methods
    private Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
    
    // 6. Overridden Methods
    @Override
    public String toString() {
        return "BooksController{" + "books=" + books + '}';
    }
}
```

### Error Handling

```java
// ❌ DON'T: Broad exception catching
try {
    // code
} catch (Exception e) {
    System.out.println("Error");
}

// ✅ DO: Specific exception catching
try {
    // code
} catch (NullPointerException e) {
    System.err.println("Null pointer: " + e.getMessage());
    return ResponseEntity.badRequest().build();
} catch (IllegalArgumentException e) {
    System.err.println("Invalid argument: " + e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
} catch (Exception e) {
    System.err.println("Unexpected error: " + e.getMessage());
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}
```

### Input Validation

```java
// ❌ DON'T: Minimal validation
public Book createBook(@RequestBody Book book) {
    books.add(book);
    return book;
}

// ✅ DO: Comprehensive validation
public Book createBook(@RequestBody Book book) {
    if (book == null) {
        throw new IllegalArgumentException("Book cannot be null");
    }
    
    if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
        throw new IllegalArgumentException("Title cannot be empty");
    }
    
    if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
        throw new IllegalArgumentException("Author cannot be empty");
    }
    
    if (book.getCategory() == null || book.getCategory().trim().isEmpty()) {
        throw new IllegalArgumentException("Category cannot be empty");
    }
    
    // Additional business logic
    books.add(book);
    System.out.println("Book created: " + book.getTitle());
    return book;
}
```

## Commit Guidelines

### Commit Message Format

Follow the [Conventional Commits](https://www.conventionalcommits.org/) specification:

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Type

| Type | Description |
|------|-------------|
| `feat` | A new feature |
| `fix` | A bug fix |
| `docs` | Documentation only |
| `style` | Changes that don't affect code logic (formatting) |
| `refactor` | Code refactoring without feature changes |
| `perf` | Performance improvements |
| `test` | Adding or updating tests |
| `chore` | Changes to build process, dependencies |

### Examples

```bash
# Feature
git commit -m "feat(controller): add batch update endpoint"

# Bug fix
git commit -m "fix(validation): handle null author field"

# Documentation
git commit -m "docs(api): update endpoint documentation"

# Refactoring
git commit -m "refactor(controller): separate concerns into service layer"

# Multiple changes
git commit -m "feat(books): add book management endpoints

- Add GET /api/books endpoint
- Add POST /api/books/create endpoint
- Add validation for required fields

Closes #123"
```

### Commit Message Best Practices

1. **Use imperative mood**: "Add feature" not "Added feature"
2. **Be specific**: Mention what changed and why
3. **Keep it concise**: First line < 50 characters
4. **Add context**: Explain the 'why', not just the 'what'
5. **Reference issues**: "Closes #123", "Fixes #456"

## Pull Request Process

### Before Submitting

1. **Update your branch**:
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```

2. **Run tests**:
   ```bash
   mvn test
   ```

3. **Build project**:
   ```bash
   mvn clean package
   ```

4. **Check for errors**:
   ```bash
   mvn clean compile
   ```

5. **Verify Swagger UI** works:
   ```bash
   mvn spring-boot:run
   # Visit http://localhost:8080/swagger-ui.html
   ```

### Submitting a Pull Request

1. **Push your branch**:
   ```bash
   git push origin feature/your-feature-name
   ```

2. **Create Pull Request** on GitHub:
   - Go to your fork on GitHub
   - Click "Compare & pull request"
   - Fill in the PR template

3. **PR Description Template**:
   ```markdown
   ## Description
   Brief explanation of changes
   
   ## Related Issue
   Closes #123
   
   ## Changes Made
   - Change 1
   - Change 2
   - Change 3
   
   ## Testing
   Describe how to test these changes
   
   ## Screenshots (if applicable)
   Add screenshots or GIFs
   
   ## Checklist
   - [ ] Code follows style guidelines
   - [ ] I have performed a self-review
   - [ ] I have commented my code, particularly in hard-to-understand areas
   - [ ] I have made corresponding changes to the documentation
   - [ ] My changes generate no new warnings
   - [ ] Tests pass and I have added tests for new functionality
   ```

### PR Review Process

1. **Maintainers will review** your code
2. **Address feedback** and push additional commits
3. **Re-request review** when ready
4. **PR will be merged** once approved

### PR Merge Strategy

- **Squash commits**: Preferred for feature branches
- **Rebase merges**: Used for cleaner history
- **Merge commits**: For major features

## Testing Guidelines

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=BooksControllerTest

# Run with coverage
mvn test jacoco:report

# Run integration tests
mvn verify
```

### Writing Tests

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BooksControllerTest {
    
    private BooksController controller;
    
    @Before
    public void setUp() {
        controller = new BooksController();
    }
    
    @Test
    public void testGetAllBooks() {
        List<Book> books = controller.getBooks();
        assertNotNull(books);
        assertTrue(books.size() > 0);
    }
    
    @Test
    public void testGetBookByTitle() {
        Book book = controller.getBookByTitle("Title One");
        assertNotNull(book);
        assertEquals("Title One", book.getTitle());
    }
    
    @Test
    public void testCreateBookWithValidData() {
        Book newBook = new Book("Test", "Author", "Category");
        Book created = controller.createBook(newBook);
        assertNotNull(created);
        assertEquals("Test", created.getTitle());
    }
    
    @Test
    public void testCreateBookWithNullTitle() {
        Book newBook = new Book(null, "Author", "Category");
        assertThrows(IllegalArgumentException.class, () -> {
            controller.createBook(newBook);
        });
    }
}
```

### Test Coverage Goals

- **Unit Tests**: >80% coverage
- **Integration Tests**: Key flows
- **Controller Tests**: All endpoints
- **Edge Cases**: Null inputs, empty lists, boundaries

## Documentation

### Code Documentation

- Write clear JavaDoc comments
- Explain the 'why', not just the 'what'
- Include HTTP method, URL, examples
- Document parameters and return values

### Adding Documentation Files

Create documentation for:
- New features: Document in README or API_DOCUMENTATION
- Architecture changes: Update ARCHITECTURE.md
- Setup procedures: Update README or create SETUP.md
- API changes: Update API_DOCUMENTATION.md

### Example Documentation Entry

```markdown
## New Feature: Soft Delete

### Overview
Soft delete marks books as deleted without removing data.

### Endpoint
```
DELETE /api/books/soft/{title}
```

### Implementation
- Add `isDeleted` flag to Book entity
- Update queries to exclude deleted books
- Provide restore endpoint

### Testing
- Test soft delete creates correct flag
- Test deleted books don't appear in lists
- Test restore functionality
```

## Useful Commands

```bash
# View git history
git log --oneline -n 10

# View current changes
git status
git diff

# Stash changes temporarily
git stash
git stash pop

# Squash commits
git rebase -i HEAD~3

# Reset to last commit
git reset --hard HEAD

# Update from upstream
git fetch upstream
git merge upstream/main
```

## Getting Help

- **Issues**: Check existing issues or create new one
- **Discussions**: Use GitHub Discussions for questions
- **Email**: Contact maintainers
- **Documentation**: Check README and ARCHITECTURE.md

## Recognition

Contributors will be recognized in:
- CONTRIBUTORS.md file
- Release notes
- GitHub insights

Thank you for contributing! 🎉

---

**Last Updated**: April 2, 2026

