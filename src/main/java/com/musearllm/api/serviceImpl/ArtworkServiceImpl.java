package com.musearllm.api.serviceImpl;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.exception.ExceptionEnum;
import com.musearllm.api.mapper.ArtworkMapper;
import com.musearllm.api.model.Artwork;
import com.musearllm.api.service.ArtworkService;
import com.musearllm.api.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkMapper artworkMapper;

    @Override
    public Artwork createArtwork(Artwork artwork) throws BizException {
        try {
            artwork.setArtworkId(UUID.randomUUID());
            LocalDateTime now = DateTimeUtil.getNow();
            artwork.setCreatedAt(now);
            artwork.setUpdatedAt(now);

            int result = artworkMapper.insertArtwork(artwork);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to create artwork");
            }
            return artwork;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error creating artwork: " + e.getMessage());
        }
    }

    @Override
    public Artwork getArtworkById(UUID artworkId) throws BizException {
        try {
            Artwork artwork = artworkMapper.selectArtworkById(artworkId);
            if (artwork == null) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Artwork not found with ID: " + artworkId);
            }
            return artwork;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving artwork: " + e.getMessage());
        }
    }

    @Override
    public List<Artwork> getAllArtworks() throws BizException {
        try {
            return artworkMapper.selectAllArtworks();
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving artworks: " + e.getMessage());
        }
    }

    @Override
    public List<Artwork> searchArtworksByTitle(String title) throws BizException {
        try {
            if (title == null || title.trim().isEmpty()) {
                throw new BizException(ExceptionEnum.BODY_NOT_MATCH, "Search title cannot be empty");
            }
            return artworkMapper.selectArtworksByTitle("%" + title + "%");
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error searching artworks: " + e.getMessage());
        }
    }

    @Override
    public List<Artwork> getArtworksByArtistId(UUID artistId) throws BizException {
        try {
            return artworkMapper.selectArtworksByArtistId(artistId);
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving artworks by artist: " + e.getMessage());
        }
    }

    @Override
    public List<Artwork> getArtworksByGalleryId(UUID galleryId) throws BizException {
        try {
            return artworkMapper.selectArtworksByGalleryId(galleryId);
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving artworks by gallery: " + e.getMessage());
        }
    }

    @Override
    public Artwork updateArtwork(UUID artworkId, Artwork artwork) throws BizException {
        try {
            if (!artworkMapper.existsById(artworkId)) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Artwork not found with ID: " + artworkId);
            }
            artwork.setArtworkId(artworkId);
            artwork.setUpdatedAt(DateTimeUtil.getNow());

            int result = artworkMapper.updateArtwork(artwork);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to update artwork");
            }
            return artworkMapper.selectArtworkById(artworkId);
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error updating artwork: " + e.getMessage());
        }
    }

    @Override
    public void deleteArtwork(UUID artworkId) throws BizException {
        try {
            if (!artworkMapper.existsById(artworkId)) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Artwork not found with ID: " + artworkId);
            }
            int result = artworkMapper.deleteArtworkById(artworkId);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to delete artwork");
            }
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error deleting artwork: " + e.getMessage());
        }
    }

    @Override
    public int getArtworksCount() throws BizException {
        try {
            return artworkMapper.countArtworks();
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error counting artworks: " + e.getMessage());
        }
    }
}