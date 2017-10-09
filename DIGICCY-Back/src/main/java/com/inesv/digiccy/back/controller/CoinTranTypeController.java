package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.dto.CoinTranTypeDto;
import com.inesv.digiccy.validata.coin.CoinTranTypeValidata;
import com.inesv.digiccy.validata.coin.CoinValidata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
@Controller
@RequestMapping("/coinTradeType")
public class CoinTranTypeController {

    private static Logger logger = LoggerFactory.getLogger(CoinTranTypeController.class);

    @Autowired
    CoinTranTypeValidata coinTranTypeValidata;
    
    @Autowired
    CoinValidata coinValidata;
    
    @RequestMapping(value = "gotoAdd",method = RequestMethod.GET)
    public ModelAndView gotoAdd(){
    	Map<String,Object> map = new HashMap<>();
		map.put("coin", coinValidata.getAllCrowdCoinByNoRMB());
		return new ModelAndView("/coin/addTradeType",map);
    }
    
    @RequestMapping(value = "gotoEdit",method = RequestMethod.GET)
    public ModelAndView gotoEdit(String id){
    	Map<String,Object> map = new HashMap<>();
		map.put("coin",  coinTranTypeValidata.getById(id));
		map.put("coins", coinValidata.getAllCrowdCoinByNoRMB());
		return new ModelAndView("/coin/editTradeType",map);
    }

    @RequestMapping(value = "gotoCoinTradeType",method = RequestMethod.GET)
    public String gotoCoinTradeType(){
        return "/coin/coinTradeType";
    }

    @RequestMapping(value = "getAllCoinTradeType",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllCoinTradeType(){
        Map<String,Object> coinMap = coinTranTypeValidata.get();
        return coinMap;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> add(CoinTranTypeDto coinTranTypeDto) throws Exception{
    	Map<String,Object> result=new HashMap<>();
    	if(coinTranTypeDto.getCoin_no() == coinTranTypeDto.getTran_coin_no()) {
    		result.put("code","200");
            result.put("desc","兑换货币编号不能和货币编号一致，抱歉！");
            return result;
    	}
    	List<CoinTranTypeDto> dto=coinTranTypeValidata.getByCoin_no(String.valueOf(coinTranTypeDto.getCoin_no()),String.valueOf(coinTranTypeDto.getTran_coin_no()));
    	if(dto.size() == 0){
    		result = coinTranTypeValidata.add(coinTranTypeDto.getCoin_no(), coinTranTypeDto.getState(), coinTranTypeDto.getTran_coin_no(), new Date());
    	}else{
    		result.put("code","200");
            result.put("desc","已有该货币交易类型！");
    	}
    	return result;
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(CoinTranTypeDto coinTranTypeDto) throws Exception{
    	Map<String,Object> result=new HashMap<>();
    	if(coinTranTypeDto.getCoin_no() == coinTranTypeDto.getTran_coin_no()) {
    		result.put("code","200");
            result.put("desc","兑换货币编号不能和货币编号一致，抱歉！");
            return result;
    	}
    	List<CoinTranTypeDto> dto1=coinTranTypeValidata.getByCoin_no(String.valueOf(coinTranTypeDto.getCoin_no()),String.valueOf(coinTranTypeDto.getTran_coin_no()));
    	CoinTranTypeDto dto2=coinTranTypeValidata.getByType_id(String.valueOf(coinTranTypeDto.getId()));
    	if(dto1.size() == 0){
    		result = coinTranTypeValidata.edit(coinTranTypeDto.getId(),coinTranTypeDto.getCoin_no(), coinTranTypeDto.getState(), coinTranTypeDto.getTran_coin_no(), new Date());
    	}else if(dto1.get(0).getCoin_no() == dto2.getCoin_no() && dto1.get(0).getTran_coin_no() == dto2.getTran_coin_no()) {
    		result = coinTranTypeValidata.edit(coinTranTypeDto.getId(),coinTranTypeDto.getCoin_no(), coinTranTypeDto.getState(), coinTranTypeDto.getTran_coin_no(), new Date());
    	}else{
    		result.put("code","200");
            result.put("desc","已有该货币交易类型！");
    	}
        return result;
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delete(@RequestParam String id){
        Map<String,Object> coinMap = coinTranTypeValidata.delete(Integer.valueOf(id));
        return coinMap;
    }

}
