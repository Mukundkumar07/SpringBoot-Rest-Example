package com.love2code.books.controller;

import org.springframework.web.bind.annotation.RestController;

import com.love2code.books.entity.MyBook;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class MyBookController {

  private List<MyBook> myBooksList;
  
   public MyBookController() {
    initializeMyBooks();
  }

  public void initializeMyBooks() {
    myBooksList = new ArrayList<>();
    myBooksList.add(new MyBook("Book 1", "Author 1", "Category 1"));
    myBooksList.add(new MyBook("Book 2", "Author 2", "Category 2"));
    myBooksList.add(new MyBook("Book 3", "Author 3", "Category 3"));
  }

  @GetMapping("/api/mybooks")
  public List<MyBook> getMyBooks() {
      return myBooksList;
  }
  
}
