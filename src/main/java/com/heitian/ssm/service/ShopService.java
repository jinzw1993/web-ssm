package com.heitian.ssm.service;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.ShopBo;
import com.heitian.ssm.model.Shop;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
public interface ShopService {
    ShopBo getShopByName(String name);

    List<ShopBo> getShops(int pageNum, int pageCount);

    Result updateShop(Shop shop, String name);

    Result addShop(Shop shop);

    int getCount();
}
