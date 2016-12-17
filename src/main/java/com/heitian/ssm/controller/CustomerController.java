package com.heitian.ssm.controller;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
    public Result login(@RequestBody Customer customer) {
        Result result = customerService.customerLogin(customer);
        return result;
    }

    @ResponseBody
    @RequestMapping("/register")
    public Result register(@RequestBody Customer customer, Model model) {
        Result result = customerService.addCustomer(customer);
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/activate",method= RequestMethod.GET)
    public ModelAndView activate(@RequestParam String telephone,@RequestParam String email) {
        Result result = customerService.customerActivate(telephone,email);
        ModelAndView mov = new ModelAndView();
        if(result.getStatus() == 1)
            mov.setViewName("success");
        else
            mov.setViewName("error");
        return mov;
    }
}
