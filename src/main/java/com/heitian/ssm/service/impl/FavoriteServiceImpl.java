package com.heitian.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.heitian.ssm.dao.*;
import com.heitian.ssm.model.*;
import com.heitian.ssm.util.ResultResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.ShopBo;
import com.heitian.ssm.service.FavoriteService;

@Service
@Transactional(rollbackFor = Exception.class)
public class FavoriteServiceImpl implements FavoriteService {
	
	@Resource
	private FavoriteProductDao favoriteProductDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private FavoriteShopDao favoriteShopDao;
	@Resource
	private ShopDao shopDao;
    @Resource
    private ProductCommentDao productCommentDao;

	@Override
	public Result deleteFavoriteProduct(Long productId, Long customerId) {
		FavoriteProduct favoriteProduct = favoriteProductDao.searchFavoriteProductByCidAndPid(productId, customerId);
		if(favoriteProduct == null) { 
			Result result = new Result();
			result.setStatus(0);
			result.setMessage("You have been deleted");
			return result;	
		} else {
			int i = favoriteProductDao.deleteFavoriteProduct(productId, customerId);
			return ResultResolver.returnRes(i);
		}
	}
	
	@Override
	public Result addFavoriteProduct(Long productId, Long customerId) {
		FavoriteProduct favoriteProduct = favoriteProductDao.searchFavoriteProductByCidAndPid(productId, customerId);
		if(favoriteProduct != null) {			
			Result result = new Result();
			result.setStatus(0);
			result.setMessage("You have been added");
			return result;						
		} else {
			FavoriteProduct fp = new FavoriteProduct();
			fp.setCustomerId(customerId);
			fp.setProductId(productId);
			fp.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
			int i = favoriteProductDao.insertFavoriteProduct(fp);
			return ResultResolver.returnRes(i);
		}
		
	}

	@Override
	public List<ProductBo> searchFavoriteProduct(Long customerId) {
		List<ProductBo> productBos = new ArrayList<ProductBo>();
		List<FavoriteProduct> favoriteProducts = favoriteProductDao.searchFavoriteProduct(customerId);
		if(favoriteProducts != null && favoriteProducts.size() > 0) {
			for(FavoriteProduct favoriteProduct : favoriteProducts) {
				
				Long productId = favoriteProduct.getProductId();
				Product product = productDao.searchProductById(productId);
				ProductBo pb = new ProductBo(product);
				String photoURL = productDao.searchPhotoURL(product.getId());
				pb.setPhotoURL(photoURL);
                setRate(pb);

				productBos.add(pb);
			}
		}
		return productBos;
	}
	
	@Override
	public Result addFavoriteShop(Long shopId, Long customerId) {
		
		FavoriteShop favoriteShop = favoriteShopDao.searchFavoriteShopByCidAndSid(shopId, customerId);	
		
		if(favoriteShop != null) {		
			Result result = new Result();
			result.setStatus(0);
			result.setMessage("You have been added");
			return result;						
		} else {			
			FavoriteShop fs = new FavoriteShop();
			fs.setCustomerId(customerId);
			fs.setShopId(shopId);
			fs.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
			int i = favoriteShopDao.insertFavoriteShop(fs);
			return ResultResolver.returnRes(i);
		}
		
	}

	@Override
	public List<ShopBo> searchFavoriteShop(Long customerId) {
		List<ShopBo> shopBos = new ArrayList<ShopBo>();
		List<FavoriteShop> favoriteShops = favoriteShopDao.searchFavoriteShop(customerId);
		if(favoriteShops != null) {
			for(FavoriteShop favoriteShop : favoriteShops) {
				
				Long shopId = favoriteShop.getShopId();
				Shop shop = shopDao.selectShopById(shopId);
				ShopBo shopBo = new ShopBo(shop, shopDao.selectUrlByOwnerId(shop.getOwnerId()));
				shopBos.add(shopBo);
			}
		}
		return shopBos;
	}
	
	@Override
	public Result deleteFavoriteShop(Long shopId, Long customerId) {
		FavoriteShop favoriteShop = favoriteShopDao.searchFavoriteShopByCidAndSid(shopId, customerId);
		if(favoriteShop == null) { 
			Result result = new Result();
			result.setStatus(0);
			result.setMessage("You have been deleted");
			return result;	
		} else {
			int i = favoriteShopDao.deleteFavoriteShop(shopId, customerId);
			return ResultResolver.returnRes(i);
		}
	}

	private void setRate(ProductBo pbo) {
        Double t = productCommentDao.getAvgRate(pbo.getId());
		pbo.setRate(t == null ? 0 : t);
	}
}
