package com.grz55.workouttracker.auth.web;

import com.grz55.workouttracker.auth.model.ExercisePerformed;
import com.grz55.workouttracker.auth.model.ExerciseSets;
import com.grz55.workouttracker.auth.model.Workout;
import com.grz55.workouttracker.auth.service.ExercisePerformedService;
import com.grz55.workouttracker.auth.service.ExerciseSetsService;
import com.grz55.workouttracker.auth.service.ResourceOwningChecker;
import com.grz55.workouttracker.auth.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ExercisePerformedController {

    ExercisePerformedService exercisePerformedService;
    WorkoutService workoutService;
    ExerciseSetsService exerciseSetsService;
    ResourceOwningChecker resourceOwningChecker;

    @Autowired
    public ExercisePerformedController(ExercisePerformedService exercisePerformedService, WorkoutService workoutService, ExerciseSetsService exerciseSetsService, ResourceOwningChecker resourceOwningChecker) {
        this.exercisePerformedService = exercisePerformedService;
        this.workoutService = workoutService;
        this.exerciseSetsService = exerciseSetsService;
        this.resourceOwningChecker = resourceOwningChecker;
    }

    @PostMapping("/save-exercise-performed/{id}")
    public String saveExercisePerformed(@ModelAttribute("exercisePerformed") @Valid ExercisePerformed exercisePerformed, BindingResult bindingResult, @PathVariable(name = "id") Long id) {

        if (bindingResult.hasErrors())
            return "redirect:/update-workout/" + id;

        Optional<Workout> workoutOptional = workoutService.getWorkout(id);
        if(workoutOptional.isPresent()){
            Workout workout = workoutOptional.get();
            exercisePerformed.setId(null);
            exercisePerformed.setWorkout(workout);
            workout.getExercisePerformedList().add(exercisePerformed);
            exercisePerformedService.saveExercisePerformed(exercisePerformed);
            workoutService.saveWorkout(workout);
            return "redirect:/update-workout/" + id;
        }
        return "error/404";
    }

    @GetMapping("/update-exercise-performed/{id}")
    public String showUpdateExercisePerformedForm(@PathVariable(name = "id") Long id, Model model) {
        if(!resourceOwningChecker.checkExercisePerformedOwnership(id))
            return "error/403";

        Optional<ExercisePerformed> exercisePerformedOptional = exercisePerformedService.getExercisePerformed(id);
        if(exercisePerformedOptional.isPresent()){
            ExercisePerformed exercisePerformed = exercisePerformedOptional.get();
            model.addAttribute("exercisePerformed",exercisePerformed);
            model.addAttribute("exerciseSets", new ExerciseSets());
            return "edit-exercise-performed-form";
        }
        return "error/404";
    }

    @PostMapping("/update-exercise-performed/{id}")
    public String updateExercisePerformed(@PathVariable(name = "id") Long id, @Valid ExercisePerformed exercisePerformed, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "redirect:/update-exercise-performed/" + exercisePerformed.getId();
        }

        exercisePerformed.setWorkout(exercisePerformedService.getExercisePerformed(id).get().getWorkout());
        exercisePerformed.setExerciseSetsList(exercisePerformedService.getExercisePerformed(id).get().getExerciseSetsList());
        exercisePerformedService.saveExercisePerformed(exercisePerformed);
        return "redirect:/update-workout/" + exercisePerformed.getWorkout().getId();
    }

    @GetMapping("/delete-exercise-performed/{id}")
    public String deleteExercisePerformed(@PathVariable(name = "id") Long id) {

        if(!resourceOwningChecker.checkExercisePerformedOwnership(id))
            return "error/403";

        Optional<ExercisePerformed> exercisePerformedOptional = exercisePerformedService.getExercisePerformed(id);
        if(exercisePerformedOptional.isPresent()){
            ExercisePerformed exercisePerformed = exercisePerformedOptional.get();
            Workout workout = exercisePerformed.getWorkout();
            Long workoutId = workout.getId();
            exercisePerformedService.deleteExercisePerformed(id);
            return "redirect:/update-workout/" + workoutId;
        }
        return "error/404";
    }
}
