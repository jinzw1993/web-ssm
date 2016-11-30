package com.heitian.ssm.service;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.ProductCondition;
<<<<<<< HEAD
import com.heitian.ssm.bo.Result;
=======
>>>>>>> origin/owner-dev
import com.heitian.ssm.model.Product;

import java.util.List;

/**
 * Created by Lanting on 2016/11/26.
 */
public interface ProductService {
    List<ProductBo> searchProductBos(ProductCondition productCondition);
    ProductBo searchProductBo(Long id);
<<<<<<< HEAD

    Result addProduct(ProductBo product);
    Result deleteProduct(ProductBo product);
    Result updateProduct(ProductBo product);
}
=======
}
>>>>>>> origin/owner-dev
