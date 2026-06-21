package com.example.playtesttracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "platform")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Naziv platforme je obavezan.")
    @Column (nullable = false, length = 150)
    private String name;

    @Column (length = 255)
    private String website;

    @Column (length = 500)
    private String description;

    @OneToMany (mappedBy = "platform")
    private List<Playtest> playtests = new ArrayList<>();

    public Platform() {
    }

    public Platform(String name, String website, String description) {
        this.name = name;
        this.website = website;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Playtest> getPlaytests() {
        return playtests;
    }

    public void setPlaytests(List<Playtest> playtests) {
        this.playtests = playtests;
    }
}
