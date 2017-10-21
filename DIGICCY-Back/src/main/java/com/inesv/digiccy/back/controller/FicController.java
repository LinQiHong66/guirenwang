package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.dto.pageDto;
import com.inesv.digiccy.validata.FicTradeValidata;
import com.inesv.digiccy.validata.FicWithdrawValidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by JimJim on 2016/12/6 0006.
 */
@Controller
@RequestMapping("/fic")
public class FicController {

    @Autowired
    FicTradeValidata ficTradeValidata;


    @Autowired
    FicWithdrawValidate ficWithdrawValidate;
    
    /**
	 * 提现审核
     * @throws Exception 
    */
    @RequestMapping(value = "ficWithSH",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> ficWithSH(Integer id) throws Exception{
    	Map<String,Object> map = null;
        try {
			map = ficWithdrawValidate.validateFirWithdrawSH(id);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "200");
            map.put("desc", "提现失败");
		}
        return map;
    }
    
    @RequestMapping(value = "gotoRecharge",method = RequestMethod.GET)
    public String gotoRecharge(){
        return "/fic/recharge";
    }

    @RequestMapping(value = "gotoWithdraw",method = RequestMethod.GET)
    public String gotoWithdraw(){
        return "/fic/withdraw";
    }

    /**
     * @param pageNumber : 当前页码
     * @param pageSize ： 每页记录数
     * @return
     */
    @RequestMapping(value = "getRecharge",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRecharge(String userCode, String phone, String realName,String state,String coinType,String startData,String endData,pageDto page){
    	 
        Map<String,Object> map = ficTradeValidata.queryAllFicRechargeInfo(userCode, phone, realName, state, coinType, startData, endData, page);
        return map;
    }

    /**
     *获取虚拟币提现信息
     * @return
     */
    @RequestMapping(value = "getWithdraw",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getWithdraw(String userCode, String phone, String realName,String state,String coinType,String startData,String endData,pageDto page){
        Map<String,Object> map = ficTradeValidata.queryAllFicWithdrawInfo(userCode, phone, realName, state, coinType, startData, endData, page);
        return map;
    }
    
    @RequestMapping(value = "excelRecharge", method=RequestMethod.POST)
    public void getRechargeExcel(HttpServletResponse response, String userCode, String phone, String realName,String state,String coinType,String startData,String endData){
    	ficTradeValidata.getRechargeExcel(response, userCode, phone, realName, state, coinType, startData, endData);
    } 
    
    @RequestMapping(value = "excelWithdraw", method=RequestMethod.POST)
    public void getWithdrawExcel(HttpServletResponse response,String userCode, String phone, String realName,String state,String coinType,String startData,String endData){
    	ficTradeValidata.getWithdrawExcel(response, userCode, phone, realName, state, coinType, startData, endData);
    }
}
