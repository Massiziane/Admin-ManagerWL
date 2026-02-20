package org.example.workoutlog.model;

public class User {
    private int id;
    private String clerkId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String role;
    private boolean isActive;

    public User(int id, String clerkId, String firstName, String lastName,
                String username, String email, String role, boolean isActive) {
        this.id = id;
        this.clerkId = clerkId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getClerkId() {
        return clerkId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }


    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setClerkId(String clerkId) {
        this.clerkId = clerkId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
