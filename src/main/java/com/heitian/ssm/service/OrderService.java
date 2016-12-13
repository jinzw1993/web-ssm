package com.heitian.ssm.service;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.OrderTimeBo;
import com.heitian.ssm.bo.Result;

import java.util.List;

/**
 * Created by unname on 2016/12/12.
 */
public interface OrderService {
    Result changeProcessStatus(Long orderId, Long status);
    OrderBo getOrderBoById(Long orderId);
    Result getOwnOrderCompleteNum(Long ownerId);
    List<OrderTimeBo> getOwnOrderByTime(Long orderId, int page, int num, int i);
    List<OrderBo> getOwnerOrderBoByPStatus(Long processStatus, Long ownerId, int page, int pageNum);
    Result getOwnerOrderBoByPStatusNum(Long processStatus, Long ownerId);
}
