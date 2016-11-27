package com.heitian.ssm.controller;

import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.ShopService;
import com.sun.deploy.net.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by oasis on 11/27/16.
 */
@RequestMapping("/service")
public class ShopController {
    private final Logger log = Logger.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "{/register}", method = RequestMethod.POST)
    public @ResponseBody Shop registerNewShop(HttpRequest request, HttpResponse response) {
        
    }
}
