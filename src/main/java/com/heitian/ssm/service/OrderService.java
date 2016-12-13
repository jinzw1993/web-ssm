package com.heitian.ssm.service;

import com.heitian.ssm.bo.Result;

/**
 * Created by unname on 2016/12/12.
 */
public interface OrderService {
    Result changeProcessStatus(Long orderId, Long status);
}
