package com.musearllm.api.controller;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.exception.ResultResponse;
import com.musearllm.api.model.Artwork;
import com.musearllm.api.service.ArtworkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/artworks")
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    @PostMapping
    public ResultResponse createArtwork(@RequestBody Artwork artwork) {
        try {
            Artwork createdArtwork = artworkService.createArtwork(artwork);
            log.info("Artwork created successfully: {}", createdArtwork.getArtworkId());
            return ResultResponse.success(createdArtwork);
        } catch (BizException e) {
            log.error("Error creating artwork: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error creating artwork: {}", e.getMessage());
            return ResultResponse.error( "Failed to create artwork");
        }
    }

    @GetMapping("/{artworkId}")
    public ResultResponse getArtwork(@PathVariable UUID artworkId) {
        try {
            Artwork artwork = artworkService.getArtworkById(artworkId);
            return ResultResponse.success(artwork);
        } catch (BizException e) {
            log.error("Error retrieving artwork: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving artwork: {}", e.getMessage());
            return ResultResponse.error( "Failed to retrieve artwork");
        }
    }

    @GetMapping
    public ResultResponse getAllArtworks() {
        try {
            List<Artwork> artworks = artworkService.getAllArtworks();
            return ResultResponse.success(artworks);
        } catch (BizException e) {
            log.error("Error retrieving artworks: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving artworks: {}", e.getMessage());
            return ResultResponse.error("Failed to retrieve artworks");
        }
    }

    @GetMapping("/search")
    public ResultResponse searchArtworks(@RequestParam("title") String title) {
        try {
            List<Artwork> artworks = artworkService.searchArtworksByTitle(title);
            return ResultResponse.success(artworks);
        } catch (BizException e) {
            log.error("Error searching artworks: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error searching artworks: {}", e.getMessage());
            return ResultResponse.error( "Failed to search artworks");
        }
    }

    @GetMapping("/artist/{artistId}")
    public ResultResponse getArtworksByArtistId(@PathVariable UUID artistId) {
        try {
            List<Artwork> artworks = artworkService.getArtworksByArtistId(artistId);
            return ResultResponse.success(artworks);
        } catch (BizException e) {
            log.error("Error retrieving artworks by artist: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving artworks by artist: {}", e.getMessage());
            return ResultResponse.error( "Failed to retrieve artworks by artist");
        }
    }

    @GetMapping("/gallery/{galleryId}")
    public ResultResponse getArtworksByGalleryId(@PathVariable UUID galleryId) {
        try {
            List<Artwork> artworks = artworkService.getArtworksByGalleryId(galleryId);
            return ResultResponse.success(artworks);
        } catch (BizException e) {
            log.error("Error retrieving artworks by gallery: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving artworks by gallery: {}", e.getMessage());
            return ResultResponse.error( "Failed to retrieve artworks by gallery");
        }
    }

    @PutMapping("/{artworkId}")
    public ResultResponse updateArtwork(@PathVariable UUID artworkId, @RequestBody Artwork artwork) {
        try {
            Artwork updatedArtwork = artworkService.updateArtwork(artworkId, artwork);
            log.info("Artwork updated successfully: {}", artworkId);
            return ResultResponse.success(updatedArtwork);
        } catch (BizException e) {
            log.error("Error updating artwork: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error updating artwork: {}", e.getMessage());
            return ResultResponse.error("Failed to update artwork");
        }
    }

    @DeleteMapping("/{artworkId}")
    public ResultResponse deleteArtwork(@PathVariable UUID artworkId) {
        try {
            artworkService.deleteArtwork(artworkId);
            log.info("Artwork deleted successfully: {}", artworkId);
            return ResultResponse.success("Artwork deleted successfully");
        } catch (BizException e) {
            log.error("Error deleting artwork: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error deleting artwork: {}", e.getMessage());
            return ResultResponse.error("Failed to delete artwork");
        }
    }

    @GetMapping("/count")
    public ResultResponse getArtworksCount() {
        try {
            int count = artworkService.getArtworksCount();
            return ResultResponse.success(count);
        } catch (BizException e) {
            log.error("Error counting artworks: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error counting artworks: {}", e.getMessage());
            return ResultResponse.error("Failed to count artworks");
        }
    }
}