package com.heitian.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.CartDao;
import com.heitian.ssm.dao.CustomerDao;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.service.CustomerService;
import com.heitian.ssm.util.SendEmail;

/**
 * Created by Lanting on 2016/11/25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;
    @Resource
    private CartDao cartDao;

    //customer register method
    public Result addCustomer(Customer customer) {
        Result result = new Result();
        if (customer == null || customer.getTelephone() == null || customer.getPassword() == null || customer.getName() == null || customer.getEmail() == null) {
            result.setStatus(0);
            result.setMessage("telephone, password,email and name is not allowed to be empty");
        } else {
            Customer cus = customerDao.getCustomerByTel(customer.getTelephone());
            Customer cus1 = customerDao.getCustomerByEmail(customer.getEmail());
            if (cus != null || cus1 != null)//数据库中已经存在此tel
            {
                result.setStatus(0);
                result.setMessage("failed，the telephone or email has been registered. After activation, you can log in with your user name and password");
            } else {
                try {
                    String email = customer.getEmail();
                    String telephone = customer.getTelephone();
                    StringBuffer sb = new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
                    sb.append("<a href=\"http://106.14.70.91:8080/web-ssm/customer/activate?email=");
                    sb.append(email);
                    sb.append("&");
                    sb.append("telephone=");
                    sb.append(telephone);

                    sb.append("\">http://106.14.70.91:8080/web-ssm/customer/activate?email=");
                    sb.append(email);
                    sb.append("&");
                    sb.append("telephone=");
                    sb.append(telephone);

                    sb.append("</a>");
                    //发送邮件
                    SendEmail.send(email, sb.toString());
                    result.setMessage("success");
                } catch (Exception e) {
                    result.setStatus(0);
                    result.setMessage("failed, please register again.");
                    return result;
                }
                int num = customerDao.addCustomer(customer);//添加用户
                result.setStatus(num);
                if (num == 0)
                    result.setMessage("failed");
                else
                    result.setMessage("sucess");
            }
        }
        return result;
    }

    public Result customerActivate(String telephone, String email) {
        Result result = new Result();
        Customer customer = customerDao.getCustomerByEmail(email);
        if (customer == null) {
            result.setStatus(0);
            result.setMessage("Email has not been registered.");
        } else {
            if (customer.getIsEmailVerified() == 1) {
                result.setStatus(0);
                result.setMessage("Email has been activated .");
            } else {
                int num = customerDao.activateCustomerEmail(telephone, email);
                result.setStatus(num);
                cartDao.insertCart(customer.getId());
            }
        }
        return result;
    }

    //customer login method
    public Result customerLogin(Customer customer) {
        Result result = new Result();
        result.setStatus(0);
        if (customer == null || customer.getTelephone() == null || customer.getPassword() == null || customer.getName() == null || customer.getEmail() == null) {
            result.setMessage("telephone/password/name/email is not allowed to be empty.");
            return result;
        }
        Customer cus = customerDao.getCustomerByEmail(customer.getEmail());
        Customer cus1 = customerDao.getCustomerByTel(customer.getTelephone());
        if (cus == null || cus1 == null) {
            result.setMessage("login failed,email or telephone is not exist");
            return result;
        }
        if (cus.getId() != cus1.getId()) {
            result.setMessage("login failed,invalid email/telephone combination");
            return result;
        }
        if (!cus.getPassword().equals(customer.getPassword())) {
            result.setMessage("Login failed,invalid email+telephone/password combination");
        } else {
            if (cus.getStatus() == 0) {
                result.setStatus(1);
                result.setMessage("login successful");
            } else {
                result.setMessage("Login failed,the account is invalid.");
            }
        }
        return result;
    }
}