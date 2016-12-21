package com.heitian.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Cart;
import com.heitian.ssm.model.CustomerAddress;
import com.heitian.ssm.service.CustomerAddressService;

/**
 * 
 * @author Zrt666
 * 用户地址管理
 */
@Controller
@RequestMapping("/customerAddress")
public class CustomerAddressController {

	private Logger log = Logger.getLogger(CustomerController.class);
    @Resource
    private CustomerAddressService customerAddressService;
    
    @RequestMapping("/id")
    @ResponseBody
    public CustomerAddress getAddress(@RequestParam Long id) {
        return customerAddressService.getAddressById(id);
    }
    
    @ResponseBody
    @RequestMapping("/add")
    public Result add(@RequestBody CustomerAddress customerAddress, HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return returnResult();
        
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));
        
		//long customerId = 1;
        customerAddress.setCustomerId(customerId);
        Result result = customerAddressService.addCustomerAddress(customerAddress);
        return result;
    }
    
    @ResponseBody
    @RequestMapping("/search")
    public List<CustomerAddress> search(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return new ArrayList<CustomerAddress>();
        
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));
        
		//long customerId = 1;
        
        return customerAddressService.searchCustomerAddress(customerId);
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
        	return returnResult();
        
        Long customerAddressId = Long.valueOf(request.getParameter("id"));
        
        return customerAddressService.deleteCustomerAddress(customerAddressId);
    }
    
    public Result returnResult() {
        Result result = new Result();
        result.setStatus(0);
        result.setMessage("you haven't log in");
        return result;
    }
}
