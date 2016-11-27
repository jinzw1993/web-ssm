package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.PhotoDao;
import com.heitian.ssm.model.Photo;
import com.heitian.ssm.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoDao photoDao;

    public boolean insertPhoto(Photo photo) {
        return photoDao.insertPhoto(photo) == null;
    }

    public boolean deletePhoto(int id) {
        return photoDao.deletePhoto(id) > 0;
    }

    public boolean updatePhoto(Photo photo) {
        return photoDao.updatePhoto(photo) > 0;
    }

    public List<Photo> getPhotosByProduct(int productId) {
        return photoDao.selectPhotosByProduct(productId);
    }

    public Photo getPhotoById(int id) {
        return photoDao.selectPhotoById(id);
    }
}
