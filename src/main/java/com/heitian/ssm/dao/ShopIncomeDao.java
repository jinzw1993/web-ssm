package com.heitian.ssm.dao;

import com.heitian.ssm.bo.IncomeBo;
import com.heitian.ssm.bo.TimeCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 2016/12/23.
 */
@Repository
public interface ShopIncomeDao {
    int insertIncome(@Param("orderId") Long orderId);
    List<IncomeBo> getIncomeByTime(@Param("i") Long i, @Param("shopId") Long shopId);
}
