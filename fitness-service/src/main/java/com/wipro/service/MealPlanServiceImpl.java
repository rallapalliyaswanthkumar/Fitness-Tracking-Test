package com.wipro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.wipro.dto.MealPlanRequest;
import com.wipro.dto.MealPlanResponse;
import com.wipro.dto.UserResponse;
import com.wipro.entity.MealPlan;
import com.wipro.exception.ResourceNotFoundException;
import com.wipro.repository.MealPlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealPlanServiceImpl
        implements MealPlanService {

    private final MealPlanRepository mealPlanRepository;
    private final ModelMapper modelMapper;
    private final UserServiceClient userServiceClient;

    @Override
    public MealPlanResponse createMealPlan(
            MealPlanRequest request) {

        UserResponse user =
                userServiceClient.getUserById(
                        request.getUserId());

        if (user == null) {
            throw new ResourceNotFoundException(
                    "User not found");
        }

        MealPlan mealPlan =
                modelMapper.map(
                        request,
                        MealPlan.class);

        MealPlan saved =
                mealPlanRepository.save(
                        mealPlan);

        return modelMapper.map(
                saved,
                MealPlanResponse.class);
    }

    @Override
    public MealPlanResponse getMealPlanById(
            Long id) {

        MealPlan mealPlan =
                mealPlanRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Meal Plan not found"));

        return modelMapper.map(
                mealPlan,
                MealPlanResponse.class);
    }

    @Override
    public List<MealPlanResponse>
            getMealPlansByUserId(Long userId) {

        return mealPlanRepository
                .findByUserId(userId)
                .stream()
                .map(meal ->
                        modelMapper.map(
                                meal,
                                MealPlanResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public MealPlanResponse updateMealPlan(
            Long id,
            MealPlanRequest request) {

        MealPlan mealPlan =
                mealPlanRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Meal Plan not found with id : "
                                        + id));

        mealPlan.setUserId(request.getUserId());
        mealPlan.setMealName(request.getMealName());
        mealPlan.setCalories(request.getCalories());
        mealPlan.setProtein(request.getProtein());
        mealPlan.setCarbs(request.getCarbs());
        mealPlan.setFats(request.getFats());

        MealPlan updatedMealPlan =
                mealPlanRepository.save(mealPlan);

        return modelMapper.map(
                updatedMealPlan,
                MealPlanResponse.class);
    }

    @Override
    public void deleteMealPlan(Long id) {

        mealPlanRepository.deleteById(id);
    }
}