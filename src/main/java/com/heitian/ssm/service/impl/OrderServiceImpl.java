package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.OrderTimeBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.OrderDao;
import com.heitian.ssm.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by unname on 2016/12/12.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    private Result result = new Result();

    public Result changeProcessStatus(Long orderId, Long status) {
        int i = orderDao.changeOrderProcessStatus(orderId, status);
        if (i > 0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setStatus(0);
            result.setMessage("failed");
        }
        return result;
    }

    public OrderBo getOrderBoById(Long orderId) {
        return orderDao.getOrderById(orderId);
    }


    public List<OrderTimeBo> getOwnOrderByTime(Long id, int page, int num, int i) {
        List<OrderTimeBo> list = new ArrayList<>();
        switch(i) {
            case 0:
                list = orderDao.getOwnOrderDaily(id, (page-1)*num, num);break;
            case 1:
                list = orderDao.getOwnOrderWeekly(id, (page-1)*num, num);break;
            case 2:
                list = orderDao.getOwnOrderMonthly(id, (page-1)*num, num);break;
            case 3:
                list = orderDao.getOwnOrderYearly(id, (page-1)*num, num);break;
        }
        return list;
    }

    public Result getOwnOrderCompleteNum(Long ownerId) {
        result.setStatus(1);
        result.setMessage(String.valueOf(orderDao.getOwnOrderCompleteNum(ownerId)));
        return result;
    }

    public List<OrderBo> getOwnerOrderBoByPStatus(Long processStatus, Long ownerId, int page, int pageNum) {
        return orderDao.getOwnerOrderBoByProcessStatus(processStatus, ownerId, (page - 1) * pageNum, pageNum);
    }

    public Result getOwnerOrderBoByPStatusNum(Long processStatus, Long ownerId) {
        result.setStatus(1);
        result.setMessage(String.valueOf(orderDao.getOwnerOrderBoByProcessStatusNum(processStatus, ownerId)));
        return result;
    }
}
