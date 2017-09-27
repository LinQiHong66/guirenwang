package com.inesv.digiccy.controller;

import com.inesv.digiccy.validata.EntrustDealValidate;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Controller
@RequestMapping("/entrustDeal")
public class EntrustDealController {
    @Autowired
    EntrustDealValidate entrustDealValidate;

    @Autowired
    CommandGateway commandGateway;

    /**委托成交查询*/
    @RequestMapping(value = "/getEntrustDealList",method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getEntrustList(HttpServletRequest request, @RequestParam Integer userNo,@RequestParam Integer dealType,@RequestParam Integer coinNo){
        Map<String , Object> map = entrustDealValidate.validataEntrustListByUserNo(userNo.toString(),dealType.toString(),coinNo.toString());
        return map; 
    }
    
    /**
     * 分页查询委托记录
     */
    @RequestMapping(value="/getPagingEntrustList", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPagingEntrustList(@RequestParam Integer pageNum, @RequestParam Integer itemCount, @RequestParam Integer userNo,@RequestParam Integer coinNo){
    	
    	return entrustDealValidate.getPagingentrList(pageNum, itemCount, userNo, coinNo);
    }
    /**
     * 得到委托管理记录
     * @param request
     * @return
     */
    @RequestMapping(value = "getEntrustManageList",method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getEntrustList(HttpServletRequest request,@RequestParam Integer userNo,@RequestParam Integer entrustType,@RequestParam Integer entrustCoin,@RequestParam Integer state){
        Map<String , Object> map = entrustDealValidate.validataEntrustManageListByUserNo(userNo.toString(),entrustType.toString(),entrustCoin.toString(),state.toString());
        return map;
    }

    /**
     *修改委托管理状态
     */
    @RequestMapping(value = "updateEntrustManage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateEntrustManage(@RequestParam Integer id){
        Map<String,Object> map = entrustDealValidate.validataUpdateEntrustManage(Long.valueOf(id.toString()));
        return map;
    }


}
