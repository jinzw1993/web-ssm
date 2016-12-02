package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.CustomerDao;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Lanting on 2016/11/25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDao customerDao;

    //customer register method
    public Result addCustomer(Customer customer) {
        Result result = new Result();
        if(customer==null||customer.getTelephone()==null||customer.getPassword()==null||customer.getName()==null)
        {
            result.setStatus(0);
            result.setMessage("telephone, password and name is not allowed to be empty");
        }
        else {
            Customer cus = customerDao.getCustomerByTel(customer.getTelephone());
            if (cus != null)//数据库中已经存在此tel
            {
                result.setStatus(0);
                result.setMessage("failed，the telephone number has been registered");
            } else {
                int num = customerDao.addCustomer(customer);//添加用户
                result.setStatus(num);
                if (num == 0)
                    result.setMessage("failed");
                else
                    result.setMessage("success");
            }
        }
        return result;
    }

    //customer login method
    public Result customerLogin(Customer customer) {
        Result result = new Result();
        if (customer == null || customer.getTelephone() == null || customer.getPassword() == null || customer.getPassword() == "") {
            result.setStatus(0);
            result.setMessage("telephone or password is not allowed to be empty.");
            return result;
        }
        Customer cus = customerDao.getCustomerByTel(customer.getTelephone());
        if (cus == null) {
            result.setStatus(0);
            result.setMessage("login failed,telephone number is not exist");
        } else if (!cus.getPassword().equals(customer.getPassword())) {
            result.setStatus(0);//password error
            result.setMessage("Login failed,invalid telephone/password combination");
        } else {
            result.setStatus(1);
            result.setMessage("login successful");
        }
        return result;
    }
}
