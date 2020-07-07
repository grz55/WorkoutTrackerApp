package com.grz55.workouttracker.auth.service;

import com.grz55.workouttracker.auth.model.ExerciseSets;
import com.grz55.workouttracker.auth.repository.ExerciseSetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExerciseSetsServiceImpl implements ExerciseSetsService {

    ExerciseSetsRepository exerciseSetsRepository;

    @Autowired
    public ExerciseSetsServiceImpl(ExerciseSetsRepository exerciseSetsRepository) {
        this.exerciseSetsRepository = exerciseSetsRepository;
    }

    @Override
    public void saveExerciseSets(ExerciseSets exerciseSets) {
        exerciseSetsRepository.save(exerciseSets);
    }

    @Override
    public void deleteExerciseSets(Long id) {
        exerciseSetsRepository.deleteById(id);
    }

    @Override
    public Optional<ExerciseSets> getExerciseSets(Long id) {
        return exerciseSetsRepository.findById(id);
    }
}
