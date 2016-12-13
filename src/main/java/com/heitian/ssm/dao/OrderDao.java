package com.heitian.ssm.dao;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.OrderTimeBo;
import com.heitian.ssm.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 12/12/16.
 */
@Repository
public interface OrderDao {
    int changeOrderProcessStatus(Long orderId, Long status);
    OrderBo getOrderById(Long orderId);
    int getOwnOrderCompleteNum(Long id);
    List<OrderTimeBo> getOwnOrderYearly(@Param("id") Long id, @Param("page") int page, @Param("count") int count);
    List<OrderTimeBo> getOwnOrderMonthly(@Param("id") Long id, @Param("page") int page, @Param("count") int count);
    List<OrderTimeBo> getOwnOrderWeekly(@Param("id") Long id, @Param("page") int page, @Param("count") int count);
    List<OrderTimeBo> getOwnOrderDaily(@Param("id") Long id, @Param("page") int page, @Param("count") int count);
}
