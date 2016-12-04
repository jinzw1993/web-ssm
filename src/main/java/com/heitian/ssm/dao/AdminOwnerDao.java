package com.heitian.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heitian.ssm.bo.OwnerCondition;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.model.Owner;

@Repository
public interface AdminOwnerDao {

	//根据id查询用户信息
	Owner findOwnerById(long id);
	//将用户拉入黑名单
	void blacklist(long id);
	//将用户拉入白名单
	void whitelist(long id);
	//删除用户信息
	void deleteOwnerById(long id);
	//根据用户状态查询用户信息
	List<Owner> searchOwnerWithKeyword(OwnerCondition ownerCondition);
	//分页
	List<Owner> searchByNum(@Param("start")int start, @Param("num")int num);
}
