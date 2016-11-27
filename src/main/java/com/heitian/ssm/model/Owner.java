package com.heitian.ssm.model;

/**
 * Created by oasis on 11/26/16.
 */

public class Owner {
    private Long id;
    private String ownerName;
    private String ownerPwd;
    private Short status;

    private Shop shop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPwd() {
        return ownerPwd;
    }

    public void setOwnerPwd(String ownerPwd) {
        this.ownerPwd = ownerPwd;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
