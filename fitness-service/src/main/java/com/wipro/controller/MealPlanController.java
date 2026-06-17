package com.wipro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.dto.MealPlanRequest;
import com.wipro.dto.MealPlanResponse;
import com.wipro.service.MealPlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealPlanController {

    private final MealPlanService mealPlanService;

    @PostMapping
    public ResponseEntity<MealPlanResponse> createMealPlan(
            @RequestBody MealPlanRequest request) {

        return new ResponseEntity<>(
                mealPlanService.createMealPlan(request),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealPlanResponse> getMealPlanById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                mealPlanService.getMealPlanById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MealPlanResponse>>
            getMealPlansByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                mealPlanService.getMealPlansByUserId(userId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MealPlanResponse>
    updateMealPlan(
            @PathVariable Long id,
            @RequestBody MealPlanRequest request) {

        return ResponseEntity.ok(
                mealPlanService.updateMealPlan(
                        id,
                        request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMealPlan(
            @PathVariable Long id) {

        mealPlanService.deleteMealPlan(id);

        return ResponseEntity.ok(
                "Meal Plan deleted successfully");
    }
}