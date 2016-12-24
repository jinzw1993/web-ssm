package com.heitian.ssm.bo;

import com.heitian.ssm.model.Product;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Lanting on 2016/11/28.
 */
public class ProductBo extends Product implements Serializable {
    private String photoURL;
    public ProductBo(){}
    public ProductBo(Product product){
        this.setId(product.getId());
        this.setOwnId(product.getOwnId());
        this.setShopId(product.getShopId());
        this.setProductPhotoId(product.getProductPhotoId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategoryId());
        this.setDetail(product.getDetail());
        this.setCreatedAt(product.getCreatedAt());
        this.setModifiedAt(product.getModifiedAt());
        this.setAmount(product.getAmount());
    }
    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

}
