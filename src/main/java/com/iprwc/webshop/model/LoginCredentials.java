package com.iprwc.webshop.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginCredentials {

    private String email;
    private String password;
    private String roles;
}

