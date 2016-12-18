package com.heitian.ssm.controller;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.ProductInOrderBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.TimeCondition;
import com.heitian.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oasis on 12/13/16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private Result result = new Result();

    /**
     * 店主更改 process status
     * @param id
     * @param status
     * @param request
     * @return
     */
    @RequestMapping("/changeProcessStatus")
    public @ResponseBody
    Result changeProcessStatus(@RequestParam Long id, @RequestParam Long status, HttpServletRequest request) {
        if(request.getHeader("Authorization") == null) {
            return returnFailResult();
        }
        return orderService.changeProcessStatus(id, status);
    }

    /**
     * 获取order
     * @param id
     * @return
     */
    @RequestMapping("/getById")
    public @ResponseBody
    OrderBo getOrderBoById(@RequestParam Long id) {
        return orderService.getOrderBoById(id);
    }

    /**
     * 更据process_status获取order列表（处理中、运送中、已送达列表），分页
     * @param status
     * @param page
     * @param count
     * @param request
     * @return 返回的OrderTimeBo含有year month week day属性
     */
    @RequestMapping("/listByProcessStatus")
    public @ResponseBody
    List<OrderBo> getListByProcessStatus(@RequestParam Long status,
                                         @RequestParam int page,
                                         @RequestParam int count,
                                         HttpServletRequest request) {
//        String auth = request.getHeader("Authorization");
//        if(auth == null)
//            return new ArrayList<>();
//        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        String ownerId= "4";
        return orderService.getOwnerOrderBoByPStatus(status, Long.valueOf(ownerId), page, count);
    }

    /**
     * 更据process_status获取order数量，用于分页
     * @param status
     * @param request
     * @return
     */
    @RequestMapping("/listByProcessStatusNum")
    public @ResponseBody
    Result getListByProcessStatusNum(@RequestParam Long status,
                                  HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null) {
            return returnFailResult();
        }
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        return orderService.getOwnerOrderBoByPStatusNum(status, Long.valueOf(ownerId));
    }

    /**
     * 店主按日周月年查询正常订单列表，time的值 0天 1周 2月 3年
     * @param time
     * @param request
     * @return
     */
    @RequestMapping("/listByOwnerTime")
    public @ResponseBody
    List<OrderBo> getListByOwnerTime(@RequestBody TimeCondition time,
                                         HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return new ArrayList<>();
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        return orderService.getOwnOrderByTime(Long.valueOf(ownerId), time);
    }

    /**
     * 店主查询正常所有订单数目，用于按日周月年查询的分页
     * @param request
     * @return
     */
    @RequestMapping("/listByOwnerTimeNum")
    public @ResponseBody
    Result getListByOwnerTimeNum(@RequestBody TimeCondition time,
                                 HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null) {
            return returnFailResult();
        }
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        return orderService.getOwnOrderByTimeNum(Long.valueOf(ownerId), time);
    }

    /**
     * 根据订单查询商品
     * @param id
     * @return
     */
    @RequestMapping("/products")
    public @ResponseBody
    List<ProductInOrderBo> getProductInOrder(@RequestParam Long id) {
        return orderService.getProductInOrder(id);
    }

    private Result returnFailResult() {
        result.setStatus(0);
        result.setMessage("haven't log in");
        return result;
    }
}
