package com.inesv.digiccy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.PushInfoValidata;

@Controller
@RequestMapping("pushInfo")
public class PushInfoController {

	@Autowired
	PushInfoValidata pushInfoValidata;

	// 设置用户推送区间
	public Map<String, Object> setSectionOfPush(int userNo, float maxPrice, float minPrice, Boolean isPush){
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}
	// 设置driverToken
	@RequestMapping(value = "setDriverToken", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setDriverToken(String driverToken, int userNo) {
		Map<String, Object> map = new HashMap<>();
		map = pushInfoValidata.setDriverToken(driverToken, userNo);
		return map;
	}
}
