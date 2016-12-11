package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.ProductAdDao;
import com.heitian.ssm.dao.ProductDao;
import com.heitian.ssm.service.ProductAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by oasis on 12/11/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductAdServiceImpl implements ProductAdService {
    @Autowired
    private ProductAdDao productAdDao;
    @Autowired
    private ProductDao productDao;

    private Result result = new Result();

    @Override
    public Result addProductAd(Long id) {
        if(productDao.searchProductById(id) == null ) {
            result.setMessage("product doesn't exist");
            result.setStatus(1);
        }
        if(productAdDao.addProductAd(id, new Date()) > 0) {
            result.setMessage("success");
            result.setStatus(0);
        } else {
            result.setMessage("failed");
            result.setStatus(1);
        }
        return result;
    }
}
