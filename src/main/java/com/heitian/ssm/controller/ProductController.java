package com.heitian.ssm.controller;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.ProductCondition;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.service.ProductService;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lanting on 2016/11/26.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private Logger log = Logger.getLogger(ProductController.class);

    @Resource
    private ProductService productService;

    /**
     *根据条件搜索商品
     * @param productCondition ProductCondition对象，需要page(默认1), num(默认30), 和其他任意ProductCondition的属性
     * @return ProductBo List
     */
    @ResponseBody
    @RequestMapping("/search")
    public Model searchProductBos(@RequestBody ProductCondition productCondition, Model model) {
        model.addAttribute("List",productService.searchProductBos(productCondition));
        model.addAttribute("MaxPage",productService.searchProductGN(productCondition));
        return model;
    }

    /**
     *  根据id搜索商品
     * @param request 需要参数id
     * @return ProductBo List
     */
    @ResponseBody
    @RequestMapping(value="/id",method= RequestMethod.GET)
    public ProductBo searchProductBo(HttpServletRequest request) {
        String idt=request.getParameter("id");
        Long id=Long.parseLong(idt);
        return productService.searchProductBo(id);
    }

    /**
     * 添加商品
     * @param productBo ProductBo对象，需要 ownId, shopId, name, price, categoryId, detail, photoURL
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/add")
    public Result addProduct(@RequestBody ProductBo productBo,
                             @CookieValue(value = "OwnerEmail",defaultValue = "swc") String email) {
        if("swc".equals(email))
            return returnResult();
        return productService.addProduct(productBo);
    }

    /**
     * 删除商品
     * @param productBo ProductBo对象，需要id, photoURL
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Result deleteProduct(@RequestBody ProductBo productBo,
                                @CookieValue(value = "OwnerEmail",defaultValue = "swc") String email) {
        if("swc".equals(email))
            return returnResult();
        return productService.deleteProduct(productBo);
    }

    /**
     * 更新商品
     * @param productBo  ProductBo对象，需要id, name, detail, categoryId, photoURL, price, productPhotoId
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/update")
    public Result updateProduct(@RequestBody ProductBo productBo,
                                @CookieValue(value = "OwnerEmail",defaultValue = "swc") String email) {
        if("swc".equals(email))
            return returnResult();
        return productService.updateProduct(productBo);
    }

    /**
     * 根据Owner查找product
     * @param ownerId 当前owner的ownerId
     * @param page 当前页
     * @param pageNum 每页条目数
     * @return ProductBo List
     */
    @ResponseBody
    @RequestMapping(value="/searchByOwner",method= RequestMethod.GET)
    public List<ProductBo> searchByOwner(@RequestParam long ownerId, @RequestParam int page, @RequestParam int pageNum,
                                         @CookieValue(value = "OwnerEmail",defaultValue = "swc") String email) {
        if("swc".equals(email))
            return new ArrayList<>();
        return productService.searchProductBosByOwner(ownerId,page,pageNum);
    }

    /**
     * 得到当前owner的product数量
     * @param ownerId 当前owner的ownerId
     * @return 存到result.message中
     */
    @ResponseBody
    @RequestMapping(value="/getNum",method= RequestMethod.GET)
    public Result getProductsNum(@RequestParam long ownerId) {
        Result result =new Result();
        result.setMessage(Integer.toString(productService.getOwnerProductCount(ownerId)));
        result.setStatus(1);
        return result;
    }

    public Result returnResult() {
        Result result = new Result();
        result.setStatus(0);
        result.setMessage("you haven't log in");
        return result;
    }
}
