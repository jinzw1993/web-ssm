package com.heitian.ssm.service;

import java.sql.Date;
import java.util.List;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.ProductAd;

/**
 * Created by oasis on 12/11/16.
 */
public interface ProductAdService {
	Result addProductAd(Long proId,Date date);

	List<ProductAd> showProductAd();

	Result deleteProductAd(Long id);

	List<ProductAd> applyProductAd();
}
