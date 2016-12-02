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

    @RequestMapping(value="/searchByStatus")
    public @ResponseBody
    List<ShopBo> getShopsByStatus(@RequestParam int page, @RequestParam int count, @RequestParam long status) {
        log.info("根据status查询列表");
        return shopService.getShops(page, count, status);
    }

    @RequestMapping(value= "/searchAll")
    public @ResponseBody
    List<ShopBo> getShops(@RequestParam int page, @RequestParam int count) {
        log.info("查询店铺列表");
        return shopService.getShops(page, count ,(long)0);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    Result addNewShop(@RequestBody ShopBo shopBo, @CookieValue(value = "OwnerEmail",defaultValue = "swc") String name) {
        log.info("新店注册");
        if("swc".equals(name))
            return returnResult();
        return shopService.addShop(shopBo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    Result update(@RequestBody ShopBo shopBo, @CookieValue(value = "OwnerEmail",defaultValue = "swc") String name) {
        log.info("店铺更新" + name);
        if("swc".equals(name)) {
            return returnResult();
        }
        return shopService.updateInfo(shopBo);
    }

    @RequestMapping(value = "/count")
    public @ResponseBody
    Result getCount() {
        log.info("查询店铺数量");
        Result result = new Result();
        result.setStatus(1);
        result.setMessage(String.valueOf(shopService.getCount((long)0)));
        return result;
    }
    @RequestMapping(value = "/countByStatus")
    public @ResponseBody
    Result getCountByStatus(@RequestParam Long status) {
        log.info("查询店铺数量");
        Result result = new Result();
        result.setStatus(1);
        result.setMessage(String.valueOf(shopService.getCount(status)));
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Result delete(@RequestParam Long id, @CookieValue(value = "OwnerEmail",defaultValue = "swc") String name) {
        if("swc".equals(name)) {
            return returnResult();
        }
        log.info("店铺删除");
        return shopService.updateStatus(id, (long)2);
    }

    @RequestMapping(value = "/changeStatus")
    public @ResponseBody
    Result changeStatus(@RequestParam Long id, @RequestParam Long status) {
        log.info("店铺状态更改");
        return shopService.updateStatus(id, status);
    }

    public Result returnResult() {
        Result result = new Result();
        result.setStatus(0);
        result.setMessage("you haven't log in");
        return result;
    }
}
