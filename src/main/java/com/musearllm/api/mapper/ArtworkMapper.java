package com.musearllm.api.mapper;

import com.musearllm.api.model.Artwork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ArtworkMapper {
    int insertArtwork(Artwork artwork);
    Artwork selectArtworkById(@Param("artworkId") UUID artworkId);
    List<Artwork> selectAllArtworks();
    List<Artwork> selectArtworksByTitle(@Param("title") String title);
    List<Artwork> selectArtworksByArtistId(@Param("artistId") UUID artistId);
    List<Artwork> selectArtworksByGalleryId(@Param("galleryId") UUID galleryId);
    int updateArtwork(Artwork artwork);
    int deleteArtworkById(@Param("artworkId") UUID artworkId);
    boolean existsById(@Param("artworkId") UUID artworkId);
    int countArtworks();
}