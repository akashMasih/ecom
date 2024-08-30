package com.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.config.JwtProvider;
import com.ecom.model.Address;
import com.ecom.model.User;
import com.ecom.repository.UserRepository;
import com.ecom.request.CreateUserRequest;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws Exception {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception("User not found with id " + id);

        }
        return user.get();

    }

    @Override
    public void deleteUserById(Long id) throws Exception {
        User user = getUserById(id);
        userRepository.delete(user);

    }

    @Override
    public User updateUser(Long id, CreateUserRequest createUserRequest) throws Exception {
        User user = getUserById(id);
        List<Address> addresses = createUserRequest.getAddresses();
        for (Address address : addresses) {
            user.getAddresses().add(address);
        }
        if (createUserRequest.getName() != null)
            user.setName(createUserRequest.getName());
        if (createUserRequest.getEmail() != null)
            user.setEmail(createUserRequest.getEmail());
        if (createUserRequest.getPhone() != null)
            user.setPhone(createUserRequest.getPhone());
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserByJwtToken(String jwtToken) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwtToken);
        User user = userRepository.findByEmail(email);
        return user;
    }

}
