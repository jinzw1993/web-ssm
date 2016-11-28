package com.heitian.ssm.controller;

import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.ShopService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by oasis on 11/27/16.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
    private final Logger log = Logger.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public @ResponseBody String registerNewShop(@RequestParam Long ownerId,
                                              @RequestParam String name,
                                              @RequestParam String contact,
                                              @RequestParam String email,
                                              @RequestParam String telephone,
                                              @RequestParam Long status) {
        log.info("新店注册");
        Shop shop = new Shop(ownerId, name, contact, email, telephone,status);
        if(shopService.insertShop(shop)) {
            log.info("注册成功");
            return "success";
        }
        else {
            log.info("注册失败");
            return "error";
        }
    }
}
