package com.grz55.workouttracker.auth.service;

import com.grz55.workouttracker.auth.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
