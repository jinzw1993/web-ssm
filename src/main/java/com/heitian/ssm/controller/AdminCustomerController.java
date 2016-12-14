package com.heitian.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heitian.ssm.bo.CustomerBo;
import com.heitian.ssm.bo.CustomerCondition;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.service.AdminCustomerService;

@Controller
@RequestMapping("/adminCustomer")
@Transactional(rollbackOn = Exception.class)
public class AdminCustomerController {
	
	private Logger log = Logger.getLogger(AdminCustomerController.class);
	
	@Resource
	private AdminCustomerService adminCustomerService;
	
	@ResponseBody
	@RequestMapping("/findCustomerById")
	public CustomerBo findCustomerById(HttpServletRequest request){
		String idt = request.getParameter("id");
		return adminCustomerService.findCustomerBoById(Long.valueOf(idt));
	}
	
	@ResponseBody
	@RequestMapping("/blacklist")
	public Result blacklist(HttpServletRequest request){		
		String idt = request.getParameter("id");
		return adminCustomerService.blacklist(Long.valueOf(idt));
	}
	
	@ResponseBody
	@RequestMapping("/whitelist")
	public Result whitelist(HttpServletRequest request){		
		String idt = request.getParameter("id");
		return adminCustomerService.whitelist(Long.valueOf(idt));
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Result delete(HttpServletRequest request){		
		String idt = request.getParameter("id");
		return adminCustomerService.deleteCustomerById(Long.valueOf(idt));
	}
	
	@ResponseBody
	@RequestMapping("/searchCustomer")
	public List<CustomerBo> searchCustomerWithKeyword(@RequestBody CustomerCondition customerCondition){
		return adminCustomerService.searchCustomerWithKeyword(customerCondition);
	}

}
