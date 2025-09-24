package com.musearllm.api.service;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.model.Gallery;

import java.util.List;
import java.util.UUID;

public interface GalleryService {
    Gallery createGallery(Gallery gallery) throws BizException;
    Gallery getGalleryById(UUID galleryId) throws BizException;
    List<Gallery> getAllGalleries() throws BizException;
    List<Gallery> searchGalleriesByName(String name) throws BizException;
    List<Gallery> getGalleriesByMuseumId(UUID museumId) throws BizException;
    Gallery updateGallery(UUID galleryId, Gallery gallery) throws BizException;
    void deleteGallery(UUID galleryId) throws BizException;
    int getGalleriesCount() throws BizException;
}