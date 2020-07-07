package com.grz55.workouttracker.auth.service;

import com.grz55.workouttracker.auth.model.ExercisePerformed;
import com.grz55.workouttracker.auth.model.ExerciseSets;
import com.grz55.workouttracker.auth.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceOwningChecker {

    WorkoutService workoutService;
    ExercisePerformedService exercisePerformedService;
    ExerciseSetsService exerciseSetsService;

    @Autowired
    public ResourceOwningChecker(WorkoutService workoutService, ExercisePerformedService exercisePerformedService, ExerciseSetsService exerciseSetsService) {
        this.workoutService = workoutService;
        this.exercisePerformedService = exercisePerformedService;
        this.exerciseSetsService = exerciseSetsService;
    }

    public boolean checkWorkoutOwnership(Long id) {
        Optional<Workout> workoutOptional = workoutService.getWorkout(id);
        if(workoutOptional.isPresent()){
            Workout workout = workoutOptional.get();
            String workoutOwnerUsername = workout.getUser().getUsername();
            String loggedUsername = getLoggedUserUsername();
            if(!workoutOwnerUsername.equals(loggedUsername)){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkExercisePerformedOwnership(Long id) {
        Optional<ExercisePerformed> exercisePerformedOptional = exercisePerformedService.getExercisePerformed(id);
        if(exercisePerformedOptional.isPresent()){
            ExercisePerformed exercisePerformed = exercisePerformedOptional.get();
            Workout workout = exercisePerformed.getWorkout();
            String workoutOwnerUsername = workout.getUser().getUsername();
            String loggedUsername = getLoggedUserUsername();
            if(!workoutOwnerUsername.equals(loggedUsername)){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkExerciseSetsOwnership(Long id) {
        Optional<ExerciseSets> exerciseSetsOptional = exerciseSetsService.getExerciseSets(id);
        if(exerciseSetsOptional.isPresent()){
            ExerciseSets exerciseSets = exerciseSetsOptional.get();
            ExercisePerformed exercisePerformed = exerciseSets.getExercisePerformed();
            Workout workout = exercisePerformed.getWorkout();
            String workoutOwnerUsername = workout.getUser().getUsername();
            String loggedUsername = getLoggedUserUsername();
            if(!workoutOwnerUsername.equals(loggedUsername)){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkExercisePerformedToWorkoutBelonging(ExercisePerformed exercisePerformed, Workout workout) {
        List<ExercisePerformed> exercisePerformedList = workout.getExercisePerformedList();
        for (ExercisePerformed exPerformed : exercisePerformedList) {
            if(exPerformed.getId().equals(exercisePerformed.getId()))
                return true;
        }
        return false;
    }

    public boolean checkExerciseSetToExercisePerformedBelonging(ExerciseSets exerciseSets, ExercisePerformed exercisePerformed) {
        List<ExerciseSets> exerciseSetsList = exercisePerformed.getExerciseSetsList();
        for (ExerciseSets exSets : exerciseSetsList) {
            if(exSets.getId().equals(exerciseSets.getId()))
                return true;
        }
        return false;
    }

    public String getLoggedUserUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        return userDetails.getUsername();
    }



}
