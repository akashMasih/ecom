package com.ecom.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;

}
