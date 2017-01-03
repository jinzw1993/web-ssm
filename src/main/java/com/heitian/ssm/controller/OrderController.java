package com.heitian.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.heitian.ssm.bo.*;
import com.heitian.ssm.util.ResultResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        if(status == 3) {
            String number  = request.getParameter("number");
            Long expressId = Long.valueOf(request.getParameter("expressId"));
            return orderService.deliver(id, expressId, number);
        }

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
     * 按日周月年查询正常订单列表，time的值 0天 1周 2月 3年
     * @param time
     * @return
     */
    @RequestMapping("/listByTimeA")
    public @ResponseBody
    List<OrderBo> getListByTimeA(@RequestBody TimeCondition time) {
		time.setStart(time.getPage());
        return orderService.getOrderByTime(0L, time, 0);

    }
    @RequestMapping("/listByTimeO")
    public @ResponseBody
    List<OrderBo> getListByTimeO(@RequestBody TimeCondition time,
                                HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
		time.setStart(time.getPage());
        if (ownerId != null && !"".equals(ownerId))
            return orderService.getOrderByTime(Long.valueOf(ownerId), time, 1);
        return new ArrayList<>();


    }
    @RequestMapping("/listByTimeC")
    public @ResponseBody
    List<OrderBo> getListByTimeC(@RequestBody TimeCondition time,
                                HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        String customerId = auth.split(";")[1].substring(11);
		time.setStart(time.getPage());
        if (customerId != null && !"".equals(customerId))
            return orderService.getOrderByTime(Long.valueOf(customerId), time, 2);
        return new ArrayList<>();
    }

    /**
     * 查询正常所有订单数目，用于按日周月年查询的分页
     * @return
     */
    @RequestMapping("/listByTimeNumA")
    public @ResponseBody
    Result getListByTimeNumA(@RequestBody TimeCondition time) {
		time.setStart(time.getPage());
        return orderService.getOrderByTimeNum(0L, time, 0);
    }

    @RequestMapping("/listByTimeNumO")
    public @ResponseBody
    Result getListByTimeNumO(@RequestBody TimeCondition time,
                            HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
		time.setStart(time.getPage());
        if (ownerId != null && ownerId != "")
            return orderService.getOrderByTimeNum(Long.valueOf(ownerId), time, 1);
        return ResultResolver.returnRes(0);
    }

    @RequestMapping("/listByTimeNumC")
    public @ResponseBody
    Result getListByTimeNumC(@RequestBody TimeCondition time,
                            HttpServletRequest request) {
        String auth = request.getHeader("Authorization");

        String customerId = auth.split(";")[1].substring(11);
		time.setStart(time.getPage());
        if (customerId != null && customerId != "")
            return orderService.getOrderByTimeNum(Long.valueOf(customerId), time, 2);
        return ResultResolver.returnRes(0);
    }

    /**
     * 日/周/月/年 返回订单数目
     * @param cond
     * @return
     */
    @RequestMapping("/numA")
    public @ResponseBody
    List<OrderCountBo> getOrderNumA(@RequestParam int cond) {
        return orderService.getOrderNum(0L, cond, 0);
    }

    @RequestMapping("/numO")
    public @ResponseBody
    List<OrderCountBo> getOrderNumO(@RequestParam int cond, HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        if (ownerId != null && !"".equals(ownerId))
            return orderService.getOrderNum(Long.valueOf(ownerId), cond, 1);
        return new ArrayList<>();
    }
    @RequestMapping("/numC")
    public @ResponseBody
    List<OrderCountBo> getOrderNumC(@RequestParam int cond, HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        String customerId = auth.split(";")[1].substring(11);
        if (customerId != null && !"".equals(customerId))
            return orderService.getOrderNum(Long.valueOf(customerId), cond, 2);
        return new ArrayList<>();
    }

    @RequestMapping("/sumA")
    public @ResponseBody
    Result getOrderSumNumA() {
        return orderService.getOrderSumNum(0L, 0);
    }
    @RequestMapping("/sumO")
    public @ResponseBody
    Result getOrderSumNumO(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        if (ownerId != null && !"".equals(ownerId))
            return orderService.getOrderSumNum(Long.valueOf(ownerId), 1);
        return ResultResolver.returnRes(0);
    }
    @RequestMapping("/sumC")
    public @ResponseBody
    Result getOrderSumNumC(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        String customerId = auth.split(";")[1].substring(11);
        if (customerId != null && !"".equals(customerId))
            return orderService.getOrderSumNum(Long.valueOf(customerId), 2);
        return ResultResolver.returnRes(0);
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
    public List<OrderBo> addOrder(HttpServletRequest request) {
    	String auth = request.getHeader("Authorization");
        if(auth == null)
            return new ArrayList<OrderBo>();       
       
        //Long cartId = Long.valueOf(request.getParameter("cartId"));
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));
        
        return orderService.addOrder(customerId);
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
        String auth = request.getHeader("Authorization");
        if(auth == null) {
            returnFailResult();
        }
        String customerId = auth.split(";")[1].substring(11);
        if(0 == status) {
        	Long addressId = Long.valueOf(request.getParameter("addressId"));
        	return orderService.confirmOrder(id, addressId, Long.valueOf(customerId));
        } else if(1 == status) {
        	return orderService.pay(id);
        } else if(2 == status){
        	return orderService.cancel(id);
        } else {
        	Result r= new Result();
        	r.setStatus(0);
        	r.setMessage("The parameters of the illegal!");
        	return r;
        }
    }

    @RequestMapping("/pay")
    public @ResponseBody
    Result payOrders(@RequestBody List<Long> ids, HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        Result result = new Result();
        result.setMessage("you haven't login");
        result.setStatus(0);
        if(auth == null) {
            return result;
        }
        Long customerId = Long.valueOf(auth.split(";")[1].substring(11));
        return orderService.payAllOrders(customerId, ids);
    }

    /**
     * 用户查询自己订单
     * @param request
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public List<OrderBo> search(@RequestBody PageCondition page, HttpServletRequest request) {
    	String auth = request.getHeader("Authorization");
    	
        if(auth == null) {
            return new ArrayList<OrderBo>();
        }
        
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));
    	//long customerId = 1;
        page.setStart(page.getPage());
        return orderService.search(page, customerId);
    }

    private Result returnFailResult() {
        result.setStatus(0);
        result.setMessage("haven't log in");
        return result;
    }
}
