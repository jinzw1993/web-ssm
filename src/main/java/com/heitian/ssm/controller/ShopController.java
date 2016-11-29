package com.heitian.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.ShopService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oasis on 11/27/16.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
    private final Logger log = Logger.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    @RequestMapping(value="/getShopByName", produces="application/json;charset=UTF-8", method = RequestMethod.GET)
    public Object getShopByName(@RequestParam String name) {
        log.info("查询店铺:" + name);
        Shop shop = shopService.getShopByName(name);
        Map<String, Object> map = new HashMap<>();
        if(shop != null)
            return shop;
        else
            return map.put("result","1");
    }

    @RequestMapping(value = "/register", produces="application/json;charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody String registerNewShop(@RequestParam Long ownerId,
                                              @RequestParam String name,
                                              @RequestParam String contact,
                                              @RequestParam String email,
                                              @RequestParam String telephone) {
        log.info("新店注册");
        if(shopService.getShopByName(name) == null) {
            Shop shop = new Shop(ownerId, name, contact, email, telephone, (long) 0);
            if (shopService.insertShop(shop)) {
                log.info("注册成功");
                return "{\"result\",0}";
            } else {
                log.info("注册失败");
                return "{\"result\",1}";
            }
        } else {
            log.info("名称重复");
            return "{\"result\",2}";
        }
    }
}
