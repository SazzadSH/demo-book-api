package com.demo.bookapi.service;


import com.demo.bookapi.dto.BookDTO;
import com.demo.bookapi.model.Book;
import com.demo.bookapi.repository.AuthorDAO;
import com.demo.bookapi.repository.BookDAO;
import com.demo.bookapi.repository.BookRepo;
import com.demo.bookapi.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookDAO bookDAO;
    @Mock
    private BookRepo bookRepo;
    @Mock
    private AuthorService authorService;
    @Mock
    private AuthorDAO authorDAO;

    @Test
    public void testAddBook() {
        when(authorDAO.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(Utils.author()));

        when(bookDAO.save(any(Book.class))).thenReturn(Utils.book());

        BookDTO bookDTO = bookService.addBook(Utils.bookDTO());

        assertNotNull(bookDTO);
        assertEquals("Toilet Paper Origami", bookDTO.getName());
    }


    @Test
    void testDeleteBook() {

        when(bookDAO.findById(Long.valueOf(1))).thenReturn(java.util.Optional.ofNullable(Utils.book()));
        bookService.deleteBook(Long.valueOf(1));
        verify(bookDAO).deleteById(Long.valueOf(1));
    }


    @Test
    public void testGetABook() {

        when(bookDAO.findById(any())).thenReturn(java.util.Optional.ofNullable(Utils.book()));

        Book book = bookService.getBook(Long.valueOf(1));

        assertThat(book).isNotNull();

        assertEquals(Utils.book(), book);


    }

    @Test
    void testUpdateBookSuccess() {
        when(bookDAO.findById(any())).thenReturn(java.util.Optional.ofNullable(Utils.book()));
        when(bookDAO.save(any(Book.class))).thenReturn(Utils.book());

        bookService.updateBook(Long.valueOf(1), Utils.bookDTO());

        verify(bookDAO, times(1)).save(isA(Book.class));

    }
}
