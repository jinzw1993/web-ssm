package com.heitian.ssm.dao;

import com.heitian.ssm.model.ShopAd;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by oasis on 12/11/16.
 */
@Repository
public interface ShopAdDao {
    int insertShopAd(@Param("shopId") Long shopId, @Param("date") Date date);
}
