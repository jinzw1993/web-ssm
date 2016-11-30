package com.heitian.ssm.bo;

import com.heitian.ssm.model.Shop;

import java.io.Serializable;

/**
 * Created by oasis on 11/30/16.
 */
public class ShopBo extends Shop implements Serializable{
    private String idPhotoUrl;

    public ShopBo() {}

    public ShopBo(Shop shop) {
        this.setId(shop.getId());
        this.setOwnerId(shop.getOwnerId());
        this.setName(shop.getName());
        this.setContact(shop.getContact());
        this.setEmail(shop.getEmail());
        this.setTelephone(shop.getTelephone());
        this.setStatus(shop.getStatus());
    }

    public ShopBo(Shop shop, String url) {
        this.setId(shop.getId());
        this.setOwnerId(shop.getOwnerId());
        this.setName(shop.getName());
        this.setContact(shop.getContact());
        this.setEmail(shop.getEmail());
        this.setTelephone(shop.getTelephone());
        this.setStatus(shop.getStatus());
        idPhotoUrl = url;
    }

    public String getIdPhotoUrl() {
        return idPhotoUrl;
    }

    public void setIdPhotoUrl(String idPhotoUrl) {
        this.idPhotoUrl = idPhotoUrl;
    }
}
