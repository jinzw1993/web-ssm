package com.heitian.ssm.model;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Lanting on 2016/11/26.
 */
public class Product implements Serializable{
    private Long id;
    private Long ownId;
    private Long shopId;
    private Long brandId;
    private Long productPhotoId;
    private String name;
    private Long price;
    private Long categoryId;
    private String detail;
    private Time createdAt;
    private Time modifiedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnId() {
        return ownId;
    }

    public void setOwnId(Long ownId) {
        this.ownId = ownId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductPhotoId() {
        return productPhotoId;
    }

    public void setProductPhotoId(Long productPhotoId) {
        this.productPhotoId = productPhotoId;
    }

    public Time getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Time createdAt) {
        this.createdAt = createdAt;
    }

    public Time getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Time modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
