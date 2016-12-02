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

    List<ShopBo> getShopsByName(String name);

    List<ShopBo> getVerifiedShops(int pageNum, int pageCount);

    Result updateStatus(Long id, Long status);

    Result addShop(ShopBo shop);

    int getCount();

    int getVerifiedCount();

    Result updateInfo(ShopBo shopBo);
}
