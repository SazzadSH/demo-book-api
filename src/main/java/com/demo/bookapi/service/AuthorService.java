package com.demo.bookapi.service;

import com.demo.bookapi.model.Author;
import com.demo.bookapi.model.Book;

import java.util.List;

public interface AuthorService {

    public Author addAuthor(Author author);
    public Author getAuthor(Long id);
    public List<Author> getAllAuthors(int pageNo, int pageSize);
    public void removeAuthor(Long id);
    public List<Book> getBooksByAuthor(Long id);
    Author updateAuthor(Long id, Author author);
    //public Author getAuthor(Long id);
    //public List<Author> getAuthors(String name);
}
