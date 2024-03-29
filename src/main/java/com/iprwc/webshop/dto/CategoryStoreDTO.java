package com.iprwc.webshop.dto;

import com.iprwc.webshop.model.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryStoreDTO {

    @NotNull
    private String title;

    @NotNull
    private String description;


    public Category toCategory() {
        Category category = new Category();
        category.setTitle(title);
        category.setDescription(description);
        return category;
    }

}
