package com.iprwc.webshop.dto;

import com.iprwc.webshop.model.Car;
import com.iprwc.webshop.model.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CarUpdateDTO {

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

    private String thumbnail_uri;

    public Car toCar(Car carToUpdate) {
        carToUpdate.setTitle(title);
        carToUpdate.setDescription(description);
        carToUpdate.setCategory(category);
        carToUpdate.setManufacturer(manufacturer);
        carToUpdate.setEngine_displacement(engine_displacement);
        carToUpdate.setCylinders(cylinders);
        carToUpdate.setYear(year);
        carToUpdate.setKm(km);
        carToUpdate.setThumbnailUri(thumbnail_uri);

        return carToUpdate;
    }

}
