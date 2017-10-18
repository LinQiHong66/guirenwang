package com.inesv.digiccy.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.api.command.CreateUserCommand;
import com.inesv.digiccy.api.command.LoginLogCommand;
import com.inesv.digiccy.api.command.UserCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.LoginLogDto;
import com.inesv.digiccy.dto.UserBasicInfoDto;
import com.inesv.digiccy.query.QueryUserBasicInfo;
import com.inesv.digiccy.query.QueryUserInfo;
import com.inesv.digiccy.sms.SendMsgUtil;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.validata.UserVoucherValidate;
import com.inesv.digiccy.validata.integra.IntegralRuleValidata;
import com.inesv.digiccy.validata.user.InesvUserValidata;
import com.inesv.digiccy.validata.user.OpUserValidata;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private IntegralRuleValidata ruleData;

	@Autowired
	UserVoucherValidate userVoucherValidate;

	@Autowired
	private QueryUserInfo queryUserInfo;

	@Autowired
	OpUserValidata regUserValidata;

	@Autowired
	QueryUserBasicInfo queryUserBasicInfo;

	@Autowired
	InesvUserValidata inesvUserValidata;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	SendMsgUtil sendMsgUtil;

	@RequestMapping(value = "wel")
	public ModelAndView wel() {

		return new ModelAndView("wel");
	}

	@RequestMapping(value = "loginPage")
	public @ResponseBody Object loginPage() {
		return null;
	}

	/*
	 * @AutoDocMethod(author = DeveloperType.HUANGWEIHANG, createTime = "2016-12-1",
	 * cver = VersionType.V100, name = "测试登录接口", description = "测试登录接口", model =
	 * ModelType.LOGIN, dtoClazz = BaseRes.class, reqParams =
	 * {"tid","uid"},//有参才需要加的 progress = ProgressType.FINISHED)
	 * 
	 * @AutoDocMethodParam(note = "测试id@@用户id", name = "tid@@uid")
	 */
	@RequestMapping(value = "/testindex")
	public ModelAndView testindex(@RequestParam String tid, @RequestParam String uid, HttpServletRequest request) {
		// System.out.println(sendMsgUtil.sendMsg("13580127947", 1)+":121211212");
		redisTemplate.opsForValue().set("tid", tid);
		redisTemplate.opsForValue().set("uid", uid);
		String test = redisTemplate.opsForValue().get("uid").toString();
		// System.out.println(test);
		// System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
		return new ModelAndView("/index");
	}

	// 忘记密码
	@RequestMapping(value = "forgetPassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forgetPassword(String phoneCode, String phone, String newPassword) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> phoneCodeMap = regUserValidata.validataCompare(phone, phoneCode);
		Object code = phoneCodeMap.get("code");
		if ("100".equals(code)) {
			// 短信验证成功
			boolean ok = regUserValidata.modifyLoginPwd(phone, newPassword);
			if (ok) {
				map.put("msg", "修改成功");
				map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
			} else {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
				map.put("msg", "修改失败，请稍后重试");
			}
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("msg", "短信验证失败");
		}
		return map;
	}

	@RequestMapping(value = "login")
	public @ResponseBody Map<String, Object> login(HttpSession session, HttpServletRequest request,
			HttpServletResponse resp, @RequestParam String username, @RequestParam String password,
			@RequestParam String ip) {
		Map<String, Object> map = new HashMap<String, Object>();
		InesvUserDto user = queryUserInfo.loadUser(username, password);

		if (user == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "用户名或密码错误！");
			return map;
		}
		if (user.getState() != 1) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该用户未启动，请联系管理人员！");
			return map;
		}
		if (ip == null || ip.equals("")) { 
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "IP地址不能为空");
			return map;
		}
		String valtoken = (String) redisTemplate.opsForValue().get(username);// (String)
																				// redisTemplate.opsForValue().get(username);
		if (valtoken != null) {
			Date lastDate = (Date) redisTemplate.opsForValue().get(valtoken + ":lastTime");
			Date curDate = new Date(System.currentTimeMillis());
			if (lastDate != null) {
				Long secend = curDate.getTime() - lastDate.getTime();
				if (secend <= 10 * 1000) {
					map.put("code", ResponseCode.FAIL);
					map.put("desc", "在别处已登录！！！");
					return map;
				}
			}
		}
		if (user != null) {
			String tokens = request.getParameter("token");
			try {
				// redisTemplate.delete(tokens);
			} catch (Exception e) {

			}
			Long tokenStr = user.getId() + new Date().getTime();
			String token = new MD5().getMD5(String.valueOf(tokenStr));
			UserBasicInfoDto basicUserInfo = queryUserBasicInfo.getUserBasicInfo(user.getUser_no());

			redisTemplate.opsForValue().set(username, token, 7, TimeUnit.DAYS);
			redisTemplate.opsForValue().set(token + "getuserNo", user.getId(), 7, TimeUnit.DAYS);
			redisTemplate.opsForValue().set(token, token, 7, TimeUnit.DAYS);
			redisTemplate.opsForValue().set(token + ":lastTime", new Date(System.currentTimeMillis()));

			session.setAttribute("userName", username);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			user.setPassword("******");
			user.setDeal_pwd("******");
			Map<String, Object> isValidata = inesvUserValidata.isPealPwd(user.getUser_no());
			if ("100".equals(isValidata.get("code"))) {
				user.setDeal_pwdstate(1);
			} else {
				user.setDeal_pwdstate(0);
			}
			map.put("loginUserInfo", user);
			map.put("token", token);
			Map<String, Object> voucherMap = userVoucherValidate.getValidateInfo(user.getUser_no());
			map.put("validateState", voucherMap.get("validateState"));
			map.put("validateInfo", voucherMap.get("validateInfo"));

			map.put("basicinfo", basicUserInfo);
			map.put("basicUserInfoState", !(basicUserInfo == null));
			LoginLogCommand loginLogCommand = new LoginLogCommand(user.getUser_no(), 1, "通过用户名登录", ip, "", 1,
					new Date());

			ruleData.addIntegral(user.getId(), "denglu");

			commandGateway.send(loginLogCommand);
		} else {
			map.put("code", ResponseCode.FAIL);
			// map.put("msg", ResponseCode.FAIL_DESC);
			map.put("desc", "用户账号密码不正确！！！");

		}
		return map;
	}

	@RequestMapping(value = "getLoginInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getLoginInfoByToken(String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long id = (Long) redisTemplate.opsForValue().get(token + "getuserNo");
		InesvUserDto dto = queryUserInfo.getUserInfoById(id);
		if (dto != null) {
			UserBasicInfoDto basicUserInfo = queryUserBasicInfo.getUserBasicInfo(dto.getUser_no());
			Map<String, Object> voucherMap = userVoucherValidate.getValidateInfo(dto.getUser_no());

			map.put("validateState", voucherMap.get("validateState"));
			map.put("validateInfo", voucherMap.get("validateInfo"));
			map.put("basicinfo", basicUserInfo);
			map.put("basicUserInfoState", !(basicUserInfo == null));
			map.put("loginUserInfo", dto);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", "获取用户信息失败!");
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "获取用户信息失败!");
		}
		return map;
	}

	@RequestMapping(value = "logout")
	public @ResponseBody Map<String, Object> logout(HttpSession session, HttpServletRequest request,
			HttpServletResponse resp, String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String userName = (String) session.getAttribute("userName");
			if (userName != null && !"".equals(userName)) {
				redisTemplate.delete(userName);
			}
			redisTemplate.delete(token + "getuserNo");
			redisTemplate.delete(token);
			redisTemplate.delete(token + ":lastTime");
			map.put("code", ResponseCode.SUCCESS);
			map.put("msg", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL_LOGOUT);
			map.put("msg", ResponseCode.FAIL_LOGOUT_DESC);
		}
		return map;
	}

	@RequestMapping(value = "getLoginLogInfo", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getLoginLogInfo(Integer userNo) {
		Map<String, Object> map = new HashMap<>();
		List<LoginLogDto> list = queryUserInfo.getLoginLogInfo(userNo);
		if (list == null || list.size() == 0) {
			map.put("code", ResponseCode.FAIL_USER_IP_LOG);
			map.put("desc", ResponseCode.FAIL_USER_IP_LOG_DESC);
		} else {
			map.put("data", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public void create(String username, String password) {
		CreateUserCommand command = new CreateUserCommand(2L, username, password);
		commandGateway.send(command);
	}

	/*
	 * 测试
	 */
	@RequestMapping(value = "getUsers")
	@ResponseBody
	public Map<String, Object> getUsers() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<InesvUserDto> user = queryUserInfo.getAllUsers();
		resultMap.put("data", user);
		return resultMap;
	}

	/*
	 * 测试
	 */
	@RequestMapping(value = "updateUsers")
	@ResponseBody
	public Map<String, Object> updateUsers(Long id, int user_no) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserCommand UserCommand = new UserCommand(id, "123456789", user_no, "1", "1", "1", "1", "1", "1", "1", 0,
				"updateUsers");
		commandGateway.send(UserCommand);
		resultMap.put("data", UserCommand);
		return resultMap;
	}

	/*
	 * 测试
	 */
	@RequestMapping(value = "addUsers")
	@ResponseBody
	public Map<String, Object> addUsers(InesvUserDto userDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserCommand userCommand = new UserCommand(userDto.getId(), "123456789", userDto.getUser_no(), "1", "1", "1",
				"1", "1", "1", "1", 0, "addUsers");
		commandGateway.send(userCommand);
		resultMap.put("data", userCommand);
		return resultMap;
	}

	/*
	 * 测试
	 */
	@RequestMapping(value = "deleteUsers")
	public @ResponseBody Map<String, Object> deleteUsers(InesvUserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		UserCommand userCommand = new UserCommand(userDto.getUser_no(), "deleteUsers");
		commandGateway.send(userCommand);
		resultMap.put("data", userCommand);
		return resultMap;
	}

}
