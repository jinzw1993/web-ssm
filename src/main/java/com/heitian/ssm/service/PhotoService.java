package com.heitian.ssm.service;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Photo;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
public interface PhotoService {
    Result insertPhoto(Photo photo);

    Long insertPhoto(String path);

    Result deletePhoto(Long productId, Long url);

    Result updatePhoto(Photo photo);

    List<Photo> getPhotosByProduct(int productId);

    Photo getPhotoById(Long id);
}
