# Books API - Detailed API Documentation

Complete reference for all API endpoints, request/response formats, and usage examples.

## Table of Contents

1. [API Overview](#api-overview)
2. [Authentication & Headers](#authentication--headers)
3. [Request/Response Format](#requestresponse-format)
4. [GET Endpoints](#get-endpoints)
5. [POST Endpoints](#post-endpoints)
6. [PUT Endpoints](#put-endpoints)
7. [PATCH Endpoints](#patch-endpoints)
8. [DELETE Endpoints](#delete-endpoints)
9. [Error Handling](#error-handling)
10. [Status Codes Reference](#status-codes-reference)

## API Overview

| Property | Value |
|----------|-------|
| **Base URL** | `http://localhost:8080` |
| **API Version** | v1 |
| **Default Port** | 8080 |
| **Content-Type** | application/json |
| **Swagger UI** | `/swagger-ui.html` |
| **OpenAPI Docs** | `/v3/api-docs` |

## Authentication & Headers

Currently, the API does **not require authentication**. However, the following headers are important:

### Standard Headers

```
Content-Type: application/json
Accept: application/json
```

### Request Headers

| Header | Required | Example | Description |
|--------|----------|---------|-------------|
| `Content-Type` | POST/PUT/PATCH | `application/json` | Request body format |
| `Accept` | Optional | `application/json` | Response format preference |

## Request/Response Format

### JSON Request Format

```json
{
    "title": "Book Title",
    "author": "Author Name",
    "category": "Category Name"
}
```

### JSON Response Format

```json
{
    "title": "Book Title",
    "author": "Author Name",
    "category": "Category Name"
}
```

### List Response Format

```json
[
    {
        "title": "Book 1",
        "author": "Author 1",
        "category": "Category 1"
    },
    {
        "title": "Book 2",
        "author": "Author 2",
        "category": "Category 2"
    }
]
```

---

## GET Endpoints

### 1. Get All Books

**Endpoint**: `GET /api/books`

**Description**: Retrieve all books from the system

**Request**:
```bash
curl -X GET http://localhost:8080/api/books
```

**Response**:
```json
[
    {
        "title": "Title One",
        "author": "Author One",
        "category": "Science"
    },
    {
        "title": "Title Two",
        "author": "Author Two",
        "category": "Math"
    }
]
```

**Status**: 200 OK

---

### 2. Get Book by Title

**Endpoint**: `GET /api/books/title/{title}`

**Description**: Retrieve a specific book by its title

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `title` | String | Path | Yes | Book title (URL-encoded) |

**Request**:
```bash
curl -X GET "http://localhost:8080/api/books/title/Great%20Gatsby"
```

**Response (Found)**:
```json
{
    "title": "Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "category": "Fiction"
}
```

**Response (Not Found)**:
```
null
```

**Status**: 200 OK

**Notes**: 
- Title is case-insensitive
- URL encoding required for spaces and special characters

---

### 3. Get Books by Category

**Endpoint**: `GET /api/books/category/{category}`

**Description**: Retrieve all books in a specific category

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `category` | String | Path | Yes | Category name (URL-encoded) |

**Request**:
```bash
curl -X GET "http://localhost:8080/api/books/category/Science"
```

**Response**:
```json
[
    {
        "title": "Physics 101",
        "author": "Einstein",
        "category": "Science"
    },
    {
        "title": "Chemistry Basics",
        "author": "Marie Curie",
        "category": "Science"
    }
]
```

**Status**: 200 OK

**Notes**:
- Category is case-insensitive
- Returns empty array if no books found

---

### 4. Get Books by Author

**Endpoint**: `GET /api/books/author/{author}`

**Description**: Retrieve all books by a specific author

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `author` | String | Path | Yes | Author name (URL-encoded) |

**Request**:
```bash
curl -X GET "http://localhost:8080/api/books/author/Shakespeare"
```

**Response**:
```json
[
    {
        "title": "Hamlet",
        "author": "Shakespeare",
        "category": "Drama"
    },
    {
        "title": "Macbeth",
        "author": "Shakespeare",
        "category": "Drama"
    }
]
```

**Status**: 200 OK

---

### 5. Get Books by Title and Category

**Endpoint**: `GET /api/books/title/{title}/category/{category}`

**Description**: Retrieve a specific book by both title AND category

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `title` | String | Path | Yes | Book title |
| `category` | String | Path | Yes | Book category |

**Request**:
```bash
curl -X GET "http://localhost:8080/api/books/title/Hamlet/category/Drama"
```

**Response**:
```json
{
    "title": "Hamlet",
    "author": "Shakespeare",
    "category": "Drama"
}
```

**Status**: 200 OK

---

### 6. Get Book by Title Using Streams

**Endpoint**: `GET /api/books/stream/title/{title}`

**Description**: Retrieve book using Stream API (functional approach)

**Request**:
```bash
curl -X GET "http://localhost:8080/api/books/stream/title/Hamlet"
```

**Response**:
```json
{
    "title": "Hamlet",
    "author": "Shakespeare",
    "category": "Drama"
}
```

**Status**: 200 OK

**Notes**: Demonstrates functional programming with Java Streams

---

### 7. Get Books by Category with Query Parameter

**Endpoint**: `GET /api/books/getBooksByCategory?category={category}`

**Description**: Retrieve books by category using query parameter

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `category` | String | Query | Yes | Book category |

**Request**:
```bash
curl -X GET "http://localhost:8080/api/books/getBooksByCategory?category=Science"
```

**Response**:
```json
[
    {
        "title": "Physics 101",
        "author": "Einstein",
        "category": "Science"
    }
]
```

**Status**: 200 OK

---

### 8. Get Books by Category Using Streams

**Endpoint**: `GET /api/books/stream/getBooksByCategory?category={category}`

**Description**: Retrieve books using Stream API with query parameter

**Request**:
```bash
curl -X GET "http://localhost:8080/api/books/stream/getBooksByCategory?category=Science"
```

**Response**: Same as above

**Status**: 200 OK

---

### 9. Get Books by Author (Optional Query Parameter)

**Endpoint**: `GET /api/books/author/getBooksByAuthor?author={author}`

**Description**: Retrieve books by author (optional parameter - returns all if not provided)

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `author` | String | Query | No | Author name |

**Request (with parameter)**:
```bash
curl -X GET "http://localhost:8080/api/books/author/getBooksByAuthor?author=Shakespeare"
```

**Request (without parameter - returns all)**:
```bash
curl -X GET "http://localhost:8080/api/books/author/getBooksByAuthor"
```

**Response**:
```json
[
    {
        "title": "Hamlet",
        "author": "Shakespeare",
        "category": "Drama"
    }
]
```

**Status**: 200 OK

**Notes**: Parameter is optional; omit to get all books

---

## POST Endpoints

### 1. Create Single Book

**Endpoint**: `POST /api/books/create`

**Description**: Create a new book with validation

**Request Body**:
```json
{
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "category": "Fiction"
}
```

**cURL Request**:
```bash
curl -X POST http://localhost:8080/api/books/create \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "category": "Fiction"
  }'
```

**Response (Success)**:
```json
{
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "category": "Fiction"
}
```

**Status**: 201 Created

**Response (Validation Error)**:
```
Error: Title cannot be empty
Error: Author cannot be empty
Error: Category cannot be empty
```

**Status**: 400 Bad Request

**Validation Rules**:
- `title`: Required, not empty
- `author`: Required, not empty
- `category`: Required, not empty

---

### 2. Create Multiple Books (Batch)

**Endpoint**: `POST /api/books/createMultiple`

**Description**: Create multiple books in one request

**Request Body**:
```json
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
```

**cURL Request**:
```bash
curl -X POST http://localhost:8080/api/books/createMultiple \
  -H "Content-Type: application/json" \
  -d '[
    {"title": "Book 1", "author": "Author 1", "category": "Science"},
    {"title": "Book 2", "author": "Author 2", "category": "Math"}
  ]'
```

**Response**:
```json
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
```

**Status**: 201 Created

**Notes**:
- All items validated before creation
- Empty list returns error

---

## PUT Endpoints

### 1. Update Book by Title (Full Update)

**Endpoint**: `PUT /api/books/update/{title}`

**Description**: Completely replace a book with new data

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `title` | String | Path | Yes | Book title to update |

**Request Body** (all fields required):
```json
{
    "title": "Updated Title",
    "author": "New Author",
    "category": "NewCategory"
}
```

**cURL Request**:
```bash
curl -X PUT "http://localhost:8080/api/books/update/The%20Great%20Gatsby" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Great Gatsby Revised",
    "author": "F. Scott Fitzgerald",
    "category": "Classic Fiction"
  }'
```

**Response (Success)**:
```json
{
    "title": "The Great Gatsby Revised",
    "author": "F. Scott Fitzgerald",
    "category": "Classic Fiction"
}
```

**Status**: 200 OK

**Response (Not Found)**:
```json
{
    "error": "Book not found"
}
```

**Status**: 404 Not Found

**Notes**:
- All fields are required (full replacement)
- Null values will replace existing data

---

### 2. Update Book by ID

**Endpoint**: `PUT /api/books/{id}`

**Description**: Update book by numeric ID

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `id` | String | Path | Yes | Book ID |

**Request Body**:
```json
{
    "title": "Updated Title",
    "author": "New Author",
    "category": "NewCategory"
}
```

**Response**: Same as above

**Status**: 200 OK or 404 Not Found

---

### 3. Batch Update Multiple Books

**Endpoint**: `PUT /api/books/batch/update`

**Description**: Update multiple books at once

**Request Body** (array of books):
```json
[
    {
        "title": "Book 1",
        "author": "New Author 1",
        "category": "NewCat1"
    },
    {
        "title": "Book 2",
        "author": "New Author 2",
        "category": "NewCat2"
    }
]
```

**cURL Request**:
```bash
curl -X PUT http://localhost:8080/api/books/batch/update \
  -H "Content-Type: application/json" \
  -d '[
    {"title": "Book 1", "author": "New Author 1", "category": "Cat1"},
    {"title": "Book 2", "author": "New Author 2", "category": "Cat2"}
  ]'
```

**Response**:
```json
[
    {
        "title": "Book 1",
        "author": "New Author 1",
        "category": "Cat1"
    },
    {
        "title": "Book 2",
        "author": "New Author 2",
        "category": "Cat2"
    }
]
```

**Status**: 200 OK

---

### 4. Update Book Category Only

**Endpoint**: `PUT /api/books/{title}/category`

**Description**: Update only the category field of a book

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `title` | String | Path | Yes | Book title |

**Request Body**:
```json
{
    "category": "NewCategory"
}
```

**cURL Request**:
```bash
curl -X PUT "http://localhost:8080/api/books/Hamlet/category" \
  -H "Content-Type: application/json" \
  -d '{"category": "Classic Drama"}'
```

**Response**:
```json
{
    "title": "Hamlet",
    "author": "Shakespeare",
    "category": "Classic Drama"
}
```

**Status**: 200 OK or 404 Not Found

---

## PATCH Endpoints

### 1. Partial Update (Selective Fields)

**Endpoint**: `PATCH /api/books/partial/{title}`

**Description**: Update only specified fields, leave others unchanged

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `title` | String | Path | Yes | Book title |

**Request Body** (only changed fields):
```json
{
    "title": "New Title",
    "author": "New Author"
}
```

**cURL Request**:
```bash
curl -X PATCH "http://localhost:8080/api/books/partial/Hamlet" \
  -H "Content-Type: application/json" \
  -d '{"title": "Prince of Denmark"}'
```

**Response**:
```json
{
    "title": "Prince of Denmark",
    "author": "Shakespeare",
    "category": "Drama"
}
```

**Status**: 200 OK or 404 Not Found

**Notes**:
- Only provided fields are updated
- Omitted fields remain unchanged
- More efficient than PUT for partial updates

---

### 2. Update Title Only

**Endpoint**: `PATCH /api/books/{title}/title`

**Description**: Update only the title field

**Request Body**:
```json
{
    "value": "New Title"
}
```

**cURL Request**:
```bash
curl -X PATCH "http://localhost:8080/api/books/Hamlet/title" \
  -H "Content-Type: application/json" \
  -d '{"value": "The Prince of Denmark"}'
```

**Response**:
```json
{
    "message": "Field updated successfully",
    "field": "title",
    "old_value": "Hamlet",
    "new_value": "The Prince of Denmark"
}
```

**Status**: 200 OK or 404 Not Found

---

### 3. Update Author Only

**Endpoint**: `PATCH /api/books/{title}/author`

**Description**: Update only the author field

**Request Body**:
```json
{
    "value": "New Author"
}
```

**Response** (same pattern as title update):
```json
{
    "message": "Field updated successfully",
    "field": "author",
    "old_value": "Shakespeare",
    "new_value": "New Author"
}
```

---

### 4. Update Category Only

**Endpoint**: `PATCH /api/books/{title}/category`

**Description**: Update only the category field

**Request Body**:
```json
{
    "value": "NewCategory"
}
```

**Response** (same pattern as title/author update)

---

## DELETE Endpoints

### 1. Delete Book (No Response Body)

**Endpoint**: `DELETE /api/books/delete/{title}`

**Description**: Delete a book and return no response body (204 No Content)

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `title` | String | Path | Yes | Book title |

**cURL Request**:
```bash
curl -X DELETE "http://localhost:8080/api/books/delete/Hamlet"
```

**Response**: (empty)

**Status**: 204 No Content

**Notes**:
- Returns empty response body
- 204 status indicates successful deletion

---

### 2. Delete Book (With Confirmation Response)

**Endpoint**: `DELETE /api/books/{title}`

**Description**: Delete a book and return confirmation message

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `title` | String | Path | Yes | Book title |

**cURL Request**:
```bash
curl -X DELETE "http://localhost:8080/api/books/Hamlet"
```

**Response (Success)**:
```json
{
    "message": "Book deleted successfully",
    "title": "Hamlet",
    "deletedAt": "2026-04-02T23:37:15"
}
```

**Status**: 200 OK

**Response (Not Found)**:
```json
{
    "error": "Book not found"
}
```

**Status**: 404 Not Found

---

### 3. Delete Multiple Books (Batch)

**Endpoint**: `DELETE /api/books/batch/delete`

**Description**: Delete multiple books in one request

**Request Body**:
```json
{
    "titles": ["Book1", "Book2", "Book3"]
}
```

**cURL Request**:
```bash
curl -X DELETE http://localhost:8080/api/books/batch/delete \
  -H "Content-Type: application/json" \
  -d '{"titles": ["Hamlet", "Macbeth", "Romeo and Juliet"]}'
```

**Response**:
```json
{
    "message": "Books deleted successfully",
    "count": 3,
    "deletedBooks": ["Hamlet", "Macbeth", "Romeo and Juliet"]
}
```

**Status**: 200 OK

---

### 4. Delete All Books in Category

**Endpoint**: `DELETE /api/books/category/{category}`

**Description**: Delete all books in a specific category

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `category` | String | Path | Yes | Category name |

**cURL Request**:
```bash
curl -X DELETE "http://localhost:8080/api/books/category/Drama"
```

**Response**:
```json
{
    "message": "All books in category deleted",
    "category": "Drama",
    "count": 3
}
```

**Status**: 200 OK

---

### 5. Delete All Books (DANGEROUS)

**Endpoint**: `DELETE /api/books/all`

**Description**: Delete ALL books (testing only - be careful!)

**cURL Request**:
```bash
curl -X DELETE http://localhost:8080/api/books/all
```

**Response**:
```json
{
    "message": "All books deleted",
    "count": 10
}
```

**Status**: 200 OK

**⚠️ WARNING**: This deletes all data! Use only in development/testing.

---

### 6. Soft Delete (Non-Permanent)

**Endpoint**: `DELETE /api/books/soft/{title}`

**Description**: Mark book as deleted without removing data (allows recovery)

**Parameters**:
| Parameter | Type | Location | Required | Description |
|-----------|------|----------|----------|-------------|
| `title` | String | Path | Yes | Book title |

**cURL Request**:
```bash
curl -X DELETE "http://localhost:8080/api/books/soft/Hamlet"
```

**Response**:
```json
{
    "message": "Book marked as deleted (soft delete)",
    "title": "Hamlet",
    "status": "deleted"
}
```

**Status**: 200 OK

**Notes**:
- Book data is preserved in database
- Marked as deleted for business logic
- Can be recovered later
- Better for production systems

---

## Error Handling

### Common Error Responses

#### 400 Bad Request
```json
{
    "error": "Invalid input",
    "message": "Title cannot be empty"
}
```

#### 404 Not Found
```json
{
    "error": "Not found",
    "message": "Book not found"
}
```

#### 500 Internal Server Error
```json
{
    "error": "Internal server error",
    "message": "An unexpected error occurred"
}
```

### Error Handling Best Practices

1. **Always include error messages** in response
2. **Use appropriate status codes** (400, 404, 500)
3. **Provide detail** about what went wrong
4. **Suggest corrections** when possible

---

## Status Codes Reference

| Code | Name | Meaning | When Used |
|------|------|---------|-----------|
| **200** | OK | Request succeeded | GET, POST (sometimes), PUT, PATCH |
| **201** | Created | Resource created | POST (typically) |
| **204** | No Content | Success, no response body | DELETE |
| **400** | Bad Request | Invalid request | Missing/invalid data |
| **404** | Not Found | Resource not found | GET/PUT/DELETE non-existent resource |
| **409** | Conflict | Resource already exists | POST duplicate |
| **500** | Internal Server Error | Server error | Unexpected server issues |

### Status Code Selection Guide

**GET Requests**:
- `200 OK` - Return requested data
- `404 Not Found` - Resource doesn't exist

**POST Requests**:
- `201 Created` - New resource created
- `400 Bad Request` - Invalid input data
- `409 Conflict` - Resource already exists

**PUT Requests**:
- `200 OK` - Resource updated, return updated data
- `204 No Content` - Updated, no response body
- `400 Bad Request` - Invalid input
- `404 Not Found` - Resource not found

**PATCH Requests**:
- `200 OK` - Partial update successful
- `400 Bad Request` - Invalid input
- `404 Not Found` - Resource not found

**DELETE Requests**:
- `204 No Content` - Deleted successfully (no body)
- `200 OK` - Deleted with confirmation response
- `404 Not Found` - Resource not found

---

**Last Updated**: April 2, 2026
**API Version**: 1.0.0

