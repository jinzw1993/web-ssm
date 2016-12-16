package com.heitian.ssm.service;

import java.sql.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.ProductAd;

/**
 * Created by oasis on 12/11/16.
 */
public interface ProductAdService {
	Result addProductAd(Long proId,Date date);
	
	Result rejectProductAd(Long proId);
	
	Result agreeProductAd(Long proId);

	List<ProductAd> showProductAd();

	Result deleteProductAd(Long id);

	List<ProductAd> applyProductAd();
}
