package com.inesv.digiccy.back.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinLevelProportionDto;
import com.inesv.digiccy.dto.CoinTranAstrictDto;
import com.inesv.digiccy.validata.CoinLevelProportionValidata;
import com.inesv.digiccy.validata.coin.CoinValidata;

@Controller
@RequestMapping("/coinproportion")
public class CoinLevelProportionController {
	
	@Autowired
    CoinValidata coinValidata;
	
	@Autowired
	CoinLevelProportionValidata validata;
	
	@RequestMapping(value="goto",method = RequestMethod.GET)
	public String gotoLevel(){
		return "/coin/coinUserLevel";
	}
	
	@RequestMapping(value="gotoAdd",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView gotoAdd(){
		Map<String,Object> map = new HashMap<>();
		map.put("level_coin", coinValidata.getAllCoinRMBInfo());
		map.put("coin", coinValidata.getAllCrowdCoin());
		return new ModelAndView("/coin/addCoinUserLevel",map);
	}
	
	@RequestMapping(value="gotoEdit",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView gotoEdit(String id){
		Map<String,Object> map = new HashMap<>();
		map.put("level",  validata.queryById(id));
		map.put("level_coin", coinValidata.getAllCoinRMBInfo());
		map.put("coins", coinValidata.getAllCrowdCoin());
		return new ModelAndView("/coin/editCoinUserLevel",map);
	}
	
	
	@RequestMapping(value="queryAll",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> queryAll(){
		return validata.queryAll();
	}
	
	@RequestMapping(value="queryById",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> queryById(String id){
		return validata.queryById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="update",method = RequestMethod.POST)
	public Map<String,Object> updateById(CoinLevelProportionDto levelDto){
		Map<String,Object> map = new HashMap<String,Object>();
		if((levelDto.getLevel_one().doubleValue() + levelDto.getLevel_two().doubleValue() + levelDto.getLevel_three().doubleValue() 
				+ levelDto.getLevel_four().doubleValue() + levelDto.getLevel_five().doubleValue()) > 1) {
			map.put("code","200");
			map.put("desc","比例相加不能大于1，抱歉！");
			return map;
		}
		return validata.updateLevelById(levelDto);
	}
	
	@ResponseBody
	@RequestMapping(value="insert",method = RequestMethod.POST)
	public Map<String,Object> insert(CoinLevelProportionDto levelDto){
		Map<String,Object> map = new HashMap<String,Object>();
		if((levelDto.getLevel_one().doubleValue() + levelDto.getLevel_two().doubleValue() + levelDto.getLevel_three().doubleValue() 
				+ levelDto.getLevel_four().doubleValue() + levelDto.getLevel_five().doubleValue()) > 1) {
			map.put("code","200");
			map.put("desc","比例相加不能大于1，抱歉！");
			return map;
		}
		CoinLevelProportionDto dto=validata.getByCoin_no(levelDto.getCoin_no());
		if(dto == null) {
			map = validata.insert(levelDto);
		}else {
			map.put("code","201");
			map.put("desc","已有该货币的分润比例！");
		}
		return map;
		
	}
	
	@ResponseBody
	@RequestMapping(value="delete",method = RequestMethod.POST)
	public Map<String,Object> delete(String id){
		return validata.delete(id);
	}
}
