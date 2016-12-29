package com.heitian.ssm.service;

import java.util.List;

import com.heitian.ssm.bo.CartBo;
import com.heitian.ssm.bo.ProductInCartBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Cart;
import com.heitian.ssm.model.ProductInCart;

public interface CartService {

	public List<CartBo> searchCart(Long customerId);

	public Result addCart(Long productId, Long customerId, Long amount);
	
	public Result deleteProductInCart(Long productId, Long customerId);
	
	public Result updateProductAmount(Long productId, Long customerId, Long amount);
	
	public Cart getCartById(Long id);
	
	public ProductInCart getProductInCartById(Long id);

	public List<ProductInCartBo> getProductInCart(Long id);
}
