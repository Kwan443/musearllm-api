package com.musearllm.api.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Artwork {
    private UUID artworkId;
    private UUID artistId;
    private UUID currentGalleryId;
    private String title;
    private String description;
    private String history;
    private Integer yearCreated;
    private String medium;
    private String dimensions;
    private String origin;
    private Boolean hasArAssets;
    private String wikipediaUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    public UUID getArtworkId() { return artworkId; }
    public void setArtworkId(UUID artworkId) { this.artworkId = artworkId; }
    public UUID getArtistId() { return artistId; }
    public void setArtistId(UUID artistId) { this.artistId = artistId; }
    public UUID getCurrentGalleryId() { return currentGalleryId; }
    public void setCurrentGalleryId(UUID currentGalleryId) { this.currentGalleryId = currentGalleryId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getHistory() { return history; }
    public void setHistory(String history) { this.history = history; }
    public Integer getYearCreated() { return yearCreated; }
    public void setYearCreated(Integer yearCreated) { this.yearCreated = yearCreated; }
    public String getMedium() { return medium; }
    public void setMedium(String medium) { this.medium = medium; }
    public String getDimensions() { return dimensions; }
    public void setDimensions(String dimensions) { this.dimensions = dimensions; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public Boolean getHasArAssets() { return hasArAssets; }
    public void setHasArAssets(Boolean hasArAssets) { this.hasArAssets = hasArAssets; }
    public String getWikipediaUrl() { return wikipediaUrl; }
    public void setWikipediaUrl(String wikipediaUrl) { this.wikipediaUrl = wikipediaUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}