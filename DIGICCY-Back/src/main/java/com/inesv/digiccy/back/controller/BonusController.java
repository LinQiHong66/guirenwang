package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.BonusValidata;
import com.inesv.digiccy.validata.UserBalanceValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/7 0007.
 */
@Controller
@RequestMapping("/bonus")
public class BonusController {

    @Autowired
    UserBalanceValidate userBalanceValidate;

    @Autowired
    BonusValidata bonusValidata;

    @RequestMapping(value = "gotoBonus",method = RequestMethod.GET)
    public String gotoBonus(){
        return "/bonus/bonus";
    }

    @RequestMapping(value = "gotoRecord",method = RequestMethod.GET)
    public String gotoRecord(){
        return "/bonus/record";
    }

    @RequestMapping(value = "getBonusDetail",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getBonusDetail(){
        Map<String,Object> map = bonusValidata.validataBonusDetail();
        return map;
    }

    @RequestMapping(value = "getBonusRecord",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getBonusRecord(){
        Map<String,Object> map = bonusValidata.validataBonus();
        return map;
    }

    @RequestMapping(value = "addBonusDetail",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addBonusDetail(String bonusName,Integer coin,String num){
        Map<String,Object> map = bonusValidata.addBonusDetailed(bonusName,coin,new BigDecimal(num));
        return map;
    }

    @RequestMapping(value = "updateBonusDetail",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateBonusDetail(String id,String bonusName,String coin,String num){
        Map<String,Object> map = bonusValidata.updateBonusDetailed(Integer.valueOf(id),bonusName,Integer.valueOf(coin),new BigDecimal(num));
        return map;
    }

    @RequestMapping(value = "deleteBonusDetail",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> deleteBonusDetail(Integer id){
        Map<String,Object> map = bonusValidata.deleteBonusDetailed(id);
        return map;
    }

    @RequestMapping(value = "doBonus",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doBonus(String bonusName,String coinId,String num){
        Map<String,Object> map = bonusValidata.doBonusDetailed(bonusName,Integer.valueOf(coinId),new BigDecimal(num));
        return map;
    }

    @RequestMapping(value = "getCoinCount",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getCoinCount(){
        Map<String,Object> coinMap = userBalanceValidate.validataQueryCoinCount();
        return coinMap;
    }

}
