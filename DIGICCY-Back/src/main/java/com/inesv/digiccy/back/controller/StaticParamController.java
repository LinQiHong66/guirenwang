package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.BackInfoValidate;
import com.inesv.digiccy.validata.StaticParamValidata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by JimJim on 2016/12/14 0014.
 */
@Controller
@RequestMapping("/param")
public class StaticParamController {

	@Autowired
	StaticParamValidata staticParamValidata;

	@Autowired
	BackInfoValidate backInfoValidate;

	@RequestMapping(value = "gotoStaticParam", method = RequestMethod.GET)
	public String gotoStaticParam() {
		return "/param/param";
	}

	@RequestMapping(value = "backInfo", method = RequestMethod.GET)
	public String gotoBackInfo() {
		return "/param/backinfo";
	}

	@RequestMapping(value = "getBackInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBackInfo() {
		return backInfoValidate.getBankInfo();
	}

	@RequestMapping(value = "modifyBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyBankInfo(int id, String name, String cardId, String backName, String remark) {
		return backInfoValidate.modifyBankInfo(id, name, cardId, backName, remark);
	}


	@RequestMapping(value = "getStaticParam", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getStaticParam() {
		Map<String, Object> map = staticParamValidata.getStaticParam();
		return map;
	}


	@RequestMapping(value = "addStaticParam", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addStaticParam(String param, String value) {
		Map<String, Object> map = staticParamValidata.addStaticParam(param, value);
		return map;
	}

	@RequestMapping(value = "updateStaticParam", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStaticParam(String id, String param, String value) {
		Map<String, Object> map = staticParamValidata.updateStaticParam(id, param, value);
		return map;
	}

	@RequestMapping(value = "deleteStaticParam", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteStaticParam(String id) {
		Map<String, Object> map = staticParamValidata.deleteStaticParam(id);
		return map;
	}
}
