package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.ShopBo;
import com.heitian.ssm.dao.OwnerPhotoDao;
import com.heitian.ssm.dao.ShopDao;
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
    private OwnerPhotoDao photoDao;

    private Result result = new Result();

    public ShopBo getShopByName(String name) {
        Shop shop =  shopDao.selectShopByName(name);
        if(shop == null)
            return new ShopBo();
        ShopBo shopBo = new ShopBo(shop);
        shopBo.setIdPhotoUrl(shopDao.selectUrlByOwnerId(shop.getOwnerId()));
        return shopBo;
    }

    public List<ShopBo> getPhotos(List<Shop> shops) {
        if(shops == null)
            return new ArrayList<ShopBo>();
        List<ShopBo> shopBos = new ArrayList<>();
        for(Shop shop : shops) {
            shopBos.add(new ShopBo(shop, shopDao.selectUrlByOwnerId(shop.getOwnerId())));
        }
        return shopBos;
    }

    public List<ShopBo> getShopsByName(String name) {
        List<Shop> shops =  shopDao.selectShopsByName(name);
        return getPhotos(shops);
    }

    public List<ShopBo> getShops(int pageNum, int pageCount, Long status) {
        List<Shop> shops = shopDao.selectShops((pageNum - 1)*pageCount, pageCount, status);
        return getPhotos(shops);
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
        photoDao.insertPhoto(new OwnerPhoto(shopBo.getIdPhotoUrl(), shopBo.getOwnerId()));
        shopBo.setStatus((long)3);
        return judge(shopDao.insertShop(shopBo) != 0);
    }

    public Result updateInfo(ShopBo shopBo) {
        Shop tmp = shopDao.selectShopByName(shopBo.getName());
        if(tmp != null && tmp.getOwnerId() != shopBo.getOwnerId()) {
            result.setStatus(0);
            result.setMessage("failed, the shop name has been used");
            return result;
        }
        photoDao.updatePhoto(new OwnerPhoto(shopBo.getIdPhotoUrl(), shopBo.getOwnerId()));
        return judge(shopDao.updateShop(shopBo) > 0);
    }

    public Result updateStatus(Long id, Long status) {
        return judge(shopDao.updateStatus(id, status) > 0);
    }

    public Result judge(boolean var) {
        if (var) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setStatus(0);
            result.setMessage("failed");
        }
        return result;
    }

    public int getCount(Long status) {
        return shopDao.selectCount(status);
    }
}