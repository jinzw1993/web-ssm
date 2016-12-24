package com.heitian.ssm.service;

import com.heitian.ssm.bo.ProductCommentBo;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.ProductComment;

import java.util.List;

/**
 * Created by unname on 2016/12/13.
 */
public interface ProductCommentService {
    List<ProductCommentBo> getProductCommentBos(Long productId, int page, int pageNum);

    Result getCommentNum(Long productId);

    Result addComment(ProductComment productCommentBo);
}
