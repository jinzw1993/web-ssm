package com.heitian.ssm.service;

import java.util.List;

import com.heitian.ssm.bo.ProductAdBo;
import com.heitian.ssm.bo.Result;


/**
 * Created by oasis on 12/11/16.
 */
public interface ProductAdService {
    //changed
    //Admin Part
    List<ProductAdBo> getVerifiedProductAd();

    Result getVerifiedPAdNum();

    Result updateProductAdStatus(Long id, Long status);

    List<ProductAdBo> getUnverifiedProductAd(int page, int pageNum);

    Result getUnverifiedPAdNum();

    //Owner Part
    Result addProductAd(Long productId, Double price);

    List<ProductAdBo> getProductAdByOwnerId(Long ownerId, int page, int pageNum);

    Result getProductAdNumByOwnerId(Long ownerId);

    Result deleteProductAd(Long id);
}
