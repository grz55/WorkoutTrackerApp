package com.grz55.workouttracker.service;

import com.grz55.workouttracker.model.ExercisePerformed;
import com.grz55.workouttracker.repository.ExercisePerformedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExercisePerformedService {

    private final ExercisePerformedRepository exercisePerformedRepository;

    @Autowired
    public ExercisePerformedService(ExercisePerformedRepository exercisePerformedRepository) {
        this.exercisePerformedRepository = exercisePerformedRepository;
    }

    public void saveExercisePerformed(ExercisePerformed exercisePerformed) {
        exercisePerformedRepository.save(exercisePerformed);
    }

    public void saveExercisesPerformed(List<ExercisePerformed> exercisePerformedList) {
        exercisePerformedRepository.saveAll(exercisePerformedList);
    }

    public Optional<ExercisePerformed> getExercisePerformed(Long id) {
        return exercisePerformedRepository.findById(id);
    }

    public void deleteExercisePerformed(Long id) {
        exercisePerformedRepository.deleteById(id);
    }
}
