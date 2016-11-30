package com.heitian.ssm.dao;

import com.heitian.ssm.model.Owner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WinterSykes on 2016/11/27.
 */
@Repository
public interface OwnerDao {

    int insertOwner(@Param("owner") Owner owner);

    int updateOwner(@Param("owner") Owner owner);

    Owner selectOwnerById(@Param("id") long id);

    Owner selectOwnerByName(@Param("ownerName") String ownerName);

    Owner selectOwnerByEmail(@Param("email") String email);

    List<Owner> selectAllOwners(@Param("start") int start, @Param("pageNum") int pageNum);

    List<Owner> getAllUnverifiedOwner(@Param("start") int start, @Param("pageNum") int pageNum);

    int deleteOwnerById(@Param("id") long id);

    int deleteOwnerByName(@Param("ownerName") String ownerName);

    int getOwnerNum();
    int getUnverifiedNum();
}
