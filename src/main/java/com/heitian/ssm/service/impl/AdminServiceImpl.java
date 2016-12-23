package com.heitian.ssm.service.impl;


import com.heitian.ssm.bo.IncomeBo;
import com.heitian.ssm.bo.TimeCondition;
import com.heitian.ssm.dao.MallIncomeDao;
import org.springframework.stereotype.Service;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Admin;
import com.heitian.ssm.service.AdminService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Resource
    private MallIncomeDao mallIncomeDao;

	@Override
	public Result adminLogin(Admin admin) {
		Result result = new Result();
        if (admin == null || admin.getPassword() == null) {
            result.setStatus(0);
            result.setMessage("name or password is not allowed to be empty");
            return result;
        } else if(!admin.getName().equals("parknshop")) {
    		 result.setMessage("Failed, admin name is wrong!");
             result.setStatus(0);
             return result;
    	} else if(!admin.getPassword().equals("parknshop")) {
    		result.setMessage("Failed, admin password is wrong!");
            result.setStatus(0);
            return result;
    	} else {
    		result.setStatus(1);
            result.setMessage("Login success!");
            return result;
    	} 	

	}

    public List<IncomeBo> getIncome(Long i) {
        List<IncomeBo> income = mallIncomeDao.getIncomeByTime(i);
        if(income == null)
            return new ArrayList<>();
        return income;
    }

}
