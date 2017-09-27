package com.inesv.digiccy.back.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.BonusLevelValidata;

@Controller
@RequestMapping("/bonuslevel")
public class BonusLevelController {
	
	@Autowired
	BonusLevelValidata bonusValidata;
	
	@RequestMapping(value = "goto",method = RequestMethod.GET)
	public String gotoVeiw(){
		return "bonus/bonuslevel";
	}
	
	@ResponseBody
	@RequestMapping(value="queryAll",method = RequestMethod.GET)
	public Map<String,Object> queryAll(){
		return bonusValidata.queryAll();
	}
	//导出excel
	@RequestMapping(value="/getExcel", method=RequestMethod.POST)
	public void getExcel(HttpServletResponse response){
		bonusValidata.getExcel(response);
	}
}
