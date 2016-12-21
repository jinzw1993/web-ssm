package com.heitian.ssm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.MallConfig;
import com.heitian.ssm.service.MallConfigService;

@Controller
@RequestMapping("/mallConfig")
@Transactional(rollbackOn = Exception.class)
public class MallConfigController {
	
private Logger log = Logger.getLogger(AdminCustomerController.class);
	
	@Resource
	private MallConfigService mcs;
	
	@ResponseBody
	@RequestMapping("/add")
	public Result add(HttpServletRequest request){
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		MallConfig mallConfig = new MallConfig(key, value);
		return mcs.addMallConfig(mallConfig);
	}
	
	@ResponseBody
	@RequestMapping("/get")
	public MallConfig getMallConfigByKey(HttpServletRequest request){
		String key = request.getParameter("key");				
		return mcs.getMallConfigByKey(key);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Result update(HttpServletRequest request){
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		MallConfig mallConfig = new MallConfig(key, value);
		return mcs.updateMallConfig(mallConfig);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Result delete(HttpServletRequest request){
		String key = request.getParameter("key");
		return mcs.deleteMallConfig(key);
	}

}
