package com.grz55.workouttracker.auth.service;

import com.grz55.workouttracker.auth.model.ExerciseSets;

import java.util.Optional;

public interface ExerciseSetsService {

    void saveExerciseSets(ExerciseSets exerciseSets);

    void deleteExerciseSets(Long id);

    Optional<ExerciseSets> getExerciseSets(Long id);
}
