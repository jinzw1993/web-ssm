package com.heitian.ssm.dao;

import com.heitian.ssm.model.OwnerPhoto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 11/30/16.
 */
@Repository
public interface OwnerPhotoDao {
    int insertPhoto(OwnerPhoto photo);
    int deletePhoto(@Param("ownerId") Long ownerId, @Param("path") String path);
    int updatePhoto(OwnerPhoto photo);
}
