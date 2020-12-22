package com.demo.bookapi.utils;

import com.demo.bookapi.dto.BookDTO;
import com.demo.bookapi.model.Author;
import com.demo.bookapi.model.Book;
import com.demo.bookapi.model.Story;
import com.demo.bookapi.model.Thesis;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Author> createAuthor() {
        Author firstAuthor = new Author();
        firstAuthor.setAuthorId(Integer.valueOf(0).longValue());
        firstAuthor.setAge(15);
        firstAuthor.setName("Charles L. Robinson");

        Author secondAuthor = new Author();
        secondAuthor.setName("Andrew T. Price");
        secondAuthor.setAge(89);
        secondAuthor.setAuthorId(Integer.valueOf(1).longValue());

        Author thirdAuthor = new Author();
        thirdAuthor.setAuthorId(Integer.valueOf(7).longValue());
        thirdAuthor.setName("Michael T. Burgos");
        thirdAuthor.setAge(103);

        List<Author> authors = new ArrayList<Author>();
        authors.add(firstAuthor);
        authors.add(secondAuthor);
        authors.add(thirdAuthor);

        return authors;
    }

    public static Author author() {
        Author author = new Author();

        author.setAuthorId(Integer.valueOf(1).longValue());
        author.setName("Andrew T. Price");
        author.setAge(89);

        return author;
    }

    public static BookDTO bookDTO() {
        BookDTO bookDTO = new BookDTO();

        bookDTO.setAuthorId(Long.valueOf(1));
        bookDTO.setName("Toilet Paper Origami");
        bookDTO.setGenre("Tutorial");

        return bookDTO;


    }

    public static Book book() {
        Story story = new Story();

        story.setId(Long.valueOf(1));
        story.setGenre("Tutorial");
        story.setName("Toilet Paper Origami");

        return story;
    }

    public static List<Book> bookList() {
        List<Book> books = new ArrayList<>();

        Story story = new Story();
        story.setId(Integer.valueOf(0).longValue());
        story.setName("Toilet Paper Origami");
        story.setGenre("Tutorial");
        story.setAuthor(author());

        Thesis thesis = new Thesis();
        thesis.setId(Integer.valueOf(1).longValue());
        thesis.setName("Gravitational force is hoax");
        thesis.setTopic("Theoratical Physics");
        thesis.setAuthor(author());

        books.add(story);
        books.add(thesis);

        return books;

    }

}
