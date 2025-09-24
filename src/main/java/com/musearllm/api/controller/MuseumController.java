package com.musearllm.api.controller;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.exception.ResultResponse;
import com.musearllm.api.model.Museum;
import com.musearllm.api.service.MuseumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/museums")
public class MuseumController {

    @Autowired
    private MuseumService museumService;

    @PostMapping
    public ResultResponse createMuseum(@RequestBody Museum museum) {
        try {
            Museum createdMuseum = museumService.createMuseum(museum);
            log.info("Museum created successfully: {}", createdMuseum.getMuseumId());
            return ResultResponse.success(createdMuseum);
        } catch (BizException e) {
            log.error("Error creating museum: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error creating museum: {}", e.getMessage());
            return ResultResponse.error("Failed to create museum");
        }
    }

    @GetMapping("/{museumId}")
    public ResultResponse getMuseum(@PathVariable UUID museumId) {
        try {
            Museum museum = museumService.getMuseumById(museumId);
            return ResultResponse.success(museum);
        } catch (BizException e) {
            log.error("Error retrieving museum: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving museum: {}", e.getMessage());
            return ResultResponse.error("Failed to retrieve museum");
        }
    }

    @GetMapping
    public ResultResponse getAllMuseums() {
        try {
            List<Museum> museums = museumService.getAllMuseums();
            return ResultResponse.success(museums);
        } catch (BizException e) {
            log.error("Error retrieving museums: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error retrieving museums: {}", e.getMessage());
            return ResultResponse.error("Failed to retrieve museums");
        }
    }

    @GetMapping("/search")
    public ResultResponse searchMuseums(@RequestParam("name") String name) {
        try {
            List<Museum> museums = museumService.searchMuseumsByName(name);
            return ResultResponse.success(museums);
        } catch (BizException e) {
            log.error("Error searching museums: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error searching museums: {}", e.getMessage());
            return ResultResponse.error( "Failed to search museums");
        }
    }

    @PutMapping("/{museumId}")
    public ResultResponse updateMuseum(@PathVariable UUID museumId, @RequestBody Museum museum) {
        try {
            Museum updatedMuseum = museumService.updateMuseum(museumId, museum);
            log.info("Museum updated successfully: {}", museumId);
            return ResultResponse.success(updatedMuseum);
        } catch (BizException e) {
            log.error("Error updating museum: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error updating museum: {}", e.getMessage());
            return ResultResponse.error("Failed to update museum");
        }
    }

    @DeleteMapping("/{museumId}")
    public ResultResponse deleteMuseum(@PathVariable UUID museumId) {
        try {
            museumService.deleteMuseum(museumId);
            log.info("Museum deleted successfully: {}", museumId);
            return ResultResponse.success("Museum deleted successfully");
        } catch (BizException e) {
            log.error("Error deleting museum: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error deleting museum: {}", e.getMessage());
            return ResultResponse.error("Failed to delete museum");
        }
    }

    @GetMapping("/count")
    public ResultResponse getMuseumsCount() {
        try {
            int count = museumService.getMuseumsCount();
            return ResultResponse.success(count);
        } catch (BizException e) {
            log.error("Error counting museums: {}", e.getMessage());
            return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("Unexpected error counting museums: {}", e.getMessage());
            return ResultResponse.error("Failed to count museums");
        }
    }
}