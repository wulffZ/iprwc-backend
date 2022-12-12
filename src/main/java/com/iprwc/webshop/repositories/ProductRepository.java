package com.iprwc.webshop.repositories;

import com.iprwc.webshop.model.Category;
import com.iprwc.webshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Product p SET p.title = :title, p.description = :description, p.thumbnail_uri = :thumbnail_uri, p.category = :category WHERE p.id = :id")
    void update(String title, String description, String thumbnail_uri, Category category, int id);
}
