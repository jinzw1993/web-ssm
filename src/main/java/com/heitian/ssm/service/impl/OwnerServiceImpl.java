package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.OwnerDao;
import com.heitian.ssm.model.Owner;
import com.heitian.ssm.service.OwnerService;
import com.heitian.ssm.util.SendEmail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
     * @param owner 对象需要 email, password
     * @return Result
     */
    public Result ownerLogin(Owner owner) {
        Result result = new Result();
        if (owner == null || owner.getEmail() == null || owner.getPassword() == null) {
            result.setStatus(0);
            result.setMessage("email or password is not allowed to be empty");
        }
        else {
            Owner own = ownerDao.selectOwnerByEmail(owner.getEmail());
            //验证用户存在
            if (own != null) {
                //验证密码
                if (own.getPassword().equals(owner.getPassword())) {
                    //验证账户状态
                    long status=own.getStatus();
                    int ev=own.getIsEmailVerified();
                    if (status==0&&ev==1) {
                        result.setStatus(1);
                        result.setMessage("success");
                    }
                    else if(status!=0){//账户处于黑名单、已删除或未审核
                        result.setMessage("account is in blacklist or deleted");
                        result.setStatus(0);
                    }
                    else {
                        result.setMessage("email unverified");
                        result.setStatus(0);
                    }
                }
            }
            else {//用户不存在或密码错误
                result.setStatus(0);
                result.setMessage("failed, user name or password is wrong");
            }
        }
        return result;
    }

    /**
     * Owner注册
     *
     * @param owner 对象需要name, email, password属性
     * @return Result
     */
    public Result ownerRegister(Owner owner) {
        Result result = new Result();
        //验证信息完整性
        if (owner == null || owner.getPassword() == null || owner.getName() == null|| owner.getEmail()==null) {

            result.setStatus(0);
            result.setMessage("email, name or password is not allowed to be empty");
        } else {
            Owner own = ownerDao.selectOwnerByName(owner.getName());
            Owner own2 = ownerDao.selectOwnerByEmail(owner.getEmail());
            //验证是否已存在
            if (own != null) {
                result.setStatus(0);
                result.setMessage("failed, name has already existed");
            } else if (own2!=null) {
                result.setStatus(0);
                result.setMessage("failed, email has already been used");
            } else {

                owner.setStatus((long) 0);
                owner.setIsEmailVerified(0);
                int num = ownerDao.insertOwner(owner);
                if (num == 0) {
                    result.setStatus(0);
                    result.setMessage("failed to insert into database");
                } else {
                    result.setMessage("success, waiting for email verifying");
                    result.setStatus(1);
                    processRegister(owner.getEmail());
                }
            }
        }
        return result;
    }

    private void processRegister(String email){
    //邮件内容
        StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8080/owner/activate&email=");
        sb.append(email);

        sb.append("\">http://localhost:8080/owner/activate?&email=");
        sb.append(email);

        sb.append("</a>");
    //发送邮件
        SendEmail.send(email,sb.toString());
    }

    public Result processActivate(String email) {
        Owner owner=ownerDao.selectOwnerByEmail(email);
        Result result=new Result();
        result.setStatus(0);
        if(owner!=null) {
            //验证用户激活状态
            if (owner.getIsEmailVerified() == 0) {
                owner.setIsEmailVerified(1);
                ownerDao.updateOwner(owner);
                result.setStatus(1);
                result.setMessage("success");
            } else {
                result.setMessage("already activated");
            }
        } else {
            result.setMessage("not exist");
        }
        return result;
    }


    /**
     * 根据Owner.email更新Owner密码或Status
     *
     * @param owner 对象需要 email, password, status, isEmailVerified
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
     * 根据email查找Owner
     *
     * @param email
     * @return Owner对象
     */
    public Owner selectOwnerByEmail(String email) {
        Owner owner = ownerDao.selectOwnerByEmail(email);
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
     * 分页选择所有未验证Owner
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

    public int getOwnerNum() {
        return ownerDao.getOwnerNum();
    }

    public int getUnverifiedNum() {
        return ownerDao.getUnverifiedNum();
    }

}
