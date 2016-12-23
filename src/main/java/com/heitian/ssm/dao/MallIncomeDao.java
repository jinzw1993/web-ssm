package com.heitian.ssm.dao;

import com.heitian.ssm.bo.TimeCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 2016/12/23.
 */
@Repository
public interface MallIncomeDao {
    int insertIncome(Long orderId);
    List<Long> getIncomeByTime(TimeCondition con);
}
