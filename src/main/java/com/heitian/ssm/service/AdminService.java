package com.heitian.ssm.service;


import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Admin;
/**
 * 
 * @author Zrt666
 * 管理员登录
 *
 */
public interface AdminService {
	
	public Result adminLogin(Admin admin);

}
