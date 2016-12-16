package com.ruitao.shop;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.heitian.ssm.bo.CartBo;
import com.heitian.ssm.bo.CustomerBo;
import com.heitian.ssm.bo.CustomerCondition;
import com.heitian.ssm.bo.OwnerBo;
import com.heitian.ssm.bo.OwnerCondition;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.AdminCustomerDao;
import com.heitian.ssm.dao.CartDao;
import com.heitian.ssm.model.Admin;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.service.AdminCustomerService;
import com.heitian.ssm.service.AdminOwnerService;
import com.heitian.ssm.service.AdminService;
import com.heitian.ssm.service.CartService;
import com.heitian.ssm.service.FavoriteService;

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
	@Autowired
	private AdminCustomerDao acd;
	@Autowired
	private FavoriteService fs;
	@Autowired
	private CartService cartService;
	@Autowired
	private CartDao cartDao;

    @Before
    public void setup() {
    }
    
    @Test
    public void testFindCustomerById() {
    	Customer customer = acs.findCustomerBoById(1);
    	System.out.println(customer.getName());
    }
    
    @Test
    public void testFindCustomerByIdDao() {
    	Customer customer = acd.findCustomerById(1000);
    	System.out.println(customer);
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
    	Result result = acs.blacklist(400);

    	System.out.println(result.getMessage());
    }
    
    @Test
    public void testWhiteList() {
    	Result result = aos.whitelist(227);

    	System.out.println(result.getMessage());
    }
    
    @Test
    public void testLogin() {
    	Admin admin = new Admin();
    	Result result = as.adminLogin(admin);
    	System.out.println(result.getMessage());
    }

    @Test
    public void testOwnerFindId() {
    	OwnerBo ownerBo = aos.findOwnerBoById(100);
    	System.out.println(ownerBo.toString());
    }
    
    @Test
    public void testOwnerSearch() {
    	OwnerCondition oc = new OwnerCondition();
    	//oc.setStatus(4);
    	oc.setEmail("asdf");
    	System.out.println(aos.searchOwnerWithKeyword(oc).size());
    }
    
    @Test
    public void adminLogin() throws Exception {
    	String add_url = "http://127.0.0.1:8090/ParknShop/owner/login";  
		   URL url = new URL(add_url);  
		   HttpURLConnection connection = (HttpURLConnection)url.openConnection();  
		   connection.setDoInput(true);  
		   connection.setDoOutput(true);  
		   connection.setRequestMethod("POST");  
		   connection.setUseCaches(false);  
		   connection.setInstanceFollowRedirects(true);  
		   connection.setRequestProperty("Content-Type","application/json");  
		   connection.connect();  
		   DataOutputStream out = new DataOutputStream(connection.getOutputStream());  
		   JSONObject obj = new JSONObject();  
		      
		   obj.put("email", "asd");       
		   obj.put("password", "msg");  
		   out.writeBytes(obj.toString());  
		   out.flush();  
		   out.close();  
    }
    
    @Test
    public void testFun1() {
    	String s = "ownerId=123;customerId=1243;adress=sadgfsadg;";
    	String a[] = s.split(";");
    	/*
    	System.out.println(i);
    	int j = s.indexOf(";");
    	System.out.println(j);
    	System.out.println(s.substring(i+11, j));*/
    	//int i = s.indexOf("customerId=");
    	System.out.println(a[1].substring(11));
    }
    
    @Test
    public void testAddFavoriteP() {
    	Result result = fs.addFavoriteProduct(Long.valueOf("2"), Long.valueOf("2"));
    	System.out.println(result.getMessage());
    }
    
    @Test
    public void testDate() {
    	Timestamp ts = new Timestamp(new Date().getTime());
    	System.out.println(ts);
    }
    
    @Test
    public void testCartServiceSearch() {
    	List<CartBo> cartBos = cartService.searchCart(Long.valueOf("2"));
    	System.out.println(cartBos);
    }
    
   

}
