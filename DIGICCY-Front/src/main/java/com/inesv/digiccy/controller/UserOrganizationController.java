package com.inesv.digiccy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.InesvUserOrganizationValidata;

@Controller
@RequestMapping(value="/userOrganization")
public class UserOrganizationController {
	
	@Autowired
	InesvUserOrganizationValidata inesvUserOrganizationValidata;

	/*
	 * 添加升级机构
	 */
    @RequestMapping(value = "addUserOrganization" , method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> addUserOrganization(Integer userNo){
    	Map<String,Object> map=new HashMap<String,Object>();
    	map=inesvUserOrganizationValidata.addOrganizationValidate(userNo);
    	return map;
    }
}
