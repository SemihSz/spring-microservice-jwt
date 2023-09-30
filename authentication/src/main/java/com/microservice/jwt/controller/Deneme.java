package com.microservice.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Semih, 30.09.2023
 */
@RestController
@RequiredArgsConstructor
public class Deneme {

    @GetMapping("/validate")
    public String validateToken() {
        return "Token is valid";
    }

}
