package com.heitian.ssm.bo;

import com.heitian.ssm.model.ProductComment;

import java.io.Serializable;

/**
 * Created by unname on 2016/12/15.
 */
public class ProductCommentBo extends ProductComment implements Serializable {
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    private String customerEmail;
}
