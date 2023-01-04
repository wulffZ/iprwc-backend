package com.iprwc.webshop.repositories;

import com.iprwc.webshop.model.Car;
import com.iprwc.webshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CarRepository extends JpaRepository<Car, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Car c SET c.title = :title, c.description = :description, c.category = :category, c.manufacturer = :manufacturer, c.engine_displacement = :engine_displacement, c.cylinders = :cylinders, c.year = :year, c.km = :km, c.price = :price, c.sold = :sold, c.thumbnail_uri = :thumbnail_uri WHERE c.id = :id")
    void update(String title, String description, Category category, String manufacturer, String engine_displacement, String cylinders, String year, String km, String price, boolean sold, String thumbnail_uri,int id);
}
