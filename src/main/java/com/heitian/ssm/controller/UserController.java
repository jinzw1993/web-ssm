package com.heitian.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.heitian.ssm.model.Customer;
import com.heitian.ssm.model.User;
import com.heitian.ssm.service.AdminCustomerService;
import com.heitian.ssm.service.UserService;

/**
 * Created by LanTing on 2016/11/24.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource AdminCustomerService acs;

    @RequestMapping("/showUser")
    public String showUser(HttpServletRequest request, Model model){
        log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "showUser";
    }
    
    @RequestMapping("/test")
    public Customer testCustomer(@RequestBody String id, HttpServletResponse response) {
    	Customer customer = acs.findCustomerBoById(Long.valueOf(id));
    	return customer;
    }
}
