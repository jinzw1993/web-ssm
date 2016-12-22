package com.heitian.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heitian.ssm.bo.OrderBo;
import com.heitian.ssm.bo.PageCondition;
import com.heitian.ssm.bo.ProductInOrderBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.TimeCondition;
import com.heitian.ssm.model.Order;
import com.heitian.ssm.service.OrderService;

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
            returnFailResult();
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
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return new ArrayList<>();
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
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
            returnFailResult();
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
            returnFailResult();
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
    
    /* -------------------customer------------------*/
    
    /**
     * 添加订单
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public OrderBo addOrder(HttpServletRequest request) {
    	String auth = request.getHeader("Authorization");
        if(auth == null)
            return new OrderBo();       
       
        Long cartId = Long.valueOf(request.getParameter("cartId"));
        
        return orderService.addOrder(cartId);
    }
    
    /**
     * 确认订单
     * @return
     */
    @RequestMapping("/confirm")
    @ResponseBody
    public Result confirmOrder(HttpServletRequest request) {
    	String auth = request.getHeader("Authorization");
        if(auth == null)
            return returnFailResult();       
       
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        
        Long addressId = Long.valueOf(request.getParameter("addressId"));
        return orderService.confirmOrder(orderId, addressId);
    }
    
    /**
     * 用户状态响应 status
     * @param id
     * @param status
     * @param request
     * @return
     */
    @RequestMapping("/changeStatus")
    public @ResponseBody
    Result changeStatus(@RequestParam Long id, @RequestParam Long status, HttpServletRequest request) {
        if(request.getHeader("Authorization") == null) {
            returnFailResult();
        }
        return orderService.changeStatus(id, status);
    }
    
    /**
     * 用户查询自己订单
     * @param request
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public List<OrderBo> search(@RequestBody PageCondition page, HttpServletRequest request) {
    	/*String auth = request.getHeader("Authorization");
    	
        if(auth == null) {
            return new ArrayList<OrderBo>();
        }
        
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));*/
    	long customerId = 1;
        
        return orderService.search(page, customerId);
    }

    private Result returnFailResult() {
        result.setStatus(0);
        result.setMessage("haven't log in");
        return result;
    }
}
