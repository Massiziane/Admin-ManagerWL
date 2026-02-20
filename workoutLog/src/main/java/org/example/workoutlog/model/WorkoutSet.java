package org.example.workoutlog.model;

public class WorkoutSet {
    private int id;
    private Integer order;
    private Integer setNumber;
    private int workoutExerciseId;
    private Integer setTemplateId;
    private Integer reps;
    private Double weight;
    private String tempo;
    private Integer restTime;

    public WorkoutSet() {}

    public WorkoutSet(int id, Integer order, Integer setNumber,
                      int workoutExerciseId, Integer setTemplateId,
                      Integer reps, Double weight,
                      String tempo, Integer restTime) {
        this.id = id;
        this.order = order;
        this.setNumber = setNumber;
        this.workoutExerciseId = workoutExerciseId;
        this.setTemplateId = setTemplateId;
        this.reps = reps;
        this.weight = weight;
        this.tempo = tempo;
        this.restTime = restTime;
    }
    // Getters
    public int getId() {
        return id;
    }

    public Integer getOrder() {
        return order;
    }

    public Integer getSetNumber() {
        return setNumber;
    }

    public int getWorkoutExerciseId() {
        return workoutExerciseId;
    }

    public Integer getSetTemplateId() {
        return setTemplateId;
    }

    public Integer getReps() {
        return reps;
    }

    public Double getWeight() {
        return weight;
    }

    public String getTempo() {
        return tempo;
    }

    public Integer getRestTime() {
        return restTime;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setSetNumber(Integer setNumber) {
        this.setNumber = setNumber;
    }

    public void setWorkoutExerciseId(int workoutExerciseId) {
        this.workoutExerciseId = workoutExerciseId;
    }

    public void setSetTemplateId(Integer setTemplateId) {
        this.setTemplateId = setTemplateId;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public void setRestTime(Integer restTime) {
        this.restTime = restTime;
    }
}