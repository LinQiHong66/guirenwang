package com.inesv.digiccy.back.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.CoinLevelProportionValidata;

@Controller
@RequestMapping("/coinproportion")
public class CoinLevelProportionController {
	@Autowired
	CoinLevelProportionValidata validata;
	
	@RequestMapping(value="goto",method = RequestMethod.GET)
	public String goal(){
		//Map<String, Object> map = new HashMap<String, Object>();
		return "/coin/coinUserLevel";
	}
	
	
	@RequestMapping(value="queryAll",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> queryAll(){
		System.out.println("*******************");
		//Map<String, Object> map = new HashMap<String, Object>();
		return validata.queryAll();
	}
	
	@ResponseBody
	@RequestMapping(value="update",method = RequestMethod.POST)
	public Map<String,Object> updateByCoin_no(String id,String level_one,String level_two){
		//Map<String, Object> map = new HashMap<String, Object>();
		return validata.updateLevelByCoinNO(Long.valueOf(id), new BigDecimal(level_one), new BigDecimal(level_two), new BigDecimal(0));
	}
	
	@ResponseBody
	@RequestMapping(value="insert",method = RequestMethod.POST)
	public Map<String,Object> insert(String coin_no,String level_one,String level_two){
		//Map<String, Object> map = new HashMap<String, Object>();
		return validata.insert(Long.valueOf(coin_no), new BigDecimal(level_one), new BigDecimal(level_two), new BigDecimal(0));
	}
}
