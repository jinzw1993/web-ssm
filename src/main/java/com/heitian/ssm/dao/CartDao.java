package com.heitian.ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heitian.ssm.model.Cart;

@Repository
public interface CartDao {
	
	public Cart searchCartById(Long id);
	public Cart searchCartByCustomerId(Long customerId);
	public int insertCart(Long customerId);
	public int deleteCart(Long id);
	public void updateCartAmount(@Param("amount") Long amount, @Param("customerId") Long customerId);

}
