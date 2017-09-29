package com.inesv.digiccy.controller;

import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.validata.CoinValidate;
import com.inesv.digiccy.validata.RmbRechargeValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yc on 2016/12/9 0009.
 * 人民币充值
 */
@Controller
@RequestMapping("/front/coin")
public class CoinController {

    @Autowired
    CoinValidate coinValidate;

    /**
     *资产记录
     */
    @RequestMapping(value = "getBalanceBecord",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getBalanceBecord(Integer userNo){
        Map<String, Object> map = coinValidate.validateBalanceBecord(userNo);
        return map;
    }
    
    
    /**
     *总资产（折合成人民币），人民币资产,其他币资产
     */
    @RequestMapping(value = "getBalaninfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getBalaninfo(Integer userNo){
        Map<String, Object> map = coinValidate.validateBalanceInfo(userNo);
        return map;
    }
    
    @RequestMapping(value="getInfoByNo", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getInfoByNo(int coinNo){
    	return coinValidate.getInfoByNo(coinNo);
    }
 
    /**
     *获取虚拟币交易信息
     */
    @RequestMapping(value = "getCoinTrateInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getCoinTrateInfo(){
        Map<String, Object> map = coinValidate.validateCoinTrateInfo();
        return map;
    }
    

    
    
    
    /**
     *查询币种列表
     */
    @RequestMapping(value = "getAllCoinType",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllCoinType(){
        Map<String, Object> map = coinValidate.validateCoinTypeInfo();
        List<CoinDto> dtos = (List<CoinDto>) map.get("data");
        return map;
    }

    
    
    @RequestMapping(value = "upload")
    @ResponseBody
    public Map<String,Object> upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {  
    	Map<String, Object> map=new HashMap<>();
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("video");  
        String fileName = file.getOriginalFilename();  
        fileName="video.mp4";
         
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        System.out.println("----- "+path);
        map.put("fileUrl", request.getContextPath()+"/video/"+fileName);
        return map;  
    }  
 
}
