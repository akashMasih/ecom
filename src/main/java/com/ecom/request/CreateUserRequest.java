package com.ecom.request;

import java.util.List;

import com.ecom.model.Address;
import com.ecom.model.USER_ROLE;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")

    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private USER_ROLE role;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;
    private List<Address> addresses;

}
