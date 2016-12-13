package com.heitian.ssm.dao;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.model.Order;
import org.springframework.stereotype.Repository;

/**
 * Created by oasis on 12/12/16.
 */
@Repository
public interface OrderDao {
    int changeOrderProcessStatus(Long orderId, Long status);
    OrderBo getOrderById(Long orderId);
}
