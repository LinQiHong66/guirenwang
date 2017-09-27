package com.inesv.digiccy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.dto.CrowdFundingDto;
import com.inesv.digiccy.validata.CrowdFundingValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/crowFunding")
public class CrowdFundingController {
	
	@Autowired
	CrowdFundingValidata crowdFundingValidata;
	
	/**
     * 得到所有众筹项目的信息
     * @return
     */
    @RequestMapping(value = "getAllCrowdFunding" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllCrowdFunding(String pageSize,String lineSize){
        Map<String,Object> crowdFundingMap = crowdFundingValidata.validataAllCrowdFunding(pageSize,lineSize);
        return crowdFundingMap;
    }
    /**
     * 得到指定众筹项目的信息
     * @return
     */
    @RequestMapping(value = "getCrowdFundingInfo" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getCrowdFundingInfo(String icoNo) throws Exception {
        Map<String,Object> crowdFundingMap = crowdFundingValidata.validataCrowdFundingInfo(icoNo);
        return crowdFundingMap;
    }
}
