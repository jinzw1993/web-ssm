package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.OwnerDao;
import com.heitian.ssm.model.Owner;
import com.heitian.ssm.service.OwnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by S.W on 2016/11/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OwnerServiceImpl implements OwnerService {
    @Resource
    private OwnerDao ownerDao;

    /**
     * Owner登录
     *
     * @param owner
     * @return Result
     */
    public Result ownerLogin(Owner owner) {
        Result result = new Result();
        if (owner == null || owner.getName() == null || owner.getPassword() == null) {
            result.setStatus(0);
            result.setMessage("name or password is not allowed to be empty");
        } else {
            Owner own = ownerDao.selectOwnerByName(owner.getName());
            if (own != null) {//验证用户存在
                if (own.getPassword().equals(owner.getPassword())) {//验证密码
                    if (own.getStatus() == 0) {//验证账户状态
          //              owner.setId(own.getId());
                        result.setStatus(1);
                        result.setMessage("success");
                    } else {//账户处于黑名单、已删除或未审核
                        result.setMessage("this account is in blacklist or deleted or unverified");
                        result.setStatus(0);
                    }
                }
            } else {//用户不存在或密码错误
                result.setStatus(0);
                result.setMessage("failed, user name or password is wrong");
            }
        }
        return result;
    }

    /**
     * Owner注册
     *
     * @param owner
     * @return Result
     */
    public Result ownerRegister(Owner owner) {
        Result result = new Result();
        //验证信息完整性
        if (owner == null || owner.getPassword() == null || owner.getName() == null) {
            result.setStatus(0);
            result.setMessage("user name or password is not allowed to be empty");
        } else {
            Owner own = ownerDao.selectOwnerByName(owner.getName());
            if (own != null) {//验证是否已存在
                result.setStatus(0);
                result.setMessage("failed, name has already existed");
            } else {
                //设置状态为待管理员审核
                owner.setStatus((long) 3);
                int num = ownerDao.insertOwner(owner);
                result.setStatus(num);
                if (num == 0) {
                    result.setStatus(0);
                    result.setMessage("failed to insert into database");
                } else {
                    result.setMessage("register success, waiting for administrator verifying");
                    result.setStatus(1);
                }
            }
        }
        return result;
    }

    /**
     * 根据Owner.name更新Owner密码或Status
     *
     * @param owner
     * @return true or false
     */
    public Result updateOwner(Owner owner) {
        Result result = new Result();
        if (ownerDao.updateOwner(owner) > 0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setStatus(0);
            result.setMessage("failed");
        }
        return result;
    }

    /**
     * 根据id查找Owner
     *
     * @param id
     * @return Owner对象
     */
    public Owner selectOwnerById(long id) {
        Owner owner = ownerDao.selectOwnerById(id);
        if (owner == null) {
            owner = new Owner();
        }
        return owner;
    }

    /**
     * 根据name查找Owner
     *
     * @param ownerName
     * @return Owner对象
     */
    public Owner selectOwnerByName(String ownerName) {
        Owner owner = ownerDao.selectOwnerByName(ownerName);
        if (owner == null) {
            owner = new Owner();
        }
        return owner;
    }

    /**
     * 分页选择Owner
     *
     * @param page    第page页，从1开始
     * @param pageNum 每页条目数
     * @return Owner List
     */
    public List<Owner> selectAllOwners(int page, int pageNum) {
        List<Owner> ownerList = ownerDao.selectAllOwners((page - 1) * pageNum, pageNum);
        if (ownerList == null) {
            ownerList = new ArrayList<Owner>();
        }
        return ownerList;
    }

    /**
     * 分页选择所有未审核Owner
     * @param page    第page页，从1开始
     * @param pageNum 每页条目数
     * @return Owner List
     */
    public List<Owner> getAllUnverifiedOwner(int page, int pageNum) {
        List<Owner> ownerList = ownerDao.getAllUnverifiedOwner((page - 1) * pageNum,  pageNum);
        if (ownerList == null) {
            ownerList = new ArrayList<Owner>();
        }
        return ownerList;
    }

}
