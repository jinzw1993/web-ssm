package com.heitian.ssm.controller;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.service.ShopAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by oasis on 12/11/16.
 */
@Controller
@RequestMapping("shopAd")
public class ShopAdController {
    @Autowired
    private ShopAdService shopAdService;

    private Result result = new Result();

    @RequestMapping("/add")
    public @ResponseBody
    Result addShopAd(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null) {
            result.setMessage("haven't log in");
            result.setStatus(0);
            return result;
        }
        String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        return shopAdService.addShopAd(Long.valueOf(id));
    }
}
