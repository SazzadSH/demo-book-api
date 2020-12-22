package com.demo.bookapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long authorId;

    @Column
    private String name;
    @Column
    private int age;

    @JsonIgnore
    @OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL)
    private List books;


    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Author() {
    }

    public List getBooks() {
        return books;
    }

    public void setBooks(List books) {
        this.books = books;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Author(String name, int age) {

        this.name = name;
        this.age = age;
    }
}
