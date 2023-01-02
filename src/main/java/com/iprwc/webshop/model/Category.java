package com.iprwc.webshop.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;

    public Category(){}

    public Category(String name, String email) {
        this.title = name;
        this.description = email;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}