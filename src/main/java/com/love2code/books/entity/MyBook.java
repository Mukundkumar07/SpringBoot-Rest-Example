package com.love2code.books.entity;

public class MyBook {
    private String title;
    private String author;
    private String category;

    public MyBook(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
