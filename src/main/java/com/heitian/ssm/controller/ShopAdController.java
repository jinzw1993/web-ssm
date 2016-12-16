package com.heitian.ssm.controller;

import java.sql.Date;
import java.util.List;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.ShopAd;
import com.heitian.ssm.service.ShopAdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by oasis on 12/11/16.
 */
@Controller
@RequestMapping("shopAd")
public class ShopAdController {
    @Resource
    private ShopAdService shopAdService;

    private Result result = new Result();

    @RequestMapping("/add")
    public @ResponseBody
    Result addShopAd(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null) {
            result.setMessage("haven't log in");
            result.setStatus(0);
            return result;
        }
        String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
		Date d=new Date(new java.util.Date().getTime());
        return shopAdService.addShopAd(Long.valueOf(id),d);
    }

	@RequestMapping("/show")
	@ResponseBody
	public  List<ShopAd> showShopAd()
	{
		return shopAdService.showShopAd();
	}

	@RequestMapping("/apply")
	@ResponseBody
	public  List<ShopAd> applyShopAd()
	{
		return shopAdService.applyShopAd();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public  Result deleteShopAd(HttpServletRequest request)
	{
		String auth = request.getHeader("Authorization");
		if (auth == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
		return shopAdService.deleteShopAd(Long.valueOf(id));
	}
	
	@RequestMapping("/agree")
	@ResponseBody
	public  Result agreeShopAd(HttpServletRequest request)
	{
		String auth = request.getHeader("Authorization");
		if (auth == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
		return shopAdService.agreeShopAd(Long.valueOf(id));
	}
	
	@RequestMapping("/reject")
	@ResponseBody
	public  Result rejectShopAd(HttpServletRequest request)
	{
		String auth = request.getHeader("Authorization");
		if (auth == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
		return shopAdService.rejectShopAd(Long.valueOf(id));
	}

}
