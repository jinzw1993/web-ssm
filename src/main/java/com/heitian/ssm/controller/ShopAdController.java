package com.heitian.ssm.controller;

import java.util.List;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.ShopAdBo;
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

    Result result = new Result();

    @RequestMapping("/add")
    public @ResponseBody
    Result addShopAd(HttpServletRequest request, @RequestParam String photoUrl, @RequestParam Double price) {
        String auth = request.getHeader("Authorization");
        if(auth == null) {
            result.setMessage("haven't log in");
            result.setStatus(0);
            return result;
        }
        String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        return shopAdService.addShopAd(Long.valueOf(id), photoUrl, price);
    }

	@RequestMapping("/status")
	@ResponseBody
	public Result showShopAdStatus(HttpServletRequest request)
	{
		String auth = request.getHeader("Authorization");
		if(auth == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
		return shopAdService.showShopAdStatus(Long.valueOf(id));
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteShopAd(HttpServletRequest request)
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

	@RequestMapping("/changeStatus")
	@ResponseBody
	public Result changeShopAdStatus(@RequestParam Long shopId, @RequestParam Long status)
	{
        return shopAdService.changeShopAdStatus(Long.valueOf(shopId), status);
	}

	@RequestMapping("/num")
	@ResponseBody
	public  Result getNum()
	{
		return shopAdService.getNum(0L);
	}

    @RequestMapping("/unverified")
    @ResponseBody
    public List<ShopAdBo> unverifiedShopAd(@RequestParam int page, @RequestParam int count) {
        return shopAdService.unverifiedShopAd(page, count);
    }

    @RequestMapping("/verified")
    @ResponseBody
    public List<ShopAdBo> verifiedShopAd() {
        return shopAdService.verifiedShopAd();
    }

}
