package com.heitian.ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heitian.ssm.model.MallConfig;

@Repository
public interface MallConfigDao {
	
	public int addMallConfig(@Param("mallConfig") MallConfig mallConfig);
	public MallConfig getMallConfigByKey(String key);
	public int updateMallConfig(@Param("mallConfig") MallConfig mallConfig);

}
