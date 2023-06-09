package com.revature.hikingbuddy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.revature.hikingbuddy.dtos.requests.NewLoginRequest;
import com.revature.hikingbuddy.dtos.requests.NewUserRequest;
import com.revature.hikingbuddy.dtos.responses.Principal;
import com.revature.hikingbuddy.entities.User;
import com.revature.hikingbuddy.services.TokenService;
import com.revature.hikingbuddy.services.UserService;
import com.revature.hikingbuddy.utils.custom_exceptions.UserAlreadyExistsException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userservice;
    private final TokenService tokenservice;


    @PostMapping("/register")
    public ResponseEntity<?> createuser(@RequestBody NewUserRequest rq)
    {
        
        System.out.println("In responseHandler");
        Boolean isUniqueUsername = userservice.isUniqueUsername(rq.getUsername());
        if(isUniqueUsername == false)
        {
            throw new UserAlreadyExistsException("User Already exists. Exception thrown at AuthController.createuser()");
        }
        User user = userservice.registerUser(rq);
       return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody NewLoginRequest rq)
    {
        Principal principal = userservice.login(rq);

        String token = tokenservice.generateToken(principal);

        principal.setToken(token);


        return ResponseEntity.status(HttpStatus.OK).body(principal);
    }
   
    
}
