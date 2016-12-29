package com.heitian.ssm.bo;

public class CartBo {
	
	private Long cartId;
	private Long allAmount;
	private Double allPrice;
	
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	
	public Long getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(Long allAmount) {
		this.allAmount = allAmount;
	}

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}
	   
}
