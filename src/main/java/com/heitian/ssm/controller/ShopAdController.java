package com.heitian.ssm.controller;

import java.sql.Date;
import java.util.List;
import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.ProductAd;
import com.heitian.ssm.model.ShopAd;
import com.heitian.ssm.service.ProductAdService;
import com.heitian.ssm.service.ShopAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by oasis on 12/11/16.
 */
@Controller
@RequestMapping("shopAd")
public class ShopAdController {
	@Autowired
	private ShopAdService shopAdService;

	private Result result = new Result();

	@RequestMapping("/add")
	public @ResponseBody Result addShopAd(@RequestParam Long id,@RequestParam Date date, HttpServletRequest request)
	{
		if (request.getHeader("Authorization") == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		return shopAdService.addShopAd(id,date);
	}

	@RequestMapping("/show")
	public @ResponseBody List<ShopAd> showShopAd()
	{
		return shopAdService.showShopAd();
	}

	@RequestMapping("/apply")
	public @ResponseBody List<ShopAd> applyShopAd()
	{
		return shopAdService.applyShopAd();
	}

	@RequestMapping("/delete")
	public @ResponseBody Result deleteShopAd(@RequestParam Long id, HttpServletRequest request)
	{
		if (request.getHeader("Authorization") == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		return shopAdService.deleteShopAd(id);
	}

}