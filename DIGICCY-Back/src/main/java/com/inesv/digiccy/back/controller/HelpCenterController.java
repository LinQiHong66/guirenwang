package com.inesv.digiccy.back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.dto.HelpCenterDto;
import com.inesv.digiccy.validata.HelpCenterValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/helpCenter")
public class HelpCenterController{
	
	@Autowired
	HelpCenterValidata helpCenterValidata;
	
	@RequestMapping(value = "gotoHelpCenter",method = RequestMethod.GET)
    public String gotoHelpCenter(){
        return "/helpCenter/helpCenter";
    }
	
	@RequestMapping(value = "gotoAdd",method = RequestMethod.GET)
    public ModelAndView gotoAdd(){
		Map<String,Object> map = new HashMap<>();
		map.put("helpCenter", helpCenterValidata.validataAllHelpCenterByOne().get("data"));
        return new ModelAndView("/helpCenter/add",map);
    }
	
	@RequestMapping(value = "gotoEdit",method = RequestMethod.GET)
	@ResponseBody
    public ModelAndView gotoEdit(String id){
		Map<String,Object> map = new HashMap<>();
		map.put("helpCenter", helpCenterValidata.validataHelpCenter(id));
		map.put("helpCenterParent", helpCenterValidata.validataAllHelpCenterByOne().get("data"));
        return new ModelAndView("/helpCenter/edit",map);
    }
	/**
     * 得到所有信息
     * @return
     */
    @RequestMapping(value = "getAllHelpCenter" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllHelpCenter(){
        Map<String,Object> helpCenterMap = helpCenterValidata.validataAllHelpCenter();
        return helpCenterMap;
    }
    /**
     * 得到一级所有信息
     * @return
     */
    @RequestMapping(value = "getAllHelpCenterByOne" ,method = RequestMethod.GET)
    @ResponseBody
    public List<HelpCenterDto> getAllHelpCenterByOne(){
        List<HelpCenterDto> helpCenterList = (List<HelpCenterDto>) helpCenterValidata.validataAllHelpCenterByOne().get("data");
        return helpCenterList;
    }
    /**
     * 增加信息
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "addHelpCenter" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addHelpCenter(String help_name, String help_grade, String help_parent, String help_remark,HttpServletRequest request) throws Exception{
    	Map<String,Object> crowdFundingMap = new HashMap<String,Object>();
        crowdFundingMap = helpCenterValidata.validataAddHelpCenter(help_name,help_grade,help_parent,help_remark);
    	return crowdFundingMap;
    }
    /**
     * 修改信息
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "editHelpCenter" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editHelpCenter(String id,String help_name, String help_grade, String help_parent, String help_remark) throws Exception{
    	Map<String,Object> helpCenterMap = new HashMap<String,Object>();
    	helpCenterMap = helpCenterValidata.validataEditHelpCenter(id,help_name,help_grade,help_parent,help_remark);
    	return helpCenterMap;
    }
    /**
     * 删除信息
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "deleteHelpCenter" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteHelpCenter(@RequestParam String id){
    	System.out.println("------------" + id);
        Map<String,Object> helpCenterMap = helpCenterValidata.validataDeleteHelpCenter(id);
        return helpCenterMap;
    }
    
}
