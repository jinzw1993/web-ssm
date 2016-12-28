package com.heitian.ssm.controller;

import java.util.List;

import com.heitian.ssm.bo.ProductAdBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.service.ProductAdService;
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
@RequestMapping("productAd")
public class ProductAdController {
    @Resource
    private ProductAdService productAdService;

    private Result result = new Result();

    /**
     * 需要 productId
     *
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result addProductAd(HttpServletRequest request,@RequestParam Long productId, @RequestParam Double price) {
        String auth = request.getHeader("Authorization");
        if (auth == null) {
            result.setMessage("haven't log in");
            result.setStatus(0);
            return result;
        }
        return productAdService.addProductAd(productId, price);
    }

    /**
     * 需要ProductAd.id
     *
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteProductAd(HttpServletRequest request, @RequestParam Long productAdId) {
        String auth = request.getHeader("Authorization");
        if (auth == null) {
            result.setMessage("haven't log in");
            result.setStatus(0);
            return result;
        }
        return productAdService.deleteProductAd(productAdId);
    }

    /**
     * 更新productAd.status需要ProductAd.id ProductAd.status
     *
     * @param
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result updateProductAd(@RequestParam Long pAdId,@RequestParam Long status) {
        return productAdService.updateProductAdStatus(pAdId, status);
    }


    @RequestMapping("/getVerified")
    @ResponseBody
    public List<ProductAdBo> getVerifiedProductAd() {
        return productAdService.getVerifiedProductAd();
    }


    @RequestMapping("/getUnverified")
    @ResponseBody
    public List<ProductAdBo> getUnverifiedPAd(@RequestParam int page, @RequestParam int pageNum) {
        return productAdService.getUnverifiedProductAd(page, pageNum);
    }

    @RequestMapping("/getUnverifiedNum")
    @ResponseBody
    public Result getUnverifiedAdNum() {
        return productAdService.getUnverifiedPAdNum();
    }

    @RequestMapping("/getPAdByOwner")
    @ResponseBody
    public List<ProductAdBo> getPAdByOwnerId(HttpServletRequest request,@RequestParam int page, @RequestParam int pageNum) {
        String auth = request.getHeader("Authorization");
        if (auth == null) {
            result.setMessage("haven't log in");
            result.setStatus(0);
            return null;
        }
        String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        return productAdService.getProductAdByOwnerId(Long.valueOf(id), page, pageNum);
    }

    @RequestMapping("/getPAdNumByOwner")
    @ResponseBody
    public Result getPAdNumByOwnerId(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (auth == null) {
            result.setMessage("haven't log in");
            result.setStatus(0);
            return result;
        }
        String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        return productAdService.getProductAdNumByOwnerId(Long.valueOf(id));
    }

    /*

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

	@RequestMapping("/reject")
	@ResponseBody
	public  Result rejectProductAd( HttpServletRequest request)
	{
		String auth = request.getHeader("Authorization");
		if (auth == null) {
			result.setMessage("haven't log in");
			result.setStatus(0);
			return result;
		}
		String id = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
		return productAdService.rejectProductAd(Long.valueOf(id));
	}*/
}