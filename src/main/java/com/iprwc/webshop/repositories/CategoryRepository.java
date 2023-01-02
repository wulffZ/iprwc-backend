package com.iprwc.webshop.repositories;

import com.iprwc.webshop.model.Category;
import com.iprwc.webshop.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Category c SET c.title = :title, c.description = :description WHERE c.id = :id")
    void update(String title, String description, int id);
}
