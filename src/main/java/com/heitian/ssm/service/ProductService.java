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

    Result addProduct(ProductBo product);
    Result deleteProduct(ProductBo product);
    Result updateProduct(ProductBo product);
}
