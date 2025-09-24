package com.musearllm.api.serviceImpl;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.exception.ExceptionEnum;
import com.musearllm.api.mapper.MuseumMapper;
import com.musearllm.api.model.Museum;
import com.musearllm.api.service.MuseumService;
import com.musearllm.api.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MuseumServiceImpl implements MuseumService {

    @Autowired
    private MuseumMapper museumMapper;

    @Override
    public Museum createMuseum(Museum museum) throws BizException {
        try {
            museum.setMuseumId(UUID.randomUUID());
            LocalDateTime now = DateTimeUtil.getNow();
            museum.setCreatedAt(now);
            museum.setUpdatedAt(now);

            int result = museumMapper.insertMuseum(museum);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to create museum");
            }
            return museum;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error creating museum: " + e.getMessage());
        }
    }

    @Override
    public Museum getMuseumById(UUID museumId) throws BizException {
        try {
            Museum museum = museumMapper.selectMuseumById(museumId);
            if (museum == null) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Museum not found with ID: " + museumId);
            }
            return museum;
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving museum: " + e.getMessage());
        }
    }

    @Override
    public List<Museum> getAllMuseums() throws BizException {
        try {
            return museumMapper.selectAllMuseums();
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error retrieving museums: " + e.getMessage());
        }
    }

    @Override
    public List<Museum> searchMuseumsByName(String name) throws BizException {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new BizException(ExceptionEnum.BODY_NOT_MATCH, "Search name cannot be empty");
            }
            return museumMapper.selectMuseumsByName("%" + name + "%");
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error searching museums: " + e.getMessage());
        }
    }

    @Override
    public Museum updateMuseum(UUID museumId, Museum museum) throws BizException {
        try {
            if (!museumMapper.existsById(museumId)) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Museum not found with ID: " + museumId);
            }
            museum.setMuseumId(museumId);
            museum.setUpdatedAt(DateTimeUtil.getNow());

            int result = museumMapper.updateMuseum(museum);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to update museum");
            }
            return museumMapper.selectMuseumById(museumId);
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error updating museum: " + e.getMessage());
        }
    }

    @Override
    public void deleteMuseum(UUID museumId) throws BizException {
        try {
            if (!museumMapper.existsById(museumId)) {
                throw new BizException(ExceptionEnum.NOT_FOUND, "Museum not found with ID: " + museumId);
            }
            int result = museumMapper.deleteMuseumById(museumId);
            if (result == 0) {
                throw new BizException(ExceptionEnum.DB_EXCEPTION, "Failed to delete museum");
            }
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error deleting museum: " + e.getMessage());
        }
    }

    @Override
    public int getMuseumsCount() throws BizException {
        try {
            return museumMapper.countMuseums();
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DB_EXCEPTION, "Error counting museums: " + e.getMessage());
        }
    }
}