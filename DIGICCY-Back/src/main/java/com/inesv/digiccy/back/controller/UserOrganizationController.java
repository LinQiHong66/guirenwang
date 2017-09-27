package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.InesvUserOrganizationValidata;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JimJim on 2016/12/5 0005.
 */

@Controller
@RequestMapping("/userOrganization")
public class UserOrganizationController {

    @Autowired
    InesvUserOrganizationValidata userOrganizationValidata;
    
    @RequestMapping(value = "gotoOrganization",method = RequestMethod.GET)
    public String gotoOrganization(){
        return "/user/organization";
    }
    
    /**
     * 得到所有升级的信息
     * @return
     */
    @RequestMapping(value = "getAllOrganization" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllOrganization(){
        Map<String,Object> map = userOrganizationValidata.getOrganizationValidate();
        return map;
    }
    
    /**
     * 升级操作
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "updateOrganization" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateOrganization(Integer id,Integer userNo,Integer org_type) throws Exception{
    	Map<String,Object> map = userOrganizationValidata.updateOrganizationValidate(id, userNo, org_type);
    	return map;
    }

   
}
