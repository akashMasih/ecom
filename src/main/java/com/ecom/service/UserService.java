package com.ecom.service;

import java.util.List;
import com.ecom.model.User;
import com.ecom.request.CreateUserRequest;

public interface UserService {


    public List<User> getAllUsers();

    public User getUserById(Long id) throws Exception;

    public void deleteUserById(Long id) throws Exception;

    public User updateUser(Long id, CreateUserRequest createUserRequest) throws Exception;

    public User getUserByJwtToken(String jwtToken) throws Exception;

}
