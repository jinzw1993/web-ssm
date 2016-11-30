package com.heitian.ssm.controller;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.ProductCondition;
import com.heitian.ssm.model.Product;
import com.heitian.ssm.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.enterprise.inject.Model;
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

    @ResponseBody
    @RequestMapping("/search")
    public List<ProductBo> searchProductBos(@RequestBody ProductCondition productCondition) {
        return productService.searchProductBos(productCondition);
    }

    @ResponseBody
    @RequestMapping(value="/id",method= RequestMethod.GET)
    public ProductBo searchProductBo(HttpServletRequest request) {
        String idt=request.getParameter("id");
        Long id=Long.parseLong(idt);
        return productService.searchProductBo(id);
    }
}
