package com.wipro.dto;

import lombok.Data;

@Data
public class MealPlanRequest {

    private Long userId;
    private String mealName;
    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fats;
}