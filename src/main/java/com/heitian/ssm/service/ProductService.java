package com.heitian.ssm.service;

import com.heitian.ssm.model.Product;


import java.util.List;

/**
 * Created by unname on 2016/11/27.
 */
public interface ProductService {
    public boolean insertProduct(Product product);

    public boolean deleteProductById( long id);

    public boolean updateProduct(Product product);

    public List<Product> selectByName( String name);
    public List<Product> selectByShopId(long id);
    public List<Product> selectByShopName(String name);
    public List<Product> selectByBrand( String brand);
    public List<Product> selectByCategory( String category);
}
