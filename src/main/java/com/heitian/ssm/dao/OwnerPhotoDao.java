package com.heitian.ssm.dao;

import com.heitian.ssm.model.OwnerPhoto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by oasis on 11/30/16.
 */
public interface OwnerPhotoDao {
    int insertPhoto(OwnerPhoto photo);
    int deletePhoto(@Param("ownerId") Long ownerId, @Param("path") String path);
    int updatePhoto(@Param("ownerId") OwnerPhoto photo);
    OwnerPhoto selectPhotoById(Long id);
    Long selectMaxId();
}
