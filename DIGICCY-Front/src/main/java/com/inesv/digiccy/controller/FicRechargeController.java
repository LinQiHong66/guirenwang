package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.validata.FicRechargeValidate;
import com.inesv.digiccy.validata.FicWithdrawValidate;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Controller
@RequestMapping("/virtual")
public class FicRechargeController {

    @Autowired
    FicRechargeValidate ficRechargeValidate;

    @Autowired
    private FicWithdrawValidate ficWithdrawValidate;

    /**
     *跳转充值页面,获取钱包地址与交易记录
     */
    @RequestMapping(value = "/goVirtualRecharge",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> goVirtualRecharge(String username,int userNo,int coinType){
        Map<String,Object> map = ficRechargeValidate.validateVirtualRecharge(username,userNo,coinType);
        return map;
    }

    /**
     *确认充值
     */
    /*@RequestMapping(value = "/rechargeCoin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> rechargeCoin(){
        Map<String,Object> map = new HashMap<>();
        ficRechargeValidate.validateRechargeCoin();
        map.put("code", ResponseCode.SUCCESS);
        map.put("desc", ResponseCode.SUCCESS_DESC);
        return map;
    }*/

    @RequestMapping(value = "/getUserBalanceAndAddress",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserBalanceAndAddress(String userNo,String coinNo){
        Map<String,Object> map = ficWithdrawValidate.getUserBalanceAndAddress(new Integer(userNo),new Integer(coinNo));
        return map;
    }

    @RequestMapping(value = "/getUserBalanceWithdraw",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserBalanceWithdraw(String userNo,String coinNo){
        Map<String,Object> map = ficWithdrawValidate.getUserBalanceWithdraw(new Integer(userNo),new Integer(coinNo));
        return map;
    }

}
