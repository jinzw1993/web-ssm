package com.heitian.ssm.dao;

import java.util.List;

import com.heitian.ssm.bo.OrderCountBo;
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

    List<OrderBo> getOrdersTime(@Param("id") Long id, @Param("time")TimeCondition time, @Param("kind") int kind);
    int getOrdersTimeNum(@Param("id") Long id, @Param("time")TimeCondition time, @Param("kind") int kind);
    List<OrderCountBo> getOrderNum(@Param("id") Long id, @Param("i") int i, @Param("kind") int kind);
    int getOrderSumNum(@Param("id") Long id, @Param("kind") int kind);

    List<OrderBo> getOwnerOrderBoByProcessStatus(@Param("processStatus") Long processStatus, @Param("ownerId")Long ownerId, @Param("start") int start,@Param("pageNum") int pageNum);
    int getOwnerOrderBoByProcessStatusNum(@Param("processStatus") Long processStatus, @Param("ownerId")Long ownerId);
    int setExpress(@Param("expressId") Long expressId, @Param("number") String number, @Param("orderId") Long orderId);

    public int insertOrder(@Param("order") Order order);
    public int getMaxOrderId();
	public int changeOrderStatus(@Param("orderId") Long orderId,@Param("status") Long status);
	public List<OrderBo> search(@Param("page") PageCondition page, @Param("customerId") Long customerId);
	public int changeOrderAddress(@Param("orderId") Long orderId,@Param("addressId") Long addressId, @Param("expressPrice") Long expressPrice);
}
