package com.inesv.digiccy.controller;

import com.inesv.digiccy.validata.RmbWithdrawValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

@Controller
@RequestMapping("rmbWithdraw")
public class RmbWithdrwaController {

    @Autowired
    RmbWithdrawValidate rmbWithdrawValidate;

    /**
     *获取人民币提现记录,可用金额及提现地址
     */
    @RequestMapping(value = "getWithdrawInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getWithdrawInfo(int userNo){
        Map<String,Object> map = rmbWithdrawValidate.validateWithdrawInfo(userNo,0);//0为人民币
        return map;
    }

    /**
     *新增提现地址
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Map<String,Object> updateRmbAddress(){
        return null;
    }

    /**
     *获取短信验证码
     */
    @RequestMapping(value = "sendCodeCode",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> sendCodeCode(String mobile){
        Map<String,Object> map =rmbWithdrawValidate.validatePhoneCode(1,mobile);
        return map;
    }

    /**
     *申请RMB提现
     * @throws Exception 
     */
    @RequestMapping(value = "goRmbWithdraw", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> goRmbWithdraw(int userNo,String price,int bank,String dealPwd,String moble,String code) throws Exception{
        Map<String,Object> map = rmbWithdrawValidate.validateRmbWithdraw(userNo,price,bank,dealPwd,moble,code);
        return map;
    }
}
