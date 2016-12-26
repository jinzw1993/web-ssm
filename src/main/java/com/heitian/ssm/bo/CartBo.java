package com.heitian.ssm.bo;

import java.io.Serializable;

import com.heitian.ssm.model.Product;

public class CartBo extends Product implements Serializable {
	
	private Long cartId;
	private Long productInCartId;
	private Long productId;
	private Long amount;
	private Double subPrice;
	private String photoURL;
	private Long allAmount;
	private Double allPrice;
	
	public CartBo(){}
    public CartBo(Product product){
        this.setId(product.getId());
        this.setOwnId(product.getOwnId());
        this.setShopId(product.getShopId());
        this.setProductPhotoId(product.getProductPhotoId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategoryId());
        this.setDetail(product.getDetail());
        this.setCreatedAt(product.getCreatedAt());
        this.setModifiedAt(product.getModifiedAt());
        this.setAmount(product.getAmount());
    }
	
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Long getProductInCartId() {
		return productInCartId;
	}
	public void setProductInCartId(Long productInCartId) {
		this.productInCartId = productInCartId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Double getSubPrice() {
		return subPrice;
	}
	public void setSubPrice(Double subPrice) {
		this.subPrice = subPrice;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
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
