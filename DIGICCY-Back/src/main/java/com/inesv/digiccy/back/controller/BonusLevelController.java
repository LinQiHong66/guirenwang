package com.inesv.digiccy.back.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.BonusLevelValidata;

@Controller
@RequestMapping("/bonuslevel")
public class BonusLevelController {

	@Autowired
	BonusLevelValidata bonusValidata;

	@RequestMapping(value = "goto", method = RequestMethod.GET)
	public String gotoVeiw() {
		return "bonus/bonuslevel";
	}

	@ResponseBody
	@RequestMapping(value = "queryAll", method = RequestMethod.GET)
	public Map<String, Object> queryAll(int curPage, int pageItem, String userName, String userPhone, String userCode,
			String startDate, String endDate) {
		try {
			if (userName.equals(new String(userName.getBytes("iso-8859-1"), "iso-8859-1"))) {
				userName = new String(userName.getBytes("iso-8859-1"), "utf-8");
			}
			if (userCode.equals(new String(userCode.getBytes("iso-8859-1"), "iso-8859-1"))) {
				userCode = new String(userCode.getBytes("iso-8859-1"), "utf-8");
			}
		} catch (Exception e) {

		}
		return bonusValidata.queryAll(curPage, pageItem, userName, userPhone, userCode, startDate, endDate);
	}

	// 导出excel
	@RequestMapping(value = "/getExcel", method = RequestMethod.POST)
	public void getExcel(HttpServletResponse response, String userName, String userPhone, String userCode,
			String startDate, String endDate) {
		try {
			if (userName.equals(new String(userName.getBytes("iso-8859-1"), "iso-8859-1"))) {
				userName = new String(userName.getBytes("iso-8859-1"), "utf-8");
			}
			if (userCode.equals(new String(userCode.getBytes("iso-8859-1"), "iso-8859-1"))) {
				userCode = new String(userCode.getBytes("iso-8859-1"), "utf-8");
			}
		} catch (Exception e) {

		}
		bonusValidata.getExcel(response, userName, userPhone, userCode, startDate, endDate);
	}
}
