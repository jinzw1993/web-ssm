package com.heitian.ssm.controller;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.ProductCondition;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.service.ProductService;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
     * @param productCondition 需要page(默认1), num(默认30), 和其他任意ProductCondition的属性
     * @return ProductBo List
     */
    @ResponseBody
    @RequestMapping("/search")
    public List<ProductBo> searchProductBos(@RequestBody ProductCondition productCondition) {
        return productService.searchProductBos(productCondition);
    }

    /**
     *  根据id搜索商品
     * @param request 需要id
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
     * @param productBo 需要 ownId, shopId, name, price, categoryId, detail, photoURL
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/add")
    public Result addProduct(@RequestBody ProductBo productBo) {
        return productService.addProduct(productBo);
    }

    /**
     * 删除商品
     * @param productBo 需要id
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Result deleteProduct(@RequestBody ProductBo productBo) {
        return productService.deleteProduct(productBo);
    }

    /**
     * 更新商品
     * @param productBo  需要id, name, detail, categoryId, photoURL, price, productPhotoId
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/update")
    public Result updateProduct(@RequestBody ProductBo productBo) {
        return productService.updateProduct(productBo);
    }
}
