package com.heitian.ssm.dao;

import org.springframework.stereotype.Repository;

import com.heitian.ssm.model.Cart;

@Repository
public interface CartDao {
	
	public Cart searchCartById(Long id);
	public Cart searchCartByCustomerId(Long customerId);
	public int insertCart(Long customerId);
	public void verify(Long id);
	public int deleteCart(Long id);

}
