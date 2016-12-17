package com.heitian.ssm.dao;

import com.heitian.ssm.bo.ProductInOrderBo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oasis on 2016/12/16.
 */
@Repository
public interface ProductInOrderDao {
    List<ProductInOrderBo> getProductByOrderId(Long id);
}
