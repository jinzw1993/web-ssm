package com.heitian.ssm.dao;

import com.heitian.ssm.model.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
@Repository
public interface PhotoDao {
    Object insertPhoto(Photo photo);
    int deletePhoto(int id);
    int updatePhoto(Photo photo);
    List<Photo> selectPhotosByProduct(int productId);
    Photo selectPhotoById(int id);
}
