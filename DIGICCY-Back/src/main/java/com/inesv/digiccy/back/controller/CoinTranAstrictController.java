package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinTranAstrictDto;
import com.inesv.digiccy.validata.coin.CoinTranAstrictValidata;
import com.inesv.digiccy.validata.coin.CoinValidata;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
@Controller
@RequestMapping("/coinTranAstrict")
public class CoinTranAstrictController {

    private static Logger logger = LoggerFactory.getLogger(CoinTranAstrictController.class);

    @Autowired
    CoinTranAstrictValidata coinTranAstrictValidata;
    @Autowired
    CoinValidata coinValidata;
    
    @RequestMapping(value = "gotoAdd",method = RequestMethod.GET)
    public ModelAndView gotoAdd(){
    	Map<String,Object> map = new HashMap<>();
		map.put("coin", coinValidata.getAllCrowdCoin());
		return new ModelAndView("/coin/addTranAstrict",map);
    }
    
    @RequestMapping(value = "gotoEdit",method = RequestMethod.GET)
    public ModelAndView gotoEdit(String id){
    	Map<String,Object> map = new HashMap<>();
		map.put("coin",  coinTranAstrictValidata.getById(id));
		map.put("coins", coinValidata.getAllCrowdCoin());
		return new ModelAndView("/coin/editTranAstrict",map);
    }

    @RequestMapping(value = "gotoCoinTranAstrict",method = RequestMethod.GET)
    public String gotoCoin(){
        return "/coin/coinTranAstrict";
    }

    @RequestMapping(value = "getAllCoinTranAstrict",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllCoinTranAstrict(){
        Map<String,Object> coinMap = coinTranAstrictValidata.get();
        return coinMap;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> add(CoinTranAstrictDto coinTranAstrictDto,String beginDate,String endDate) throws Exception{
    	Map<String,Object> result=new HashMap<>();
    	CoinTranAstrictDto dto=coinTranAstrictValidata.getByCoin_no(String.valueOf(coinTranAstrictDto.getCoin_no()));
    	if(dto==null){
    		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    		result = coinTranAstrictValidata.add(coinTranAstrictDto.getCoin_no(),
    				coinTranAstrictDto.getBuy_min_price(),coinTranAstrictDto.getBuy_max_price(),
    				coinTranAstrictDto.getSell_min_price(),coinTranAstrictDto.getSell_max_price(),
    				coinTranAstrictDto.getSingle_min_price(),coinTranAstrictDto.getSingle_max_price(),
    				coinTranAstrictDto.getRose_astrict(),coinTranAstrictDto.getDrop_astrict(),
    				sf.parse(beginDate),sf.parse(endDate),coinTranAstrictDto.getState());
    	}else{
    		result.put("code","201");
            result.put("desc",ResponseCode.FAIL_DESC);
    	}
    		return result;
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(CoinTranAstrictDto coinTranAstrictDto,String beginDate,String endDate) throws Exception{
    	Map<String,Object> result=new HashMap<>();
    	System.out.println("+++++++++++" + coinTranAstrictDto.getSingle_max_price());
    	CoinTranAstrictDto dto1=coinTranAstrictValidata.getByCoin_no(String.valueOf(coinTranAstrictDto.getCoin_no()));
    	CoinTranAstrictDto dto2=coinTranAstrictValidata.getByCoin_id(String.valueOf(coinTranAstrictDto.getId()));
    	if(dto1==null || dto1.getId()==dto2.getId()){
    		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    		result = coinTranAstrictValidata.edit(coinTranAstrictDto.getId(),coinTranAstrictDto.getCoin_no(),
    				coinTranAstrictDto.getBuy_min_price(),coinTranAstrictDto.getBuy_max_price(),
    				coinTranAstrictDto.getSell_min_price(),coinTranAstrictDto.getSell_max_price(),
    				coinTranAstrictDto.getSingle_min_price(),coinTranAstrictDto.getSingle_max_price(),
    				coinTranAstrictDto.getRose_astrict(),coinTranAstrictDto.getDrop_astrict(),
    				sf.parse(beginDate),sf.parse(endDate),coinTranAstrictDto.getState());
    	}else{
    		result.put("code","201");
            result.put("desc","已有该货币交易条件");
    	}
        return result;
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delete(@RequestParam String id){
        Map<String,Object> coinMap = coinTranAstrictValidata.delete(Long.valueOf(id));
        return coinMap;
    }

}
