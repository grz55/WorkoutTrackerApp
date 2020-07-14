package com.grz55.workouttracker.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "exercise_sets")
public class ExerciseSets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0)
    @NotNull
    private int weight;

    @Min(value = 0)
    @NotNull
    private int reps;

    @ManyToOne
    private ExercisePerformed exercisePerformed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public ExercisePerformed getExercisePerformed() {
        return exercisePerformed;
    }

    public void setExercisePerformed(ExercisePerformed exercisePerformed) {
        this.exercisePerformed = exercisePerformed;
    }

    @Override
    public String toString() {
        return weight + "kg x " + reps;
    }
}
