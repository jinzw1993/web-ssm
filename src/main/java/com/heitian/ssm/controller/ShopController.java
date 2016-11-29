package com.heitian.ssm.controller;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.ShopService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
    private final Logger log = Logger.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    @RequestMapping(value="/getShopByName")
    public @ResponseBody
    Shop getShopByName(@RequestParam String name) {
        log.info("查询店铺:" + name);
        return shopService.getShopByName(name);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    Result addNewShop(@RequestBody Shop shop, @CookieValue(value = "OwnerName",defaultValue = "swc") String name) {
        log.info("新店注册");
        if(name != null)
            return shopService.addShop(shop);
        else{
            Result result = new Result();
            result.setStatus(0);
            result.setMessage("you haven't log in");
            return result;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    Result update(@RequestBody Shop shop, @CookieValue(value = "OwnerName",defaultValue = "swc") String name) {
        if("swc".equals(name)) {
            Result result = new Result();
            result.setStatus(0);
            result.setMessage("you haven't log in");
            return result;
        }
        log.info("店铺更新" + shop.getName());
        return shopService.updateShop(shop, name);
    }

    @RequestMapping(value= "/getShops")
    public @ResponseBody
    List<Shop> getShops(@RequestParam int page, @RequestParam int count) {
        log.info("查询店铺列表");
        return shopService.getShops(page, count);
    }
}
