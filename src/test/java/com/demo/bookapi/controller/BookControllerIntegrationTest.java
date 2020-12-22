package com.demo.bookapi.controller;

import com.demo.bookapi.dto.BookDTO;
import com.demo.bookapi.model.Book;
import com.demo.bookapi.service.BookService;
import com.demo.bookapi.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BookController.class)
public class BookControllerIntegrationTest {

    @InjectMocks
    private BookController bookController;
    @MockBean
    private BookService bookService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetAllBooks() throws Exception {

        when(bookService.getAllBooks(""))
                .thenReturn(Utils.bookList());

        List<Book> bookList = bookService.getAllBooks("");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/books/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(bookList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookName", is("Hundred Years of Solitude")));

    }

    @Test
    public void testPostBook() throws Exception {

        String bookType = "story";
        String authorId = "1";
        when(bookService.addBook(any(BookDTO.class)))
                .thenReturn(Utils.bookDTO());

        BookDTO bookDTO = bookService.addBook(Utils.bookDTO());
        mockMvc.perform(MockMvcRequestBuilders.post("/book/{type}/{id}", bookType, authorId)
                .content(new ObjectMapper().writeValueAsString(bookDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Toilet Paper Origami"));
    }


    @Test
    public void testDeleteBook(){
        Utils.bookList();

        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand = UriComponentsBuilder.fromHttpUrl("http://localhost:8080"+ "/book/delete/{id}").buildAndExpand(params);

        ResponseEntity<String> deleteResponse = testRestTemplate.exchange(buildAndExpand.toUriString(),  HttpMethod.DELETE, new HttpEntity<>(null,null),String.class);
        assertSame(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

    }
}
