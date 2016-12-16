package com.ruitao.shop;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.heitian.ssm.bo.CartBo;
import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.CartDao;
import com.heitian.ssm.dao.FavoriteProductDao;
import com.heitian.ssm.dao.ProductInCartDao;
import com.heitian.ssm.model.Cart;
import com.heitian.ssm.model.FavoriteProduct;
import com.heitian.ssm.model.ProductInCart;
import com.heitian.ssm.service.CartService;
import com.heitian.ssm.service.FavoriteService;
import com.heitian.ssm.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestParknShop2 {
	
	@Autowired
	private CartDao cartDao;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductInCartDao picd;
	@Autowired
	private ProductService productService;
	@Autowired
	private FavoriteService fs;
	@Autowired
	private FavoriteProductDao fpd;

    @Before
    public void setup() {
    }
    
    @Test
    public void testFpd() {
    	List<FavoriteProduct> lsit = fpd.searchFavoriteProduct();
    	for(FavoriteProduct f : lsit) {
    		System.out.println(f.getCreatedAt());
    	}
    }
    
    @Test
    public void testSearchFavorite() {
    	List<ProductBo> list = fs.searchFavoriteProduct();
    	System.out.println(list.size());
    }
    
    @Test
    public void testAddCart() {
    	Result result = cartService.addCart(Long.valueOf("3"), Long.valueOf("3"), Long.valueOf("2"));
    	System.out.println(result.getMessage());
    }
    
    @Test
    public void testCartDao() {
    	Cart cart = cartDao.searchCartByCustomerId(Long.valueOf("4"));
    	//Cart cart = cartDao.searchCartById(Long.valueOf("1"));
    	//int i = cartDao.insertCart(Long.valueOf("2"));
    	//System.out.println(i);
    	if(cart != null)
    		System.out.println(cart.getId());
    }
    
    @Test
    public void testProductInCartDao() {
    	
    	/*ProductInCart p = new ProductInCart();
    	p.setAmount(Long.valueOf("5"));
    	p.setCartId(Long.valueOf("3"));
    	p.setProductId(Long.valueOf("1"));
    	p.setCreatedAt(new Timestamp(new Date().getTime()));
    	int i = picd.updateProductInCart(p);
    	System.out.println(i);*/
    	//List<ProductInCart> p = picd.searchProductInCartByCartId(Long.valueOf("3"));
    	ProductInCart p = picd.searchProductInCartByCartIdAndProductId(Long.valueOf("3"), Long.valueOf("1"));
    	System.out.println(p.getAmount());
    }
    
    @Test
    public void testSearchCart() {
    	List<CartBo> cartBos = cartService.searchCart(Long.valueOf("2"));
    	System.out.println(cartBos);
    	/*for(CartBo c : cartBos) {
    		System.out.println(c);
    	}*/
    }
    
    //ownId, shopId, name, price, categoryId, detail, photoURL
    @Test
    public void testAddProduct() {
    	ProductBo product = new ProductBo();
    	product.setOwnId(Long.valueOf("1"));
    	product.setShopId(Long.valueOf("1"));
    	product.setName("Android");
    	product.setPhotoURL("XXX");
    	product.setCategoryId(Long.valueOf("1"));
    	product.setDetail("XXXXXX");
    	product.setPrice(Long.valueOf("99999"));
    	productService.addProduct(product);
    }
    
    @Test
    public void testDeleteProductInCart() {
    	Result r = cartService.deleteProductInCart(Long.valueOf("2"), Long.valueOf("1"));
    	System.out.println(r.getMessage());
    }
    
    @Test
    public void testDeleteProductInCartDao() {
    	ProductInCart pic = picd.searchProductInCartByCartIdAndProductId(Long.valueOf("1"), Long.valueOf("3"));
    	picd.deleteProductInCart(pic);
    }
    
    @Test
    public void testUpdateAmount() {
    	Result result = cartService.updateProductAmount(Long.valueOf("1"), Long.valueOf("1"), Long.valueOf("6"));
    	System.out.println(result.getMessage());
    	//ProductInCart pic = picd.searchProductInCartByCartIdAndProductId(Long.valueOf("1"), Long.valueOf("1"));
    	//System.out.println(pic.getAmount());
    	//.setAmount(Long.valueOf("5"));
    	//picd.deleteProductInCart(pic);
    	//picd.updateProductInCart(pic);
    }

}
