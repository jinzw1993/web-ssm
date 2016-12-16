package com.heitian.ssm.dao;

import java.sql.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.heitian.ssm.model.ShopAd;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopAdDao {

	int deleteShopAd(@Param(value = "shopId") Long shopId);

	List<ShopAd> applyShopAd();

	List<ShopAd> showShopAd();

	int addShopAd(@Param(value = "shopId") Long shopId, @Param(value = "date") Date date);
	
	int agreeShopAd(@Param(value = "shopId") Long shopId);
	
	int rejectShopAd(@Param(value = "shopId") Long shopId);

}
