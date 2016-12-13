package com.heitian.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heitian.ssm.model.FavoriteProduct;
import com.heitian.ssm.model.Product;

@Repository
public interface FavoriteProductDao {
	
	public int insertFavoriteProduct(@Param("favoriteProduct") FavoriteProduct favoriteProduct);
	public List<FavoriteProduct> searchFavoriteProduct();

}
