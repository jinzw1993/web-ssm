package com.heitian.ssm.service;

import java.util.List;

import com.heitian.ssm.bo.CartBo;
import com.heitian.ssm.bo.Result;

public interface CartService {

	public List<CartBo> searchCart(Long customerId);

	public Result addCart(Long productId, Long customerId, Long amount);
	
	public Result deleteProductInCart(Long productId, Long customerId);
	
	public Result updateProductAmount(Long productId, Long customerId, Long amount);
}
