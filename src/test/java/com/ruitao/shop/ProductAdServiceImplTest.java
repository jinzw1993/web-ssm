package com.ruitao.shop;

import java.sql.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.ProductAdDao;
import com.heitian.ssm.dao.ShopAdDao;
import com.heitian.ssm.model.ProductAd;
import com.heitian.ssm.model.ShopAd;
import com.heitian.ssm.service.ProductAdService;
import com.heitian.ssm.service.ShopAdService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class ProductAdServiceImplTest {

	@Autowired
	private ProductAdDao ppa;
	@Autowired
	private ShopAdDao sha;
	@Autowired
	private ShopAdService sas;
	@Autowired
	private ProductAdService ps;

//
//	public void testShowProductAd()
//	{
//		List<ProductAd> productAds = ppa.showProductAd();
//		System.out.println("-----------------------\n" + productAds.size());
//
//	}

	public void testDeleteProductAd()
	{
		ppa.deleteProductAd(2L);
	}

	public void testApplyProductAd()
	{
		List<ShopAd> pro = sha.applyShopAd();
		System.out.println("-----------------------\n" + pro.size());
	}
//
//	public void testAddProductAd()
//	{
//		Result i=ps.addProductAd(10L, new Date(1010020010L));
//		System.out.println(i.getMessage());
//	}
	
	
//	public void testRejectProductAd()
//	{
//		Result i=ps.rejectProductAd(3L);
//		System.out.println(i.getMessage());
//	}
//
//
//	public void testAgreeProductAd()
//	{
//		Result i=ps.agreeProductAd(3L);
//		System.out.println(i.getMessage());
//	}
	
	
	


	
	public void testShowShopAd()
	{
		List<ShopAd> productAds = sha.showShopAd();
		for(ShopAd s : productAds) {
			System.out.println(s.getDate());
		}
		System.out.println("-----------------------\n" + productAds.size());

	}
	
	
	public void testDeleteShopAd()
	{
		sas.deleteShopAd(2L);
	}
	
	public void testApplyShopAd()
	{
		List<ShopAd> pro = sas.applyShopAd();
		System.out.println("-----------------------\n" + pro.size());
	}
	
	
	public void testAddShopAd()
	{
		Result r = sas.addShopAd(Long.valueOf("3"), new Date(1010020010L));
		System.out.println(r.getMessage());
	}
	@Test
	public void testRejectShopAd()
	{
		Result i=sas.rejectShopAd(3L);
		System.out.println(i.getMessage());
	}
	
	
	public void testAgreeShopAd()
	{
		Result i=sas.agreeShopAd(3L);
		System.out.println(i.getMessage());
	}
}
