package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.OwnerDao;
import com.heitian.ssm.model.Owner;
import com.heitian.ssm.service.OwnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by unname on 2016/11/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OwnerServiceImpl implements OwnerService{
    @Resource
    private OwnerDao ownerDao;

    public boolean insertOwner( Owner owner) {
        return ownerDao.insertOwner(owner)!=null;
    }

    public boolean changeStatus(long status, Owner owner) {
        owner.setStatus(status);
        return ownerDao.updateOwner(owner)>0;
    }
//    public boolean deleteOwnerById(long id) {
//        return ownerDao.deleteOwnerById(id)>0;
//    }
//    public boolean deleteOwnerByName(String ownerName) {
//        return ownerDao.deleteOwnerByName(ownerName)>0;
//    }
//
    public boolean updateOwner(Owner owner) {
        return ownerDao.updateOwner(owner)>0;
    }
    public Owner selectOwnerById( long id) {
        return ownerDao.selectOwnerById(id);
    }
    public Owner selectOwnerByName(String ownerName) {
        return ownerDao.selectOwnerByName(ownerName);
    }
    public List<Owner> selectAllOwners() {
        return ownerDao.selectAllOwners();
    }
}
