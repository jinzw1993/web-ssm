package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.OrderDao;
import com.heitian.ssm.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by unname on 2016/12/12.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    public Result changeProcessStatus(Long orderId, Long status) {
        Result result = new Result();
        int i = orderDao.changeOrderProcessStatus(orderId, status);
        if (i > 0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setStatus(0);
            result.setMessage("failded");
        }
        return result;
    }

    public OrderBo getOrderBoById(Long orderId) {
        return orderDao.getOrderById(orderId);
    }

    public List<OrderBo> getOwnerOrderBoByPStatus(Long processSatuts, Long ownerId, int page, int pageNum) {
        return orderDao.getOwnerOrderBoByProcessStatus(processSatuts, ownerId, (page - 1) * pageNum, pageNum);
    }

    public int getOwnerOrderBoByPStatusNum(Long prcessStatus, Long ownerId) {
        return orderDao.getOwnerOrderBoByProcessStatusNum(prcessStatus, ownerId);
    }
}
