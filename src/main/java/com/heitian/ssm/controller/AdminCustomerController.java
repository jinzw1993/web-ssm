package com.heitian.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.heitian.ssm.bo.CustomerBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.service.AdminCustomerService;

@Controller
@RequestMapping("/AdminCustomer")
public class AdminCustomerController {
	
	@Resource
	private AdminCustomerService adminCustomerService;
	
	@RequestMapping("/findCustomerById")
	public CustomerBo findCustomerById(HttpServletRequest request){
		String idt = request.getParameter("id");
		//System.out.println("ASDArwqr234123c423cr" + adminCustomerService.findCustomerBoById(Integer.valueOf(idt)));
		return adminCustomerService.findCustomerBoById(Integer.valueOf(idt));
	}
	
	@RequestMapping("/findCustomerByName")
	public List<CustomerBo> findCustomerByName(HttpServletRequest request){
		String namet = request.getParameter("name");
		//System.out.println("根据名字查找用户信息" + adminCustomerService.findCustomerBoByName(namet));
		return adminCustomerService.findCustomerBoByName(namet);
	}
	
	@RequestMapping("/findAllCustomer")
	public List<CustomerBo> findAllCustomer(HttpServletRequest request){
		//System.out.println(adminCustomerService.findCustomerBoAll().size());
		return adminCustomerService.findCustomerBoAll();
	}
	
	@RequestMapping("/selectName")
	public List<CustomerBo> selectCustomerByName(HttpServletRequest request){		
		String namet = request.getParameter("name");
		//System.out.println(adminCustomerService.findCustomerBoByName(namet).size());
		return adminCustomerService.findCustomerBoByName(namet);
	}
	
	@RequestMapping("/blacklist")
	public Result blacklist(HttpServletRequest request){		
		String idt = request.getParameter("id");
		//System.out.println(adminCustomerService.blacklist(Integer.valueOf(idt)).getMessage());
		return adminCustomerService.blacklist(Integer.valueOf(idt));
	}
	
	@RequestMapping("/whitelist")
	public Result whitelist(HttpServletRequest request){		
		String idt = request.getParameter("id");
		//System.out.println(adminCustomerService.whitelist(Integer.valueOf(idt)).getMessage());
		return adminCustomerService.whitelist(Integer.valueOf(idt));
	}
	
	@RequestMapping("/blackOrWhiteList")
	public List<CustomerBo> findlistByStatus(HttpServletRequest request){
		String statust = request.getParameter("status");
		//System.out.println(adminCustomerService.findCustomerBoList(Integer.valueOf(statust)).size());
		return adminCustomerService.findCustomerBoList(Integer.valueOf(statust));
	}
	
	@RequestMapping("/delete")
	public Result delete(HttpServletRequest request){		
		String idt = request.getParameter("id");
		//System.out.println(adminCustomerService.deleteCustomerById(Integer.valueOf(idt)).getMessage());
		return adminCustomerService.deleteCustomerById(Integer.valueOf(idt));
	}

}
