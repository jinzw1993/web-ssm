package com.heitian.ssm.service;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Owner;


import java.util.List;

/**
 * Created by S.W on 2016/11/27.
 */
public interface OwnerService {

    public Result ownerLogin(Owner owner);
    public Result ownerRegister(Owner owner);
    public Result updateOwner(Owner owner);

    public Owner selectOwnerById( long id);
    public Owner selectOwnerByName(String ownerName);

 //   public List<Owner> selectAllOwners();

    public List<Owner> selectAllOwners(int page, int pageNum);
    public List<Owner> getAllUnverifiedOwner(int page, int pageNum);

    //  public Result insertOwner(Owner owner);
    //    public boolean deleteOwnerById(long id);
//    public boolean deleteOwnerByName(String ownerName);
}
