package com.heitian.ssm.controller;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.ProductComment;
import com.heitian.ssm.service.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by oasis on 12/13/16.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ProductCommentService productCommentService;

    @RequestMapping("/list")
    public @ResponseBody
    List<ProductComment> getProductComment(@RequestParam("id") Long id,
                                           @RequestParam("page") int page,
                                           @RequestParam("count") int count) {
        return productCommentService.getProductComment(id, page, count);
    }

    @RequestMapping("/num")
    public @ResponseBody
    Result getProductCommentNum(@RequestParam("id") Long id) {
        return productCommentService.getCommentNum(id);
    }
}
