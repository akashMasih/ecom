package com.ecom.service;

import java.util.List;
import com.ecom.model.User;
import com.ecom.request.CreateUserRequest;

public interface UserService {

    public User createUser(CreateUserRequest createUserRequest) throws Exception;

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public void deleteUserById(Long id);

    public User updateUser(Long id, CreateUserRequest createUserRequest);

}
