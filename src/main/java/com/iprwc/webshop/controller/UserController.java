package com.iprwc.webshop.controller;

import com.iprwc.webshop.Message;
import com.iprwc.webshop.dao.UserDAO;
import com.iprwc.webshop.dto.UserUpdateDTO;
import com.iprwc.webshop.model.ApiResponse;
import com.iprwc.webshop.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/info")
    public User getInfo(){
        return userDAO.getUserDetails();
    }

    @RequestMapping(value = "{employee_id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateUserRole(@PathVariable("employee_id") int employee_id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        User originalUser = this.userDAO.show(employee_id);
        if(isNull(originalUser)){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, new Message("Could not find the employee")).getResponse();
        }

        User user = userUpdateDTO.toUser(originalUser, this.userDAO.getUserDetails());

        user = this.userDAO.update(user);

        return new ApiResponse<>(HttpStatus.OK, user).getResponse();
    }

}
