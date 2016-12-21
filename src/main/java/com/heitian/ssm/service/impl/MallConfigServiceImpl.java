package com.heitian.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.MallConfigDao;
import com.heitian.ssm.model.MallConfig;
import com.heitian.ssm.service.MallConfigService;

@Service
@Transactional(rollbackFor = Exception.class)
public class MallConfigServiceImpl implements MallConfigService {
	
	@Resource
	private MallConfigDao mallConfigDao;
	
	@Override
	public Result addMallConfig(MallConfig mallConfig) {
		int i = mallConfigDao.addMallConfig(mallConfig);
		return returnRes(i);
	}
		

	@Override
	public MallConfig getMallConfigByKey(String key) {
		return mallConfigDao.getMallConfigByKey(key);
	}

	@Override
	public Result updateMallConfig(MallConfig mallConfig) {
		int i = mallConfigDao.updateMallConfig(mallConfig);
		return returnRes(i);
	}

	@Override
	public Result deleteMallConfig(String key) {		
		int i = mallConfigDao.deleteMallConfig(key);
		return returnRes(i);
	}
	
	private Result returnRes(int i) {
        Result result = new Result();
        if(i!=0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setMessage("failed");
            result.setStatus(0);
        }
        return result;
    }

}
