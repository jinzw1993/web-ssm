package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.OwnerDao;
import com.heitian.ssm.dao.ShopDao;
import com.heitian.ssm.model.Owner;
import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private OwnerDao ownerDao;

    private Result result = new Result();

    public Shop getShopByName(String name) {
        Shop shop =  shopDao.selectShopByName(name);
        if(shop == null)
            return new Shop();
        return shop;
    }

    public List<Shop> getShops(int pageNum, int pageCount) {
        List<Shop> shops = shopDao.selectShops((pageNum - 1)*pageCount, pageCount);
        if(shops == null)
            return new ArrayList<Shop>();
        else
            return shops;
    }

    public Result addShop(Shop shop) {
        if(shopDao.selectShopByName(shop.getName()) != null) {
            result.setStatus(0);
            result.setMessage("failed, the shop name has been used.");
        } else if(shopDao.insertShop(shop) == 0) {
            result.setStatus(0);
            result.setMessage("failed");
        } else {
            result.setStatus(1);
            result.setMessage("success");
        }
        return result;
    }

    public Result updateShop(Shop shop, String name) {
        Owner owner = ownerDao.selectOwnerByName(name);
        if (owner == null || owner.getId() != shop.getOwnerId()){
            result.setStatus(0);
            result.setMessage("failed, you don't have right");
        } else {
            if (shopDao.updateShop(shop) > 0) {
                result.setStatus(1);
                result.setMessage("success");
            } else {
                result.setStatus(0);
                result.setMessage("failed");
            }
        }
        return result;
    }
}