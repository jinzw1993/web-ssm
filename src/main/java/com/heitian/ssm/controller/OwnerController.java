package com.heitian.ssm.controller;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Owner;
import com.heitian.ssm.service.OwnerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by S.W on 2016/11/28.
 */
@Controller
@RequestMapping("/owner")
public class OwnerController {

    private Logger log = Logger.getLogger(OwnerController.class);
    @Resource
    private OwnerService ownerService;

    /**
     * 注册
     * @param owner 需要name, password, email
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping(value="/register")
    public Result ownerRegister(@RequestBody Owner owner) {
        log.info("注册新店主");
        return ownerService.ownerRegister(owner);
    }

    /**
     * 激活成功页面
     * @param email
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping(value="/activate",method = RequestMethod.GET)
    public Result ownerActivate( @RequestParam String email) {
        return ownerService.processActivate(email);
    }

    /**
     * 登录
     * @param owner  需要email, password
     * @param response
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/login")
    public Result ownerLogin(@RequestBody Owner owner, HttpServletResponse response) {
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

    /**
     * 根据Owner.email更新Owner密码或Status
     *
     * @param owner 对象需要 email, password, status, isEmailVerified
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/update")
    public Result updateOwner(@RequestBody Owner owner) {
        log.info("更新Owner");
        return ownerService.updateOwner(owner);
    }

    /**
     *根据name查找Owner
     * @param name Owner name
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/getByName")
    public Owner getOwnerByName(@RequestParam String name) {
        log.info("根据名字查找店主");
        Owner o = ownerService.selectOwnerByName(name);
        return o;
    }

    /**
     *根据id查找Owner
     * @param id Owner id
     * @return Owner对象
     */
    @ResponseBody
    @RequestMapping("getById")
    public Owner getOwnerById(@RequestParam long id) {
        log.info("根据id查找店主");
        Owner o = ownerService.selectOwnerById(id);
        return o;
    }

    /**
     *得到所有Owner
     * @param page 第page页
     * @param pageNum 每页条目数
     * @return Owner List
     */
    @ResponseBody
    @RequestMapping("/getAllOwner")
    public List<Owner> getAllOwner(@RequestParam int page, @RequestParam int pageNum) {
        List<Owner> o = ownerService.selectAllOwners(page,pageNum);
        return o;
    }

    /**
     *得到所有未邮件认证的Owner
     * @param page 第page页
     * @param pageNum 每页条目数
     * @return Owner List
     */
    @ResponseBody
    @RequestMapping("/getAllUnverified")
    public List<Owner> getAllUnverified(@RequestParam int page, @RequestParam int pageNum)  {
        return ownerService.getAllUnverifiedOwner(page,pageNum);
    }

    /**
     * 得到owner数量
     * @return 存到result.message中
     */
    @ResponseBody
    @RequestMapping("/getOwnerNum")
    public Result getOwnerNum() {
        Result result =new Result();
        result.setMessage(Integer.toString(ownerService.getOwnerNum()));
        result.setStatus(1);
        return result;
    }

    /**
     * 得到未进行邮件认证的owner数量
     * @return 存到result.message中
     */
    @ResponseBody
    @RequestMapping("getUnverifiedNum")
    public Result getUnverifiedNum() {
        Result result =new Result();
        result.setMessage(Integer.toString(ownerService.getUnverifiedNum()));
        result.setStatus(1);
        return result;
    }
}
