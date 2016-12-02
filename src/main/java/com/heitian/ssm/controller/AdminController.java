package com.heitian.ssm.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Admin;
import com.heitian.ssm.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(AdminController.class);
    @Resource
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("/login")
    public Result login(@RequestBody Admin admin, Model model, HttpServletResponse response) {
        Result result = adminService.adminLogin(admin);
        if (result.getStatus() == 1&&response!=null) {
           // Cookie nameCookie = new Cookie("telephone", customer.getTelephone() + "");
            Cookie pwdCookie = new Cookie("password", admin.getPassword());
           // nameCookie.setMaxAge(60 * 60 * 24 * 3);
            pwdCookie.setMaxAge(60 * 60 * 24 * 3);
           // response.addCookie(nameCookie);
            response.addCookie(pwdCookie);
        }
        return result;
    }

}
