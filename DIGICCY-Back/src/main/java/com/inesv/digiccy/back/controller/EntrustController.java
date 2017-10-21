package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.dto.pageDto;
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
    public Map<String,Object> getAllEntrustRecord(String userCode, String phone, String realName,String state,String startData,String endData,pageDto page){
    	 
        return entrustDealValidate.validateQueryEntrustAll(userCode, phone, realName, state, startData, endData, page);
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
    @RequestMapping(value="getExcel", method=RequestMethod.GET)
    public void getExcel(HttpServletResponse response,String userCode, String phone, String realName,String state,String startData,String endData){
    	entrustDealValidate.getExcel(response, userCode, phone, realName, state, startData, endData);
    	
    }
}
