package com.heitian.ssm.service;

import com.heitian.ssm.model.Owner;


import java.util.List;

/**
 * Created by unname on 2016/11/27.
 */
public interface OwnerService {
    public boolean insertOwner( Owner owner);
//    public boolean deleteOwnerById(long id);
//    public boolean deleteOwnerByName(String ownerName);
    public boolean updateOwner(Owner owner);
    public boolean changeStatus(long status, Owner owner);
    public Owner selectOwnerById( long id);
    public Owner selectOwnerByName(String ownerName);
    public List<Owner> selectAllOwners();
}
