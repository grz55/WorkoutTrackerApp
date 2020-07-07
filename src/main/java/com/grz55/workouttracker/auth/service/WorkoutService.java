package com.grz55.workouttracker.auth.service;

import com.grz55.workouttracker.auth.model.Workout;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {

    List<Workout> getWorkouts();

    void saveWorkout(Workout workout);

    Optional<Workout> getWorkout(Long id);

    void deleteWorkout(Long id);

    List<Workout> getMyWorkouts(Long id);

}
