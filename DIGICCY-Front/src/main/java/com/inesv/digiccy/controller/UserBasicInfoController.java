package com.inesv.digiccy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.UserBasicInfoDto;
import com.inesv.digiccy.query.QueryUserBasicInfo;
import com.inesv.digiccy.validata.UserVoucherValidate;
import com.inesv.digiccy.validata.user.OpUserValidata;
import com.inesv.digiccy.validata.user.UserBasicInfoValidata;

@Controller
@RequestMapping("/userbasic")
public class UserBasicInfoController {

	@Autowired
	UserBasicInfoValidata userBasicInfoValidate;

	@Autowired
	UserVoucherValidate userVoucherValidate;

	@Autowired
	OpUserValidata opUserValidata;

	@Autowired
	QueryUserBasicInfo queryUserBasicInfo;

	//获取基本信息
	@RequestMapping(value="getInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBasicInfo(int userNo){
		Map<String, Object> map = new HashMap<String, Object>();
		UserBasicInfoDto dto = queryUserBasicInfo.getUserBasicInfo(userNo);
		map.put("code", ResponseCode.SUCCESS);
		map.put("desc", ResponseCode.SUCCESS_DESC);
		map.put("basicState", !(dto == null));
		map.put("basicInfo", dto);
		return map;
	}
	
	// 添加基本信息
	@RequestMapping(value = "addinfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInfo(int userNo, String nationality, String sex, String job, String birthday,
			String realName) {
		Map<String, Object> map = new HashMap<>();
		try {
			job = new String(job.getBytes("iso-8859-1"), "utf-8");
			nationality = new String(nationality.getBytes("iso-8859-1"), "utf-8");
			sex = new String(sex.getBytes("iso-8859-1"), "utf-8");
			realName = new String(realName.getBytes("iso-8859-1"), "utf-8");
			// 添加基本信息
			userBasicInfoValidate.addUserInfo(userNo, nationality, sex, job, birthday, realName);
			UserBasicInfoDto dto = queryUserBasicInfo.getUserBasicInfo(userNo);
			
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("basicinfo", dto);
			map.put("basicUserInfoState", !(dto == null));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
