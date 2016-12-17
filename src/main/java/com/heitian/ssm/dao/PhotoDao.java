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
    int insertPhoto(@Param("photo") String photo);
    int deletePhoto(@Param("productId") Long productId);
    int updatePhoto(Photo photo);
    int updatePhotoProId(Photo photo);
    List<Photo> selectPhotosByProduct(Long productId);
    Photo selectPhotoById(Long id);
    Long selectMaxId();
}
