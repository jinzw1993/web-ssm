package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.ProductCondition;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.PhotoDao;

import com.heitian.ssm.dao.ProductDao;
import com.heitian.ssm.model.Photo;
import com.heitian.ssm.model.Product;
import com.heitian.ssm.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

/**
 * Created by Lanting on 2016/11/26.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;
    private PhotoDao photoDao;

    public List<ProductBo> searchProductBos(ProductCondition productCondition)
    {
        List<ProductBo> productBos=new ArrayList<ProductBo>();
        List<Product> products;
        if (productCondition==null||(productCondition.getKeyWord()==null&&productCondition.getCategoryId()==null))
            products = productDao.searchByNone(productCondition.getStart(), productCondition.getNum());
        else
            products = productDao.searchWithKeyword(productCondition);

        if(products!=null) {
            for (int i = 0; i < products.size(); i++) {
                String path = productDao.searchPhotoURL(products.get(i).getProductPhotoId());
                ProductBo productBo = new ProductBo(products.get(i));
                productBo.setPhotoURL(path);
                productBos.add(productBo);
            }
        }
        return productBos;
    }

    public ProductBo searchProductBo(Long id) {
        Product product=productDao.searchProductById(id);

        ProductBo productBo=new ProductBo();
        if(product!=null) {
            String path=productDao.searchPhotoURL(product.getId());
            productBo=new ProductBo(product);
            productBo.setPhotoURL(path);
        }
        return productBo;
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
    public Result addProduct(ProductBo prdtBo) {

        Photo photo=new Photo();
        photo.setPath(prdtBo.getPhotoURL());
     //   photoDao.insertPhoto(prdtBo.getPhotoURL());
    //    long pId=photoDao.selectMaxId();

        prdtBo.setProductPhotoId((long)1);

        Product product=(Product)prdtBo;
        int i= productDao.insertProduct(product);
        return returnRes(i);

    }
    public Result deleteProduct(ProductBo prdtBo) {

        photoDao.deletePhoto(prdtBo.getId(),prdtBo.getPhotoURL());

        Product product=(Product)prdtBo;
        int i = productDao.deleteProduct(product);
        return returnRes(i);
    }
    public Result updateProduct(ProductBo prdtBo) {

        Date date=new Date();

        prdtBo.setModifiedAt(new java.sql.Time(date.getTime()));

        Photo photo = new Photo();
        photo.setPath(prdtBo.getPhotoURL());
        photo.setProductId(prdtBo.getId());
        photo.setId(prdtBo.getProductPhotoId());
        photoDao.updatePhoto(photo);
        Product product = (Product)prdtBo;

        int i= productDao.updateProduct(product);

        return returnRes(i);


    }
}