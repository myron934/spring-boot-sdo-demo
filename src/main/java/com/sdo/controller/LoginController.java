package com.sdo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sdo.annotation.RequestLog;
import com.sdo.entity.ResmsEntity;
import com.sdo.mapper.global.ResmsMapper;

@RestController
@RequestMapping(value="user")

public class LoginController {
	@Autowired
	ResmsMapper resmsMapper;
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestParam("userId") String userId) {
        return "{\"msg\":\"成功了\"}";
    }
	@RequestMapping(value="/login2", produces = "application/json;charset=UTF-8")
	@RequestLog(forbidden="true")
	public String login2(HttpServletRequest request) {
//		request.getLocalAddr();
		logger.info((String)request.getParameter("userId"));
		logger.info(request.getServerName());
		return request.getLocalAddr();
	}
	
	@RequestMapping(value="/logout", method={ RequestMethod.GET,RequestMethod.POST })
    public String logout(@RequestParam("userId") String userId) {
		System.out.println(userId);
        return "{\"msg\":\"success\"}";
    }
	
	@RequestMapping(value="/info", method={ RequestMethod.GET,RequestMethod.POST })
    public String resmsInfo() {
		List<ResmsEntity> list=resmsMapper.listAll();
		for(ResmsEntity resms:list){
//			System.out.println(resms);
			logger.debug(resms.toString());
		}
		return "success";
		
    }
	@RequestMapping(value="/insert", method={ RequestMethod.GET,RequestMethod.POST })
	@Transactional
	public String insert() {
		ResmsEntity resms = new ResmsEntity();
		resms.setResmsNumber(10690079);
		resms.setTelecomName("中国微信");
		resms.setTelecom("10001");
		resms.setTelecomStatus(1);
		resmsMapper.insert(resms);
		resms.setResmsNumber(10690079);
		resms.setTelecomName("中国X信");
		resms.setTelecom("100011");
		resms.setTelecomStatus(1);
		resmsMapper.insert(resms);
//		int[] arr=new int[1];
//		System.out.println(arr[2]);
		return "success";
		
	}

}
