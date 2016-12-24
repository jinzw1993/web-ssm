package com.heitian.ssm.service.impl;

import com.heitian.ssm.bo.ProductCommentBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.ProductCommentDao;
import com.heitian.ssm.model.ProductComment;
import com.heitian.ssm.service.ProductCommentService;
import com.heitian.ssm.util.ResultResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by unname on 2016/12/13.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductCommentServiceImpl implements ProductCommentService {
    @Resource
    private ProductCommentDao productCommentDao;

    public List<ProductCommentBo> getProductCommentBos(Long productId, int page, int pageNum) {
        return productCommentDao.getCommentBos(productId, (page - 1) * pageNum, pageNum);
    }

    public Result getCommentNum(Long productId) {
        Result result = new Result();
        result.setStatus(1);
        result.setMessage(String.valueOf(productCommentDao.getCommentNum(productId)));
        return result;
    }

    /**
     * 添加评论，先判断商品是否已评论
     *
     * @param comment
     * @return
     */
    public Result addComment(ProductComment comment) {
        ProductComment pm = productCommentDao.getCommentByPidOid(comment.getProductId(), comment.getOrderId());
        Result result = new Result();
        if (pm != null) {
            result.setMessage("has commented");
            result.setStatus(0);
            return result;
        }
        return ResultResolver.returnRes(productCommentDao.addComment(comment));
    }
}
