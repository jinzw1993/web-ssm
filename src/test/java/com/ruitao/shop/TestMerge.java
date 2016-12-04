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
import com.heitian.ssm.bo.CustomerCondition;
import com.heitian.ssm.bo.OwnerBo;
import com.heitian.ssm.bo.OwnerCondition;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Admin;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.service.AdminCustomerService;
import com.heitian.ssm.service.AdminOwnerService;
import com.heitian.ssm.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMerge {
	
	@Autowired
    private AdminCustomerService acs;
	@Autowired
	private AdminService as;
	@Autowired
	private AdminOwnerService aos;

    @Before
    public void setup() {
    }
    
    @Test
    public void testFindCustomerById() {
    	Customer customer = acs.findCustomerBoById(1);
    	System.out.println(customer.toString());
    }
    
   @Test
   public void search() {
	   CustomerCondition cc = new CustomerCondition();
	   //cc.setStatus(1);
	   //cc.setKeyWord("bbb");
	   cc.setTelephone("2222222");
	   List<CustomerBo> customerBos = acs.searchCustomerWithKeyword(cc);
	   System.out.println(customerBos.size());
   }
    
    @Test
    public void testBlackList() {
    	Result result = acs.blacklist(1);

    	System.out.println(result.getMessage());
    }
    
    @Test
    public void testWhiteList() {
    	Result result = aos.whitelist(1);

    	System.out.println(result.getMessage());
    }
    
    @Test
    public void testLogin() {
//    	Admin admin = new Admin("parknshop","123");
//    	Result result = as.adminLogin(admin);
//    	System.out.println(result.getMessage());
    }

    @Test
    public void testOwnerFindId() {
    	OwnerBo ownerBo = aos.findOwnerBoById(1);
    	System.out.println(ownerBo.toString());
    }
    
    @Test
    public void testOwnerSearch() {
    	OwnerCondition oc = new OwnerCondition();
    	oc.setStatus(0);
    	System.out.println(aos.searchOwnerWithKeyword(oc).size());
    }
    
}
