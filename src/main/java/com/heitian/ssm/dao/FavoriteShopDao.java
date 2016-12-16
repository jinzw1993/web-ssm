package com.heitian.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heitian.ssm.model.FavoriteShop;
@Repository
public interface FavoriteShopDao {
	
	public int insertFavoriteShop(@Param("favoriteShop") FavoriteShop favoriteShop);
	public List<FavoriteShop> searchFavoriteShop();
	public FavoriteShop searchFavoriteShopBySidAndPid(@Param("shopId") Long shopId, @Param("customerId") Long customerId);
	public int deleteFavoriteShop(@Param("shopId") Long shopId, @Param("customerId") Long customerId);

}
