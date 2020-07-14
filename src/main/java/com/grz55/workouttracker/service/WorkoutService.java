package com.grz55.workouttracker.service;

import com.grz55.workouttracker.model.Workout;
import com.grz55.workouttracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    public void saveWorkout(Workout workout) {
        workoutRepository.save(workout);
    }

    public Optional<Workout> getWorkout(Long id) {
        return workoutRepository.findById(id);
    }

    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }

    public List<Workout> getMyWorkouts(Long id) {
        return workoutRepository.findByUserId(id);
    }
}
