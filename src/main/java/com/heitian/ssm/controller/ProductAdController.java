package com.heitian.ssm.controller;

import java.sql.Date;
import java.util.List;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.ProductAd;
import com.heitian.ssm.service.ProductAdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by oasis on 12/11/16.
 */
@Controller
@RequestMapping("productAd")
public class ProductAdController {
	@Resource
	private ProductAdService productAdService;

	private Result result = new Result();

	@RequestMapping("/add")
	@ResponseBody
	public  Result addProductAd(HttpServletRequest request)
	{
		String auth = request.getHeader("Authorization");
		if(auth == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
		Date d=new Date(new java.util.Date().getTime());
		return productAdService.addProductAd(Long.valueOf(id),d);
	}

	@RequestMapping("/show")
	@ResponseBody
	public  List<ProductAd> showProductAd()
	{
		return productAdService.showProductAd();
	}

	@RequestMapping("/apply")
	@ResponseBody
	public  List<ProductAd> applyProductAd()
	{
		return productAdService.applyProductAd();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public  Result deleteProductAd(HttpServletRequest request)
	{
		String auth = request.getHeader("Authorization");
		if(auth == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
		return productAdService.deleteProductAd(Long.valueOf(id));
	}

}