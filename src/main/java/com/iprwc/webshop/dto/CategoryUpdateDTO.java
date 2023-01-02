package com.iprwc.webshop.dto;

import com.iprwc.webshop.model.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryUpdateDTO {

    @NotNull
    private String title;

    @NotNull
    private String description;


    public Category toCategory(Category categoryToUpdate) {
        categoryToUpdate.setTitle(title);
        categoryToUpdate.setDescription(description);
        return categoryToUpdate;
    }
}
