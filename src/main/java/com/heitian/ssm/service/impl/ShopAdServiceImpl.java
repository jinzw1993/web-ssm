package com.heitian.ssm.service.impl;

import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.ShopAdDao;
import com.heitian.ssm.model.ShopAd;
import com.heitian.ssm.service.ShopAdService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopAdServiceImpl implements ShopAdService {

	@Resource
	private ShopAdDao shopAdDao;

	@Override
	public List<ShopAd> showShopAd()
	{

		List<ShopAd> Shop = shopAdDao.showShopAd();
		if (Shop == null || Shop.size() == 0) {
			return null;
		}
		return Shop;
	}

	@Override
	public Result deleteShopAd(Long id)
	{
		int i = shopAdDao.deleteShopAd(id);
		return returnRes(i);
	}

	@Override
	public List<ShopAd> applyShopAd()
	{
		List<ShopAd> Shop = shopAdDao.applyShopAd();
		if (Shop == null || Shop.size() == 0) {
			return null;
		}

		return Shop;
	}

	@Override
	public Result addShopAd(Long shopId, Date date)
	{
		int i = shopAdDao.addShopAd(shopId, date);
		return returnRes(i);
	}

	private Result returnRes(int i)
	{
		Result result = new Result();
		if (i != 0) {
			result.setStatus(1);
			result.setMessage("success");
		} else {
			result.setMessage("failed");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public Result rejectShopAd(Long id)
	{
		int i = shopAdDao.rejectShopAd(id);
		return returnRes(i);
	}

	@Override
	public Result agreeShopAd(Long id)
	{
		int i = shopAdDao.agreeShopAd(id);
		return returnRes(i);
	}

	
}
