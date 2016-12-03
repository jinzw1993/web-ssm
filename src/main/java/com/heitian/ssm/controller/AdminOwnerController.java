package com.heitian.ssm.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.heitian.ssm.bo.OwnerBo;
import com.heitian.ssm.bo.OwnerCondition;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.service.AdminOwnerService;

@Controller
@RequestMapping("/adminOwner")
public class AdminOwnerController {
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(AdminOwnerController.class);
	
	@Resource
	private AdminOwnerService adminOwnerService;
	
	@RequestMapping("/findOwnerById")
	public OwnerBo findOwnerById(HttpServletRequest request) {
		String idt = request.getParameter("id");
		return adminOwnerService.findOwnerBoById(Long.valueOf(idt));
	}
	
	@RequestMapping("/blacklist")
	public Result blacklist(HttpServletRequest request){		
		String idt = request.getParameter("id");
		System.out.println(adminOwnerService.blacklist(Long.valueOf(idt)).getMessage());
		return adminOwnerService.blacklist(Long.valueOf(idt));
	}
	
	@RequestMapping("/whitelist")
	public Result whitelist(HttpServletRequest request){		
		String idt = request.getParameter("id");
		System.out.println(adminOwnerService.whitelist(Long.valueOf(idt)).getMessage());
		return adminOwnerService.whitelist(Long.valueOf(idt));
	}
	
	@RequestMapping("/delete")
	public Result delete(HttpServletRequest request){		
		String idt = request.getParameter("id");
		System.out.println(adminOwnerService.deleteOwnerById(Long.valueOf(idt)).getMessage());
		return adminOwnerService.deleteOwnerById(Long.valueOf(idt));
	}
	
	@RequestMapping("/searchOwner")
	public List<OwnerBo> searchOwnerWithKeyword(@RequestBody OwnerCondition ownerCondition){
		System.out.println("根据用户信息查找数据" + adminOwnerService.searchOwnerWithKeyword(ownerCondition).size());
		return adminOwnerService.searchOwnerWithKeyword(ownerCondition);
	}

}
