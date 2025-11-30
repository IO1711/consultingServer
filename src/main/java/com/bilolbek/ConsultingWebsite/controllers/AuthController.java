package com.bilolbek.ConsultingWebsite.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bilolbek.ConsultingWebsite.DTO.LoginDTO;
import com.bilolbek.ConsultingWebsite.DTO.RegisterDTO;
import com.bilolbek.ConsultingWebsite.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private UserService userService;

    /*
        {
            "firstname" : "string",
            "lastname" : "string",
            "avatar" : "string",
            "email" : "string",
            "password" : "string"
        }
    */
    @PostMapping("register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDTO registerDTO){

        return userService.register(registerDTO);
    }

    /*
        {
            "email" : "string",
            "password" : "string"
        }
    */
    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO){
        return userService.verify(loginDTO.getEmail(), loginDTO.getPassword());
    }

    @PostMapping("registerAdmin")
    public ResponseEntity<Map<String, String>> registerAdmin(@RequestBody RegisterDTO registerDTO){
        return userService.registerAdmin(registerDTO);
    }
}
