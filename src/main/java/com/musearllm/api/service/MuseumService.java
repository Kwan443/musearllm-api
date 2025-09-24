package com.musearllm.api.service;

import com.musearllm.api.exception.BizException;
import com.musearllm.api.model.Museum;

import java.util.List;
import java.util.UUID;

public interface MuseumService {
    Museum createMuseum(Museum museum) throws BizException;
    Museum getMuseumById(UUID museumId) throws BizException;
    List<Museum> getAllMuseums() throws BizException;
    List<Museum> searchMuseumsByName(String name) throws BizException;
    Museum updateMuseum(UUID museumId, Museum museum) throws BizException;
    void deleteMuseum(UUID museumId) throws BizException;
    int getMuseumsCount() throws BizException;
}