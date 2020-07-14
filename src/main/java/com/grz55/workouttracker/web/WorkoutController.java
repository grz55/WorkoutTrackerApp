package com.grz55.workouttracker.web;

import com.grz55.workouttracker.model.ExercisePerformed;
import com.grz55.workouttracker.model.ExerciseSets;
import com.grz55.workouttracker.model.User;
import com.grz55.workouttracker.model.Workout;
import com.grz55.workouttracker.service.ExercisePerformedService;
import com.grz55.workouttracker.service.ResourceOwningChecker;
import com.grz55.workouttracker.service.UserService;
import com.grz55.workouttracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class WorkoutController {

    private final UserService userService;
    private final WorkoutService workoutService;
    private final ExercisePerformedService exercisePerformedService;
    private final ResourceOwningChecker resourceOwningChecker;

    @Autowired
    public WorkoutController(UserService userService, WorkoutService workoutService, ExercisePerformedService exercisePerformedService, ResourceOwningChecker resourceOwningChecker) {
        this.userService = userService;
        this.workoutService = workoutService;
        this.exercisePerformedService = exercisePerformedService;
        this.resourceOwningChecker = resourceOwningChecker;
    }

    @GetMapping({"/", "/my-workouts"})
    public String listMyWorkouts(Model model) {
        List<Workout> myWorkoutsList = workoutService.getMyWorkouts(getLoggedUserId());
        model.addAttribute("workoutsList", myWorkoutsList);
        model.addAttribute("username", getLoggedUserUsername());
        return "list-my-workouts";
    }

    @GetMapping("/add-workout")
    public String showAddWorkoutForm(Model model) {
        Workout workout = new Workout();
        model.addAttribute("workout", workout);
        return "add-workout-form";
    }

    @PostMapping("/save-workout")
    public String saveWorkout(@Valid @ModelAttribute("workout") Workout workout, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "add-workout-form";
        }

        workout.setUser(getLoggedUser());
        workoutService.saveWorkout(workout);

        return "redirect:/update-workout/" + workout.getId();
    }

    @GetMapping("/update-workout/{id}")
    public String showUpdateWorkoutForm(@PathVariable(name = "id") Long id, Model model) {
        if (!resourceOwningChecker.checkWorkoutOwnership(id))
            return "error/403";

        Optional<Workout> workoutOptional = workoutService.getWorkout(id);
        if (workoutOptional.isPresent()) {
            Workout workout = workoutOptional.get();
            model.addAttribute("workout", workout);
            model.addAttribute("exercisePerformedList", workout.getExercisePerformedList());
            model.addAttribute("exercisePerformed", new ExercisePerformed());
            model.addAttribute("exerciseSets", new ExerciseSets());
            return "edit-workout-form";
        }
        return "error/404";
    }

    @PostMapping("/update-workout/{id}")
    public String updateWorkout(@PathVariable(name = "id") Long id, @Valid Workout workout, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "redirect:/update-workout/" + id;
        }

        workout.setUser(getLoggedUser());
        workout.setExercisePerformedList(workoutService.getWorkout(id).get().getExercisePerformedList());
        workoutService.saveWorkout(workout);
        return "redirect:/my-workouts";
    }


    @GetMapping("/delete-workout/{id}")
    public String deleteWorkout(@PathVariable(name = "id") Long id) {
        if (!resourceOwningChecker.checkWorkoutOwnership(id))
            return "error/403";

        workoutService.deleteWorkout(id);
        return "redirect:/my-workouts";
    }

    public String getLoggedUserUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        return userDetails.getUsername();
    }

    public Long getLoggedUserId() {
        User user = getLoggedUser();
        return user.getId();
    }

    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userDetailsName = userDetails.getUsername();
        return userService.findByUsername(userDetailsName);
    }

}
