package org.example.workoutlog.model;

public class Exercise {
    private int id;
    private String name;
    private String notes;
    private Integer categoryId;
    private Integer muscleGroupId;

    public Exercise(int id, String name, String notes,
                    Integer categoryId, Integer muscleGroupId) {
        this.id = id;
        this.name = name;
        this.notes = notes;
        this.categoryId = categoryId;
        this.muscleGroupId = muscleGroupId;
    }
    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getMuscleGroupId() {
        return muscleGroupId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setMuscleGroupId(Integer muscleGroupId) {
        this.muscleGroupId = muscleGroupId;
    }
}
