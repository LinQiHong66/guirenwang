package com.inesv.digiccy.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

	// 添加基本信息
	@RequestMapping(value = "addinfo")
	public void addInfo(int userNo, String nationality, String sex, String job, String birthday, String realName) {
		Map<String, Object> map = new HashMap<>();
		try {
			job = new String(job.getBytes("iso-8859-1"), "utf-8");
			nationality = new String(nationality.getBytes("iso-8859-1"), "utf-8");
			sex = new String(sex.getBytes("iso-8859-1"), "utf-8");
			realName = new String(realName.getBytes("iso-8859-1"), "utf-8");
			// 添加基本信息
			userBasicInfoValidate.addUserInfo(userNo, nationality, sex, job, birthday, realName);
			UserBasicInfoDto dto = queryUserBasicInfo.getUserBasicInfo(userNo);
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("basicinfo", dto);
			map.put("basicUserInfoState", !(dto==null));
		} catch (Exception e) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
	}
}
