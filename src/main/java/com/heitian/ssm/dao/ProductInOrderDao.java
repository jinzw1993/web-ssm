package com.heitian.ssm.dao;

import com.heitian.ssm.bo.ProductInOrderBo;

import java.util.List;

/**
 * Created by oasis on 2016/12/16.
 */
public interface ProductInOrderDao {
    List<ProductInOrderBo> getProductByOrderId(Long id);
}
