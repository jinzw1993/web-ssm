package com.heitian.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.heitian.ssm.bo.CustomerCondition;
import com.heitian.ssm.model.Customer;

@Repository
public interface AdminCustomerDao {
	//根据id查询用户信息
	Customer findCustomerById(long id);
	//根据用户名查询用户信�?
	//List<Customer> findCustomerByName(String name);
	//查询�?有用户信�?
	//List<Customer> findAllCustomer();
	//根据名字模糊查询用户信息
	//List<Customer> selectCustomerByName(String strname);
	//将用户拉入黑名单
	void blacklist(long id);
	//查询黑名单或者白名单下的�?有用�?
	//List<Customer> findlist(int status);
	//将用户拉入白名单
	void whitelist(long id);
	//删除用户信息
	void deleteCustomerById(long id);
	//根据用户状�?�查询用户信�?
	List<Customer> searchCustomerWithKeyword(CustomerCondition customerCondition);

}
