package com.inesv.digiccy.back.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.PoundatgeValidata;

@Controller
@RequestMapping("/poundatge")
public class PoundatgeController {
	
	@Autowired
	PoundatgeValidata poundatgeValidata;
	
	@RequestMapping(value = "goto",method = RequestMethod.GET)
	public String gotoVeiw(){
		return "entrust/getPoundatge";
	}
	
	@ResponseBody
	@RequestMapping(value="queryAll",method = RequestMethod.GET)
	public Map<String,Object> queryAll(){
		return poundatgeValidata.queryAll();
	}
	
	//导出excel
	@RequestMapping(value="/getExcel", method=RequestMethod.POST)
	public void getExcel(HttpServletResponse response){
		poundatgeValidata.getExcel(response);
	}
}
