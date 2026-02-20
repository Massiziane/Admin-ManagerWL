package org.example.workoutlog.model;

public class Workout {
    private int id;
    private String name;
    private Integer order;
    private Integer frequency;
    private int userId;

    public Workout(int id, String name, Integer order,
                   Integer frequency, int userId) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.frequency = frequency;
        this.userId = userId;
    }
    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getOrder() {
        return order;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public int getUserId() {
        return userId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
