package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.OwnerDao;
import com.heitian.ssm.dao.ShopAdDao;
import com.heitian.ssm.dao.ShopDao;
import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.ShopAdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by unname on 2016/12/11.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopAdServiceImpl implements ShopAdService {
    @Resource
    private ShopAdDao shopAdDao;
    @Resource
    private ShopDao shopDao;

    public Result addShopAd(Long ownerId, Date date) {
        Shop shop = shopDao.selectShopByOwnerId(ownerId);
        Result result = new Result();
        if(shop==null) {
            result.setMessage("shop not found");
            result.setStatus(0);
            return result;
        }
        long status=shop.getStatus();
        if(status!=0) {
            result.setMessage("shop abnormal");
            result.setStatus(0);
            return result;
        }
        Long shopId = shop.getId();
        int i = shopAdDao.insertShopAd(shopId, date);
        if(i!=0) {
            result.setMessage("success");
            result.setStatus(1);
            return result;
        } else {
            result.setMessage("insert failed");
            result.setStatus(0);
            return result;
        }
    }
}
