package com.inesv.digiccy.controller;

import com.inesv.digiccy.validata.PrizeValidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
@Controller
@RequestMapping("/prize")
public class PrizeController {
    @Autowired
    PrizeValidate prizeValidate;

    /**查询我的奖品*/
    @RequestMapping(value = "getPrize",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getPrize(Integer userNo){
        Map<String,Object > map = prizeValidate.validatePrize(userNo.toString());
        return map;
    }



}
