package com.heitian.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.ShopBo;
import com.heitian.ssm.service.FavoriteService;

@Controller
@RequestMapping("/Favorite")
public class FavoriteController {
	
	private Logger log = Logger.getLogger(ProductController.class);

	@Resource
	private FavoriteService favoriteService;
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="/addProduct",method= RequestMethod.GET)
    public Result addProduct(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return returnResult();
        
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));
        
        Long productId = Long.valueOf(request.getParameter("id"));
        
        return favoriteService.addFavoriteProduct(productId, customerId);
    }
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="/searchFavoriteProduct",method= RequestMethod.GET)
    public List<ProductBo> searchProduct(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return new ArrayList<>();
        return favoriteService.searchFavoriteProduct();
    }
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="/addShop",method= RequestMethod.GET)
    public Result addShop(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return returnResult();
        
        String s[] = auth.split(";");//前提是，传参为ownerId=xxx;customerId=xxx;adress=xxx...格式
        Long customerId = Long.valueOf(s[1].substring(11));
        
        Long shopId = Long.valueOf(request.getParameter("id"));
        
        return favoriteService.addFavoriteProduct(shopId, customerId);
    }
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="/searchFavoriteShop",method= RequestMethod.GET)
    public List<ShopBo> searchShop(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return new ArrayList<>();
        return favoriteService.searchFavoriteShop();
    }
	
	public Result returnResult() {
		Result result = new Result();
		result.setStatus(0);
		result.setMessage("you haven't log in");
		return result;
	}

}
