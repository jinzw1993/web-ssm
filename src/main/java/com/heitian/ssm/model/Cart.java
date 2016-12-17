package com.heitian.ssm.model;

public class Cart {
	
	private Long id;
	private Long customerId;
	private Long amount;	
	
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
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	

}
