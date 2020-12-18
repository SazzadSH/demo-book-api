package com.demo.bookapi.repository;

import com.demo.bookapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long> {

    public Author getByAuthorId(Long id);;

}
