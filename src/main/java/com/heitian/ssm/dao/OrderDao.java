package com.heitian.ssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.PageCondition;
import com.heitian.ssm.bo.TimeCondition;
import com.heitian.ssm.model.Order;

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

    public int insertOrder(@Param("order") Order order);
    public Order getOrderByCreatedAt(Date date);
    public int getMaxOrderId();
	public int changeOrderStatus(@Param("orderId") Long orderId,@Param("status") Long status);
	public List<OrderBo> search(@Param("page") PageCondition page, @Param("customerId") Long customerId);
}
