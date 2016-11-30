package com.heitian.ssm.controller;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Owner;
import com.heitian.ssm.service.OwnerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by LanTing on 2016/11/28.
 */
@Controller
@RequestMapping("/owner")
public class OwnerController {

    private Logger log = Logger.getLogger(OwnerController.class);
    @Resource
    private OwnerService ownerService;

    @ResponseBody
    @RequestMapping("/register")
    public Result ownerRegister(@RequestBody Owner owner) {
        log.info("注册新店主");
        Result result = ownerService.ownerRegister(owner);
        return result;
    }

    @ResponseBody
    @RequestMapping("/login")
    public Result ownerLogin(@RequestBody Owner owner, Model model, HttpServletResponse response) {
        log.info("店主登录");

        Result result = ownerService.ownerLogin(owner);

        if (result.getStatus() == 1 && response != null) {
            Cookie nameCookie = new Cookie("OwnerName", owner.getName());
            Cookie pwdCookie = new Cookie("OwnerPassword", owner.getPassword());
            nameCookie.setMaxAge(60 * 60 * 24 * 3);
            pwdCookie.setMaxAge(60 * 60 * 24 * 3);
            response.addCookie(nameCookie);
            response.addCookie(pwdCookie);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result updateOwner(@RequestBody Owner owner) {
        log.info("更新Owner");
        Result result = ownerService.updateOwner(owner);
        return result;
    }

    @ResponseBody
    @RequestMapping("/getByName")
    public Owner getOwnerByName(@RequestParam String name) {
        log.info("根据名字查找店主");
        Owner o = ownerService.selectOwnerByName(name);
        return o;
    }
    @ResponseBody
    @RequestMapping("getById")
    public Owner getOwnerById(@RequestParam long id) {
        log.info("根据id查找店主");
        Owner o = ownerService.selectOwnerById(id);
        return o;
    }
    @ResponseBody
    @RequestMapping("/getAllOwner")
    public List<Owner> getAllOwner(@RequestParam int page, @RequestParam int pageNum) {
        List<Owner> o = ownerService.selectAllOwners(page,pageNum);
        return o;
    }
    @ResponseBody
    @RequestMapping("/getAllUnverified")
    public List<Owner> getAllUnverified(@RequestParam int page, @RequestParam int pageNum)  {
        return ownerService.getAllUnverifiedOwner(page,pageNum);
    }

}
