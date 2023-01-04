package com.iprwc.webshop.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id")
    private Category category;
    @NotBlank
    private String manufacturer;
    @NotBlank
    private String engine_displacement;
    @NotBlank
    private String cylinders;
    @NotBlank
    private String year;
    @NotBlank
    private String km;
    @NotBlank
    String price;
    boolean sold = false;

    @NotBlank
    @Lob
    @Column(name="thumbnail_uri", length=512)
    private String thumbnail_uri;

    public Car() {}

    public Car(String name, String email, String password, Category category) {
        this.title = name;
        this.description = email;
        this.thumbnail_uri = password;
        this.category = category;
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

    public String getThumbnailUri() {
        return thumbnail_uri;
    }

    public Category getCategory() { return category; }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getEngine_displacement() {
        return engine_displacement;
    }

    public void setEngine_displacement(String engine_displacement) {
        this.engine_displacement = engine_displacement;
    }

    public String getCylinders() {
        return cylinders;
    }

    public void setCylinders(String cylinders) {
        this.cylinders = cylinders;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public void setCategory(Category category) { this.category = category; }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnailUri(String thumbnail_uri) {
        this.thumbnail_uri = thumbnail_uri;
    }
}