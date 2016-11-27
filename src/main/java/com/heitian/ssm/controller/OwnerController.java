package com.heitian.ssm.controller;

import com.heitian.ssm.service.OwnerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by unname on 2016/11/28.
 */
@Controller
@RequestMapping("/Owner")
public class OwnerController {
    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private OwnerService ownerService;

    @RequestMapping("/")


}
