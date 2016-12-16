package com.heitian.ssm.dao;

import com.heitian.ssm.model.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 11/27/16.
 */
@Repository
public interface ShopDao {
    Shop selectShopByName(String name);

    Shop selectShopByOwnerId(Long id);

    List<Shop> selectShopsByName(String name);

    List<Shop> selectShops(@Param("start") int start,@Param("count") int count,@Param("status") Long status);

    List<Shop> selectAllShops(@Param("start") int start,@Param("count") int count);

    int updateShop(Shop shop);

    int updateStatus(@Param("id") Long id, @Param("status") Long status);

    int insertShop(Shop shop);

    String selectUrlByOwnerId(Long id);

    int selectCount(Long status);
}
