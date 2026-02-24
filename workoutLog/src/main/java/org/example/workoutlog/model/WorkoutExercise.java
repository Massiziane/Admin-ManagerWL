package org.example.workoutlog.model;

public class WorkoutExercise {
    private int id;
    private Integer order;
    private String notes;
    private int workoutId;
    private int exerciseId;

    public WorkoutExercise() {}

    public WorkoutExercise(int id, Integer order, String notes,
                           int workoutId, int exerciseId) {
        this.id = id;
        this.order = order;
        this.notes = notes;
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
    }
    // Getters
    public int getId() {
        return id;
    }

    public Integer getOrder() {
        return order;
    }

    public String getNotes() {
        return notes;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}