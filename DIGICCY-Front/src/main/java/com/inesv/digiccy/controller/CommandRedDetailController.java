package com.inesv.digiccy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CommandRedDetailDto;
import com.inesv.digiccy.query.QueryCommandRedDetailInfo;
import com.inesv.digiccy.validata.CommandRedDetailValidata;
import com.inesv.digiccy.validata.CommandRedValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/commandRedDetail")
public class CommandRedDetailController {

	@Autowired
	private CommandRedDetailValidata commandRedDetailValidata;
	
    /**
     * 得到用户红包
     * @return
     */
    @RequestMapping(value = "getCommandRedDetail" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getCommandRedDetail(String user_id){
        Map<String,Object> map = commandRedDetailValidata.validataCommandRedDetailInfo(user_id);
        return map;
    }
}
