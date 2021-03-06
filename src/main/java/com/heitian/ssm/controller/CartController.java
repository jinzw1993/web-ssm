package com.heitian.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heitian.ssm.bo.CartBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private Logger log = Logger.getLogger(ProductController.class);

    @Resource
    private CartService cartService;

    /**
     * 查询购物车及其条目
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/searchCart",method= RequestMethod.GET)
    public List<CartBo> searchProduct(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return new ArrayList<>();
        
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));
        
        return cartService.searchCart(customerId);
    }
    
    @ResponseBody
    @RequestMapping("/addCart")
    public Result addCart(HttpServletRequest request) {
    	String auth = request.getHeader("Authorization");
        if(auth == null)
            return returnResult();
        
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));
        
        Long productId = Long.valueOf(request.getParameter("id"));
        
        Long amount = Long.valueOf(request.getParameter("amount"));
        
        Result result = cartService.addCart(productId, customerId, amount);
    	
        return result;
    }
    
    @ResponseBody
    @RequestMapping("/deleteProductInCart")
    public Result deleteProductInCart(HttpServletRequest request) {
    	String auth = request.getHeader("Authorization");
        if(auth == null)
            return returnResult();
        
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));
        
        Long productId = Long.valueOf(request.getParameter("id"));
        
        Result result = cartService.deleteProductInCart(productId, customerId);
    	
        return result;
    }
    
    @ResponseBody
    @RequestMapping("/updateAmount")
    public Result updateProductAmount(HttpServletRequest request) {
    	String auth = request.getHeader("Authorization");
        if(auth == null)
            return returnResult();
        
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));
        
        Long productId = Long.valueOf(request.getParameter("id"));
        
        Long amount = Long.valueOf(request.getParameter("amount"));
        
        Result result = cartService.updateProductAmount(productId, customerId, amount);
    	
        return result;
    }
    
    private Result returnResult() {
		Result result = new Result();
		result.setStatus(0);
		result.setMessage("you haven't log in");
		return result;
	}

}
