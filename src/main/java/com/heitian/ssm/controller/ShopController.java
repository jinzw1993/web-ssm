package com.heitian.ssm.controller;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.ShopBo;
import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.ShopService;
import org.apache.ibatis.annotations.Param;
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

    @RequestMapping(value="/search")
    public @ResponseBody
    ShopBo getShopByName(@RequestParam String name) {
        log.info("查询店铺:" + name);
        return shopService.getShopByName(name);
    }

    @RequestMapping(value="/searchLike")
    public @ResponseBody
    List<ShopBo> getShopsByName(@RequestParam String name) {
        log.info("模糊查询店铺:" + name);
        return shopService.getShopsByName(name);
    }

    @RequestMapping(value="/searchVerified")
    public @ResponseBody
    List<ShopBo> getVerifiedShops(@RequestParam int page, @RequestParam int count) {
        log.info("查询待审核列表");
        return shopService.getVerifiedShops(page, count);
    }

    @RequestMapping(value= "/searchAll")
    public @ResponseBody
    List<ShopBo> getShops(@RequestParam int page, @RequestParam int count) {
        log.info("查询店铺列表");
        return shopService.getShops(page, count);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    Result addNewShop(@RequestBody ShopBo shopBo, @CookieValue(value = "OwnerName",defaultValue = "swc") String name) {
        log.info("新店注册");
        if(!"swc".equals(name))
            return shopService.addShop(shopBo);
        else{
            Result result = new Result();
            result.setStatus(0);
            result.setMessage("you haven't log in");
            return result;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    Result update(@RequestBody ShopBo shopBo, @CookieValue(value = "OwnerName",defaultValue = "swc") String name) {
        if("swc".equals(name)) {
            Result result = new Result();
            result.setStatus(0);
            result.setMessage("you haven't log in");
            return result;
        }
        log.info("店铺更新" + shopBo.getName());
        return shopService.updateInfo(shopBo, name);
    }

    @RequestMapping(value = "/count")
    public @ResponseBody
    Result getCount() {
        log.info("查询店铺数量");
        Result result = new Result();
        result.setStatus(1);
        result.setMessage(String.valueOf(shopService.getCount()));
        return result;
    }
    @RequestMapping(value = "/verifiedCount")
    public @ResponseBody
    Result getVerifiedCount() {
        log.info("查询店铺数量");
        Result result = new Result();
        result.setStatus(1);
        result.setMessage(String.valueOf(shopService.getVerifiedCount()));
        return result;
    }

}
