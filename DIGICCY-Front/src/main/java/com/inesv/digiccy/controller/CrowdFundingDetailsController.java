package com.inesv.digiccy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.validata.CrowdFundingDetailsValidata;
import com.inesv.digiccy.validata.CrowdFundingValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/crowdFundingDetails")
public class CrowdFundingDetailsController {
	
	@Autowired
	CrowdFundingDetailsValidata crowdFundingDetailsValidata;
	
	/**
     * 参与众筹项目筹备
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "addCrowdFundingInfo" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCrowdFundingInfo(@RequestParam String icoNo,@RequestParam Integer userNo,
    		@RequestParam Integer icoNumber,@RequestParam String payPassword) throws Exception{
    	Map<String,Object> crowdFundingMap = crowdFundingDetailsValidata.validataAddCrowdFunding(icoNo,userNo,icoNumber,payPassword);
        return crowdFundingMap;
    }

}
