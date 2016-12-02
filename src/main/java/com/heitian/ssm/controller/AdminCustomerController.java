package com.heitian.ssm.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.heitian.ssm.bo.CustomerBo;
import com.heitian.ssm.bo.CustomerCondition;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.service.AdminCustomerService;

@Controller
@RequestMapping("/adminCustomer")
public class AdminCustomerController {
	
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(AdminCustomerController.class);
	
	@Resource
	private AdminCustomerService adminCustomerService;
	
	@RequestMapping("/findCustomerById")
	public CustomerBo findCustomerById(HttpServletRequest request){
		String idt = request.getParameter("id");
		System.out.println("根据id查找" + adminCustomerService.findCustomerBoById(Long.valueOf(idt)));
		return adminCustomerService.findCustomerBoById(Long.valueOf(idt));
	}
	
	@RequestMapping("/blacklist")
	public Result blacklist(HttpServletRequest request){		
		String idt = request.getParameter("id");
		System.out.println(adminCustomerService.blacklist(Long.valueOf(idt)).getMessage());
		return adminCustomerService.blacklist(Long.valueOf(idt));
	}
	
	@RequestMapping("/whitelist")
	public Result whitelist(HttpServletRequest request){		
		String idt = request.getParameter("id");
		System.out.println(adminCustomerService.whitelist(Long.valueOf(idt)).getMessage());
		return adminCustomerService.whitelist(Long.valueOf(idt));
	}
	
	@RequestMapping("/delete")
	public Result delete(HttpServletRequest request){		
		String idt = request.getParameter("id");
		System.out.println(adminCustomerService.deleteCustomerById(Long.valueOf(idt)).getMessage());
		return adminCustomerService.deleteCustomerById(Long.valueOf(idt));
	}
	
	@RequestMapping("/searchCustomer")
	public List<CustomerBo> searchCustomerWithKeyword(@RequestBody CustomerCondition customerCondition){
		System.out.println("根据用户信息查找数据" + adminCustomerService.searchCustomerWithKeyword(customerCondition).size());
		return adminCustomerService.searchCustomerWithKeyword(customerCondition);
	}

}
