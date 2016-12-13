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

    public Result changeProcessStatus(Long orderId, Long status) {
        Result result = new Result();
        int i = orderDao.changeOrderProcessStatus(orderId, status);
        if(i>0) {
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

    public int getOwnOrderCompleteNum(Long orderId) {
        return orderDao.getOwnOrderCompleteNum(orderId);
    }
}
