package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.ShopDao;
import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    public Shop getShopByName(String name) {
        return shopDao.selectShopByName(name);
    }

    public List<Shop> getAllShops() {
        return shopDao.selectAllShops();
    }

    public boolean insertShop(Shop shop) {
        if(shopDao.insertShop(shop) == 0) {
            return false;
        } else
            return true;
    }

    public boolean updateShop(Shop shop) {
        if(shopDao.updateShop(shop) > 0) {
            return true;
        } else
            return false;
    }

    public boolean changeStatus(Shop shop,Long status) {
        shop.setStatus(status);
        return updateShop(shop);
    }
}