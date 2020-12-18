package com.demo.bookapi.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "book_type")
public abstract class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column
    private String name;

    public Book() {
    }

    public Book(String name) {
        this.name = name;

    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getType();

    @OneToOne(targetEntity = Author.class)
    @JoinTable(name = "BookAuthor")
    private Author author;

    public void setId(Long id) {
        this.id = id;
    }
}
