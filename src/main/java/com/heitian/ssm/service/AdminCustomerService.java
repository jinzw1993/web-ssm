package com.heitian.ssm.service;

import java.util.List;

import com.heitian.ssm.bo.CustomerBo;
import com.heitian.ssm.bo.CustomerCondition;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Customer;

public interface AdminCustomerService {
	public CustomerBo findCustomerBoById(long id);
	public Result blacklist(long id);
	public Result whitelist(long id);
	public Result deleteCustomerById(long id);
	public List<CustomerBo> searchCustomerWithKeyword(CustomerCondition customerCondition);

}
