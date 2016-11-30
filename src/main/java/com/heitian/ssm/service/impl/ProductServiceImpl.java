package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.ProductCondition;
<<<<<<< HEAD
import com.heitian.ssm.bo.Result;
=======
>>>>>>> origin/owner-dev
import com.heitian.ssm.dao.ProductDao;
import com.heitian.ssm.model.Product;
import com.heitian.ssm.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Lanting on 2016/11/26.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;

    public List<ProductBo> searchProductBos(ProductCondition productCondition)
    {
        List<ProductBo> productBos=new ArrayList<ProductBo>();
        List<Product> products;
        if (productCondition==null||(productCondition.getKeyWord()==null&&productCondition.getCategoryId()==null))
            products = productDao.searchByNone(productCondition.getStart(), productCondition.getNum());
        else
            products = productDao.searchWithKeyword(productCondition);
<<<<<<< HEAD
        if(products!=null) {
            for(int i=0;i<products.size();i++)
            {
                String path=productDao.searchPhotoURL(products.get(i).getProductPhotoId());
                ProductBo productBo=new ProductBo(products.get(i));
                productBo.setPhotoURL(path);
                productBos.add(productBo);
            }
=======
        for(int i=0;i<products.size();i++)
        {
            String path=productDao.searchPhotoURL(products.get(i).getProductPhotoId());
            ProductBo productBo=new ProductBo(products.get(i));
            productBo.setPhotoURL(path);
            productBos.add(productBo);
>>>>>>> origin/owner-dev
        }
        return productBos;
    }

    public ProductBo searchProductBo(Long id) {
        Product product=productDao.searchProductById(id);
<<<<<<< HEAD
        ProductBo productBo=new ProductBo();
        if(product!=null) {
            String path=productDao.searchPhotoURL(product.getId());
            productBo=new ProductBo(product);
            productBo.setPhotoURL(path);
        }
        return productBo;
    }

    public Result addProduct(ProductBo prdtBo) {
        Result result = new Result();
        //get photo id
        //int i= photoDao.insertPhoto(prdtBo.getPhotoURL());
        //long picId=phhotoDao.getPhotoId(url);
        long picId=0;
        prdtBo.setProductPhotoId(picId);
        Product product = (Product)prdtBo;
        int i = productDao.insertProduct(product);


        if(i!=0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setMessage("failed");
            result.setStatus(0);
        }
        return result;

    }
    public Result deleteProduct(ProductBo prdtBo) {
        Result result = new Result();
        Product product = (Product)prdtBo;

        //delete photo
        //int j=photoDao.deletePhoto(product.getProductPhotoId());

        int i = productDao.deleteProduct(product);
        if(i!=0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setMessage("failed");
            result.setStatus(0);
        }
        return result;
    }
    public Result updateProduct(ProductBo prdtBo) {
        Result result = new Result();
        Product product = (Product)prdtBo;
        //update photo
        //int j = photoDao.updatePhoto(product.getProductPhotoId(), prdtBo.getPhotoURL());

        int i= productDao.updateProduct(product);

        if(i!=0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setMessage("failed");
            result.setStatus(0);
        }
        return result;
=======
        String path=productDao.searchPhotoURL(product.getId());
        ProductBo productBo=new ProductBo(product);
        productBo.setPhotoURL(path);
        return productBo;
>>>>>>> origin/owner-dev
    }
}