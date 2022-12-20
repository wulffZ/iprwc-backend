package com.iprwc.webshop.dto;

import com.iprwc.webshop.model.Category;
import com.iprwc.webshop.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
