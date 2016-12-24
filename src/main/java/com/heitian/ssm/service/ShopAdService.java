package com.heitian.ssm.service;

import java.util.List;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.ShopAdBo;

/**
 * Created by oasis on 12/11/16.
 */
public interface ShopAdService {
	Result addShopAd(Long shopId, String photoUrl, Double price);

	Result showShopAdStatus(Long id);

	Result deleteShopAd(Long id);

	List<ShopAdBo> verifiedShopAd();

	List<ShopAdBo> unverifiedShopAd(int page, int count);

	Result changeShopAdStatus(Long id, Long status);

    Result getNum(Long status);
}
