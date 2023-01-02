package com.iprwc.webshop.controller;

import com.iprwc.webshop.Message;
import com.iprwc.webshop.dao.CarDAO;
import com.iprwc.webshop.dao.RoleDAO;
import com.iprwc.webshop.dto.CarStoreDTO;
import com.iprwc.webshop.dto.CarUpdateDTO;
import com.iprwc.webshop.dto.CategoryStoreDTO;
import com.iprwc.webshop.dto.RoleStoreDTO;
import com.iprwc.webshop.model.ApiResponse;
import com.iprwc.webshop.model.Car;
import com.iprwc.webshop.model.Category;
import com.iprwc.webshop.model.Role;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.UUID;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("api/car")
public class CarController {

    private final CarDAO carDAO;

    public CarController(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity index() {
        ArrayList<Car> cars = carDAO.index();

        return new ApiResponse(HttpStatus.OK, cars).getResponse();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity show(@PathVariable int id) {
        if(isNull(carDAO.show(id))){
            return new ApiResponse(HttpStatus.NOT_FOUND, "Car with id " + id + " could not be found").getResponse();
        }

        return new ApiResponse(HttpStatus.OK, carDAO.show(id)).getResponse();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity store(@Valid @NotNull @RequestBody CarStoreDTO carStoreDTO) {
        Car car = carStoreDTO.toCar();
        Car returnedCar = carDAO.store(car);

        if(returnedCar != null){
            return new ApiResponse(HttpStatus.CREATED, car).getResponse();
        }

        return new ApiResponse(HttpStatus.BAD_REQUEST, new Message("Something went wrong with creating the car")).getResponse();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(@Valid @NotNull @RequestBody CarUpdateDTO carUpdateDTO, @PathVariable int id) {
        Car carToUpdate = carDAO.show(id);

        Car updatedCar = carUpdateDTO.toCar(carToUpdate);
        boolean result = carDAO.update(updatedCar);

        if(result){
            return new ApiResponse(HttpStatus.CREATED, updatedCar).getResponse();
        }

        return new ApiResponse(HttpStatus.BAD_REQUEST, new Message("Something went wrong with updating the car")).getResponse();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Car car = carDAO.show(id);

        boolean response = carDAO.delete(car);
        if(response){
            return new ApiResponse(HttpStatus.NO_CONTENT, new Message("Deleted car with id " + id)).getResponse();
        }

        return new ApiResponse(HttpStatus.NOT_FOUND, new Message("Could not find car with id " + id)).getResponse();
    }
}
