package com.heitian.ssm.dao;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.ProductCondition;
import com.heitian.ssm.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lanting on 2016/11/26.
 */
@Repository
public interface ProductDao {
    List<Product> searchByNone(@Param("start")int start, @Param("num")int num);
    int searchByNoneGN();
    List<Product> searchWithKeyword(ProductCondition productCondition);
    int searchWithKeywordGN(ProductCondition productCondition);

    List<Product> searByOwner(@Param("ownerId") Long ownerId,@Param("start") int start,@Param("pageNum") int pageNum);
    Integer getOwnerProductCount(Long ownerId);

    String searchPhotoURL(Long productPhotoId);
    Product searchProductById(Long id);

    int insertProduct(@Param("product") Product product);
    int deleteProduct(@Param("product") Product product);
    int updateProduct(@Param("product") Product product);
    int selectMaxId();
}

