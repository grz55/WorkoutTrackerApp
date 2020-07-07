package com.grz55.workouttracker.auth.service;

import com.grz55.workouttracker.auth.model.ExercisePerformed;
import com.grz55.workouttracker.auth.repository.ExercisePerformedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExercisePerformedServiceImpl implements ExercisePerformedService{

    ExercisePerformedRepository exercisePerformedRepository;

    @Autowired
    public ExercisePerformedServiceImpl(ExercisePerformedRepository exercisePerformedRepository) {
        this.exercisePerformedRepository = exercisePerformedRepository;
    }

    @Override
    public void saveExercisePerformed(ExercisePerformed exercisePerformed) {
        exercisePerformedRepository.save(exercisePerformed);
    }

    @Override
    public void saveExercisesPerformed(List<ExercisePerformed> exercisePerformedList) {
        exercisePerformedRepository.saveAll(exercisePerformedList);
    }

    @Override
    public Optional<ExercisePerformed> getExercisePerformed(Long id) {
        return exercisePerformedRepository.findById(id);
    }

    @Override
    public void deleteExercisePerformed(Long id) {
        exercisePerformedRepository.deleteById(id);
    }
}
