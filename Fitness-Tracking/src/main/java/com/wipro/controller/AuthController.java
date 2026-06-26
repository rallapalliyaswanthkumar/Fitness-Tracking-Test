package com.wipro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.dto.AuthResponse;
import com.wipro.dto.LoginRequest;
import com.wipro.dto.RegisterRequest;
import com.wipro.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController                                                  // This class handles API requests//
@RequestMapping("/api/auth")                                     //Base for All URLs start with /api/auth//
@RequiredArgsConstructor                                         // automatically injects dependencies (AuthService)//
public class AuthController {

    private final AuthService authService;                         //Controller is calling service layer, they are only forwarding request//

    @PostMapping("/register")                               //API for user registration → POST /api/auth/register
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {    // Takes JSON input and validates it
                                                              
 
    	
        return ResponseEntity
                .status(HttpStatus.CREATED)                   // Returns HTTP 201 (Created)
                .body(authService.register(request));             // Calls service to register user
    }

    @PostMapping("/login")                      // API for login → POST /api/auth/login
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {      // Takes login data (email, password)


        return ResponseEntity.ok(
                authService.login(request));         // Calls service to authenticate user
    }
}