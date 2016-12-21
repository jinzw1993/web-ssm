package com.heitian.ssm.dao;

import com.heitian.ssm.bo.ProductInOrderBo;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.heitian.ssm.bo.ProductInOrderBo;
import com.heitian.ssm.model.ProductInOrder;

/**
 * Created by oasis on 2016/12/16.
 */
@Repository
public interface ProductInOrderDao {
    List<ProductInOrderBo> getProductByOrderId(Long id);
    
    public int insertProductInOrder(@Param("productInOrder") ProductInOrder productInOrder);
}
