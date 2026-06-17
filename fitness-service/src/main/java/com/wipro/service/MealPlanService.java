package com.wipro.service;

import java.util.List;

import com.wipro.dto.MealPlanRequest;
import com.wipro.dto.MealPlanResponse;

public interface MealPlanService {

    MealPlanResponse createMealPlan(
            MealPlanRequest request);

    MealPlanResponse getMealPlanById(
            Long id);

    List<MealPlanResponse> getMealPlansByUserId(
            Long userId);
    
    MealPlanResponse updateMealPlan(
            Long id,
            MealPlanRequest request);

    void deleteMealPlan(
            Long id);
}