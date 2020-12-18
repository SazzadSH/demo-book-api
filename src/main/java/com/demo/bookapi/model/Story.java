package com.demo.bookapi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Story")
@DiscriminatorValue("Story")
public class Story extends Book {

    @Column
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String getType() {
        return "story";
    }
}
