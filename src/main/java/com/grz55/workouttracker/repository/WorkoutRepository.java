package com.grz55.workouttracker.repository;

import com.grz55.workouttracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM workout WHERE user_id = :id")
    List<Workout> findByUserId(Long id);
}
