package com.heitian.ssm.controller;

import java.sql.Date;
import java.util.List;
import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.ProductAd;
import com.heitian.ssm.service.ProductAdService;
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
@RequestMapping("productAd")
public class ProductAdController {
	@Autowired
	private ProductAdService productAdService;

	private Result result = new Result();

	@RequestMapping("/add")
	public @ResponseBody Result addProductAd(@RequestParam Long id,@RequestParam Date date, HttpServletRequest request)
	{
		if (request.getHeader("Authorization") == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		return productAdService.addProductAd(id,date);
	}

	@RequestMapping("/show")
	public @ResponseBody List<ProductAd> showProductAd()
	{
		return productAdService.showProductAd();
	}

	@RequestMapping("/apply")
	public @ResponseBody List<ProductAd> applyProductAd()
	{
		return productAdService.applyProductAd();
	}

	@RequestMapping("/delete")
	public @ResponseBody Result deleteProductAd(@RequestParam Long id, HttpServletRequest request)
	{
		if (request.getHeader("Authorization") == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		return productAdService.deleteProductAd(id);
	}

}