package com.heitian.ssm.bo;

import java.io.Serializable;

import com.heitian.ssm.model.Product;

public class CartBo extends Product implements Serializable{
	
	private Long amount;
	private Long subPrice;
	private String photoURL;
	private Long allAmount;
	
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
    }
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getSubPrice() {
		return subPrice;
	}
	public void setSubPrice(Long subPrice) {
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

	@Override
	public String toString() {
		return "CartBo [amount=" + amount + ", subPrice=" + subPrice
				+ ", getId()=" + getId() + ", getOwnId()=" + getOwnId()
				+ ", getShopId()=" + getShopId() + ", getName()=" + getName()
				+ ", getPrice()=" + getPrice() + ", getCategoryId()="
				+ getCategoryId() + ", getDetail()=" + getDetail()
				+ ", getProductPhotoId()=" + getProductPhotoId()
				+ ", getCreatedAt()=" + getCreatedAt() + ", getModifiedAt()="
				+ getModifiedAt() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
    
}
