package com.heitian.ssm.bo;

import com.heitian.ssm.model.ProductAd;

import java.io.Serializable;

/**
 * Created by unname on 2016/12/17.
 */
public class ProductAdBo extends ProductAd implements Serializable {
    private String name;//product name
    private Long price;//product price
    private Long categoryId;//
    private String photoURL;

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

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}
