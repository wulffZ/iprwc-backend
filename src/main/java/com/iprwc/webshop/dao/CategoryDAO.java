package com.iprwc.webshop.dao;

import com.iprwc.webshop.model.Car;
import com.iprwc.webshop.model.Category;
import com.iprwc.webshop.model.User;
import com.iprwc.webshop.repositories.CategoryRepository;
import com.iprwc.webshop.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CategoryDAO {

    private final CategoryRepository categoryRepository;

    public CategoryDAO(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ArrayList<Category> index(){
        return (ArrayList<Category>) categoryRepository.findAll();
    }

    /**
     * Returns a category object with a specific id.
     * If there is no category with the specified id, returns null
     *
     * @param id the id of the category
     * @return category
     */
    public Category show(Integer id) {
        Optional<Category> category = this.categoryRepository.findById(id);

        return category.orElse(null);
    }

    public Category store(Category category){
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        this.categoryRepository.update(category.getTitle(),  category.getDescription(), category.getProducts(), category.getId());
        return category;
    }
}