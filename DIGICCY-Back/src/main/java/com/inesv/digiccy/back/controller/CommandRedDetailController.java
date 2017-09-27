package com.inesv.digiccy.back.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CommandRedDetailDto;
import com.inesv.digiccy.validata.CommandRedDetailValidata;
import com.inesv.digiccy.validata.CommandRedValidata;
import com.inesv.digiccy.validata.UserBalanceDetailValidata;
import com.inesv.digiccy.validata.coin.CoinValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/commandRedDetail")
public class CommandRedDetailController{
	
	@Autowired
	CommandRedValidata commandRedValidata;
	@Autowired
	CommandRedDetailValidata commandRedDetailValidata;
	@Autowired
	UserBalanceDetailValidata userBalanceDetailValidata;
	
	@RequestMapping(value = "gotoCommandRedDetail",method = RequestMethod.GET)
    public String gotoCommandRed(){
        return "/commandRed/commandRedDetail";
    }
	/**
     * 得到所有获取口令红包的信息
     * @return
     */
    @RequestMapping(value = "getAllCommandRedDetail" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllCommandRedDetail(){
        Map<String,Object> commandRedMap = commandRedValidata.validataAllCommandRedDetail();
        return commandRedMap;
    }
    /**
     * 修改指定口令红包的状态
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "editCommandRedInfoState" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editCommandRedInfoState(@RequestParam String id,@RequestParam String state) throws Exception{	
    	Map<String,Object> crowdFundingMap = new HashMap<>();
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal(); 
    	String admin_no = userDetails.getUsername();
        CommandRedDetailDto commandRedDetailDto=(CommandRedDetailDto)commandRedDetailValidata.validataCommandRedDetailInfoById(id).get("data");
        if(commandRedDetailDto.getState()==0){
        	crowdFundingMap = commandRedValidata.validataEditCommandRedInfoState(id,state,Integer.valueOf(commandRedDetailDto.getUser_id().toString()),admin_no,0,Integer.valueOf(commandRedDetailDto.getCommand_name_price()),"口令红包拨币");
        }else{
        	crowdFundingMap.put("code",ResponseCode.FAIL);
        	crowdFundingMap.put("desc",ResponseCode.FAIL_DESC);
        }
        return crowdFundingMap;
    }
	
}
