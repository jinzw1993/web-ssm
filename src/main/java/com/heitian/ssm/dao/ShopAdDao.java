package com.heitian.ssm.dao;

import java.util.List;

import com.heitian.ssm.bo.ShopAdBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopAdDao {

    int addShopAd(@Param(value = "shopId") Long shopId, @Param("photoUrl") String photoUrl, @Param("price") Double price);

	int deleteShopAd(@Param(value = "shopId") Long shopId);

    Long selectStatus(@Param("shopId") Long shopId);

	List<ShopAdBo> verifiedShopAdBo();

    List<ShopAdBo> unverifiedShopAdBo(@Param("start") int start, @Param("count") int count);

    int getNum(@Param("status") Long status);

	int changeShopAdStatus(@Param(value = "shopId") Long shopId, @Param("status") Long status);
}
