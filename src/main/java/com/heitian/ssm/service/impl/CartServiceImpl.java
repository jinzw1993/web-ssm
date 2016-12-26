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
import com.heitian.ssm.dao.OwnerDao;
import com.heitian.ssm.dao.ProductDao;
import com.heitian.ssm.dao.ProductInCartDao;
import com.heitian.ssm.dao.ShopDao;
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
	@Resource
	private OwnerDao ownerDao;
	@Resource
	private ShopDao shopDao;

	@Override
	public List<CartBo> searchCart(Long customerId) {
		double allPrice = 0.0;
		Cart cart = cartDao.searchCartByCustomerId(customerId);
		List<CartBo> cartBos = new ArrayList<CartBo>();
		if(cart != null) {
			List<ProductInCart> productInCarts = productInCartDao.searchProductInCartByCartId(cart.getId());
			
			for(ProductInCart productInCart : productInCarts) {
				Product product = productDao.searchProductById(productInCart.getProductId());
				allPrice += productInCart.getAmount() * product.getPrice();
			}
			
			for(ProductInCart productInCart : productInCarts) {				
				Product product = productDao.searchProductById(productInCart.getProductId());
				
				String path=productDao.searchPhotoURL(product.getId());
				CartBo cartBo = new CartBo(product);
				cartBo.setCartId(cart.getId());
				cartBo.setProductId(product.getId());
				cartBo.setProductInCartId(productInCart.getId());
				cartBo.setAmount(productInCart.getAmount());
				cartBo.setSubPrice(productInCart.getAmount() * product.getPrice());
				cartBo.setPhotoURL(path);
				cartBo.setAllAmount(cart.getAmount());
				cartBo.setAllPrice(allPrice);
				
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
		if(amount < 0) {
			Result r = new Result();
			r.setStatus(0);
			r.setMessage("Amount cannot be negative! ");
			return r;
		}
		long AllAmount = productDao.searchProductById(productId).getAmount();
		int i = 0;
		//productDao.updateProductAmount(productId,  - amount);
		if(AllAmount < amount) {
			Result r = new Result();
			r.setStatus(0);
			r.setMessage("Product's amount is " + AllAmount);
			return r;
		} else {
			
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
				cartDao.updateCartAmount(cart.getAmount() + amount, customerId);
				productDao.updateProductAmount(productId, AllAmount - amount);
			} else {
				Result r = new Result();
				r.setStatus(0);
				r.setMessage("You can't active!");
				return r;
			}
		}		
		
		return returnRes(i);
	}
	
	@Override
	public Result deleteProductInCart(Long productId, Long customerId) {
		
		int i = 0;
		Cart cart = cartDao.searchCartByCustomerId(customerId);
		if(cart != null) {
			ProductInCart productInCart = productInCartDao.searchProductInCartByCartIdAndProductId(cart.getId(), productId);
			if(productInCart == null) {
				Result r = new Result();
				r.setStatus(0);
				r.setMessage("You have been deleted");
				return r;
			} else 
				i = productInCartDao.deleteProductInCart(productInCart);
				cartDao.updateCartAmount(cart.getAmount() - productInCart.getAmount(), customerId);
		}
		
		return returnRes(i);
	}

	@Override
	public Result updateProductAmount(Long productId, Long customerId,
			Long amount) {
		if(amount < 0) {
			Result r = new Result();
			r.setStatus(0);
			r.setMessage("Amount cannot be negative! ");
			return r;
		}
		long allAmount = productDao.searchProductById(productId).getAmount();
		int i = 0;
		Cart cart = cartDao.searchCartByCustomerId(customerId);
		
		if(cart != null) {
			ProductInCart productInCart = productInCartDao.searchProductInCartByCartIdAndProductId(cart.getId(), productId);
			long pAmount = productInCart.getAmount();
			if(amount > pAmount + allAmount) {
				Result r = new Result();
				r.setStatus(0);
				r.setMessage("Product's amount is " + ( allAmount + pAmount ));
				return r;
			} else {
				productInCart.setAmount(amount);
				i = productInCartDao.updateProductInCart(productInCart);
				cartDao.updateCartAmount(cart.getAmount() - pAmount + amount, customerId);
				productDao.updateProductAmount(productId, allAmount + pAmount - amount);
			}
			
		} else {
			Result r = new Result();
			r.setStatus(0);
			r.setMessage("You can't active!");
			return r;
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

	@Override
	public Cart getCartById(Long id) {
		return cartDao.searchCartById(id);
	}

	@Override
	public ProductInCart getProductInCartById(Long id) {
		return productInCartDao.searchProductInCartById(id);
	}	

}
