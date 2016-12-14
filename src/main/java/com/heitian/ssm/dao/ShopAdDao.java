package com.heitian.ssm.dao;

import java.sql.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.heitian.ssm.model.ShopAd;

public interface ShopAdDao {

	int deleteShopAd(@Param(value = "shopId") Long shopId);

	List<ShopAd> applyShopAd();

	List<ShopAd> showShopAd();

	int addShopAd(@Param(value = "shopId") Long shopId, @Param(value = "date") Date date);

}
