package com.heitian.ssm.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.ShopAd;
import com.heitian.ssm.service.ShopAdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@ResponseBody 
	public Result addShopAd(@RequestParam Long id, HttpServletRequest request) throws ParseException
	{
		if (request.getHeader("Authorization") == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		Date d = new Date(new java.util.Date().getTime());
		return shopAdService.addShopAd(id,d);
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
	public  Result deleteShopAd(@RequestParam Long id, HttpServletRequest request)
	{
		if (request.getHeader("Authorization") == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		return shopAdService.deleteShopAd(id);
	}

}