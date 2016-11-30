package com.heitian.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.heitian.ssm.bo.CustomerBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.AdminCustomerDao;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.service.AdminCustomerService;

@Service
public class AdminCustomerServiceImpl implements AdminCustomerService {
	
	@Resource
	private AdminCustomerDao adminCustomerDao;

	public CustomerBo findCustomerBoById(int id) {
		Customer customer = adminCustomerDao.findCustomerById(id);
		CustomerBo customerBo = new CustomerBo(customer);
		return customerBo;
	}

	public List<CustomerBo> findCustomerBoByName(String name) {
		List<CustomerBo> customerBos = new ArrayList<CustomerBo>();
		List<Customer> customers = adminCustomerDao.findCustomerByName(name);
		for(Customer customer : customers) {
			customerBos.add(new CustomerBo(customer));
		}
		return customerBos;
	}

	public List<CustomerBo> findCustomerBoAll() {
		List<CustomerBo> customerBos = new ArrayList<CustomerBo>();
		List<Customer> customers = adminCustomerDao.findAllCustomer();
		for(Customer customer : customers) {
			customerBos.add(new CustomerBo(customer));
		}
		return customerBos;
	}

	public List<CustomerBo> selectCustomerByName(String strname) {
		List<CustomerBo> customerBos = new ArrayList<CustomerBo>();
		List<Customer> customers = adminCustomerDao.selectCustomerByName(strname);
		for(Customer customer : customers) {
			customerBos.add(new CustomerBo(customer));
		}
		return customerBos;
	}

	public Result blacklist(int id) {
		Result result = new Result();
		int status = adminCustomerDao.findCustomerById(id).getStatus();
		if(status == 1) {
			result.setStatus(0);
            result.setMessage("The Customer has been to join blacklist!");
		} else {
			adminCustomerDao.blacklist(id);
			result.setStatus(1);
            result.setMessage("Join the blacklist success!");
		}
			
		return result;
	}

	public List<CustomerBo> findCustomerBoList(int status) {
		List<CustomerBo> customerBos = new ArrayList<CustomerBo>();
		List<Customer> customers = null;
		if(status == 0) {
			customers = adminCustomerDao.findlist(status);
		}		
		else if(status == 1) {
			customers = adminCustomerDao.findlist(status);
		}
			
		for(Customer customer : customers) {
			customerBos.add(new CustomerBo(customer));
		}
		return customerBos;
	}

	public Result whitelist(int id) {
		Result result = new Result();
		int status = adminCustomerDao.findCustomerById(id).getStatus();
		if(status == 0) {
			result.setStatus(0);
            result.setMessage("The Customer has been to join whitelist!");
		} else {
			adminCustomerDao.whitelist(id);
			result.setStatus(1);
            result.setMessage("Join the whitelist success!");
		}
			
		return result;
	}

	public Result deleteCustomerById(int id) {
		Result result = new Result();
		int status = adminCustomerDao.findCustomerById(id).getStatus();
		if(status == 2) {
			result.setStatus(0);
            result.setMessage("The Customer has deleted!");
		} else {
			adminCustomerDao.deleteCustomerById(id);
			result.setStatus(1);
            result.setMessage("Delete success!");
		}
			
		return result;
	}

}
