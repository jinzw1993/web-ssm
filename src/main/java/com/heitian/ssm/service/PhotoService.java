package com.heitian.ssm.service;

import com.heitian.ssm.model.Photo;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
public interface PhotoService {
    boolean insertPhoto(Photo photo);

    boolean deletePhoto(int id);

    boolean updatePhoto(Photo photo);

    List<Photo> getPhotosByProduct(int productId);

    Photo getPhotoById(int id);
}
