package com.musearllm.api.controller;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.exception.ResultResponse;
import com.musearllm.api.model.Artist;
import com.musearllm.api.service.ArtistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @PostMapping
    public ResultResponse createArtist(@RequestBody Artist artist) {
        try {
            Artist createdArtist = artistService.createArtist(artist);
            log.info("Artist created successfully: {}", createdArtist.getArtistId());
            return ResultResponse.success(createdArtist);
        } catch (BizException e) {
            log.error("Error creating artist: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error creating artist: {}", e.getMessage());
            return ResultResponse.error("Failed to create artist");
        }
    }

    @GetMapping("/{artistId}")
    public ResultResponse getArtist(@PathVariable String artistId) {
        try {
            UUID uuid = UUID.fromString(artistId);
            Artist artist = artistService.getArtistById(uuid);
            return ResultResponse.success(artist);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format: {}", artistId);
            return ResultResponse.error("Invalid artist ID format");
        } catch (BizException e) {
            log.error("Error retrieving artist: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving artist: {}", e.getMessage());
            return ResultResponse.error("Failed to retrieve artist");
        }
    }

    @GetMapping
    public ResultResponse getAllArtists() {
        try {
            List<Artist> artists = artistService.getAllArtists();
            return ResultResponse.success(artists);
        } catch (BizException e) {
            log.error("Error retrieving artists: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving artists: {}", e.getMessage());
            return ResultResponse.error("Failed to retrieve artists");
        }
    }

    @GetMapping("/search")
    public ResultResponse searchArtists(@RequestParam("name") String name) {
        try {
            List<Artist> artists = artistService.searchArtistsByName(name);
            return ResultResponse.success(artists);
        } catch (BizException e) {
            log.error("Error searching artists: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error searching artists: {}", e.getMessage());
            return ResultResponse.error("Failed to search artists");
        }
    }

    @GetMapping("/nationality/{nationality}")
    public ResultResponse getArtistsByNationality(@PathVariable String nationality) {
        try {
            List<Artist> artists = artistService.getArtistsByNationality(nationality);
            return ResultResponse.success(artists);
        } catch (BizException e) {
            log.error("Error retrieving artists by nationality: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving artists by nationality: {}", e.getMessage());
            return ResultResponse.error("Failed to retrieve artists by nationality");
        }
    }

    @PutMapping("/{artistId}")
    public ResultResponse updateArtist(@PathVariable String artistId, @RequestBody Artist artist) {
        try {
            UUID uuid = UUID.fromString(artistId);
            Artist updatedArtist = artistService.updateArtist(uuid, artist);
            log.info("Artist updated successfully: {}", artistId);
            return ResultResponse.success(updatedArtist);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format: {}", artistId);
            return ResultResponse.error("Invalid artist ID format");
        } catch (BizException e) {
            log.error("Error updating artist: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error updating artist: {}", e.getMessage());
            return ResultResponse.error("Failed to update artist");
        }
    }

    @DeleteMapping("/{artistId}")
    public ResultResponse deleteArtist(@PathVariable String artistId) {
        try {
            UUID uuid = UUID.fromString(artistId);
            artistService.deleteArtist(uuid);
            log.info("Artist deleted successfully: {}", artistId);
            return ResultResponse.success("Artist deleted successfully");
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format: {}", artistId);
            return ResultResponse.error("Invalid artist ID format");
        } catch (BizException e) {
            log.error("Error deleting artist: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error deleting artist: {}", e.getMessage());
            return ResultResponse.error("Failed to delete artist");
        }
    }

    @GetMapping("/count")
    public ResultResponse getArtistsCount() {
        try {
            int count = artistService.getArtistsCount();
            return ResultResponse.success(count);
        } catch (BizException e) {
            log.error("Error counting artists: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error counting artists: {}", e.getMessage());
            return ResultResponse.error("Failed to count artists");
        }
    }
}