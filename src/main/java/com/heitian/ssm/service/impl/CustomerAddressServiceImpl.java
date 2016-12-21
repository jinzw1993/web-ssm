package com.heitian.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.CustomerAddressDao;
import com.heitian.ssm.model.CustomerAddress;
import com.heitian.ssm.service.CustomerAddressService;
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerAddressServiceImpl implements CustomerAddressService {
	
	@Resource
	private CustomerAddressDao cad;

	@Override
	public List<CustomerAddress> searchCustomerAddress(Long customerId) {
		return cad.getCustomerAddressByCustomerId(customerId);
	}

	@Override
	public Result deleteCustomerAddress(Long customerAddressId) {
		int i = cad.deleteCustomerAddress(customerAddressId);
		return returnRes(i);
	}

	@Override
	public Result addCustomerAddress(CustomerAddress customerAddress) {
		int i = cad.addCustomerAddress(customerAddress);
		return returnRes(i);
	}
	
	private Result returnRes(int i) {
        Result result = new Result();
        if(i!=0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setMessage("failed");
            result.setStatus(0);
        }
        return result;
    }

	@Override
	public CustomerAddress getAddressById(Long id) {		
		return cad.getCustomerAddressById(id);
	}

}
