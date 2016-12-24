package com.heitian.ssm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FavoriteShop {

	private Long id;
	private Long shopId;
	private Long CustomerId;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
