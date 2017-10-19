package com.inesv.digiccy.back.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.validate.SpeculativeFundsValidate;

/**
 * 资金异常
 * 
 * @author lumia
 *
 */
@Controller
@RequestMapping("/speculativeFunds")
public class SpeculativeFundsController {
	@Autowired
	SpeculativeFundsValidate speculativeFundsValidate;

	@RequestMapping(value = "gotoSpeculativeFunds", method = RequestMethod.GET)
	public String gotoRecharge() {
		return "rmb/speculativeFunds";
	}

	@RequestMapping(value = "gotoSpeculativeFundsMotify", method = RequestMethod.GET)
	public ModelAndView gotoRechargeMotify(int id) {
		Map<String, Object> map = speculativeFundsValidate.validataSpeculativeFundById(id);
		return new ModelAndView("rmb/speculativeFundsMotify", map);
	}

	/**
	 * 查询所有异常用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/querySpeculativeFunds", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllSpeculativeFunds() {
		Map<String, Object> map = speculativeFundsValidate.validataAllSpeculativeFunds();
		return map;
	}

	@RequestMapping(value = "/updateSpeculativeFunds", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateRechargeMotify(int id, double addProperty, int typeProperty) {
		Map<String, Object> map = speculativeFundsValidate.updateSpeculativeFund(id, typeProperty, 0, addProperty);
		return map;
	}
}
