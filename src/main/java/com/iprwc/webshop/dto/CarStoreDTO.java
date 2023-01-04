package com.iprwc.webshop.dto;

import com.iprwc.webshop.model.Car;
import com.iprwc.webshop.model.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CarStoreDTO {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Category category;

    @NotNull
    private String manufacturer;

    @NotNull
    private String engine_displacement;

    @NotNull
    private String year;

    @NotNull
    private String cylinders;

    @NotNull
    private String km;

    @NotNull
    private String price;

    @NotNull
    private boolean sold;

    private String thumbnail_uri;

    public Car toCar() {
        Car car = new Car();
        car.setTitle(title);
        car.setDescription(description);
        car.setCategory(category);
        car.setManufacturer(manufacturer);
        car.setEngine_displacement(engine_displacement);
        car.setCylinders(cylinders);
        car.setYear(year);
        car.setKm(km);
        car.setPrice(price);
        car.setSold(false);
        car.setThumbnailUri(thumbnail_uri);

        return car;
    }

}
