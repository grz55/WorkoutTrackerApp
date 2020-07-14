package com.grz55.workouttracker.repository;

import com.grz55.workouttracker.model.ExercisePerformed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercisePerformedRepository extends JpaRepository<ExercisePerformed, Long> {
}
