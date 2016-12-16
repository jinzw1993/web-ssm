package com.heitian.ssm.model;

public class Cart {
	
	private Long id;
	private Long customerId;
	private Boolean status;	
	
	public Cart() {
		super();
	}
	public Cart(Long customerId) {
		super();
		this.customerId = customerId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	

}
