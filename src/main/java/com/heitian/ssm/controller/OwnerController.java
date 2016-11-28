package com.heitian.ssm.controller;

import com.heitian.ssm.model.Owner;
import com.heitian.ssm.service.OwnerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by unname on 2016/11/28.
 */
@Controller
@RequestMapping("/Owner")
public class OwnerController {
    private Logger log = Logger.getLogger(OwnerController.class);
    @Resource
    private OwnerService ownerService;

    @RequestMapping("/ownerRegister")
    public @ResponseBody  String ownerRegister(@RequestParam String name, @RequestParam String password ) {
        log.info("注册新店主");
        Owner o=ownerService.selectOwnerByName(name);
        if(o==null) {
            Owner owner = new Owner();
            owner.setStatus((long)0);
            owner.setName(name);
            owner.setPassword(password);
            ownerService.insertOwner(owner);
            return "success";
        }
        else {
            return "failed";
        }
    }

    @RequestMapping("/ownerLogin")
    public @ResponseBody String ownerLogin(@RequestParam String name, @RequestParam String password) {
        log.info("店主登录");
        Owner o = ownerService.selectOwnerByName(name);
        if(o.getName().equals(password)) {
            return "success";
        }
        else {
            return "failed";
        }
    }
//    @RequestMapping("/ownerChangePwd")
//    public @ResponseBody String ownerChangePwd(@RequestParam String oldPwd, @RequestMapping String newPwd) {
//        log.info("更改密码");
//
//    }
}
