package com.heitian.ssm.dao;

import com.heitian.ssm.model.ShopAd;
import org.apache.ibatis.annotations.Param;

/**
 * Created by oasis on 12/11/16.
 */
public interface ShopAdDao {
    int insertShopAd(@Param("ShopAd")ShopAd shopAd);
}
