package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.PhotoDao;
import com.heitian.ssm.model.Photo;
import com.heitian.ssm.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoDao photoDao;
    private Result result = new Result();

    public Result insertPhoto(Photo photo) {
        if(photoDao.insertPhoto(photo) == 0) {
            result.setStatus(0);
            result.setMessage("failed");
        } else {
            result.setStatus(1);
            result.setMessage("success");
        }
        return result;
    }

    public Long insertPhoto(String path) {
        Photo photo = new Photo();
        photo.setPath(path);
        if(insertPhoto(photo).getStatus() == 1)
            return photoDao.selectMaxId();
        return (long)0;
    }

    public Result deletePhoto(Long productId, String url) {
        if(photoDao.deletePhoto(productId, url) > 0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setStatus(0);
            result.setMessage("none deleted");
        }
        return result;
    }

    public Result updatePhoto(Photo photo) {
        if(photoDao.updatePhoto(photo) > 0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setStatus(0);
            result.setMessage("failed, can't update");
        }
        return result;
    }

    public List<Photo> getPhotosByProduct(Long productId) {
        List<Photo> list = photoDao.selectPhotosByProduct(productId);
        if(list == null)
            return new ArrayList<>();
        return list;
    }

    public Photo getPhotoById(Long id) {
        Photo photo =  photoDao.selectPhotoById(id);
        if(photo == null)
            return new Photo();
        else
            return photo;
    }
}
