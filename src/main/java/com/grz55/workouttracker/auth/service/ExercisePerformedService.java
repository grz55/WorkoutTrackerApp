package com.grz55.workouttracker.auth.service;

import com.grz55.workouttracker.auth.model.ExercisePerformed;

import java.util.List;
import java.util.Optional;

public interface ExercisePerformedService {

    void saveExercisePerformed(ExercisePerformed exercisePerformed);

    void saveExercisesPerformed(List<ExercisePerformed> exercisePerformedList);

    Optional<ExercisePerformed> getExercisePerformed(Long id);

    void deleteExercisePerformed(Long id);
}
