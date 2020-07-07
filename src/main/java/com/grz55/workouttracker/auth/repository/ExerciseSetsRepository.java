package com.grz55.workouttracker.auth.repository;

import com.grz55.workouttracker.auth.model.ExerciseSets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseSetsRepository extends JpaRepository<ExerciseSets,Long> {
}
