package com.heitian.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.heitian.ssm.bo.OwnerCondition;
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
	//根据用户状�?�查询用户信�?
	List<Owner> searchOwnerWithKeyword(OwnerCondition ownerCondition);
}
