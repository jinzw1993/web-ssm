package com.heitian.ssm.dao;

import com.heitian.ssm.model.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
@Repository
public interface ShopDao {
    List<Shop> selectShopByName(String name);

    List<Shop> selectAllShops();

    int deleteShopByName(String name);

    int updateShop(Shop shop);

    Object insertShop(Shop shop);
}
