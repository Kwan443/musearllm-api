package com.musearllm.api.serviceImpl;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.exception.ExceptionEnum;
import com.musearllm.api.mapper.GalleryMapper;
import com.musearllm.api.model.Gallery;
import com.musearllm.api.service.GalleryService;
import com.musearllm.api.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryMapper galleryMapper;

    @Override
    public Gallery createGallery(Gallery gallery) throws BizException {
        try {
            gallery.setGalleryId(UUID.randomUUID());
            LocalDateTime now = DateTimeUtil.getNow();
            gallery.setCreatedAt(now);
            gallery.setUpdatedAt(now);

            int result = galleryMapper.insertGallery(gallery);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to create gallery");
            }
            return gallery;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error creating gallery: " + e.getMessage());
        }
    }

    @Override
    public Gallery getGalleryById(UUID galleryId) throws BizException {
        try {
            Gallery gallery = galleryMapper.selectGalleryById(galleryId);
            if (gallery == null) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Gallery not found with ID: " + galleryId);
            }
            return gallery;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving gallery: " + e.getMessage());
        }
    }

    @Override
    public List<Gallery> getAllGalleries() throws BizException {
        try {
            return galleryMapper.selectAllGalleries();
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving galleries: " + e.getMessage());
        }
    }

    @Override
    public List<Gallery> searchGalleriesByName(String name) throws BizException {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new BizException(ExceptionEnum.BODY_NOT_MATCH, "Search name cannot be empty");
            }
            return galleryMapper.selectGalleriesByName("%" + name + "%");
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error searching galleries: " + e.getMessage());
        }
    }

    @Override
    public List<Gallery> getGalleriesByMuseumId(UUID museumId) throws BizException {
        try {
            return galleryMapper.selectGalleriesByMuseumId(museumId);
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving galleries by museum: " + e.getMessage());
        }
    }

    @Override
    public Gallery updateGallery(UUID galleryId, Gallery gallery) throws BizException {
        try {
            if (!galleryMapper.existsById(galleryId)) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Gallery not found with ID: " + galleryId);
            }
            gallery.setGalleryId(galleryId);
            gallery.setUpdatedAt(DateTimeUtil.getNow());

            int result = galleryMapper.updateGallery(gallery);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to update gallery");
            }
            return galleryMapper.selectGalleryById(galleryId);
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error updating gallery: " + e.getMessage());
        }
    }

    @Override
    public void deleteGallery(UUID galleryId) throws BizException {
        try {
            if (!galleryMapper.existsById(galleryId)) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Gallery not found with ID: " + galleryId);
            }
            int result = galleryMapper.deleteGalleryById(galleryId);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to delete gallery");
            }
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error deleting gallery: " + e.getMessage());
        }
    }

    @Override
    public int getGalleriesCount() throws BizException {
        try {
            return galleryMapper.countGalleries();
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error counting galleries: " + e.getMessage());
        }
    }
}