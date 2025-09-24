package com.musearllm.api.service;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.model.Artist;

import java.util.List;
import java.util.UUID;

public interface ArtistService {
    
    // 创建艺术家
    Artist createArtist(Artist artist) throws BizException;
    
    // 根据ID获取艺术家
    Artist getArtistById(UUID artistId) throws BizException;
    
    // 获取所有艺术家
    List<Artist> getAllArtists() throws BizException;
    
    // 根据名称搜索艺术家
    List<Artist> searchArtistsByName(String name) throws BizException;
    
    // 根据国籍查询艺术家
    List<Artist> getArtistsByNationality(String nationality) throws BizException;
    
    // 更新艺术家信息
    Artist updateArtist(UUID artistId, Artist artist) throws BizException;
    
    // 删除艺术家
    void deleteArtist(UUID artistId) throws BizException;
    
    // 获取艺术家数量
    int getArtistsCount() throws BizException;
}