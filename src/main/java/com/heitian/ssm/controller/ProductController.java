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
    public List<ProductBo> searchProductBos(@RequestBody ProductCondition productCondition) {
        return productService.searchProductBos(productCondition);
    }
    /**
     * 根据条件搜索商品数目
     * @param productCondition ProductCondition对象
     * @return int
     */
    @ResponseBody
    @RequestMapping("/searchMaxPage")
    public int searchProductMaxPage(@RequestBody ProductCondition productCondition) {
        return productService.searchProductGN(productCondition);
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
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping(value ="/add", method=RequestMethod.GET)
    public Result addProduct(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return returnResult();
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        ProductBo productBo = new ProductBo();
        productBo.setName(request.getParameter("name"));
        productBo.setCategoryId(Long.valueOf(request.getParameter("categoryId")));
        productBo.setPhotoURL(request.getParameter("photoURL"));
        productBo.setDetail(request.getParameter("detail"));
        productBo.setPrice(Long.valueOf(request.getParameter("price")));

        productBo.setOwnId(Long.valueOf(ownerId));
        return productService.addProduct(productBo);
    }

    /**
     * 删除商品
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Result deleteProduct(@RequestParam Long id ,
                                HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return returnResult();
        return productService.deleteProduct(id);
    }

    /**
     * 更新商品
     * @param productBo  ProductBo对象，需要id, name, detail, categoryId, photoURL, price, productPhotoId
     * @return result.status=0失败，1成功
     */
    @ResponseBody
    @RequestMapping("/update")
    public Result updateProduct(@RequestBody ProductBo productBo,
                                HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return returnResult();
        return productService.updateProduct(productBo);
    }

    /**
     * 根据Owner查找product

     * @return ProductBo List
     */
    @ResponseBody
    @RequestMapping(value="/searchByOwn",method= RequestMethod.GET)
    public List<ProductBo> searchByOwner(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return new ArrayList<>();
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));
        return productService.searchProductBosByOwner(Long.valueOf(ownerId),1,40);
    }

    /**
     * 得到当前owner的product数量
     * @return 存到result.message中
     */
    @ResponseBody
    @RequestMapping(value="/getNum",method= RequestMethod.GET)
    public Result getProductsNum(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null)
            return new Result();
        String ownerId = auth.substring(auth.indexOf("Id=") + 3, auth.indexOf(";"));

        Result result =new Result();
        result.setMessage(Integer.toString(productService.getOwnerProductCount(Long.valueOf(ownerId))));
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
