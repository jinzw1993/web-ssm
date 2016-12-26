package com.heitian.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.bo.CustomerBo;
import com.heitian.ssm.bo.CustomerCondition;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.AdminCustomerDao;
import com.heitian.ssm.dao.CustomerDao;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.service.AdminCustomerService;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminCustomerServiceImpl implements AdminCustomerService {
	
	@Resource
	private AdminCustomerDao adminCustomerDao;
	@Resource
	private CustomerDao customerDao;

	@Override
	public CustomerBo findCustomerBoById(long id) {
		Customer customer = adminCustomerDao.findCustomerById(id);
		CustomerBo customerBo = new CustomerBo();
		if(customer != null) {
			customerBo = new CustomerBo(customer);
		}
		
		return customerBo;
	}

	@Override
	public Result blacklist(long id) {
		Result result = new Result();
		Customer customer = adminCustomerDao.findCustomerById(id);
		long status = -1;
		if(customer != null) {
			status = customer.getStatus();
			if(status == 1) {
				result.setStatus(0);
	            result.setMessage("The Customer has been to join blacklist!");
			} else if (status == 2) {
				result.setStatus(0);
	            result.setMessage("The Customer has been to deleted!");
			} else {
				adminCustomerDao.blacklist(id);
				result.setStatus(1);
	            result.setMessage("Join the blacklist success!");
			}
		} else {
			result.setStatus(0);
            result.setMessage("The Customer does not exist!");
		}
			
		return result;
	}

	@Override
	public Result whitelist(long id) {
		Result result = new Result();
		Customer customer = adminCustomerDao.findCustomerById(id);
		long status = -1;
		if(customer != null) {
			status = customer.getStatus();
			if(status == 0) {
				result.setStatus(0);
	            result.setMessage("The Customer has been to join whitelist!");
			} else if (status == 2) {
				result.setStatus(0);
	            result.setMessage("The Customer has been to deleted!");
			} else {
				adminCustomerDao.whitelist(id);
				result.setStatus(1);
	            result.setMessage("Join the whitelist success!");
			}
		} else {
			result.setStatus(0);
            result.setMessage("The Customer does not exist!");
		}			
		return result;
	}

	@Override
	public Result deleteCustomerById(long id) {
		Result result = new Result();
		Customer customer = adminCustomerDao.findCustomerById(id);
		long status = -1;
		if(customer != null) {
			if(status == 2) {
				result.setStatus(0);
	            result.setMessage("The Customer has deleted!");
			} else {
				adminCustomerDao.deleteCustomerById(id);
				result.setStatus(1);
	            result.setMessage("Delete success!");
			}
		} else {
			result.setStatus(0);
            result.setMessage("The Customer does not exist!");
		}		
		return result;
	}

	@Override
	public List<CustomerBo> searchCustomerWithKeyword(
			CustomerCondition customerCondition) {
		List<CustomerBo> customerBos = new ArrayList<CustomerBo>();
		List<Customer> customers;
		if(customerCondition == null)
			customers = adminCustomerDao.searchByNum(customerCondition.getStart(), customerCondition.getNum());
		else
			customers = adminCustomerDao.searchCustomerWithKeyword(customerCondition);
		if(customers != null) {
			for(Customer customer : customers) {
				customerBos.add(new CustomerBo(customer));
			}
		}
		return customerBos;
	}

	@Override
	public Result updateBalance(String idt, double balance) {
		if(balance < 0) {
			Result r = new Result();
			r.setStatus(0);
			r.setMessage("Balance cannot be negative! ");
			return r;
		}
		if(balance > 999999999) {
			Result r = new Result();
			r.setStatus(0);
			r.setMessage("Balance id overflow! ");
			return r;
		}
		int i = customerDao.updateBalance(balance, idt);
		if(0 != i) {
			Result r = new Result();
			r.setStatus(1);
			r.setMessage("success! ");
			return r;
		} else {
			Result r = new Result();
			r.setStatus(0);
			r.setMessage("failed! ");
			return r;
		}
	}

}
