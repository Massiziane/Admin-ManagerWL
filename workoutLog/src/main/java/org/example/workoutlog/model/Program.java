package org.example.workoutlog.model;

public class Program {
    private int id;
    private String name;
    private String desc;
    private int userId;

    public Program(int id, String name, String desc, int userId) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.userId = userId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
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

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
