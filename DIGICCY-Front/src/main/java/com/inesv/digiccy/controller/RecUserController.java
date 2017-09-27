package com.inesv.digiccy.controller;

import com.inesv.digiccy.validata.RecUserValidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by yc on 2016/12/19 0019.
 * 财务中心推荐用户
 */
@Controller
@RequestMapping("/recUser")
public class RecUserController {

    @Autowired
    RecUserValidate recUserValidate;

    /**生产用户推荐码*/
    @RequestMapping(value = "/getRecCode",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getRecCode(int userNo){
        Map<String, Object> map = recUserValidate.validateRechUser(userNo);
        return map;
    }
    /**获取我推荐的人*/
    @RequestMapping(value="/getMyRecUsers", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getMyRecUsers(int userNo){
    	return recUserValidate.getMyInesv(userNo);
    }
}
