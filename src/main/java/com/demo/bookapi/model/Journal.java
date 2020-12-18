package com.demo.bookapi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Journal")
@DiscriminatorValue("Journal")
public class Journal extends Book {

    @Column
    private String publisher;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getType(){
        return "journal";
    }
}
