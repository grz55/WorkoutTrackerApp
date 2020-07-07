package com.grz55.workouttracker.auth.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
