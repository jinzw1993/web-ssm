package com.heitian.ssm.service;

import java.util.List;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.PageCondition;
import com.heitian.ssm.bo.ProductInOrderBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.TimeCondition;
import com.heitian.ssm.model.Order;

/**
 * Created by unname on 2016/12/12.
 */
public interface OrderService {
    Result changeProcessStatus(Long orderId, Long status);
    OrderBo getOrderBoById(Long orderId);
    Result getOwnOrderByTimeNum(Long orderId, TimeCondition time);
    List<OrderBo> getOwnOrderByTime(Long orderId, TimeCondition time);
    List<OrderBo> getOwnerOrderBoByPStatus(Long processStatus, Long ownerId, int page, int pageNum);
    Result getOwnerOrderBoByPStatusNum(Long processStatus, Long ownerId);
    List<ProductInOrderBo> getProductInOrder(Long orderId);
    Result deliver(Long expressId, String number, Long orderId);

    public OrderBo addOrder(Long cartId);
    public Result changeStatus(Long orderId, Long status);
	public List<OrderBo> search(PageCondition page, Long customerId);
	public Result confirmOrder(Long orderId, Long addressId);
}
