package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.User;
import com.ecom.request.CreateUserRequest;
import com.ecom.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("/user/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {

        User user = userService.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping("/user/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable Long id) throws Exception {

        userService.deleteUserById(id);

        return new ResponseEntity<>("user has been deleted successfully", HttpStatus.OK);

    }

    @PutMapping("/user/{id}")
    ResponseEntity<User> updateByUserId(@PathVariable Long id, @RequestBody CreateUserRequest req) throws Exception {

        User user = userService.updateUser(id, req);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
