package com.wipro.service;



import org.springframework.stereotype.Service;

import com.wipro.entity.FitnessProfile;
import com.wipro.entity.User;
import com.wipro.exception.UserNotFoundException;
import com.wipro.repository.FitnessProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final FitnessProfileRepository profileRepository;

    @Override
    public FitnessProfile getProfile(Long userId) {

        return profileRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "Profile not found"));
    }

    @Override
    public FitnessProfile updateProfile(Long userId, FitnessProfile profile) {

        return profileRepository.findByUserId(userId)
                .map(existing -> {
                    existing.setAge(profile.getAge());
                    existing.setHeight(profile.getHeight());
                    existing.setWeight(profile.getWeight());
                    existing.setGoal(profile.getGoal());
                    existing.setGender(profile.getGender());
                    return profileRepository.save(existing);
                })
                .orElseGet(() -> {

                    FitnessProfile newProfile = new FitnessProfile();

                    
                    User user = new User();
                    user.setId(userId);
                    newProfile.setUser(user);

                    newProfile.setAge(profile.getAge());
                    newProfile.setHeight(profile.getHeight());
                    newProfile.setWeight(profile.getWeight());
                    newProfile.setGoal(profile.getGoal());
                    newProfile.setGender(profile.getGender());

                    return profileRepository.save(newProfile);
                });
    }


}
