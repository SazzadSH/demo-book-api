package com.demo.bookapi.service;

import com.demo.bookapi.model.Author;
import com.demo.bookapi.repository.AuthorDAO;
import com.demo.bookapi.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorDAO authorDAO;


    @Test
    public void testGetAllAuthors() {
        Pageable pageable = PageRequest.of(0, 3);
        List<Author> authors = Utils.createAuthor();
        Page<Author> page = new PageImpl<>(authors);

        when(authorDAO.findAll(pageable)).thenReturn(page);

        List<Author> authorList = authorService.getAllAuthors(0, 3);

        assertNotNull(authorList);

        assertEquals("Charles L. Robinson", authorList.get(0).getName());
        assertEquals(103, authorList.get(2).getAge());

        assertEquals(3, authorList.size());
    }

    @Test
    public void testAddAuthor() {
        when(authorDAO.save(any(Author.class))).thenReturn(Utils.createAuthor().get(2));

        Author author = authorService.addAuthor(Utils.author());

        assertNotNull(author);

        assertEquals("Andrew T. Price", author.getName());
        assertEquals("89", author.getAge());
    }

    @Test
    public void testUpdateAuthor() {
        this.testAddAuthor();

        Author author = Utils.author();

        author.setAuthorId(Integer.valueOf(1).longValue());
        author.setAge(26);
        author.setName("Barbara S. Kozlowski");

        author = authorService.updateAuthor(Integer.valueOf(1).longValue(), author);

        assertEquals("Barbara S. Kozlowski", author.getName());
        assertEquals(26, author.getAge());
    }

    @Test
    public void testDeleteAuthor() {
        doNothing().when(authorDAO).deleteById(anyLong());
        authorService.removeAuthor(Integer.valueOf(1).longValue());
        verify(authorDAO, times(1)).deleteById(isA(Long.class));
    }





}
