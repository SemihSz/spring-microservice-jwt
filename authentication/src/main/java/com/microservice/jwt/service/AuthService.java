package com.food.recipe.social.api.service;

import com.food.recipe.social.api.model.JwtResponse;
import com.food.recipe.social.api.repository.UserCredentialRepository;
import com.food.recipe.social.api.entity.UserCredential;
import com.food.recipe.social.api.model.AuthLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    private final LoginService loginService;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public JwtResponse login(AuthLoginRequest request) {

        return loginService.apply(request);
    }


}
