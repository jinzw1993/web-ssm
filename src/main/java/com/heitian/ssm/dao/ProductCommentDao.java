package com.heitian.ssm.dao;

import com.heitian.ssm.bo.ProductCommentBo;
import com.heitian.ssm.model.ProductComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by unname on 2016/12/13.
 */
@Repository
public interface ProductCommentDao {
    List<ProductCommentBo> getCommentBos(@Param("productId") Long productId, @Param("start") int start, @Param("pageNum") int pageNum);
    int getCommentNum(Long productId);
    Double getAvgRate(Long productId);
}
