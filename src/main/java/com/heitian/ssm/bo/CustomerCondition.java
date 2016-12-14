package com.heitian.ssm.bo;

public class CustomerCondition {
	
	private String keyWord;
	private long status;
	private String telephone;
	private String email;
	private int page = 1;
	private int num = 30;
	private int start;
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = (page - 1) * num;
	}

}
