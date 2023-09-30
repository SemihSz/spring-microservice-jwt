package com.microservice.jwt.controller;

import com.microservice.jwt.dto.AuthRequest;
import com.microservice.jwt.model.JwtResponse;
import com.microservice.jwt.entity.UserCredential;
import com.microservice.jwt.model.AuthLoginRequest;
import com.microservice.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;


    private final AuthenticationManager authenticationManager;



    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @PostMapping(value = "/login")
    public JwtResponse login(@RequestBody AuthLoginRequest authRequest) {

        return service.login(authRequest);
    }

    @GetMapping("/validate")
    public String validateToken() {
        return "Token is valid";
    }
}
