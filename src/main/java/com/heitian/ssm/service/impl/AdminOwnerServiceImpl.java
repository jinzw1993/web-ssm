package com.heitian.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.heitian.ssm.bo.OwnerBo;
import com.heitian.ssm.bo.OwnerCondition;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.AdminOwnerDao;
import com.heitian.ssm.model.Owner;
import com.heitian.ssm.service.AdminOwnerService;

@Service
public class AdminOwnerServiceImpl implements AdminOwnerService {
	
	@Resource
	private AdminOwnerDao adminOwnerDao;

	@Override
	public OwnerBo findOwnerBoById(long id) {
		Owner owner = adminOwnerDao.findOwnerById(id);
		OwnerBo ownerBo = new OwnerBo(owner);
		return ownerBo;
	}

	@Override
	public Result blacklist(long id) {
		Result result = new Result();
		int status = adminOwnerDao.findOwnerById(id).getStatus();
		if(status == 1) {
			result.setStatus(0);
            result.setMessage("The Owner has been to join blacklist!");
		} else if (status == 2) {
			result.setStatus(0);
            result.setMessage("The Owner has been to deleted!");
		} else {
			adminOwnerDao.blacklist(id);
			result.setStatus(1);
            result.setMessage("Join the blacklist success!");
		}
			
		return result;
	}

	@Override
	public Result whitelist(long id) {
		Result result = new Result();
		int status = adminOwnerDao.findOwnerById(id).getStatus();
		if(status == 0) {
			result.setStatus(0);
            result.setMessage("The Owner has been to join whitelist!");
		} else if (status == 2) {
			result.setStatus(0);
            result.setMessage("The Owner has been to deleted!");
		} else {
			adminOwnerDao.blacklist(id);
			result.setStatus(1);
            result.setMessage("Join the blacklist success!");
		}
			
		return result;
	}

	@Override
	public Result deleteOwnerById(long id) {
		Result result = new Result();
		int status = adminOwnerDao.findOwnerById(id).getStatus();
		if(status == 2) {
			result.setStatus(0);
            result.setMessage("The Owner has deleted!");
		} else {
			adminOwnerDao.deleteOwnerById(id);
			result.setStatus(1);
            result.setMessage("Delete success!");
		}
			
		return result;
	}

	@Override
	public List<OwnerBo> searchOwnerWithKeyword(OwnerCondition ownerCondition) {
		List<OwnerBo> ownerBos = new ArrayList<OwnerBo>();
		List<Owner> owners = adminOwnerDao.searchOwnerWithKeyword(ownerCondition);
		if(owners != null) {
			for(Owner owner : owners) {
				ownerBos.add(new OwnerBo(owner));
			}
		}
		return ownerBos;
	}

}
