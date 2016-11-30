package com.heitian.ssm.model;
/**
 * 
 * @author Zrt666
 *
 */

public class Customer{

	private Integer id;
	private String name;
	private String telphone;
	private Integer status;//用户状态：0:正常，1：黑名单，2：删除
	private String password;

	public Customer(){
	}

	public Customer(Integer id,String name,String telphone,Integer status,String password){
		this.id = id;
		this.name = name;
		this.telphone = telphone;
		this.status = status;
		this.password = password;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTelphone()
	{
		return telphone;
	}

	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		return "Customer [id=" + id + ", name=" + name + ", telphone=" + telphone + ", status="
				+ status + ", password=" + password + "]";
	}

}
