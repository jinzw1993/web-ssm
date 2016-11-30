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

    public int insertOwner(@Param("owner") Owner owner);
    public int deleteOwnerById(@Param("id") long id);
    public int deleteOwnerByName(@Param("ownerName") String ownerName);
    public int updateOwner(@Param("owner") Owner owner);

    public Owner selectOwnerById(@Param("id") long id);
    public Owner selectOwnerByName(@Param("ownerName") String ownerName);

//    public List<Owner> selectAllOwners();
    public List<Owner> selectAllOwners(@Param("start") int start,@Param("pageNum") int pageNum);
    public List<Owner> getAllUnverifiedOwner(@Param("start") int start,@Param("pageNum") int pageNum);
}
