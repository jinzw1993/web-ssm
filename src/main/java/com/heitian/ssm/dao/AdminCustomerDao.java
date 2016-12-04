package com.heitian.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heitian.ssm.bo.CustomerCondition;
import com.heitian.ssm.model.Customer;

@Repository
public interface AdminCustomerDao {
	//根据id查询用户信息
	Customer findCustomerById(long id);
	//将用户拉入黑名单
	void blacklist(long id);
	//将用户拉入白名单
	void whitelist(long id);
	//删除用户信息
	void deleteCustomerById(long id);
	//根据用户状态查询用户信息
	List<Customer> searchCustomerWithKeyword(CustomerCondition customerCondition);
	//分页
	List<Customer> searchByNum(@Param("start")int start, @Param("num")int num);

}
