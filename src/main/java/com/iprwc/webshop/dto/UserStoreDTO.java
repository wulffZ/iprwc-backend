package com.iprwc.webshop.dto;

import com.iprwc.webshop.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserStoreDTO {

    @Pattern(regexp = "^[A-Za-z1-9 @.]{1,}$")
    @NotNull
    private String email;

    @Pattern(regexp = "^[A-Za-z @.]{1,}$")
    @NotNull
    private String name;

    @NotNull
    private String password;

    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        return user;
    }

}
