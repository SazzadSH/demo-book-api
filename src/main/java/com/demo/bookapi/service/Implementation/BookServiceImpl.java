package com.demo.bookapi.service.Implementation;

import com.demo.bookapi.dto.BookDTO;
import com.demo.bookapi.model.Book;
import com.demo.bookapi.model.Journal;
import com.demo.bookapi.model.Story;
import com.demo.bookapi.model.Thesis;
import com.demo.bookapi.repository.BookDAO;
import com.demo.bookapi.repository.BookRepo;
import com.demo.bookapi.service.AuthorService;
import com.demo.bookapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;
    private AuthorService authorService;
    private BookRepo bookRepo;

    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setBookRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Story addStory(BookDTO bookDTO) {


        Story story = new Story();

        story.setGenre(bookDTO.getGenre());
        story.setName(bookDTO.getName());

        story.setAuthor(authorService.getAuthor(bookDTO.getAuthorId()));




        return bookDAO.save(story);

    }

    @Override
    public Journal addJournal(BookDTO bookDTO) {
        System.out.println("journal: bookservice");
        Journal journal = new Journal();

        journal.setName(bookDTO.getName());
        journal.setPublisher(bookDTO.getPublisher());
        journal.setAuthor(authorService.getAuthor(bookDTO.getAuthorId()));

        return bookDAO.save(journal);
    }

    @Override
    public Thesis addThesis(BookDTO bookDTO) {

        Thesis thesis = new Thesis();

        thesis.setName(bookDTO.getName());
        thesis.setTopic(bookDTO.getTopic());
        thesis.setAuthor(authorService.getAuthor(bookDTO.getAuthorId()));

        return bookDAO.save(thesis);

    }

    @Override
    public List<Book> getAllBooks(String type){
        if(type.isEmpty()){
            return bookDAO.findAll();
        }
        else {
            return bookRepo.getAllBooksByType(type);//bookDAO.findAllByType(type);
        }
    }

    @Override
    public Book getBook(Long id) {
        return bookDAO.getBookById(id);//findById(id).orElse(null);
    }


    @Override
    public void deleteBook(Long id) {
        bookDAO.deleteById(id);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {

        if(bookDTO.getType().equals("story")) {
            this.addStory(bookDTO);
        }
        else if(bookDTO.getType().equals("journal")) {
            this.addJournal(bookDTO);
        }
        else if(bookDTO.getType().equals("thesis")) {
            this.addThesis(bookDTO);
        }
        else {
            return null;
        }

        return bookDTO;

    }


    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {

        Book book = this.getBook(id);

        book.setName(bookDTO.getName());
        book.setAuthor(authorService.getAuthor(bookDTO.getAuthorId()));

        if(book.getType().equals("story")) {

            Story story = (Story) book;
            story.setGenre(bookDTO.getGenre());

            bookDAO.save(story);
        }
        else if(book.getType().equals("journal")) {
            Journal journal = (Journal) book;
            journal.setPublisher(bookDTO.getPublisher());

            bookDAO.save(journal);
        }
        else if(book.getType().equals("thesis")) {
            Thesis thesis = (Thesis) book;
            thesis.setTopic(bookDTO.getTopic());

            bookDAO.save(thesis);
        }
        else
        {
            return null;
        }

        return bookDTO;
    }


}
