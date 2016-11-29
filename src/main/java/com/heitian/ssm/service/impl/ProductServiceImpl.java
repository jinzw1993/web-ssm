package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.ProductBo;
import com.heitian.ssm.bo.ProductCondition;
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
        for(int i=0;i<products.size();i++)
        {
            String path=productDao.searchPhotoURL(products.get(i).getProductPhotoId());
            ProductBo productBo=new ProductBo(products.get(i));
            productBo.setPhotoURL(path);
            productBos.add(productBo);
        }
        return productBos;
    }

    public ProductBo searchProductBo(Long id) {
        Product product=productDao.searchProductById(id);
        String path=productDao.searchPhotoURL(product.getId());
        ProductBo productBo=new ProductBo(product);
        productBo.setPhotoURL(path);
        return productBo;
    }
}
