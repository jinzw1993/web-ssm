package com.heitian.ssm.service;

import com.heitian.ssm.bo.*;

import java.util.List;

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
}
