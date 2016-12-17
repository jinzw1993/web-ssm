package com.heitian.ssm.model;

import java.util.Date;

/**
 * Created by oasis on 12/11/16.
 */
public class ProductAd {
    private Long id;
    private Long productId;
    private Long status; //0--待审核，1--已允许，2--被拒绝，3--已过期
    private Long rank;
    private Date date; //不用

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

