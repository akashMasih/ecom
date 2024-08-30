package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.User;
import com.ecom.service.UserService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    // get profile
    @GetMapping
    ResponseEntity<User> getCustomerProfile(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // update Address or profile
    // update password
}
