package com.heitian.ssm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProductInCart {
	
	private Long id;
	private Long productId;
	private Long cartId;
	private Long amount;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	

}
