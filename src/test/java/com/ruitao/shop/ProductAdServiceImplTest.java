package com.ruitao.shop;

import java.sql.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.heitian.ssm.dao.ProductAdDao;
import com.heitian.ssm.dao.ShopAdDao;
import com.heitian.ssm.model.ProductAd;
import com.heitian.ssm.model.ShopAd;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class ProductAdServiceImplTest {

	@Autowired
	private ProductAdDao ppa;
	@Autowired
	private ShopAdDao sha;

	
	public void testShowProductAd()
	{
		List<ProductAd> productAds = ppa.showProductAd();
		System.out.println("-----------------------\n" + productAds.size());

	}

	public void testDeleteProductAd()
	{
		ppa.deleteProductAd(2L);
	}

	public void testApplyProductAd()
	{
		List<ShopAd> pro = sha.applyShopAd();
		System.out.println("-----------------------\n" + pro.size());
	}
	
	public void testAddProductAd()
	{
		ppa.addProductAd(10L, new Date(1010020010L));
	}
	
	


	
	public void testShowShopAd()
	{
		List<ShopAd> productAds = sha.showShopAd();
		System.out.println("-----------------------\n" + productAds.size());

	}
	@Test
	public void testDeleteShopAd()
	{
		sha.deleteShopAd(2L);
	}

	public void testApplyShopAd()
	{
		List<ShopAd> pro = sha.applyShopAd();
		System.out.println("-----------------------\n" + pro.size());
	}
	
	public void testAddShopAd()
	{
		sha.addShopAd(Long.valueOf("2"), new Date(1010020010L));
	}

}
