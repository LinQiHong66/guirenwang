package com.inesv.digiccy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.CommandRedValidata;
import com.inesv.digiccy.validata.HelpCenterValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/helpCenter")
public class HelpCenterController {
	
	@Autowired
	HelpCenterValidata helpCenterValidata;

    /**
     * 取得一级帮助中心
     * @return
     */
    @RequestMapping(value = "getHelpCenter1" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getHelpCenter1(){
        Map<String,Object> map = helpCenterValidata.validataAllHelpCenterByOne();
        return map;
    }
    /**
     * 取得二级帮助中心
     * @return
     */
    @RequestMapping(value = "getHelpCenter2" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getHelpCenter2(String parentId){
        Map<String,Object> map = helpCenterValidata.validataAllHelpCenterByTwo(parentId);
        return map;
    }
    /**
     * 取得指定帮助中心
     * @return
     */
    @RequestMapping(value = "getHelpCenterInfo" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getHelpCenterInfo(String id){
        Map<String,Object> map = helpCenterValidata.validataHelpCenter(id);
        return map;
    }
}
