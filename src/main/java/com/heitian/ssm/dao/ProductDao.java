package com.heitian.ssm.dao;

import com.heitian.ssm.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by unname on 2016/11/27.
 */
@Repository
public interface ProductDao {
    public Object insertProduct(@Param("product")Product product);

    public int deleteProductById(@Param("id") long id);

    public int updateProduct(@Param("product")Product product);

 //   public Product  selectById(@Param("productId") long id);
    public List<Product> selectByName(@Param("name") String name);
    public List<Product> selectByShopId(@Param("shopId") long id);
    public List<Product> selectByShopName(@Param("shopName") String name);
    public List<Product> selectByBrand(@Param("brand") String brand);
    public List<Product> selectByCategory(@Param("category") String category);
}
