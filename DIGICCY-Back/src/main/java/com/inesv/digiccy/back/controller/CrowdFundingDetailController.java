package com.inesv.digiccy.back.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.validata.CrowdFundingValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/crowFundingDetail")
public class CrowdFundingDetailController {

	@Autowired
	CrowdFundingValidata crowdFundingValidata;

	@RequestMapping(value = "gotoCrowdDetail", method = RequestMethod.GET)
	public ModelAndView gotoCrowd() {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> crowdMap = crowdFundingValidata.validateCrowdDetail();
		map.put("crowd1", crowdMap.get("crowd1"));
		map.put("crowd2", crowdMap.get("crowd2"));
		map.put("crowd3", crowdMap.get("crowd3"));
		map.put("crowd4", crowdMap.get("crowd4"));
		map.put("userOrgCode", crowdMap.get("userOrgCode"));
		return new ModelAndView("/crowd/crowdDetail", map);
	}

	/**
	 * 得到所有众筹项目的信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllCrowdFundingDetail", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCrowdFundingDetail() {
		Map<String, Object> map = crowdFundingValidata.validataAllCrowdFundingDetailBack();
		return map;
	}

	/**
	 * 得到所有众筹项目的信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllCrowdFundingDetail_jigou", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllCrowdFundingDetail_jigou() {
		Map<String, Object> map = crowdFundingValidata.validataAllCrowdFundingDetailBack_Jigou();
		return map;
	}

	/**
	 * 得到所有众筹项目的信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllCrowdFundingDetail_user", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllCrowdFundingDetail_user() {
		Map<String, Object> map = crowdFundingValidata.validataAllCrowdFundingDetailBack_User();
		return map;
	}

	/**
	 * 通过id获取对应的众筹项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "getCrowdFundingDetailById_user", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCrowdFundingDetailById_user(int userId) {
		Map<String, Object> map = crowdFundingValidata.queryCrowdFundingDetailBackById(userId);
		return map;
	}

	/**
	 * 得到指定机构下所有成员众筹项目的信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllUserJigou", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllUserJigou() {
		Map<String, Object> map = crowdFundingValidata.validataAllCrowdFundingDetailBack_User();
		return map;
	}

	/**
	 * 得到指定机构下所有成员众筹项目的信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllCrowdFundingDetail_jigou_detail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllCrowdFundingDetail_jigou_detail(String orgCode) {
		Map<String, Object> map = crowdFundingValidata.validataAllCrowdFundingDetailBack_Jigou_user(orgCode);
		return map;
	}

	/*
	 * 导出excel
	 */
	@RequestMapping(value = "excelDetail", method = RequestMethod.GET)
	@ResponseBody
	public void getDetailExcel(HttpServletResponse response) {
		crowdFundingValidata.getDetailExcel(response);
	}
}
