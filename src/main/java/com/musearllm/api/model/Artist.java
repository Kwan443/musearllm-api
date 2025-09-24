package com.musearllm.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Artist {
    private UUID artistId;
    private String name;
    private String biography;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String nationality;
    private String wikipediaUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    public UUID getArtistId() { return artistId; }
    public void setArtistId(UUID artistId) { this.artistId = artistId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public LocalDate getDeathDate() { return deathDate; }
    public void setDeathDate(LocalDate deathDate) { this.deathDate = deathDate; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getWikipediaUrl() { return wikipediaUrl; }
    public void setWikipediaUrl(String wikipediaUrl) { this.wikipediaUrl = wikipediaUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}