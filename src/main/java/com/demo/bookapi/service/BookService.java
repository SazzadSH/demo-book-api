package com.demo.bookapi.service;

import com.demo.bookapi.dto.BookDTO;
import com.demo.bookapi.model.Book;
import com.demo.bookapi.model.Journal;
import com.demo.bookapi.model.Story;
import com.demo.bookapi.model.Thesis;

import java.util.List;

public interface BookService {
    public Story addStory(BookDTO bookDTO);
    public Thesis addThesis(BookDTO bookDTO);
    public Journal addJournal(BookDTO bookDTO);

    public BookDTO addBook(BookDTO bookDTO);

    public List<Book> getAllBooks(String type);
    public Book getBook(Long id);

    void deleteBook(Long id);

    BookDTO updateBook(Long id, BookDTO bookDTO);
}
