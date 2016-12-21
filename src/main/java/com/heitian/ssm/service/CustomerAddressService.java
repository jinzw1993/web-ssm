package com.heitian.ssm.service;

import java.util.List;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.CustomerAddress;

public interface CustomerAddressService {
	
	public Result deleteCustomerAddress(Long customerAddressId);
	public Result addCustomerAddress(CustomerAddress customerAddress);
	public List<CustomerAddress> searchCustomerAddress(Long customerId);

}
