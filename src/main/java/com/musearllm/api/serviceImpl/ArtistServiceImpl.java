package com.musearllm.api.serviceImpl;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.exception.ExceptionEnum;
import com.musearllm.api.mapper.ArtistMapper;
import com.musearllm.api.model.Artist;
import com.musearllm.api.service.ArtistService;
import com.musearllm.api.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistMapper artistMapper;

    @Override
    public Artist createArtist(Artist artist) throws BizException {
        try {
            // 设置ID和时间戳
            artist.setArtistId(UUID.randomUUID());
            LocalDateTime now = DateTimeUtil.getNow();
            artist.setCreatedAt(now);
            artist.setUpdatedAt(now);

            int result = artistMapper.insertArtist(artist);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to create artist");
            }

            return artist;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error creating artist: " + e.getMessage());
        }
    }

    @Override
    public Artist getArtistById(UUID artistId) throws BizException {
        try {
            Artist artist = artistMapper.selectArtistById(artistId);
            if (artist == null) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Artist not found with ID: " + artistId);
            }
            return artist;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving artist: " + e.getMessage());
        }
    }

    @Override
    public List<Artist> getAllArtists() throws BizException {
        try {
            return artistMapper.selectAllArtists();
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving artists: " + e.getMessage());
        }
    }

    @Override
    public List<Artist> searchArtistsByName(String name) throws BizException {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new BizException(ExceptionEnum.BODY_NOT_MATCH, "Search name cannot be empty");
            }
            return artistMapper.selectArtistsByName("%" + name + "%");
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error searching artists: " + e.getMessage());
        }
    }

    @Override
    public List<Artist> getArtistsByNationality(String nationality) throws BizException {
        try {
            if (nationality == null || nationality.trim().isEmpty()) {
                throw new BizException(ExceptionEnum.BODY_NOT_MATCH, "Nationality cannot be empty");
            }
            return artistMapper.selectArtistsByNationality(nationality);
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving artists by nationality: " + e.getMessage());
        }
    }

    @Override
    public Artist updateArtist(UUID artistId, Artist artist) throws BizException {
        try {
            // 检查艺术家是否存在
            if (!artistMapper.existsById(artistId)) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Artist not found with ID: " + artistId);
            }

            // 设置更新时间和ID
            artist.setArtistId(artistId);
            artist.setUpdatedAt(DateTimeUtil.getNow());

            int result = artistMapper.updateArtist(artist);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to update artist");
            }

            return artistMapper.selectArtistById(artistId);
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error updating artist: " + e.getMessage());
        }
    }

    @Override
    public void deleteArtist(UUID artistId) throws BizException {
        try {
            if (!artistMapper.existsById(artistId)) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Artist not found with ID: " + artistId);
            }

            int result = artistMapper.deleteArtistById(artistId);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to delete artist");
            }
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error deleting artist: " + e.getMessage());
        }
    }

    @Override
    public int getArtistsCount() throws BizException {
        try {
            return artistMapper.countArtists();
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error counting artists: " + e.getMessage());
        }
    }
}