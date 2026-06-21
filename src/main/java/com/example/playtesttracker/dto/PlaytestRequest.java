package com.example.playtesttracker.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PlaytestRequest {

    @NotBlank(message = "Naziv igre je obavezan.")
    private String gameTitle;

    @NotNull(message = "Datum testiranja je obavezan.")
    private LocalDate testDate;

    @NotBlank(message = "Vrsta testa je obavezna.")
    private String testType;

    @NotBlank(message = "Status je obavezan.")
    private String status;

    @NotNull(message = "Ocjena je obavezna.")
    @Min(value = 1, message = "Ocjena mora biti najmanje 1.")
    @Max(value = 10, message = "Ocjena može biti najviše 10.")
    private Integer rating;

    private String notes;

    @NotNull(message = "Platforma je obavezna.")
    private Long platformId;

    public PlaytestRequest() {
    }

    public PlaytestRequest(String gameTitle, LocalDate testDate, String testType, String status, Integer rating, String notes, Long platformId) {
        this.gameTitle = gameTitle;
        this.testDate = testDate;
        this.testType = testType;
        this.status = status;
        this.rating = rating;
        this.notes = notes;
        this.platformId = platformId;
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

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
}