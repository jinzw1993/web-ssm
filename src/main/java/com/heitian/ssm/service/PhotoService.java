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

    Result deletePhoto(Long productId, String url);

    Result updatePhoto(Photo photo);

    List<Photo> getPhotosByProduct(Long productId);

    Photo getPhotoById(Long id);
}
