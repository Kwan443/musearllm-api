package com.musearllm.api.controller;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.exception.ResultResponse;
import com.musearllm.api.model.Gallery;
import com.musearllm.api.service.GalleryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/galleries")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @PostMapping
    public ResultResponse createGallery(@RequestBody Gallery gallery) {
        try {
            Gallery createdGallery = galleryService.createGallery(gallery);
            log.info("Gallery created successfully: {}", createdGallery.getGalleryId());
            return ResultResponse.success(createdGallery);
        } catch (BizException e) {
            log.error("Error creating gallery: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error creating gallery: {}", e.getMessage());
            return ResultResponse.error("Failed to create gallery");
        }
    }

    @GetMapping("/{galleryId}")
    public ResultResponse getGallery(@PathVariable UUID galleryId) {
        try {
            Gallery gallery = galleryService.getGalleryById(galleryId);
            return ResultResponse.success(gallery);
        } catch (BizException e) {
            log.error("Error retrieving gallery: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving gallery: {}", e.getMessage());
            return ResultResponse.error("Failed to retrieve gallery");
        }
    }

    @GetMapping
    public ResultResponse getAllGalleries() {
        try {
            List<Gallery> galleries = galleryService.getAllGalleries();
            return ResultResponse.success(galleries);
        } catch (BizException e) {
            log.error("Error retrieving galleries: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving galleries: {}", e.getMessage());
            return ResultResponse.error("Failed to retrieve galleries");
        }
    }

    @GetMapping("/search")
    public ResultResponse searchGalleries(@RequestParam("name") String name) {
        try {
            List<Gallery> galleries = galleryService.searchGalleriesByName(name);
            return ResultResponse.success(galleries);
        } catch (BizException e)  {
            log.error("Error searching galleries: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error searching galleries: {}", e.getMessage());
            return ResultResponse.error("Failed to search galleries");
        }
    }

    @GetMapping("/museum/{museumId}")
    public ResultResponse getGalleriesByMuseumId(@PathVariable UUID museumId) {
        try {
            List<Gallery> galleries = galleryService.getGalleriesByMuseumId(museumId);
            return ResultResponse.success(galleries);
        } catch (BizException e) {
            log.error("Error retrieving galleries by museum: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving galleries by museum: {}", e.getMessage());
            return ResultResponse.error("Failed to retrieve galleries by museum");
        }
    }

    @PutMapping("/{galleryId}")
    public ResultResponse updateGallery(@PathVariable UUID galleryId, @RequestBody Gallery gallery) {
        try {
            Gallery updatedGallery = galleryService.updateGallery(galleryId, gallery);
            log.info("Gallery updated successfully: {}", galleryId);
            return ResultResponse.success(updatedGallery);
        } catch (BizException e) {
            log.error("Error updating gallery: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error updating gallery: {}", e.getMessage());
            return ResultResponse.error("Failed to update gallery");
        }
    }

    @DeleteMapping("/{galleryId}")
    public ResultResponse deleteGallery(@PathVariable UUID galleryId) {
        try {
            galleryService.deleteGallery(galleryId);
            log.info("Gallery deleted successfully: {}", galleryId);
            return ResultResponse.success("Gallery deleted successfully");
        } catch (BizException e) {
            log.error("Error deleting gallery: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error deleting gallery: {}", e.getMessage());
            return ResultResponse.error( "Failed to delete gallery");
        }
    }

    @GetMapping("/count")
    public ResultResponse getGalleriesCount() {
        try {
            int count = galleryService.getGalleriesCount();
            return ResultResponse.success(count);
        } catch (BizException e) {
            log.error("Error counting galleries: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error counting galleries: {}", e.getMessage());
            return ResultResponse.error("Failed to count galleries");
        }
    }
}