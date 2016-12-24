package com.heitian.ssm.dao;

import com.heitian.ssm.bo.ProductAdBo;
import com.heitian.ssm.model.ProductAd;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by oasis on 12/11/16.
 */
@Repository
public interface ProductAdDao {
    //修改
    //Owner Part
    int addProductAd(@Param(value = "productId") Long proId, @Param("price") Double price);//status:0 rank:0 date:now()

    List<ProductAdBo> getProductAdByOwnerId(@Param("ownerId") Long ownerId,
                                            @Param("start") int start, @Param("pageNum") int pageNum);//未得到photoURL

    int getProductAdNumByOwnerId(@Param("ownerId") Long ownerId);

    int deleteProductAd(@Param("id") Long id);

    ProductAd getProductAdByProductId(@Param("productId")Long productId);
    //Admin Part
    List<ProductAdBo> getPermittedProductAd();//未得到photoURL

    int getPermittedPAdNum();

    int updateProductAdStatus(@Param(value = "id") Long id, @Param("status") Long status);

    List<ProductAdBo> getUnverifiedProductAd(@Param("start") int start, @Param("pageNum") int pageNum);//未得到photoURL

    int getUnverifiedPAdNum();
}