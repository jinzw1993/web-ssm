package com.heitian.ssm.service;

import com.heitian.ssm.model.Shop;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
public interface ShopService {
    Shop getShopByName(String name);

    List<Shop> getAllShops();

    boolean changeStatus(Shop shop, Long status);

    boolean updateShop(Shop shop);

    boolean insertShop(Shop shop);
}
