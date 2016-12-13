package com.heitian.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.bo.ShopBo;
import com.heitian.ssm.dao.FavoriteProductDao;
import com.heitian.ssm.dao.FavoriteShopDao;
import com.heitian.ssm.dao.ProductDao;
import com.heitian.ssm.dao.ShopDao;
import com.heitian.ssm.model.FavoriteProduct;
import com.heitian.ssm.model.FavoriteShop;
import com.heitian.ssm.model.Product;
import com.heitian.ssm.model.Shop;
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

	@Override
	public Result addFavoriteProduct(Long productId, Long customerId) {
		FavoriteProduct favoriteProduct = new FavoriteProduct();
		favoriteProduct.setCustomerId(customerId);
		favoriteProduct.setProductId(productId);
		favoriteProduct.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
		int i = favoriteProductDao.insertFavoriteProduct(favoriteProduct);
		return returnRes(i);
	}

	@Override
	public List<ProductBo> searchFavoriteProduct() {
		List<ProductBo> productBos = new ArrayList<ProductBo>();
		List<FavoriteProduct> favoriteProducts = favoriteProductDao.searchFavoriteProduct();
		if(favoriteProducts != null) {
			for(FavoriteProduct favoriteProduct : favoriteProducts) {
				Long productId = favoriteProduct.getProductId();
				Product product = productDao.searchProductById(productId);
				ProductBo pb = new ProductBo(product);
				String photoURL = productDao.searchPhotoURL(product.getProductPhotoId());
				pb.setPhotoURL(photoURL);
				productBos.add(pb);
			}
		}
		return productBos;
	}
	
	@Override
	public Result addFavoriteShop(Long shopId, Long customerId) {
		FavoriteShop favoriteShop = new FavoriteShop();
		favoriteShop.setCustomerId(customerId);
		favoriteShop.setShopId(shopId);
		favoriteShop.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
		int i = favoriteShopDao.insertFavoriteShop(favoriteShop);
		return returnRes(i);
	}

	@Override
	public List<ShopBo> searchFavoriteShop() {
		List<ShopBo> shopBos = new ArrayList<ShopBo>();
		List<FavoriteShop> favoriteShops = favoriteShopDao.searchFavoriteShop();
		if(favoriteShops != null) {
			for(FavoriteShop favoriteShop : favoriteShops) {
				Long shopId = favoriteShop.getShopId();
				Shop shop = shopDao.selectShopByOwnerId(shopId);
				ShopBo shopBo = new ShopBo(shop);
				shopBos.add(shopBo);
			}
		}
		return shopBos;
	}
	
	private Result returnRes(int i) {
        Result result = new Result();
        if(i!=0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setMessage("failed");
            result.setStatus(0);
        }
        return result;
    }

}
