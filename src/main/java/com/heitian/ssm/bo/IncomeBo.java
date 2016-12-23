package com.heitian.ssm.bo;

import java.sql.Time;

/**
 * Created by unname on 2016/12/23.
 */
public class IncomeBo extends TimeCondition{
    Long income;

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }
}
