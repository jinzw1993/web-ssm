package com.heitian.ssm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by oasis on 12/12/16.
 */
public class Order {
    Long id;
    Long customerId;
    Long shopId;
    Long ownerId;
    Long expressId;
    Long price;
    Long amount;
    Long commission;
    Long commissionRate;
    Long status;
    Long processStatus;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public Long getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Long commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Long processStatus) {
        this.processStatus = processStatus;
    }

}
