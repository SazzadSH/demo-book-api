package com.demo.bookapi.controller;

import com.demo.bookapi.dto.BookDTO;

import com.demo.bookapi.model.Author;
import com.demo.bookapi.model.Book;
import com.demo.bookapi.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/book/{type}/add")
    @ApiOperation(
            value = "Add a book",
            notes = "Insert a book",
            response = Book.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Book.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public BookDTO addBook(@PathVariable("type") String type,
                           @RequestBody BookDTO bookDTO){

        bookDTO.setType(type.toLowerCase());

        bookDTO = bookService.addBook(bookDTO);

        if(bookDTO == null) {
            return null;
        }

        return bookDTO;
    }

    @PutMapping("/book/update/{id}")
    @ApiOperation(
            value = "Update a book",
            notes = "Update a book",
            response = Book.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Book.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public BookDTO updateBook(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO) {
        bookDTO = bookService.updateBook(id, bookDTO);

        if(bookDTO == null) {
            return null;
        }

        return bookDTO;
    }

    @GetMapping("/book/{id}")
    @ApiOperation(
            value = "Ge a book",
            notes = "Ge a book",
            response = Book.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Book.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public Book getBook(@PathVariable("id") Long id) {
        return bookService.getBook(id);

    }

    @GetMapping("/books/all/{type}")
    @ApiOperation(
            value = "Get all books by type",
            notes = "All books by type",
            response = Book.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Book.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public List<Book> getAllBooks(@RequestParam(defaultValue = "", name = "type", required = false)  String type) {
        return bookService.getAllBooks(type);
    }

    @DeleteMapping("/book/delete/{id}")
    @ApiOperation(
            value = "Remove a book",
            notes = "Delete a book",
            response = Book.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Book.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public void removeBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }


}
