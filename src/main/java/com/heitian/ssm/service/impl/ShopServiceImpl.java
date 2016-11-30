package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.ShopBo;
import com.heitian.ssm.dao.OwnerDao;
import com.heitian.ssm.dao.OwnerPhotoDaO;
import com.heitian.ssm.dao.ShopDao;
import com.heitian.ssm.model.Owner;
import com.heitian.ssm.model.OwnerPhoto;
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
    @Autowired
    private OwnerPhotoDaO photoDao;

    private Result result = new Result();

    public ShopBo getShopByName(String name) {
        Shop shop =  shopDao.selectShopByName(name);
        if(shop == null)
            return new ShopBo();
        ShopBo shopBo = new ShopBo(shop);
        shopBo.setIdPhotoUrl(shopDao.selectUrlByOwnerId(shop.getOwnerId()));
        return shopBo;
    }

    public List<ShopBo> getShops(int pageNum, int pageCount) {
        List<Shop> shops = shopDao.selectShops((pageNum - 1)*pageCount, pageCount);
        if(shops == null)
            return new ArrayList<>();
        else {
            List<ShopBo> shopBos = new ArrayList<>();
            for(Shop shop : shops) {
                shopBos.add(new ShopBo(shop, shopDao.selectUrlByOwnerId(shop.getOwnerId())));
            }
            return shopBos;
        }
    }

    public Result addShop(ShopBo shopBo) {
        if(shopDao.selectShopByName(shopBo.getName()) != null) {
            result.setStatus(0);
            result.setMessage("failed, the shop name has been used.");
            return result;
        }
        Shop tmp = shopDao.selectShopByOwnerId(shopBo.getOwnerId());
        if(tmp != null && tmp.getStatus() != 2) {   
            result.setStatus(0);
            result.setMessage("failed, the shop owner has a shop.");
            return result;
        }
        photoDao.insertPhoto(new OwnerPhoto(shopBo.getIdPhotoUrl()));
        if(shopDao.insertShop(shopBo) == 0) {
            result.setStatus(0);
            result.setMessage("failed");
        } else {
            result.setStatus(1);
            result.setMessage("success");
        }
        return result;
    }

    public Result updateShop(ShopBo shopBo, String name) {
        Owner owner = ownerDao.selectOwnerByName(name);
        if (owner == null || owner.getId() != shopBo.getOwnerId()){
            result.setStatus(0);
            result.setMessage("failed, you don't have right");
        } else {
            photoDao.updatePhoto(new OwnerPhoto(shopBo.getIdPhotoUrl(), shopBo.getOwnerId()));
            if (shopDao.updateShop(shopBo) > 0) {
                result.setStatus(1);
                result.setMessage("success");
            } else {
                result.setStatus(0);
                result.setMessage("failed");
            }
        }
        return result;
    }

    public int getCount() {
        return shopDao.selectCount();
    }
}