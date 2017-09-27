package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.EntrustDealValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by JimJim on 2017/1/4 0004.
 */
@Controller
@RequestMapping("/entrust")
public class EntrustController {

    @Autowired
    EntrustDealValidate entrustDealValidate;

    @RequestMapping(value = "getEntrust",method = RequestMethod.GET)
    public String getEntrust(){
        return "/entrust/getEntrust";
    }
    
    @RequestMapping(value = "getDayEntrust",method = RequestMethod.GET)
    public String getDayEntrust(){
        return "/entrust/getDayEntrust";
    }

    /*
     * 真分页委托记录
     */
    @RequestMapping(value = "getAllEntrustRecord",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllEntrustRecord(String userName, String state, String startDate, String endDate, Long pageItem, Float pageNum){
    	String s = pageNum.toString();
    	if(s.contains(".")){
    		s = s.substring(0, s.indexOf("."));
    	}
    	Integer k = Integer.parseInt(s);
        return entrustDealValidate.validateQueryEntrustAll(userName, state, startDate, endDate,pageItem, k);
    }
    
    /*
     * 当日委托记录
     */
    @RequestMapping(value = "getAllEntrustRecordByDay",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllEntrustRecordByDay(){
        return entrustDealValidate.validateQueryEntrustAllByDay();
    }
    
    /*
     * 真分页委托记录
     */
    @RequestMapping(value = "getAllEntrustRecordPaging",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllEntrustRecordPaging(Integer pageSize,Integer lineSize){
        return entrustDealValidate.validateQueryEntrustAllPaging(pageSize,lineSize);
    }

    /**
     *修改委托管理状态
     */
    @RequestMapping(value = "updateEntrustManage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateEntrustManage(@RequestParam String id){
        Map<String,Object> map = entrustDealValidate.validataUpdateEntrustManage(Long.parseLong(id.toString()));
        return map;
    }
    /**
     * 根据条件导出excel
     */
    @RequestMapping(value="getExcel", method=RequestMethod.POST)
    public void getExcel(HttpServletResponse response, String userName, String state, String startDate, String endDate){
    	entrustDealValidate.getExcel(response, userName, state, startDate, endDate);
    }
}
