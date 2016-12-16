package com.heitian.ssm.dao;

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
	int addProductAd(@Param(value = "productId") Long proId, @Param(value = "date") Date date);

	int deleteProductAd(@Param(value = "productId") Long proId);

	List<ProductAd> showProductAd();

	List<ProductAd> applyProductAd();
}