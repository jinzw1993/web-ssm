package com.heitian.ssm.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by unname on 2016/12/23.
 */
public class IncomeBo extends TimeCondition{
    @JsonSerialize(using=com.heitian.ssm.util.CustomDoubleSerializer.class)
    Double income;

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
}
