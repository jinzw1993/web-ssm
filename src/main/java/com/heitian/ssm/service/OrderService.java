package com.heitian.ssm.service;

import java.util.List;

import com.heitian.ssm.bo.*;
import com.heitian.ssm.model.Order;

/**
 * Created by unname on 2016/12/12.
 */
public interface OrderService {
    Result changeProcessStatus(Long orderId, Long status);
    OrderBo getOrderBoById(Long orderId);
    Result getOrderByTimeNum(Long orderId, TimeCondition time, int kind);
    List<OrderBo> getOrderByTime(Long orderId, TimeCondition time, int kind);
    List<OrderBo> getOwnerOrderBoByPStatus(Long processStatus, Long ownerId, int page, int pageNum);
    List<OrderCountBo> getOrderNum(Long id, int i, int kind);
    Result getOwnerOrderBoByPStatusNum(Long processStatus, Long ownerId);
    List<ProductInOrderBo> getProductInOrder(Long orderId);
    Result deliver(Long orderId, Long expressId, String number);
    
    public List<OrderBo> addOrder(Long customerId);
	public List<OrderBo> search(PageCondition page, Long customerId);
	public Result confirmOrder(Long orderId, Long addressId);
	public Result cancel(Long id);
	public Result pay(Long id);
}
