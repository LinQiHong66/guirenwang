package com.inesv.digiccy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CityDto;
import com.inesv.digiccy.validata.CityValidata;
import com.inesv.digiccy.validata.MyRecValidate;
/**
 * 
 * @author liukeling
 *
 */
@Controller
@RequestMapping("/city")
public class CityController {
	private static Logger logger = LoggerFactory.getLogger(CityController.class);


	@Autowired
	CityValidata cityValidata;
	@RequestMapping(value="/getCityByCode", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCitys(String code,int type) {
		Map<String, Object> map = new HashMap<>();
		try {
			ArrayList<CityDto> citys = cityValidata.getCityByCode(code, type);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("citys", citys);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage());
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
