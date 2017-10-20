package com.inesv.digiccy.back.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.PoundatgeValidata;

@Controller
@RequestMapping("/poundatge")
public class PoundatgeController {

	@Autowired
	PoundatgeValidata poundatgeValidata;

	@RequestMapping(value = "goto", method = RequestMethod.GET)
	public String gotoVeiw() {
		return "entrust/getPoundatge";
	}

	@ResponseBody
	@RequestMapping(value = "queryAll", method = RequestMethod.GET)
	public Map<String, Object> queryAll(int curPage, int pageItem, String userOrgCode, String phone, String username,
			String startDate, String endDate) {
		try {
			if (userOrgCode.equals(new String(userOrgCode.getBytes("iso-8859-1"), "iso-8859-1"))) {
				userOrgCode = new String(userOrgCode.getBytes("iso-8859-1"), "utf-8");
			}
			if (username.equals(new String(username.getBytes("iso-8859-1"), "iso-8859-1"))) {
				username = new String(username.getBytes("iso-8859-1"), "utf-8");
			}
			System.out.println(userOrgCode);
		} catch (Exception e) {

		}
		return poundatgeValidata.queryAll(curPage, pageItem, userOrgCode, phone, username, startDate, endDate);
	}

	// 导出excel
	@RequestMapping(value = "/getExcel", method = RequestMethod.POST)
	public void getExcel(HttpServletResponse response, String userOrgCode, String phone, String username,
			String startDate, String endDate) {
		poundatgeValidata.getExcel(response, userOrgCode, phone, username, startDate, endDate);
	}
}
