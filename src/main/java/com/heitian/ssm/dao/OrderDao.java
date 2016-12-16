package com.heitian.ssm.dao;

import com.heitian.ssm.bo.OrderBo;

import com.heitian.ssm.bo.TimeCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 12/12/16.
 */
@Repository
public interface OrderDao {
    int changeOrderProcessStatus(@Param("orderId") Long orderId,@Param("status") Long status);
    OrderBo getOrderById(Long orderId);

    List<OrderBo> getOwnerOrders(@Param("id") Long id, @Param("time")TimeCondition time);
    int getOwnOrderCompleteNum(@Param("id") Long id, @Param("time")TimeCondition time);

    List<OrderBo> getOwnerOrderBoByProcessStatus(@Param("processStatus") Long processStatus, @Param("ownerId")Long ownerId, @Param("start") int start,@Param("pageNum") int pageNum);
    int getOwnerOrderBoByProcessStatusNum(@Param("processStatus") Long processStatus, @Param("ownerId")Long ownerId);
}
