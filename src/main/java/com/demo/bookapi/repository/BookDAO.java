package com.demo.bookapi.repository;

import com.demo.bookapi.model.Book;
import com.demo.bookapi.model.Journal;
import com.demo.bookapi.model.Story;
import com.demo.bookapi.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<Book, Long> {

    Book getBookById(Long Id);

//    Story save(Story story);
//    Journal save(Journal journal);
//    Thesis save(Thesis thesis);

}
