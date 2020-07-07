package com.grz55.workouttracker.auth.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "exercise_performed")
public class ExercisePerformed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "exercisePerformed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSets> exerciseSetsList;

    @ManyToOne
    private Workout workout;

    @NotNull
    @Size(min = 1)
    private String name;

    @Min(value = 0)
    @NotNull
    private int restPeriod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRestPeriod() {
        return restPeriod;
    }

    public void setRestPeriod(int restPeriod) {
        this.restPeriod = restPeriod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public List<ExerciseSets> getExerciseSetsList() {
        return exerciseSetsList;
    }

    public void setExerciseSetsList(List<ExerciseSets> exerciseSetsList) {
        this.exerciseSetsList = exerciseSetsList;
    }

}
