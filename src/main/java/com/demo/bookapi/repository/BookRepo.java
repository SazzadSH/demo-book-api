package com.demo.bookapi.repository;

import com.demo.bookapi.model.Book;
import com.demo.bookapi.model.Journal;
import com.demo.bookapi.model.Story;
import com.demo.bookapi.model.Thesis;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookRepo {

    @PersistenceContext
    private EntityManager entityManager;



    public List<Book> getBooksByAuthor(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = query.from(Book.class);
        query.where(criteriaBuilder.equal(from.get("author"), id));

        return entityManager.createQuery(query).getResultList();
    }

    public List<Book> getAllBooksByType(String type) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = query.from(Book.class);

        if(type.toLowerCase().equals("story")) {
            query.where(criteriaBuilder.equal(from.type(), Story.class));
        }
        else if (type.toLowerCase().equals("thesis")) {
            query.where(criteriaBuilder.equal(from.type(), Thesis.class));
        }
        else if(type.toLowerCase().equals("journal")) {
            query.where(criteriaBuilder.equal(from.type(), Journal.class));
        }

        return entityManager.createQuery(query).getResultList();
    }
}
