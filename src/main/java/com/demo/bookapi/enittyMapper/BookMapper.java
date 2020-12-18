package com.demo.bookapi.enittyMapper;

import com.demo.bookapi.dto.BookDTO;
import com.demo.bookapi.model.Journal;
import com.demo.bookapi.model.Story;
import com.demo.bookapi.model.Thesis;
import com.demo.bookapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookMapper {

    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    public Story mapStory(BookDTO bookDTO) {
        Story story = new Story();

        story.setGenre(bookDTO.getGenre());
        story.setName(bookDTO.getName());
        story.setAuthor(authorService.getAuthor(bookDTO.getAuthorId()));

        return story;
    }

    public Thesis mapThesis(BookDTO bookDTO) {
        Thesis thesis = new Thesis();

        thesis.setName(bookDTO.getName());
        thesis.setTopic(bookDTO.getTopic());
        thesis.setAuthor(authorService.getAuthor(bookDTO.getAuthorId()));

        return thesis;
    }

    public Journal mapJournal(BookDTO bookDTO) {
        Journal journal = new Journal();

        journal.setPublisher(bookDTO.getPublisher());
        journal.setName(bookDTO.getName());
        journal.setAuthor(authorService.getAuthor(bookDTO.getAuthorId()));

        return journal;
    }
}
