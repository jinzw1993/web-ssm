package com.heitian.ssm.dao;

import com.heitian.ssm.model.Photo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
@Repository
public interface PhotoDao {
    int insertPhoto(Photo photo);
    int deletePhoto(@Param("productId") Long productId,@Param("path") Long path);
    int updatePhoto(@Param("productId") Photo photo);
    List<Photo> selectPhotosByProduct(int productId);
    Photo selectPhotoById(Long id);
    Long selectMaxId();
}
