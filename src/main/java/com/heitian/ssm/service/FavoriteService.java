package com.heitian.ssm.service;

import java.util.List;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.ShopBo;

public interface FavoriteService {
	
	public Result addFavoriteProduct(Long productId, Long customerId);
	public List<ProductBo> searchFavoriteProduct();
	
	public Result addFavoriteShop(Long shopId, Long customerId);
	public List<ShopBo> searchFavoriteShop();

}
