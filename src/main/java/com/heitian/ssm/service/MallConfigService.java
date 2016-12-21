package com.heitian.ssm.service;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.MallConfig;

public interface MallConfigService {
	
	public Result addMallConfig(MallConfig mallConfig);
	public MallConfig getMallConfigByKey(String key);
	public Result updateMallConfig(MallConfig mallConfig);
	public Result deleteMallConfig(String key);

}
