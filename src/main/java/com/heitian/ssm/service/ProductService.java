package com.heitian.ssm.service;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.ProductCondition;
import com.heitian.ssm.bo.Result;


import java.util.List;

/**
 * Created by Lanting on 2016/11/26.
 */
public interface ProductService {
    List<ProductBo> searchProductBos(ProductCondition productCondition);
    ProductBo searchProductBo(Long id);
    int searchProductGN(ProductCondition productCondition);

    Result addProduct(ProductBo product);
    Result deleteProduct(Long productId);
    Result updateProduct(ProductBo product);
    List<ProductBo> searchProductBosByOwner(long ownerId,int page, int pageNum);
    int getOwnerProductCount(Long ownerId);
    List<ProductBo> searchProductBosByOwnerForAd(long ownerId,int page, int pageNum);
    int getOwnerProductCountForAd(Long ownerId);
    List<ProductBo> searchProductBosByShop(Long shopId, int page, int count);
    int getShopProductCount(Long shopId);
}
