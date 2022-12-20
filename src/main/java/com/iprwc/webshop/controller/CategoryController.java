package com.iprwc.webshop.controller;

import com.iprwc.webshop.Message;
import com.iprwc.webshop.dao.CarDAO;
import com.iprwc.webshop.dao.CategoryDAO;
import com.iprwc.webshop.dto.CategoryStoreDTO;
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
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseEntity update(@Valid @NotNull @RequestBody RoleStoreDTO roleStoreDTO, @PathVariable int id) {
//        Role role = roleStoreDTO.toValidRole();
//        boolean response = roleDAO.update(role, id);
//
//        if(response) {
//            return new ApiResponse(HttpStatus.CREATED, role).getResponse();
//        }
//
//        return new ApiResponse(HttpStatus.BAD_REQUEST, new Message("The role you created is invalid")).getResponse();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public ResponseEntity delete(@PathVariable int id) {
//        boolean response = roleDAO.delete(id);
//
//        if(response){
//            return new ApiResponse(HttpStatus.NO_CONTENT, new Message("Deleted role with id " + id)).getResponse();
//        }
//
//        return new ApiResponse(HttpStatus.NOT_FOUND, new Message("Could not find role with id " + id)).getResponse();
//    }
}
