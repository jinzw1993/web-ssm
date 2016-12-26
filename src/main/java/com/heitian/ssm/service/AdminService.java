package com.heitian.ssm.service;


import com.heitian.ssm.bo.IncomeBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.TimeCondition;
import com.heitian.ssm.model.Admin;

import java.util.List;

/**
 * 
 * @author Zrt666
 * 管理员登录
 *
 */
public interface AdminService {
    Result adminLogin(Admin admin);

    List<IncomeBo> getIncome(Long i);

    Result getAllIncome();

}
