package com.inesv.digiccy.back.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.dto.CommandRedDto;
import com.inesv.digiccy.validata.CommandRedValidata;
import com.inesv.digiccy.validata.CrowdFundingValidata;
import com.inesv.digiccy.validata.UserBalanceDetailValidata;
import com.inesv.digiccy.validata.coin.CoinValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/commandRed")
public class UserBalanceDetailController{
	
	@Autowired
	UserBalanceDetailValidata userBalanceDetailValidata;
	
	@RequestMapping(value = "gotoUserBalanceDetail",method = RequestMethod.GET)
    public String gotoCommandRed(){
        return "/userBalanceDetail/userBalanceDetail";
    }
	/**
     * 得到所有口令红包的信息
     * @return
     */
    @RequestMapping(value = "getAllDetail" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllDetail(){
        Map<String,Object> userBalanceDetailMap = userBalanceDetailValidata.validataAllDetail();
        return userBalanceDetailMap;
    }
	/**
     * 增加币种交易记录
     * @return
	 * @throws Exception 
     */
    /*@RequestMapping(value = "addDetail" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addDetail(@RequestParam Integer user_no,@RequestParam Integer coin_type,@RequestParam Integer coin_price,@RequestParam String remark,HttpServletRequest request) throws Exception{
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal(); 
    	String admin_no = userDetails.getUsername();
    	Map<String,Object> userBalanceDetailMap = userBalanceDetailValidata.validataAddDetail(user_no,admin_no,coin_type,coin_price,remark);
    	return userBalanceDetailMap;
    }*/
	
}
