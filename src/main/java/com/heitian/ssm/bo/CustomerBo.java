package com.heitian.ssm.bo;

import java.io.Serializable;

import com.heitian.ssm.model.Customer;


public class CustomerBo extends Customer implements Serializable {
    public CustomerBo(){};
    public CustomerBo(Customer customer){
        this.setId(customer.getId());
        this.setName(customer.getName());
        this.setTelephone(customer.getTelephone());
        this.setPassword(customer.getPassword());
        this.setStatus(customer.getStatus());
        this.setEmail(customer.getEmail());
        this.setIsEmailVerified(customer.isIsEmailVerified());
    };
   
}
