package com.musearllm.api.mapper;

import com.musearllm.api.model.Gallery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface GalleryMapper {
    int insertGallery(Gallery gallery);
    Gallery selectGalleryById(@Param("galleryId") UUID galleryId);
    List<Gallery> selectAllGalleries();
    List<Gallery> selectGalleriesByName(@Param("name") String name);
    List<Gallery> selectGalleriesByMuseumId(@Param("museumId") UUID museumId);
    int updateGallery(Gallery gallery);
    int deleteGalleryById(@Param("galleryId") UUID galleryId);
    boolean existsById(@Param("galleryId") UUID galleryId);
    int countGalleries();
}