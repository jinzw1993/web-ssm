package com.heitian.ssm.service.impl;

import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.ProductAdDao;
import com.heitian.ssm.model.ProductAd;
import com.heitian.ssm.service.ProductAdService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductAdServiceImpl implements ProductAdService {

	@Resource
	private ProductAdDao productAdDao;

	@Override
	public List<ProductAd> showProductAd()
	{

		List<ProductAd> product = productAdDao.showProductAd();
		if (product == null || product.size() == 0) {
			return null;
		}
		return product;
	}

	@Override
	public Result deleteProductAd(Long id)
	{
		int i = productAdDao.deleteProductAd(id);
		return returnRes(i);
	}

	@Override
	public List<ProductAd> applyProductAd()
	{
		List<ProductAd> product = productAdDao.applyProductAd();
		if (product == null || product.size() == 0) {
			return null;
		}

		return product;
	}

	@Override
	public Result addProductAd(Long proId, Date date)
	{
		int i = productAdDao.addProductAd(proId, date);
		return returnRes(i);
	}

	private Result returnRes(int i)
	{
		Result result = new Result();
		if (i != 0) {
			result.setStatus(1);
			result.setMessage("success");
		} else {
			result.setMessage("failed");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public Result rejectProductAd(Long proId)
	{
		int i = productAdDao.rejectProductAd(proId);
		return returnRes(i);
	}

	@Override
	public Result agreeProductAd(Long proId)
	{
		int i = productAdDao.agreeProductAd(proId);
		return returnRes(i);
	}

}
