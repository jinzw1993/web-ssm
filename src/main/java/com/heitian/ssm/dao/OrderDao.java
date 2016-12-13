package com.heitian.ssm.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by oasis on 12/12/16.
 */
@Repository
public interface OrderDao {
    int changeOrderProcessStatus(Long orderId, Long status);

}
