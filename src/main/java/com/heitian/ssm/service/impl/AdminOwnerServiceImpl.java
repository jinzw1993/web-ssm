package com.heitian.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.heitian.ssm.dao.ShopDao;
import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.bo.OwnerBo;
import com.heitian.ssm.bo.OwnerCondition;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.AdminOwnerDao;
import com.heitian.ssm.model.Owner;
import com.heitian.ssm.service.AdminOwnerService;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminOwnerServiceImpl implements AdminOwnerService {
	
	@Resource
	private AdminOwnerDao adminOwnerDao;
	@Resource
	private ShopService shopService;
	@Resource
	private ShopDao shopDao;

	@Override
	public OwnerBo findOwnerBoById(long id) {
		Owner owner = adminOwnerDao.findOwnerById(id);
		OwnerBo ownerBo = new OwnerBo();
		if(owner != null) {
			ownerBo = new OwnerBo(owner);
		}
		return ownerBo;
	}

	@Override
	public Result blacklist(long id) {
		Result result = new Result();
		Owner owner = adminOwnerDao.findOwnerById(id);
		long status = -1;
		if(owner == null) {
			result.setStatus(0);
            result.setMessage("The Owner does not exist!");
		} else {
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
		}		
		return result;
	}

	@Override
	public Result whitelist(long id) {
		Result result = new Result();
		Owner owner = adminOwnerDao.findOwnerById(id);
		long status = -1;
		if(owner == null) {
			result.setStatus(0);
            result.setMessage("The Owner does not exist!");
		} else {
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
		}		
		return result;
	}

	@Override
	public Result deleteOwnerById(long id) {
		Result result = new Result();
		Owner owner = adminOwnerDao.findOwnerById(id);
		long status = -1;
		if(owner == null) {
			result.setStatus(0);
            result.setMessage("The Owner does not exist!");
		} else {
			if(status == 2) {
				result.setStatus(0);
	            result.setMessage("The Owner has deleted!");
			} else {
				Shop shop = shopDao.selectShopByOwnerId(id);
				if(shop != null)
					shopService.updateStatus(shop.getId(), 2L);
				adminOwnerDao.deleteOwnerById(id);
				result.setStatus(1);
	            result.setMessage("Delete success!");
			}
		}			
		return result;
	}

	@Override
	public List<OwnerBo> searchOwnerWithKeyword(OwnerCondition ownerCondition) {
		List<OwnerBo> ownerBos = new ArrayList<OwnerBo>();
		List<Owner> owners;
		if(ownerCondition == null) 
			owners = adminOwnerDao.searchByNum(ownerCondition.getStart(), ownerCondition.getNum());
		else
			owners = adminOwnerDao.searchOwnerWithKeyword(ownerCondition);
		if(owners != null) {
			for(Owner owner : owners) {
				ownerBos.add(new OwnerBo(owner));
			}
		}
		return ownerBos;
	}

}
