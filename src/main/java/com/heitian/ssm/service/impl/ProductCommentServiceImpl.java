package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.ProductCommentDao;
import com.heitian.ssm.model.ProductComment;
import com.heitian.ssm.service.ProductCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by unname on 2016/12/13.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductCommentServiceImpl implements ProductCommentService{
    @Resource
    private ProductCommentDao productCommentDao;

    public List<ProductComment> getProductComment(Long productId, int page, int pageNum) {
        return productCommentDao.getComment(productId, (page - 1) * pageNum, pageNum);
    }

    public int getCommentNum(Long productId) {
        return productCommentDao.getCommentNum(productId);
    }
}
