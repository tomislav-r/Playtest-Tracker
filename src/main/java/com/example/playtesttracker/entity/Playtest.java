package com.example.playtesttracker.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table( name = "playtest")
public class Playtest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String gameTitle;

    @Column(nullable = false)
    private LocalDate testDate;

    @Column(nullable = false, length = 150)
    private String testType;

    @Column(nullable = false, length = 150)
    private String status;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 1000)
    private String notes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "platform_id")
    private Platform platform;

    public Playtest() {
    }

    public Playtest(String gameTitle, LocalDate testDate, String testType, String status, Integer rating, String notes, Platform platform) {
        this.gameTitle = gameTitle;
        this.testDate = testDate;
        this.testType = testType;
        this.status = status;
        this.rating = rating;
        this.notes = notes;
        this.platform = platform;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }



}
