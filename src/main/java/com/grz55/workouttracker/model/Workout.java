package com.grz55.workouttracker.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Must match date pattern")
    private String date;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExercisePerformed> exercisePerformedList;

    @ManyToOne
    private User user;

    @Column(name = "additional_comment")
    private String additionalComment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ExercisePerformed> getExercisePerformedList() {
        return exercisePerformedList;
    }

    public void setExercisePerformedList(List<ExercisePerformed> exercisePerformedList) {
        this.exercisePerformedList = exercisePerformedList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", exercisePerformedList=" + exercisePerformedList +
                ", user=" + user +
                ", additionalComment='" + additionalComment + '\'' +
                '}';
    }
}
