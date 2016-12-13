package com.heitian.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.heitian.ssm.model.FavoriteShop;

public interface FavoriteShopDao {
	
	public int insertFavoriteShop(@Param("favoriteShop") FavoriteShop favoriteShop);
	public List<FavoriteShop> searchFavoriteShop();

}
