package com.demo.bookapi.service.Implementation;

import com.demo.bookapi.model.Author;
import com.demo.bookapi.model.Book;
import com.demo.bookapi.repository.AuthorDAO;
import com.demo.bookapi.repository.BookRepo;
import com.demo.bookapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDAO authorDAO;
    private BookRepo bookRepo;

    @Autowired
    public void setAuthorDAO(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Autowired
    public void setBookRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Author addAuthor(Author author) {
        return authorDAO.save(author);
    }

    @Override
    public Author getAuthor(Long id){
        return authorDAO.getByAuthorId(id);
    }

    @Override
    public List<Author> getAllAuthors(int pageNo, int pageSize)
    {
        List <Author> authorList = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        authorDAO.findAll(pageable).forEach(authorList::add);

        return authorList;
    }

    @Override
    public void removeAuthor(Long id) {
        authorDAO.deleteById(id);
    }

    @Override
    public List<Book> getBooksByAuthor(Long id) {
        return bookRepo.getBooksByAuthor(id);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        author.setAuthorId(id);
        return authorDAO.save(author);

    }
}
