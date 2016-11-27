package com.heitian.ssm.dao;

import com.heitian.ssm.model.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
@Repository
public interface ShopDao {
    Shop selectShopByName(String name);

    List<Shop> selectAllShops();

    boolean deleteShopByName(String name);

    boolean updateShopByName(String name, Shop shop);

    boolean insertShopByName(Shop shop);
}
