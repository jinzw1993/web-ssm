package com.heitian.ssm.bo;

import java.io.Serializable;

import com.heitian.ssm.model.Customer;


public class CustomerBo extends Customer implements Serializable {
    
	private static final long serialVersionUID = 1L;
	public CustomerBo(){};
    public CustomerBo(Customer customer){
        this.setId(customer.getId());
        this.setName(customer.getName());
        this.setTelphone(customer.getTelphone());
        this.setPassword(customer.getPassword());
        this.setStatus(customer.getStatus());
    };
   
}
