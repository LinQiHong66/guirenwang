package com.inesv.digiccy.controller;

import com.inesv.digiccy.validata.TranValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
@Controller
@RequestMapping("transfer")
public class TranController {

    @Autowired
    TranValidate tranValidate;

    /**
     *获取转账记录和可用金额
     */
    @RequestMapping(value = "goTransfer",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> goTransfer(String userNo,String coinNo){
        if(userNo != null && coinNo!= null){
            int userNo1 = Integer.parseInt(userNo);
            int coinNo1 = Integer.parseInt(coinNo);
            Map<String,Object> map = tranValidate.validateTran(userNo1,coinNo1);
            return map;
        }
        return null;
    }

    /**
     *确认转账
     *
     */
    @RequestMapping(value = "confirmTransfer",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> confirmTransfer(int userNo,int coinType,int tranuser,String coinNun,String code,String dealPwd,String mobile){
        Map<String,Object> map = tranValidate.validateConfirmTransfer(userNo,coinType,mobile,tranuser,coinNun,code,dealPwd);
        return map;
    }


    /**
     *获取短信验证码
     */
    @RequestMapping(value = "sendCodeCode",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> sendCodeCode(String mobile){
        Map<String,Object> map = tranValidate.validatePhoneCode(1,mobile);
        return map;
    }


}
