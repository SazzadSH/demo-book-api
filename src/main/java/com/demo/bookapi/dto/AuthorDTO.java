package com.demo.bookapi.dto;

import javax.validation.constraints.NotNull;

public class AuthorDTO {

    @NotNull(message = "name is mandetory")
    private String name;
    private String age;

    public AuthorDTO(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public AuthorDTO() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
