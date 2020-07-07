package com.grz55.workouttracker;

import com.grz55.workouttracker.auth.WorkoutTrackerApplication;
import com.grz55.workouttracker.auth.model.ExercisePerformed;
import com.grz55.workouttracker.auth.model.User;
import com.grz55.workouttracker.auth.model.Workout;
import com.grz55.workouttracker.auth.repository.ExercisePerformedRepository;
import com.grz55.workouttracker.auth.repository.WorkoutRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = WorkoutTrackerApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkoutTest {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    ExercisePerformedRepository exercisePerformedRepository;

    @Test
    public void testCreateWorkout() {
        Workout workout = new Workout();
        workout.setAdditionalComment("FBW");
        workout.setDate("2020-06-03");
        User user = new User();
        user.setId((long) 1);
        workout.setUser(user);
        Workout savedWorkout = workoutRepository.save(workout);

        assertNotNull(savedWorkout);

    }

    @Test
    public void testCreateWorkoutsWithExercisesPerformed() {
        exercisePerformedRepository.deleteAll();
        Workout workout = new Workout();
        workout.setAdditionalComment("FBW");
        workout.setDate("2020-06-03");

        User user = new User();
        user.setId((long) 1);
        workout.setUser(user);


        /*Workout workout2 = new Workout();
        workout2.setAdditionalComment("Arms workout");
        workout2.setDate("2020-06-04");
        User user2 = new User();
        user2.setId((long) 2);
        workout.setUser(user2);*/

        ExercisePerformed exercisePerformed = new ExercisePerformed();
        exercisePerformed.setWorkout(workout);
        exercisePerformed.setName("Squat");
        exercisePerformed.setRestPeriod(60);

        ExercisePerformed exercisePerformed2 = new ExercisePerformed();
        exercisePerformed2.setWorkout(workout);
        exercisePerformed2.setName("Deadlift");
        exercisePerformed2.setRestPeriod(90);

        List<ExercisePerformed> exercisePerformedList = new ArrayList<>();
        workout.setExercisePerformedList(exercisePerformedList);

        workout.getExercisePerformedList().add(exercisePerformed);
        workout.getExercisePerformedList().add(exercisePerformed2);

        workoutRepository.save(workout);

        exercisePerformedRepository.save(exercisePerformed);
        exercisePerformedRepository.save(exercisePerformed2);

        System.out.println(exercisePerformedRepository.findAll().toString());

        assertEquals(exercisePerformedRepository.findAll().size(), 2);

    }
}
