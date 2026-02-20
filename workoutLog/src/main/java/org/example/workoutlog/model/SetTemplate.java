package org.example.workoutlog.model;

public class SetTemplate {
    private int id;
    private Integer reps;
    private Double weight;
    private String tempo;
    private Integer restTime;
    private String type;

    public SetTemplate(int id, Integer reps, Double weight,
                       String tempo, Integer restTime, String type) {
        this.id = id;
        this.reps = reps;
        this.weight = weight;
        this.tempo = tempo;
        this.restTime = restTime;
        this.type = type;
    }
    // Getters
    public int getId() {
        return id;
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

    public String getType() {
        return type;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
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

    public void setType(String type) {
        this.type = type;
    }
}
