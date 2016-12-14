package com.heitian.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.bo.CartBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.CartDao;
import com.heitian.ssm.dao.ProductDao;
import com.heitian.ssm.dao.ProductInCartDao;
import com.heitian.ssm.model.Cart;
import com.heitian.ssm.model.Product;
import com.heitian.ssm.model.ProductInCart;
import com.heitian.ssm.service.CartService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {
	
	@Resource
	private CartDao cartDao;
	@Resource
	private ProductInCartDao productInCartDao;
	@Resource
	private ProductDao productDao;
	
	@Override
	public Result findCart(Long customerId) {
		Cart cart = cartDao.searchCartByCustomerId(customerId);
		Result result = new Result();
		if(cart == null) {
			result.setStatus(0);
			result.setMessage("Cart did not create");
			cartDao.insertCart(customerId);
			return result;
		} else {
			result.setStatus(1);
			result.setMessage("Cart has been created");
			return result;
		}
		
	}

	@Override
	public List<CartBo> searchCart(Long customerId) {
		
		Cart cart = cartDao.searchCartByCustomerId(customerId);
		List<CartBo> cartBos = new ArrayList<CartBo>();

		if(cart == null) {
				cartDao.insertCart(customerId);
				return cartBos;
		} else {
			List<ProductInCart> productInCarts = productInCartDao.searchProductInCartByCartId(cart.getId());
			
			for(ProductInCart productInCart : productInCarts) {
				Product product = productDao.searchProductById(productInCart.getProductId());
				CartBo cartBo = new CartBo(product);
				cartBo.setAmount(productInCart.getAmount());
				cartBo.setSubPrice(productInCart.getAmount() * product.getPrice());
				
				cartBos.add(cartBo);
			}
		}
		
		return cartBos;
		
	}
	
	/**
	 * 添加购物车
	 */
	@Override
	public Result addCart(Long productId, Long customerId, Long amount) {
		int i = 0;
		Cart cart = cartDao.searchCartByCustomerId(customerId);
		
		if(cart != null) {
			List<ProductInCart> productInCarts = productInCartDao.searchProductInCartByCartId(cart.getId());
			
			if(productInCarts == null || productInCarts.size() < 1) {
				ProductInCart pic2 = new ProductInCart();
				pic2.setAmount(amount);
				pic2.setCartId(cart.getId());
				pic2.setProductId(productId);
				pic2.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
				i = productInCartDao.insertProductInCart(pic2);
			} else {
				List<Long> productIds = new LinkedList<Long>();
				for(ProductInCart productInCart : productInCarts) {
					productIds.add(productInCart.getProductId());
				}
				
				if(productIds.contains(Long.valueOf(productId))) {
					ProductInCart pic1 = new ProductInCart();
					pic1.setAmount(amount + productInCartDao.searchProductInCartByCartIdAndProductId(cart.getId(), productId).getAmount());
					pic1.setCartId(cart.getId());
					pic1.setProductId(productId);
					pic1.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
					i = productInCartDao.updateProductInCart(pic1);
				} else {
					ProductInCart pic2 = new ProductInCart();
					pic2.setAmount(amount);
					pic2.setCartId(cart.getId());
					pic2.setProductId(productId);
					pic2.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
					i = productInCartDao.insertProductInCart(pic2);
				}
			}
			
		} else {
			cartDao.insertCart(customerId);
			returnRes(i);
		}
		
		/*Map<Long,ProductInCart> map=new LinkedHashMap<Long, ProductInCart>();
		for(ProductInCart productInCart : productInCarts) {
			map.put(productInCart.getProductId(), productInCart);
		}
		
		if(map.containsKey(productId)) {
			ProductInCart pic = map.get(productId);
			pic.setAmount(amount + pic.getAmount()); 
			pic.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
			map.put(productId, pic);
		} else {
			ProductInCart _productInCart = new ProductInCart();
			_productInCart.setAmount(amount);
			_productInCart.setProductId(productId);
			_productInCart.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
			_productInCart.setCartId(cart.getId());
			map.put(productId, _productInCart);
		}*/
		return returnRes(i);
	}
	
	@Override
	public Result deleteProductInCart(Long productId, Long customerId) {
		int i = 0;
		Cart cart = cartDao.searchCartByCustomerId(customerId);
		if(cart != null) {
			ProductInCart productInCart = productInCartDao.searchProductInCartByCartIdAndProductId(cart.getId(), productId);
			i = productInCartDao.deleteProductInCart(productInCart);
		}
		
		return returnRes(i);
	}

	@Override
	public Result updateProductAmount(Long productId, Long customerId,
			Long amount) {
		int i = 0;
		Cart cart = cartDao.searchCartByCustomerId(customerId);
		if(cart != null) {
			ProductInCart productInCart = productInCartDao.searchProductInCartByCartIdAndProductId(cart.getId(), productId);
			productInCart.setAmount(amount);
			i = productInCartDao.updateProductInCart(productInCart);
		}
		
		return returnRes(i);
	}
	
	private Result returnRes(int i) {
        Result result = new Result();
        if(i!=0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setMessage("failed");
            result.setStatus(0);
        }
        return result;
    }	

}
