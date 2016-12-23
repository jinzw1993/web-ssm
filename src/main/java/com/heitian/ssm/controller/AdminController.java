package com.heitian.ssm.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.heitian.ssm.bo.IncomeBo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Admin;
import com.heitian.ssm.service.AdminService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private Logger log = Logger.getLogger(AdminController.class);
    @Resource
    private AdminService adminService;
    
	/**
	 * 
	 * @param admin
	 * @param response
	 * @return
	 */
    @ResponseBody
    @RequestMapping("/login")
    public Result login(@RequestBody Admin admin, HttpServletResponse response) {
        Result result = adminService.adminLogin(admin);
        if (result.getStatus() == 1 && response != null) {
        	Cookie nameCookie = new Cookie("adminName", admin.getName());
            Cookie pwdCookie = new Cookie("adminPassword", admin.getPassword());
            nameCookie.setMaxAge(60 * 60 * 24 * 3);
            pwdCookie.setMaxAge(60 * 60 * 24 * 3);
            response.addCookie(nameCookie);
            response.addCookie(pwdCookie);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/income")
    public List<IncomeBo> getIncome(@RequestParam Long cond) {
        return adminService.getIncome(cond);
    }
}
