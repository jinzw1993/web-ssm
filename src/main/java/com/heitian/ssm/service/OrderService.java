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
    int getOwnOrderCompleteNum(Long orderId);
    List<OrderTimeBo> getOwnOrderByTime(Long orderId, int page, int num, int i);
}
