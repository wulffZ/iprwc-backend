package com.iprwc.webshop.dto;

import com.iprwc.webshop.Permission;
import com.iprwc.webshop.model.Role;
import com.iprwc.webshop.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class UserUpdateDTO {

    @Pattern(regexp = "^[A-Za-z1-9 @.]{1,}$")
    @NotNull(message = "The email attribute is required")
    private String email;

    @Pattern(regexp = "^[A-Za-z @.]{1,}$")
    @NotNull(message = "The name attribute is required")
    private String name;

    @NotNull(message = "The password attribute is required")
    private String password;

    private List<Role> roles;

    public User toUser(User userToUpdate, User requestUser) {
        userToUpdate.setEmail(this.email);
        userToUpdate.setName(this.name);
        userToUpdate.setPassword(this.password);

        if(!roles.isEmpty()) {
            for(Role role: requestUser.getRoles()) {
                List<String> permissions = List.of(role.getPermissions());
                if(permissions.contains(String.valueOf(Permission.ADMIN))){
                    userToUpdate.setRoles(this.roles);
                }
            }
        }

        return userToUpdate;
    }
}
