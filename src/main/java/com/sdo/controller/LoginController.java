package com.sdo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return "{\"msg\":\"success\"}";
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

}
