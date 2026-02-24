package org.example.workoutlog.model;

public class ProgramWorkout {
    private int id;
    private int programId;
    private int workoutId;
    private int order;

    public ProgramWorkout() {}

    public ProgramWorkout(int id, int programId,
                          int workoutId, int order) {
        this.id = id;
        this.programId = programId;
        this.workoutId = workoutId;
        this.order = order;
    }
    // Getters
    public int getId() {
        return id;
    }

    public int getProgramId() {
        return programId;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public int getOrder() {
        return order;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}