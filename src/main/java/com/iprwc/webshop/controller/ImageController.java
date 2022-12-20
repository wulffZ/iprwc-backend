package com.iprwc.webshop.controller;

import com.iprwc.webshop.Message;
import com.iprwc.webshop.dao.CarDAO;
import com.iprwc.webshop.dto.CarStoreDTO;
import com.iprwc.webshop.model.ApiResponse;
import com.iprwc.webshop.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("api/image/car")
public class ImageController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity show(@PathVariable int id) {
        return new ApiResponse(HttpStatus.OK, "nice").getResponse();
    }
}
