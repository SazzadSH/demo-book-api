package com.demo.bookapi.controller;

import com.demo.bookapi.model.Author;
import com.demo.bookapi.model.Book;
import com.demo.bookapi.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {

        this.authorService = authorService;
    }


    @PostMapping("/author/add")
    @ApiOperation(
            value = "Add a new author",
            notes = "Add a new author",
            response = Author.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Author.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public Author addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @GetMapping("/author/{id}")
    @ApiOperation(
            value = "Get an Author",
            notes = "Information of an author",
            response = Author.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Author.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public Author getAuthor(@PathVariable("id") Long id) {
        return authorService.getAuthor(id);
    }

    @GetMapping("/author/all")
    @ApiOperation(
            value = "Get All Authors",
            notes = "Information of all authors",
            response = Author.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Author.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public List<Author> getAuthors(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize

    ){
        return authorService.getAllAuthors(pageNo, pageSize);
    }

    @GetMapping("/author/{id}/books/")
    @ApiOperation(
            value = "Get all books of an author",
            notes = "All books written by an author by Author ID",
            response = Author.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Author.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public List<Book> getBooksByAuthor(@PathVariable("id") Long id) {
        return authorService.getBooksByAuthor(id);
    }

    @DeleteMapping("author/delete/{id}")
    @ApiOperation(
            value = "Delete an author",
            notes = "Remove an author"

    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Author.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public void deleteAuthor(@PathVariable("id") Long id) {

        authorService.removeAuthor(id);
    }

    @PutMapping("/author/update/{id}")
    @ApiOperation(
            value = "Update an author",
            notes = "Update an author",
            response = Author.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Successful", response = Author.class),
            @ApiResponse(code = 204, message = "No matching author was found!"),
            @ApiResponse(code = 404, message = "Resource not found!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Internal server error!")
    })
    public void updateAuthor(@PathVariable("id") Long id, @RequestBody Author author) {
        authorService.updateAuthor(id, author);
    }

}
