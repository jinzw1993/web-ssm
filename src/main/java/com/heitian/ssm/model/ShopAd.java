package com.heitian.ssm.model;

import java.util.Date;

/**
 * Created by oasis on 12/11/16.
 */
public class ShopAd {
	private Long id;
	private Long shopId;
	private Long status;
	private Long rank;
	private Date date;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getShopId()
	{
		return shopId;
	}

	public void setShopId(Long shopId)
	{
		this.shopId = shopId;
	}

	public Long getStatus()
	{
		return status;
	}

	public void setStatus(Long status)
	{
		this.status = status;
	}

	public Long getRank()
	{
		return rank;
	}

	public void setRank(Long rank)
	{
		this.rank = rank;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

}