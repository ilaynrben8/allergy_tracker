package com.alergitracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Baby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @Column(length = 1000)
    private String notes;

    @OneToMany(mappedBy = "baby", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodTrial> foodTrials = new ArrayList<>();

    public Baby() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public List<FoodTrial> getFoodTrials() { return foodTrials; }
    public void setFoodTrials(List<FoodTrial> foodTrials) { this.foodTrials = foodTrials; }
}