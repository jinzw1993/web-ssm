package com.heitian.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heitian.ssm.bo.OwnerBo;
import com.heitian.ssm.bo.OwnerCondition;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.service.AdminOwnerService;

@Controller
@RequestMapping("/adminOwner")
public class AdminOwnerController {
	
	private Logger logger = Logger.getLogger(AdminOwnerController.class);
	
	@Resource
	private AdminOwnerService adminOwnerService;
	
	@ResponseBody
	@RequestMapping("/findOwnerById")
	public OwnerBo findOwnerById(HttpServletRequest request) {
		String idt = request.getParameter("id");
		return adminOwnerService.findOwnerBoById(Long.valueOf(idt));
	}
	
	@ResponseBody
	@RequestMapping("/blacklist")
	public Result blacklist(HttpServletRequest request){		
		String idt = request.getParameter("id");
		return adminOwnerService.blacklist(Long.valueOf(idt));
	}
	
	@ResponseBody
	@RequestMapping("/whitelist")
	public Result whitelist(HttpServletRequest request){		
		String idt = request.getParameter("id");
		return adminOwnerService.whitelist(Long.valueOf(idt));
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Result delete(HttpServletRequest request){		
		String idt = request.getParameter("id");
		return adminOwnerService.deleteOwnerById(Long.valueOf(idt));
	}
	
	@ResponseBody
	@RequestMapping("/searchOwner")
	public List<OwnerBo> searchOwnerWithKeyword(@RequestBody OwnerCondition ownerCondition){
		return adminOwnerService.searchOwnerWithKeyword(ownerCondition);
	}

}
