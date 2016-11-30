package com.heitian.ssm.service;

import java.util.List;

import com.heitian.ssm.bo.CustomerBo;
import com.heitian.ssm.bo.Result;

public interface AdminCustomerService {
	public CustomerBo findCustomerBoById(int id);
	public List<CustomerBo> findCustomerBoByName(String name);
	public List<CustomerBo> findCustomerBoAll();
	public List<CustomerBo> selectCustomerByName(String strname);
	public Result blacklist(int id);
	public List<CustomerBo> findCustomerBoList(int status);
	public Result whitelist(int id);
	public Result deleteCustomerById(int id);

}
