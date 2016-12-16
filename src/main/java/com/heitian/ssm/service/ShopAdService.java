package com.heitian.ssm.service;

import java.sql.Date;
import java.util.List;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.ShopAd;

/**
 * Created by oasis on 12/11/16.
 */
public interface ShopAdService {
	Result addShopAd(Long shopId, Date date);

	List<ShopAd> showShopAd();

	Result deleteShopAd(Long id);

	List<ShopAd> applyShopAd();

	Result rejectShopAd(Long id);
	
	Result agreeShopAd(Long id);
}
