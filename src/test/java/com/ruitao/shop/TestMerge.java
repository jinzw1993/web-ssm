package com.ruitao.shop;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.heitian.ssm.bo.CustomerBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.service.AdminCustomerService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMerge {
	
	@Autowired
    private AdminCustomerService acs;

    @Before
    public void setup() {
    }
    
    @Test
    public void testFindCustomerById() {
    	Customer customer = acs.findCustomerBoById(1);
    	System.out.println(customer.toString());
    }
    
    @Test
    public void testFindCustomerByName() {
    	List<CustomerBo> customerBos = acs.findCustomerBoByName("aaa");

    	System.out.println(customerBos.size());
    }
    
    @Test
    public void testFindCustomerAll() {
    	List<CustomerBo> customerBos = acs.findCustomerBoAll();

    	System.out.println(customerBos.size());
    }
    
    @Test
    public void testSelectCustomerByName() {
    	List<CustomerBo> customerBos = acs.selectCustomerByName("b");

    	System.out.println(customerBos.size());
    }
    
    @Test
    public void testBlackList() {
    	Result result = acs.blacklist(1);

    	System.out.println(result.getMessage());
    }
    
    @Test
    public void testWhiteList() {
    	Result result = acs.whitelist(1);

    	System.out.println(result.getMessage());
    }

}
