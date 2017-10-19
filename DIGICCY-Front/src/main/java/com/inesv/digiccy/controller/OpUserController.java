package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.redis.RedisCodeImpl;
import com.inesv.digiccy.sms.SendMsgUtil;
import com.inesv.digiccy.validata.user.InesvUserValidata;
import com.inesv.digiccy.validata.user.OpUserValidata;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.UnsupportedEncodingException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
@Controller
@RequestMapping("/reg")
public class OpUserController {
	@Autowired
	CommandGateway commandGateway;

	@Autowired
	OpUserValidata regUserValidata;
	
	@Autowired
	InesvUserValidata inesvUserValidata;

	@Autowired
	SendMsgUtil sendMsgUtil;

	@Autowired
	RedisCodeImpl redisCode;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	/*    *//**
			 * 新增用户
			 *//*
				 * @AutoDocMethod(author = DeveloperType.LIUMEIJIN, createTime =
				 * "2016-12-8",cver = VersionType.V100, name = "注册", description = "注册新用户接口",
				 * model = ModelType.USER, dtoClazz = BaseRes.class, reqParams = {
				 * "username","password","region","realName","certificateNum","dealPwd","mail",
				 * "phone","invite_num"}, progress = ProgressType.TESTING)
				 * 
				 * @AutoDocMethodParam(note =
				 * "用户名@@用户密码@@用户所属地区@@真实姓名@@身份证号码@@交易密码@@用户邮箱@@用户手机号@@邀请码(可以为空)", name =
				 * "username@@password@@region@@realName@@certificateNum@@dealPwd@@mail@@phone@@invite_num")
				 * 
				 * @RequestMapping(value = "addUser",method = RequestMethod.POST)
				 * 
				 * @ResponseBody public Map<String,Object> addUser(String username,String
				 * password,String region,String realName,String certificateNum,String
				 * dealPwd,String mail,String phone,String invite_num){ Map<String,Object> map =
				 * regUserValidata.validataRegUser(username,password,region,realName,
				 * certificateNum,dealPwd,mail,phone,invite_num); return map; }
				 */

	/**
	 * 新增用户
	 */
	/*
	 * @AutoDocMethod(author = DeveloperType.LIUMEIJIN, createTime =
	 * "2016-12-8",cver = VersionType.V100, name = "注册", description = "注册新用户接口",
	 * model = ModelType.USER, dtoClazz = BaseRes.class, reqParams = {
	 * "username","password","region","realName","certificateNum","dealPwd","mail",
	 * "phone","invite_num"}, progress = ProgressType.TESTING)
	 * 
	 * @AutoDocMethodParam(note =
	 * "用户名@@用户密码@@用户所属地区@@真实姓名@@身份证号码@@交易密码@@用户邮箱@@用户手机号@@邀请码(可以为空)", name =
	 * "username@@password@@region@@realName@@certificateNum@@dealPwd@@mail@@phone@@invite_num")
	 */
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUser(String phone, String password, String invite_num) {

		try {
			invite_num = new String(invite_num.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Long count = redisTemplate.opsForValue().increment("phone1_" + phone, 1);

		if (count == 1) {
			redisTemplate.expire("phone1_" + phone, 5, TimeUnit.SECONDS);
		}
		if (count > 1) {

			Map<String, Object> map = new HashMap<>();

			map.put("code", ResponseCode.FAIL);

			map.put("desc", "频繁操作，请稍后重试" + count);

			return map;
		}

		redisTemplate.opsForValue().set(phone, phone, 30, TimeUnit.SECONDS);
		Map<String, Object> map = regUserValidata.validataRegUser(phone, password, invite_num);
		map.put("count", count);
		return map;

	}

	/**
	 * 判断该用户名是否已注册
	 */
	@RequestMapping(value = "phoneIsUnique", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> phoneIsUnique(String phone) {
		Map<String, Object> result = regUserValidata.phoneIsUnique(phone);
		return result;
	}

	/**
	 * 忘记用户密码
	 */
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePwd(String username, String password) {
		Map<String, Object> result = regUserValidata.validataUpdatePwd(username, password);
		return result;
	}

	/**
	 * 校验短信验证码
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "compare", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> compare(@RequestParam String mobile, @RequestParam String code) {
		Map<String, Object> map = regUserValidata.validataCompare(mobile, code);
		return map;

	}

	/**
	 * 发短信
	 */
	@RequestMapping(value = "sendMobile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendMobile(HttpServletRequest request, @RequestParam String mobile) throws IOException {
		int optype = 1;
		Map<String, Object> map = send(mobile, optype);
		return map;
	}

	/**
	 * 发送短信
	 *
	 * @param mobile
	 * @param type
	 * @return
	 */
	public Map<String, Object> send(String mobile, int type) {
		Map<String, Object> map = regUserValidata.validataSend(mobile, type);
		return map;
	}
	
	/**
	 * 发短信
	 */
	@RequestMapping(value = "getUser1", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUser1() throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		map = inesvUserValidata.getUserPicture();
		return map;
	}

}
