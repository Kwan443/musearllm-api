package com.musearllm.api.mapper;

import com.musearllm.api.model.Artist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper
public interface ArtistMapper {

    // 创建艺术家
    int insertArtist(Artist artist);

    // 根据ID查询艺术家
    Artist selectArtistById(@Param("artistId") UUID artistId);

    // 查询所有艺术家
    List<Artist> selectAllArtists();

    // 根据名称搜索艺术家
    List<Artist> selectArtistsByName(@Param("name") String name);

    // 根据国籍查询艺术家
    List<Artist> selectArtistsByNationality(@Param("nationality") String nationality);

    // 更新艺术家信息
    int updateArtist(Artist artist);

    // 删除艺术家
    int deleteArtistById(@Param("artistId") UUID artistId);

    // 检查艺术家是否存在
    boolean existsById(@Param("artistId") UUID artistId);

    // 统计艺术家数量
    int countArtists();
}