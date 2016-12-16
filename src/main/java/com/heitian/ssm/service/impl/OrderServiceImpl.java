package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.ProductInOrderBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.TimeCondition;
import com.heitian.ssm.dao.OrderDao;
import com.heitian.ssm.dao.ProductInOrderDao;
import com.heitian.ssm.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by unname on 2016/12/12.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private ProductInOrderDao productInOrderDao;
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
        OrderBo order = orderDao.getOrderById(orderId);
        if(order == null)
            return new OrderBo();
        else
            return order;
    }


    public List<OrderBo> getOwnOrderByTime(Long id, TimeCondition time) {
        setTimeCon(time);
        return orderDao.getOwnerOrders(id, time);
    }

    public Result getOwnOrderByTimeNum(Long ownerId, TimeCondition time) {
        setTimeCon(time);
        result.setStatus(1);
        result.setMessage(String.valueOf(orderDao.getOwnOrderCompleteNum(ownerId, time)));
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

    public List<ProductInOrderBo> getProductInOrder(Long orderId) {
        return productInOrderDao.getProductByOrderId(orderId);
    }

    private void setTimeCon(TimeCondition time) {
        if(time == null)
            time = new TimeCondition();
        //默认按天搜索
        if(time.getId() == null)
            time.setId(0);

        //默认设为当前日期
        Calendar now = Calendar.getInstance();
        now.setFirstDayOfWeek(Calendar.MONDAY);
        if(time.getYear() == null)
            time.setYear(now.get(Calendar.YEAR));
        if(time.getMonth() == null)
            time.setMonth(now.get(Calendar.MONTH) +1);
        if(time.getWeek() == null)
            time.setWeek(now.get(Calendar.WEEK_OF_YEAR) -1);
        if(time.getDay() == null)
            time.setDay(now.get(Calendar.DAY_OF_MONTH));
    }
}
