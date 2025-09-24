package com.musearllm.api.service;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.model.Artwork;

import java.util.List;
import java.util.UUID;

public interface ArtworkService {
    Artwork createArtwork(Artwork artwork) throws BizException;
    Artwork getArtworkById(UUID artworkId) throws BizException;
    List<Artwork> getAllArtworks() throws BizException;
    List<Artwork> searchArtworksByTitle(String title) throws BizException;
    List<Artwork> getArtworksByArtistId(UUID artistId) throws BizException;
    List<Artwork> getArtworksByGalleryId(UUID galleryId) throws BizException;
    Artwork updateArtwork(UUID artworkId, Artwork artwork) throws BizException;
    void deleteArtwork(UUID artworkId) throws BizException;
    int getArtworksCount() throws BizException;
}