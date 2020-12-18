package com.demo.bookapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Thesis extends Book {

    @Column
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getType() {
        return "thesis";
    }
}
