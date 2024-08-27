package com.ecom.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.ecom.config.JwtProvider;
import com.ecom.model.Cart;
import com.ecom.model.USER_ROLE;
import com.ecom.model.User;
import com.ecom.repository.CartRepository;
import com.ecom.repository.UserRepository;
import com.ecom.request.LoginRequest;
import com.ecom.response.AuthResponse;
import com.ecom.service.CustomUserDetailService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController()
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isEmailExist = userRepository.findByEmail(user.getEmail());

        if (isEmailExist != null) {
            throw new Exception("Email already used with another account");
        }
        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setName(user.getName());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setRole(user.getRole());
        userRepository.save(createdUser);

        User savedUser = userRepository.save(createdUser);
        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);
        Authentication authentication = authenticate(user.getEmail(), user.getPassword());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwtToken);
        authResponse.setMessage("User created successfully");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {
        String userName = req.getEmail();
        String password = req.getPassword();
        Authentication authentication = authenticate(userName, password);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        String jwtToken = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwtToken);

        authResponse.setRole(USER_ROLE.valueOf(role));
        authResponse.setMessage("User logged in successfully");

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username or Password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Username or Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
