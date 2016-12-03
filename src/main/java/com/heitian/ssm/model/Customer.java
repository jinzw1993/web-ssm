package com.heitian.ssm.model;
/**
 * 
 * @author Zrt666
 *
 */

public class Customer{

	private long id;
	private String name;
	private String telephone;
	private long status;//用户状态：0:正常，1：黑名单，2：删除
	private String password;
	private String email;
	private boolean is_email_verified;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telphone) {
		this.telephone = telphone;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isIs_email_verified() {
		return is_email_verified;
	}
	public void setIs_email_verified(boolean is_email_verified) {
		this.is_email_verified = is_email_verified;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", telephone="
				+ telephone + ", status=" + status + ", password=" + password
				+ ", email=" + email + ", is_email_verified="
				+ is_email_verified + "]";
	}


}
