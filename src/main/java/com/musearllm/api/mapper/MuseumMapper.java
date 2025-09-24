package com.musearllm.api.mapper;

import com.musearllm.api.model.Museum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface MuseumMapper {
    int insertMuseum(Museum museum);
    Museum selectMuseumById(@Param("museumId") UUID museumId);
    List<Museum> selectAllMuseums();
    List<Museum> selectMuseumsByName(@Param("name") String name);
    int updateMuseum(Museum museum);
    int deleteMuseumById(@Param("museumId") UUID museumId);
    boolean existsById(@Param("museumId") UUID museumId);
    int countMuseums();
}