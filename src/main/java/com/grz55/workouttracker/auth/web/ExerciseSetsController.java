package com.grz55.workouttracker.auth.web;

import com.grz55.workouttracker.auth.model.ExercisePerformed;
import com.grz55.workouttracker.auth.model.ExerciseSets;
import com.grz55.workouttracker.auth.service.ExercisePerformedService;
import com.grz55.workouttracker.auth.service.ExerciseSetsService;
import com.grz55.workouttracker.auth.service.ResourceOwningChecker;
import com.grz55.workouttracker.auth.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ExerciseSetsController {

    ExerciseSetsService exerciseSetsService;
    ExercisePerformedService exercisePerformedService;
    WorkoutService workoutService;
    ResourceOwningChecker resourceOwningChecker;

    @Autowired
    public ExerciseSetsController(ExerciseSetsService exerciseSetsService, ExercisePerformedService exercisePerformedService, ResourceOwningChecker resourceOwningChecker, WorkoutService workoutService) {
        this.exerciseSetsService = exerciseSetsService;
        this.exercisePerformedService = exercisePerformedService;
        this.resourceOwningChecker = resourceOwningChecker;
        this.workoutService = workoutService;
    }

    @PostMapping("/save-exercise-sets/{id}")
    public String saveExerciseSets(@ModelAttribute("exerciseSets") @Valid ExerciseSets exerciseSets, BindingResult bindingResult, @PathVariable(name = "id") Long id){

        if(exerciseSets.getId()==null)
            return "error/500";

        Optional<ExercisePerformed> exercisePerformedOptional = exercisePerformedService.getExercisePerformed(exerciseSets.getId());
        if(!exercisePerformedOptional.isPresent()){
            return "error/404";
        }

        ExercisePerformed exercisePerformed = exercisePerformedOptional.get();
        Long idToRedirect = exercisePerformed.getWorkout().getId();

        if (bindingResult.hasErrors())
            return "redirect:/update-workout/" + idToRedirect;

        if(!resourceOwningChecker.checkExercisePerformedOwnership(exerciseSets.getId())){
            return "error/403";
        }

        if(!resourceOwningChecker.checkExercisePerformedToWorkoutBelonging(exercisePerformed,workoutService.getWorkout(id).get())){
            return "error/403";
        }

        exerciseSets.setId(null);
        exerciseSets.setExercisePerformed(exercisePerformed);
        exercisePerformed.getExerciseSetsList().add(exerciseSets);
        exerciseSetsService.saveExerciseSets(exerciseSets);
        exercisePerformedService.saveExercisePerformed(exercisePerformed);

        return "redirect:/update-workout/"+ idToRedirect;
    }

    @PostMapping("/update-exercise-sets/{id}")
    public String updateExerciseSets(@ModelAttribute("exerciseSets") @Valid ExerciseSets exerciseSets, BindingResult bindingResult, @PathVariable(name = "id") Long id) {

        if(exerciseSets.getId()==null)
            return "error/500";

        if (bindingResult.hasErrors())
            return "redirect:/update-exercise-performed/" + exerciseSetsService.getExerciseSets(exerciseSets.getId()).get().getExercisePerformed().getId();

        if(!exerciseSetsService.getExerciseSets(exerciseSets.getId()).isPresent()){
            return "error/404";
        }

        if(!resourceOwningChecker.checkExerciseSetsOwnership(exerciseSets.getId())){
            return "error/403";
        }

        if(!resourceOwningChecker.checkExerciseSetToExercisePerformedBelonging(exerciseSets, exercisePerformedService.getExercisePerformed(id).get())){
            System.out.println("Set doesn't belong to exercise performed");
            return "error/403";
        }

        exerciseSets.setExercisePerformed(exerciseSetsService.getExerciseSets(exerciseSets.getId()).get().getExercisePerformed());
        exerciseSetsService.saveExerciseSets(exerciseSets);
        Long exercisePerformedId = exerciseSets.getExercisePerformed().getId();
        return "redirect:/update-exercise-performed/" + exercisePerformedId;
    }

    @GetMapping("/delete-exercise-sets/{id}")
    public String deleteExerciseSets(@PathVariable(name = "id") Long id) {
        if(!resourceOwningChecker.checkExerciseSetsOwnership(id))
            return "error/403";

        Optional<ExerciseSets> exerciseSetsOptional = exerciseSetsService.getExerciseSets(id);
        if(exerciseSetsOptional.isPresent()){
            ExerciseSets exerciseSets = exerciseSetsOptional.get();
            Long exercisePerformedId = exerciseSets.getExercisePerformed().getId();
            exerciseSetsService.deleteExerciseSets(id);
            return "redirect:/update-exercise-performed/" + exercisePerformedId;
        }
        return "error/404";
    }
}
