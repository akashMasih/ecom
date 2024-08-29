package com.ecom.request;

import java.util.List;

import com.ecom.model.Address;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;
    private List<Address> addresses;

}
