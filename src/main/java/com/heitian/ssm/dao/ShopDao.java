package com.heitian.ssm.dao;

import com.heitian.ssm.model.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
@Repository
public interface ShopDao {
    Shop selectShopByName(String name);

    List<Shop> selectShops(@Param("start") int start,@Param("count") int count);

    int deleteShopByName(String name);

    int updateShop(Shop shop);

    int insertShop(Shop shop);

    String selectUrlByOwnerId(Long id);

    int selectCount();
}
