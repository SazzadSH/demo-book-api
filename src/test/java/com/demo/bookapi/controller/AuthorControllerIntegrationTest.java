package com.demo.bookapi.controller;

import com.demo.bookapi.dto.AuthorDTO;
import com.demo.bookapi.model.Author;
import com.demo.bookapi.service.AuthorService;
import com.demo.bookapi.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.web.client.TestRestTemplate;

@WebMvcTest(AuthorController.class)
public class AuthorControllerIntegrationTest {

    @InjectMocks
    AuthorController authorController;
    @MockBean
    AuthorService authorService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testAddAuthor() throws Exception {

        when(authorService.addAuthor(any(Author.class))).thenReturn(Utils.author());


        Author author = authorService.addAuthor(Utils.author());

        mockMvc.perform(MockMvcRequestBuilders.post("/author/add")
        .content(new ObjectMapper().writeValueAsString(author))
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(89))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Andrew T. Price"));
    }

    @Test
    public void testDeleteAuthor() {

        Utils.createAuthor();

        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand = UriComponentsBuilder.fromHttpUrl("http://localhost:8080" + "/author/delete/{id}").
                buildAndExpand(params);

        ResponseEntity<String> deleteResponse = testRestTemplate.exchange(buildAndExpand.toUriString(),  HttpMethod.DELETE, new HttpEntity<>(null,null),String.class);
        assertSame(HttpStatus.NO_CONTENT,deleteResponse.getStatusCode());
    }


    @Test
    public void testUpdateAuthor() throws Exception {

        Author firstAuthor = new Author();

        firstAuthor.setAuthorId(Long.valueOf(1));
        firstAuthor.setName("Barbara S. Kozlowski");
        firstAuthor.setAge(24);

        when(authorService.updateAuthor(anyLong(), any(Author.class))).thenReturn(firstAuthor);

        mockMvc.perform(MockMvcRequestBuilders.put("/author/update/{id}", "1")
                .content(new ObjectMapper().writeValueAsString(firstAuthor))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(24))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Barbara S. Kozlowski"));

    }

    @Test
    public void testGetAllAuthors() throws Exception {
        when(authorService.getAllAuthors(anyInt(),anyInt()))
                .thenReturn(Utils.createAuthor());

        List<Author> authorList = authorService.getAllAuthors(0,3);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/author/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Michael T. Burgos"));

    }
}
