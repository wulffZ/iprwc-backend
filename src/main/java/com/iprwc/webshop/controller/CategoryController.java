package com.iprwc.webshop.controller;

import com.iprwc.webshop.Message;
import com.iprwc.webshop.dao.CarDAO;
import com.iprwc.webshop.dao.CategoryDAO;
import com.iprwc.webshop.dto.CarUpdateDTO;
import com.iprwc.webshop.dto.CategoryStoreDTO;
import com.iprwc.webshop.dto.CategoryUpdateDTO;
import com.iprwc.webshop.model.ApiResponse;
import com.iprwc.webshop.model.Car;
import com.iprwc.webshop.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

import static java.util.Objects.isNull;

@CrossOrigin
@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryDAO categoryDAO;

    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity index() {
        ArrayList<Category> category = categoryDAO.index();

        return new ApiResponse(HttpStatus.OK, category).getResponse();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity show(@PathVariable int id) {
        if(isNull(categoryDAO.show(id))){
            return new ApiResponse(HttpStatus.NOT_FOUND, "category with id " + id + " could not be found").getResponse();
        }

        return new ApiResponse(HttpStatus.OK, categoryDAO.show(id)).getResponse();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity store(@Valid @NotNull @RequestBody CategoryStoreDTO categoryStoreDTO) {
        Category category = categoryStoreDTO.toCategory();
        Category returnedCategory = categoryDAO.store(category);

        if(returnedCategory != null){
            return new ApiResponse(HttpStatus.CREATED, category).getResponse();
        }

        return new ApiResponse(HttpStatus.BAD_REQUEST, new Message("Something went wrong with creating the role")).getResponse();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(@Valid @NotNull @RequestBody CategoryUpdateDTO categoryUpdateDTO, @PathVariable int id) {
        Category categoryToUpdate = categoryDAO.show(id);

        Category updatedCategory = categoryUpdateDTO.toCategory(categoryToUpdate);
        boolean result = categoryDAO.update(updatedCategory);

        if (result) {
            return new ApiResponse(HttpStatus.CREATED, updatedCategory).getResponse();
        }

        return new ApiResponse(HttpStatus.BAD_REQUEST, new Message("Something went wrong with updating the car")).getResponse();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Category category = categoryDAO.show(id);

        boolean response = categoryDAO.delete(category);
        if(response){
            return new ApiResponse(HttpStatus.NO_CONTENT, new Message("Deleted category with id " + id)).getResponse();
        }

        return new ApiResponse(HttpStatus.NOT_FOUND, new Message("Could not find category with id " + id)).getResponse();
    }
}
