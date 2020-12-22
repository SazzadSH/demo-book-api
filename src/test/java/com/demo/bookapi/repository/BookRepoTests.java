package com.demo.bookapi.repository;

import com.demo.bookapi.model.Book;
import com.demo.bookapi.utils.Utils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookRepoTests {

    @InjectMocks
    private BookRepo bookRepo;

    @Mock
    private EntityManager entityManager;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<Book> bookCriteriaQuery;
    @Mock
    private Root<Book> from;
    @Mock
    private TypedQuery<Book> bookTypedQuery;

    void testGetAllBooksByAuthor() {

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Book.class)).thenReturn(bookCriteriaQuery);
        when(bookCriteriaQuery.from(Book.class)).thenReturn(from);
        when(entityManager.createQuery(bookCriteriaQuery)).thenReturn(bookTypedQuery);
        when(bookTypedQuery.getResultList()).thenReturn(Utils.bookList());

        List<Book> books = bookRepo.getBooksByAuthor(Integer.valueOf(1).longValue());

        assertEquals(Utils.bookList(), books);
    }
}
