package com.heitian.ssm.service;

import com.heitian.ssm.bo.IncomeBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.TimeCondition;
import com.heitian.ssm.model.Owner;


import java.util.List;

/**
 * Created by S.W on 2016/11/27.
 */
public interface OwnerService {

    Result ownerLogin(Owner owner);

    Result ownerRegister(Owner owner);

    Result updateOwner(Owner owner);

  //  void processRegister(String email);

    Result processActivate(String email, String validateCode);

    Owner selectOwnerById(long id);

    Owner selectOwnerByName(String ownerName);

    Owner selectOwnerByEmail(String email);

    int getOwnerNum();
    int getUnverifiedNum();

    List<Owner> selectAllOwners(int page, int pageNum);

    List<Owner> getAllUnverifiedOwner(int page, int pageNum);

    //  public Result insertOwner(Owner owner);
    //    public boolean deleteOwnerById(long id);
//    public boolean deleteOwnerByName(String ownerName);

    List<IncomeBo> getIncome(Long i, Long ownerId);

    Result getAllIncome(Long ownerId);
}
