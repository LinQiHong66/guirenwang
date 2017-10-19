package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.RmbRechargeValidate;
import com.inesv.digiccy.validata.RmbWithdrawValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by JimJim on 2016/12/12 0012.
 */
@Controller
@RequestMapping("/rmb")
public class RmbController {

    @Autowired
    RmbRechargeValidate rmbRechargeValidate;

    @Autowired
    RmbWithdrawValidate rmbWithdrawValidate;

    @RequestMapping(value = "gotoRecharge",method = RequestMethod.GET)
    public ModelAndView gotoRecharge(){
    	Map<String,Object> map = rmbRechargeValidate.validateRmbRechargeBack();
		map.put("sumPrice", map.get("sumPrice"));
		map.put("dayPrice", map.get("dayPrice"));
        return new ModelAndView("/rmb/recharge",map);
    }
    

    @RequestMapping(value = "gotoWithdraw",method = RequestMethod.GET)
    public ModelAndView gotoWithdraw(){
    	Map<String,Object> map = rmbWithdrawValidate.validateRmbWithdrawBack();
		map.put("sumPrice", map.get("sumPrice"));
		map.put("dayPrice", map.get("dayPrice"));
        return new ModelAndView("/rmb/withdraw",map);
    }
    
 
    

    /**
     * 获取充值信息
     * @param userCode
     * @param orderNumber
     * @param phone
     * @param realName
     * @param state
     * @param startDate
     * @param endDate
     * @param curPage
     * @param pageItem
     * @return
     */
    @RequestMapping(value = "getRecharge",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRecharge(String userCode, String orderNumber,String phone,String realName,String state, String startDate, String endDate,int curPage, int pageItem){
        Map<String,Object> map = rmbRechargeValidate.validateQueryRecord(userCode, orderNumber, phone, realName, state, startDate, endDate, curPage, pageItem);
        return map;
    }

    /**
     * 获取人民币提现信息
     * @param userName
     * @param state
     * @param startDate
     * @param endDate
     * @param curPage
     * @param pageItem
     * @return
     */
    @RequestMapping(value = "getWithdraw",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getWithdraw(String userCode,String realName,String phone, String state, String startDate, String endDate, int curPage, int pageItem){
        Map<String,Object> map = rmbWithdrawValidate.validateQueryRecord(userCode,realName,phone, state, startDate, endDate, curPage, pageItem);
        return map;
    }


    @RequestMapping(value = "doRecharge",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doRecharge(String ordId ){
        Map<String,Object> map = rmbRechargeValidate.confirmToAccount(ordId);
        return map;
    }

    @RequestMapping(value = "doWithdraw",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doWithdraw(String recId,String name,String price ){
        Map<String,Object> map = rmbWithdrawValidate.confirmToAccount(Integer.valueOf(recId), Integer.valueOf(name),new BigDecimal(price));
        return map;
    }
    
    
     /**
      * 导出Excel
     * @param response
     * @param userCode
     * @param orderNumber
     * @param phone
     * @param realName
     * @param state
     * @param startDate
     * @param endDate
     * @throws SQLException
     */
    @RequestMapping(value="getRechargeExcel")
    public void getRechargeExcel(HttpServletResponse response,String userCode, String orderNumber,String phone,String realName,String state, String startDate, String endDate) throws SQLException{
    	rmbRechargeValidate.getExcel(response, userCode, orderNumber, phone, realName, state, startDate, endDate);
    }
 
    @RequestMapping(value="getWithdrawExcel")
    public void getWithdraweExcel(HttpServletResponse response,String userCode, String orderNumber,String phone,String realName,String state, String startDate, String endDate){
    	rmbWithdrawValidate.getWithdrawExcel(response, userCode, realName, phone, state, startDate, endDate);
    }
}




