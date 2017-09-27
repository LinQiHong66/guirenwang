package com.inesv.digiccy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.validata.CommandRedValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/commandRed")
public class CommandRedController {
	
	@Autowired
	CommandRedValidata commandRedValidata;

    /**
     * 输入口令得到红包
     * @return
     */
    @RequestMapping(value = "editCommandRedInfo" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editCommandRedInfo(String user_id,String command_number){
        Map<String,Object> map = commandRedValidata.validataCommandRedInfoNumber(user_id,command_number);
        return map;
    }
}
