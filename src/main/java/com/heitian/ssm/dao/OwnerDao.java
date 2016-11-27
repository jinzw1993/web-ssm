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

    public boolean insertOwner(@Param("owner") Owner owner);
    public boolean deleteOwnerById(@Param("id") long id);
    public boolean deleteOwnerByName(@Param("ownerName") String ownerName);
    public boolean updateOwner(@Param("owner") Owner owner);
    public Owner selectOwnerById(@Param("id") long id);
    public Owner selectOwnerByName(@Param("ownerName") String ownerName);
    public List<Owner> selectAllOwners();
}
