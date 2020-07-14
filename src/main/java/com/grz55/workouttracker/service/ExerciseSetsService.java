package com.grz55.workouttracker.service;

import com.grz55.workouttracker.model.ExerciseSets;
import com.grz55.workouttracker.repository.ExerciseSetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExerciseSetsService {

    private final ExerciseSetsRepository exerciseSetsRepository;

    @Autowired
    public ExerciseSetsService(ExerciseSetsRepository exerciseSetsRepository) {
        this.exerciseSetsRepository = exerciseSetsRepository;
    }

    public void saveExerciseSets(ExerciseSets exerciseSets) {
        exerciseSetsRepository.save(exerciseSets);
    }

    public void deleteExerciseSets(Long id) {
        exerciseSetsRepository.deleteById(id);
    }

    public Optional<ExerciseSets> getExerciseSets(Long id) {
        return exerciseSetsRepository.findById(id);
    }
}
