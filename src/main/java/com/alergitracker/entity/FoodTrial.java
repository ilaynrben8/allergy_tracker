package com.alergitracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class FoodTrial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "baby_id")
    @JsonIgnore
    private Baby baby;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(255)")
    private TrialStatus status;

    private LocalDate trialDate;
    private String reactionDescription;
    private Integer severity;

    public FoodTrial() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Baby getBaby() { return baby; }
    public void setBaby(Baby baby) { this.baby = baby; }
    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }
    public TrialStatus getStatus() { return status; }
    public void setStatus(TrialStatus status) { this.status = status; }
    public LocalDate getTrialDate() { return trialDate; }
    public void setTrialDate(LocalDate trialDate) { this.trialDate = trialDate; }
    public String getReactionDescription() { return reactionDescription; }
    public void setReactionDescription(String reactionDescription) { this.reactionDescription = reactionDescription; }
    public Integer getSeverity() { return severity; }
    public void setSeverity(Integer severity) { this.severity = severity; }
}