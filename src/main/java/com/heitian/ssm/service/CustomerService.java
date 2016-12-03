package com.heitian.ssm.service;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Customer;


/**
 * Created by Lanting on 2016/11/25.
 */
public interface CustomerService {
    public Result addCustomer(Customer customer);
    public Result customerLogin(Customer customer);
    public Result customerActivate(String telephone, String email);

}

