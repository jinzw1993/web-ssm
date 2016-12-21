package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.ProductCondition;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.PhotoDao;

import com.heitian.ssm.dao.ProductCommentDao;
import com.heitian.ssm.dao.ProductDao;
import com.heitian.ssm.dao.ShopDao;
import com.heitian.ssm.model.Photo;
import com.heitian.ssm.model.Product;
import com.heitian.ssm.model.ProductComment;
import com.heitian.ssm.model.Shop;
import com.heitian.ssm.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by Lanting on 2016/11/26.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;
    @Resource
    private PhotoDao photoDao;
    @Resource
    private ShopDao shopDao;
    @Resource
    private ProductCommentDao productCommentDao;

    public List<ProductBo> searchProductBos(ProductCondition productCondition) {
        List<ProductBo> productBos = new ArrayList<ProductBo>();
        List<Product> products;
        if (productCondition == null || (productCondition.getKeyWord() == null && productCondition.getCategoryId() == null))
            products = productDao.searchByNone(productCondition.getStart(), productCondition.getNum());
        else
            products = productDao.searchWithKeyword(productCondition);
        return addPhotosAndRates(productBos, products);
    }

    public int searchProductGN(ProductCondition productCondition) {
        int num;
        if (productCondition==null||(productCondition.getKeyWord()==null&&productCondition.getCategoryId()==null))
            num = productDao.searchByNoneGN();
        else
            num=productDao.searchWithKeywordGN(productCondition);
        return num/30+1;
    }

    public ProductBo searchProductBo(Long id) {
        Product product=productDao.searchProductById(id);

        ProductBo productBo=new ProductBo();
        if(product!=null) {
            String path=productDao.searchPhotoURL(product.getId());
            productBo=new ProductBo(product);
            productBo.setPhotoURL(path);
            setRate(productBo);
        }
        return productBo;
    }

    public Result addProduct(ProductBo prdtBo) {

        Photo photo=new Photo();
        photo.setPath(prdtBo.getPhotoURL());
        photoDao.insertPhoto(prdtBo.getPhotoURL());
        long pId=photoDao.selectMaxId();
        prdtBo.setProductPhotoId((long)pId);
        Product product=(Product)prdtBo;
        if(prdtBo.getOwnId() == null)
            return returnRes(0);
        Shop shop = shopDao.selectShopByOwnerId(prdtBo.getOwnId());
        if(shop == null)
            return returnRes(0);
        prdtBo.setShopId(shop.getId());

        int i= productDao.insertProduct(product);
        long pdId= productDao.selectMaxId();
        photo.setId((long)pId);
        photo.setProductId(pdId);
        photoDao.updatePhotoProId(photo);
        return returnRes(i);
    }
    public Result deleteProduct(Long productId) {

        photoDao.deletePhoto(productId);
        int i = productDao.deleteProduct(productId);
        return returnRes(i);
    }
    public Result updateProduct(ProductBo prdtBo) {

        if(prdtBo.getPhotoURL() != null) {
            Photo photo = new Photo();
            photo.setPath(prdtBo.getPhotoURL());
            photo.setProductId(prdtBo.getId());
            photoDao.updatePhoto(photo);
        }
        Product product = (Product)prdtBo;

        int i= productDao.updateProduct(product);

        return returnRes(i);
    }

    public List<ProductBo> searchProductBosByOwner(long ownerId,int page, int pageNum) {
        List<ProductBo> productBos=new ArrayList<>();
        List<Product> products= productDao.searByOwner(ownerId,(page-1)*pageNum,pageNum);
        return addPhotosAndRates(productBos, products);
    }
    public int getOwnerProductCount(Long ownerId) {
        return productDao.getOwnerProductCount(ownerId);
    }

    public List<ProductBo> searchProductBosByOwnerForAd(long ownerId,int page, int pageNum) {
        List<ProductBo> productBos=new ArrayList<>();
        List<Product> products= productDao.searByOwnerForAd(ownerId,(page-1)*pageNum,pageNum);
        return addPhotosAndRates(productBos, products);
    }
    public int getOwnerProductCountForAd(Long ownerId) {
        return productDao.getOwnerProductForAdCount(ownerId);
    }

    public List<ProductBo> searchProductBosByShop(Long id,int page, int pageNum) {
        List<ProductBo> productBos=new ArrayList<>();
        List<Product> products= productDao.searByShop(id,(page-1)*pageNum,pageNum);
        return addPhotosAndRates(productBos, products);
    }
    public int getShopProductCount(Long id) {
        return productDao.getShopProductCount(id);
    }

    private List<ProductBo> addPhotosAndRates(List<ProductBo> productBos, List<Product> products) {
        if(products!=null) {
            for (int i = 0; i < products.size(); i++) {
                String path = productDao.searchPhotoURL(products.get(i).getId());
                ProductBo productBo = new ProductBo(products.get(i));
                productBo.setPhotoURL(path);
                productBos.add(productBo);
                setRate(productBo);
            }
        }
        return productBos;
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

    private void setRate(ProductBo pbo) {
        Double t = productCommentDao.getAvgRate(pbo.getId());
        pbo.setRate(t == null ? 0 : t);
    }
}