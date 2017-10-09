package com.inesv.digiccy.back.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.UserLevelValidata;

@Controller
@RequestMapping("/userlevel")
public class UserLevelController {
	@Autowired
	UserLevelValidata userLevelValidata;
	
	@RequestMapping(value = "gotoAll",method = RequestMethod.GET)
	public String gotoAll(){
		return "/userlevel/userlevel";
	}
	
	@ResponseBody
	@RequestMapping(value="queryAll",method = RequestMethod.POST)
	public Map<String,Object> queryAll(){
		return userLevelValidata.queryAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "queryByLevelId",method = RequestMethod.POST)
	public Map<String,Object> queryByLevelId(Long level_id){
		return userLevelValidata.queryByLevelId(level_id);
	}
	
	@ResponseBody
	@RequestMapping(value = "insert",method = RequestMethod.POST)
	public Map<String, Object> oo(String user_id,String level){
		return userLevelValidata.insert(Long.valueOf(user_id), Integer.valueOf(level));
	}
	
	@ResponseBody
	@RequestMapping(value = "updateLevel",method = RequestMethod.POST)
	public Map<String, Object> updateLevel(String status,String user_id,String level){
		Boolean realstatus = null;
		if(Integer.valueOf(status)==1){
			realstatus = true;
		}else if(Integer.valueOf(status)==0){
			realstatus = false;
		}
		return userLevelValidata.update(realstatus,Long.valueOf(user_id),Integer.valueOf(level));
	}
	
	@ResponseBody
	@RequestMapping(value="queryByStatus",method = RequestMethod.GET)
	public Map<String,Object> queryByStatus(){
		return userLevelValidata.queryByStatus();
	}
}
