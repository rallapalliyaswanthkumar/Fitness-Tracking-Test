package com.wipro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.entity.FitnessProfile;
import com.wipro.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;                                                              // Service layer for user operations

    @GetMapping("/{userId}/profile")                                                                    //This API fetches the fitness profile of a user using userId.
    public ResponseEntity<FitnessProfile> getProfile(
            @PathVariable Long userId) {                                                                 // Takes userId from URL

        return ResponseEntity.ok(
                userService.getProfile(userId));                                                         // Fetch user profile from service
    }

    @PutMapping("/{userId}/profile")                                                                         //This API updates the fitness profile of the user.
    public ResponseEntity<FitnessProfile> updateProfile(
            @PathVariable Long userId,
            @RequestBody FitnessProfile profile) {                                                         // Takes profile data


        return ResponseEntity.ok(
                userService.updateProfile(
                        userId,
                        profile));                                                                        // Updates profile in DB
    }
}