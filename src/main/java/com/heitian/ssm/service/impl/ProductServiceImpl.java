package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.ProductDao;
import com.heitian.ssm.model.Product;
import com.heitian.ssm.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by unname on 2016/11/27.
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Resource
    private ProductDao productDao;

    public boolean insertProduct(Product product) {
        return productDao.insertProduct(product)!=null;
    }

    public boolean deleteProductById( long id) {
        return productDao.deleteProductById(id)>0;
    }

    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product)>0;
    }

    public List<Product> selectByName(String name) {
        return productDao.selectByName(name);
    }
    public List<Product> selectByShopId(long id) {
        return productDao.selectByShopId(id);
    }
    public List<Product> selectByShopName(String name) {
        return productDao.selectByShopName(name);
    }
    public List<Product> selectByBrand( String brand) {
        return productDao.selectByBrand(brand);
    }
    public List<Product> selectByCategory( String category) {
        return productDao.selectByCategory(category);
    }
}
