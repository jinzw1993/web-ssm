package com.heitian.ssm.service;

import java.util.List;

import com.heitian.ssm.bo.OwnerBo;
import com.heitian.ssm.bo.OwnerCondition;
import com.heitian.ssm.bo.Result;

public interface AdminOwnerService {
	
	public OwnerBo findOwnerBoById(long id);
	public Result blacklist(long id);
	public Result whitelist(long id);
	public Result deleteOwnerById(long id);
	public List<OwnerBo> searchOwnerWithKeyword(OwnerCondition OwnerCondition);

}
