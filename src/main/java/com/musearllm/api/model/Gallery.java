package com.musearllm.api.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Gallery {
    private UUID galleryId;
    private UUID museumId;
    private String name;
    private String floorNumber;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    public UUID getGalleryId() { return galleryId; }
    public void setGalleryId(UUID galleryId) { this.galleryId = galleryId; }
    public UUID getMuseumId() { return museumId; }
    public void setMuseumId(UUID museumId) { this.museumId = museumId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFloorNumber() { return floorNumber; }
    public void setFloorNumber(String floorNumber) { this.floorNumber = floorNumber; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}