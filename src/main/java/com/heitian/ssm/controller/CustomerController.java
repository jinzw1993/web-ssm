package com.heitian.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Lanting on 2016/11/25.
 * 处理用户业务的controller
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private Logger log = Logger.getLogger(CustomerController.class);
    @Resource
    private CustomerService customerService;

    @ResponseBody
    @RequestMapping("/login")
    public Result login(@RequestBody Customer customer, Model model, HttpServletResponse response) {
        Result result = customerService.customerLogin(customer);
        if (result.getStatus() == 1&&response!=null) {
            Cookie nameCookie = new Cookie("telephone", customer.getTelephone() + "");
            Cookie pwdCookie = new Cookie("password", customer.getPassword());
            nameCookie.setMaxAge(60 * 60 * 24 * 3);
            pwdCookie.setMaxAge(60 * 60 * 24 * 3);
            response.addCookie(nameCookie);
            response.addCookie(pwdCookie);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/register")
    public Result register(@RequestBody Customer customer, Model model) {
        Result result = customerService.addCustomer(customer);
        return result;
    }
}
