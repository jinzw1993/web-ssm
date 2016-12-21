package com.heitian.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.heitian.ssm.model.CustomerAddress;

/**
 * 
 * @author Zrt666
 *
 */
@Repository
public interface CustomerAddressDao {
	
	public int addCustomerAddress(CustomerAddress customerAddress);
	public CustomerAddress getCustomerAddressById(Long id);
	public List<CustomerAddress> getCustomerAddressByCustomerId(Long customerId);
	public int deleteCustomerAddress(Long id);

}
