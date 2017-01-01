package com.heitian.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.heitian.ssm.bo.ShopAdBo;
import com.heitian.ssm.dao.OwnerDao;
import com.heitian.ssm.dao.ShopDao;
import com.heitian.ssm.model.Shop;
import com.heitian.ssm.util.ResultResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.ShopAdDao;
import com.heitian.ssm.service.ShopAdService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopAdServiceImpl implements ShopAdService {

	@Resource
	private ShopAdDao shopAdDao;

    @Resource
    private ShopDao shopDao;

    @Resource
    private OwnerDao ownerDao;

	@Override
	public Result addShopAd(Long ownerId, String photoUrl, Double price)
	{
        Shop tmp = shopDao.selectShopByOwnerId(ownerId);
        Long shopId = tmp.getId();
        if(shopId == null || tmp.getStatus() != 0)
            return ResultResolver.returnRes(0);
        Long status = shopAdDao.selectStatus(shopId);
        if(status == null || status > 1) {
            if(price > ownerDao.selectOwnerById(ownerId).getBalance()) {
                Result result = new Result();
                result.setStatus(0);
                result.setMessage("You balance isn't enough for this price");
                return result;
            }
            int i = shopAdDao.addShopAd(shopId, photoUrl, price);
            return ResultResolver.returnRes(i);
        } else {
            Result result = new Result();
            result.setStatus(0);
            result.setMessage("have applied before");
            return result;
        }
	}

	@Override
	public Result showShopAdStatus(Long ownerId)
	{
        Shop tmp = shopDao.selectShopByOwnerId(ownerId);
        Long shopId = tmp.getId();
        if(shopId == null || tmp.getStatus() != 0)
            return ResultResolver.returnRes(0);
        Result result = new Result();
		Long status = shopAdDao.selectStatus(shopId);
        if(status == null) {
            result.setStatus(-1);
            result.setMessage("haven't applied");
        } else {
            result.setStatus(status.intValue());
            if(status == 0) {
                result.setMessage("wait for verified");
            } else if(status == 1) {
                result.setMessage("verified");
            } else if(status == 2) {
                result.setMessage("rejected");
            } else if(shopId == 3) {
                result.setMessage("out of date");
            }
        }
		return result;
	}

	@Override
	public Result deleteShopAd(Long ownerId)
	{
        Shop tmp = shopDao.selectShopByOwnerId(ownerId);
        Long shopId = tmp.getId();
        if(shopId == null || tmp.getStatus() != 0)
            return ResultResolver.returnRes(0);
		int i = shopAdDao.deleteShopAd(shopId);
		return ResultResolver.returnRes(i);
	}

    @Override
    public Result changeShopAdStatus(Long shopId, Long status)
    {
        if(status == 1)
            if(shopAdDao.getNum(1L) > 4) {
                Result result = new Result();
                result.setStatus(0);
                result.setMessage("you have verified 5 shops");
                return result;
            }
        int i = shopAdDao.changeShopAdStatus(shopId, status);
        return ResultResolver.returnRes(i);
    }

    public List<ShopAdBo> verifiedShopAd(){
        List<ShopAdBo> list = shopAdDao.verifiedShopAdBo();
        if(list == null)
            return new ArrayList<>();
        else return list;
    }

    public List<ShopAdBo> unverifiedShopAd(int page, int count){
        List<ShopAdBo> list = shopAdDao.unverifiedShopAdBo((page-1)*count, count);
        if(list == null)
            return new ArrayList<>();
        else return list;
    }

    public Result getNum(Long status) {
        Result result = new Result();
        result.setStatus(1);
        result.setMessage(String.valueOf(shopAdDao.getNum(status)));
        return result;
    }
}
