package com.inesv.digiccy.validata.user;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;
import com.inesv.digiccy.api.command.InesvPhoneCommand;
import com.inesv.digiccy.api.command.MessageLogCommand;
import com.inesv.digiccy.api.command.RegUserCommand;
import com.inesv.digiccy.api.command.UserVoucherCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.MessageLogDto;
import com.inesv.digiccy.dto.MessageSetDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.dto.UserVoucherDto;
import com.inesv.digiccy.persistence.reg.RegUserPersistence;
import com.inesv.digiccy.persistence.sequence.SequenceOper;
import com.inesv.digiccy.query.QueryMessageSet;
import com.inesv.digiccy.query.QueryMyRecInfo;
import com.inesv.digiccy.query.QueryProvinceAbbreviation;
import com.inesv.digiccy.query.QueryUserInfo;
import com.inesv.digiccy.query.QueryUserNamePhoneInfo;
import com.inesv.digiccy.redis.RedisCodeImpl;
import com.inesv.digiccy.sms.SendMsgUtil;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.util.SmsUtil;
import com.inesv.digiccy.util.StringUtil;
import com.inesv.digiccy.validata.integra.IntegralRuleValidata;
import com.inesv.digiccy.validata.util.organization.OrganizationStructureResult;
import com.inesv.digiccy.validata.util.organization.OrganizationStructureUtil;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
@Component
public class OpUserValidata {
	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	RedisCodeImpl redisCode;

	@Autowired
	private IntegralRuleValidata ruleData;
	
	@Autowired
	SendMsgUtil sendMsgUtil;

	@Autowired
	QueryUserNamePhoneInfo queryUserNamePhoneInfo;

	@Autowired
	QueryUserInfo queryUser;

	@Autowired
	QueryMyRecInfo queryMyRecInfo;

	@Autowired
	SequenceOper sequenceOper;

	@Autowired
	QueryProvinceAbbreviation queryProvinceAbbreviation;

	@Autowired
	RegUserPersistence regUserPersistence;

	@Autowired
	QueryMessageSet queryMessageSet;

	/*
	 * 测试
	 */
	public Map<String, Object> validateGetUsers() {
		Map<String, Object> map = new HashMap<>();
		List<InesvUserDto> list = queryUser.getAllUsers();
		if (list == null) {
			map.put("code", ResponseCode.FAIL_BILL_INFO);
			map.put("desc", ResponseCode.FAIL_BILL_INFO_DESC);
		} else {
			map.put("data", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	// 修改状态
	public Map<String, Object> modifyVoucher(int userNo, int state) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserVoucherDto> vouchers = queryUser.getAllVoucher("userNo", userNo + "");
		if (vouchers != null && !vouchers.isEmpty()) {
			UserVoucherDto dto = vouchers.get(0);
			UserVoucherCommand command = new UserVoucherCommand();
			command.setUserNo(userNo);
			command.setState(state);
			command.setRealName(dto.getTrueName());
			command.setCardType(dto.getCardType());
			command.setCardId(dto.getCardId());
			command.setOperating("modifystate");
			try {
				commandGateway.send(command);
				map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
			} catch (Exception e) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
			}
		} else {

			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	// 更改登陆密码
	public boolean modifyLoginPwd(String phone, String password) {
		boolean ok = false;
		password = new MD5().getMD5(password==null?"":password);
		try {
			int size = regUserPersistence.modifyPassword(phone, password);
			ok = size > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
	}

	// 根据条件查询
	public Map<String, Object> getAllVoucher(String filed, String value) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserVoucherDto> vouchers = queryUser.getAllVoucher(filed, value);
		if (vouchers != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("result", vouchers);
		} else {
			map.put("code", ResponseCode.FAIL_BILL_INFO);
			map.put("desc", ResponseCode.FAIL_BILL_INFO_DESC);
			map.put("result", "none");
		}
		return map;
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public Map<String, Object> validataGetAllUser(String username, String phone, int state, int curpage, int pageItem) {
		Map<String, Object> map = new HashMap<>();
		List<InesvUserDto> list = queryUser.getAllUser(username, phone, state, curpage, pageItem);
		long size = queryUser.getSize(username, phone, state);
		if (list == null) {
			map.put("code", ResponseCode.FAIL_BILL_INFO);
			map.put("desc", ResponseCode.FAIL_BILL_INFO_DESC);
		} else {
			map.put("data", list);
			map.put("count", size);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public Map<String, Object> validataGetUserByPhone(String phone) {
		Map<String, Object> map = new HashMap<>();
		List<InesvUserDto> list = queryUser.getUserByPhone(phone);
		if (list == null) {
			map.put("code", ResponseCode.FAIL_BILL_INFO);
			map.put("desc", ResponseCode.FAIL_BILL_INFO_DESC);
		} else {
			map.put("data", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	public Map<String, Object> validataGetUserInfoById(Long id) {
		Map<String, Object> map = new HashMap<>();
		InesvUserDto info = queryUser.getUserInfoById(id);
		if (info == null) {
			map.put("code", ResponseCode.FAIL_BILL_INFO);
			map.put("desc", ResponseCode.FAIL_BILL_INFO_DESC);
		} else {
			map.put("data", info);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	public Map<String, Object> validataGetUserInfoByNo(int userNo) {
		Map<String, Object> map = new HashMap<>();
		InesvUserDto info = queryUser.getUserInfoByNo(userNo);
		if (info == null) {
			map.put("code", ResponseCode.FAIL_BILL_INFO);
			map.put("desc", ResponseCode.FAIL_BILL_INFO_DESC);
		} else {
			map.put("data", info);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	/**
	 * 是否存在相同手机号 身份证号码 用户编码的记录
	 * 
	 * @param userNo
	 * @param phone
	 * @param idcard
	 * @return
	 */
	public boolean isRecordExsit(int userNo, String phone, String idcard) {
		if (phone == null) {
			phone = "";
		}
		if (idcard == null) {
			idcard = "";
		}
		List<InesvUserDto> info = queryUser.getUserInfo(userNo, phone, idcard);
		if (info != null) {
			if (info.size() > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * 新增用户
	 */
	public synchronized Map<String, Object> validataRegUser(String phone, String password, String invite_num) {
		Map<String, Object> map = new HashMap<>();
		try {
			if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)) {
				map.put("code", ResponseCode.REG_FAIL_NULL);
				map.put("desc", ResponseCode.REG_FAIL_NULL_DESC);
				return map;
			}
			if (StringUtils.isBlank(invite_num)) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "邀请码不允许为空！");
				return map;
			}
			UserInfoDto databaseReguserInfo = queryMyRecInfo.queryUserInfoByUserName(phone);// 查询出注册用户的用户编号
			if (databaseReguserInfo != null) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "该手机号已经注册过用户！");
				return map;
			}
			UserInfoDto parentUserInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(invite_num);// 根据邀请码查询出此邀请码的用户信息
			List<String> provinceAbbreviationList = queryProvinceAbbreviation.getAllProvinceAbbreviation();// 获取省份简写信息
			OrganizationStructureUtil organizationStructureUtil = new OrganizationStructureUtil(invite_num,
					parentUserInfoDto, provinceAbbreviationList, sequenceOper);
			// 判断顶级机构是否已经存在
			if (organizationStructureUtil.isTopOrgUser() && queryMyRecInfo.queryUserInfoByOrgCode(invite_num) != null) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "机构编号已经存在");
				return map;
			}
			OrganizationStructureResult organizationStructureResult = organizationStructureUtil.structure();// 解析验证并返回对应的机构信息
			if (!organizationStructureResult.isSuccess()) {
				// 未通过验证
				map.put("code", ResponseCode.FAIL);
				map.put("desc", organizationStructureResult.getErrorMsg());
				return map;
			}
			String recCode = createRecCodeByOrgType(organizationStructureResult.getOrg_type());// 获取邀请码
			InesvUserDto inesvUserDto = new InesvUserDto();
			inesvUserDto.setUsername(phone);
			inesvUserDto.setPhone(phone);
			inesvUserDto.setPassword(new MD5().getMD5(password));
			inesvUserDto.setInvite_num(recCode);
			inesvUserDto.setOrg_code(organizationStructureResult.getOrg_code());// 设置用户机构编码
			inesvUserDto.setOrg_type(organizationStructureResult.getOrg_type());// 设置用户机构等级
			inesvUserDto.setOrg_parent_code(organizationStructureResult.getOrg_parent_code());// 设置用户上级机构编码
			inesvUserDto.setState(1);
			inesvUserDto.setDate(new Date());
			if (organizationStructureResult.getOrg_type() == 0) {
				inesvUserDto.setState(0);// 机构用户，暂不能登陆，需要后台审核
			}
			long userNo = regUserPersistence.addUser(inesvUserDto, recCode, invite_num);
			map.put("userNo", userNo);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			// 增加积分
		    ruleData.addIntegral(parentUserInfoDto.getId(),"yaoqingyonghu");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "注册失败！");
		}
		return map;
	}
	/**
	 * 新增用户
	 */
	/*
	 * public Map<String , Object> validataRegUser(String phone,String password,
	 * String invite_num){ Map<String , Object> map = new HashMap<>(); try {
	 * if(phone.equals("") || password.equals("") ){
	 * map.put("code",ResponseCode.REG_FAIL_NULL);
	 * map.put("desc",ResponseCode.REG_FAIL_NULL_DESC); return map; }
	 * if(StringUtils.isBlank(invite_num)){ map.put("code",ResponseCode.FAIL);
	 * map.put("desc","邀请码不允许为空"); return map; } UserInfoDto databaseReguserInfo =
	 * queryMyRecInfo.queryUserInfoByUserName(phone);//查询出注册用户的用户编号
	 * if(databaseReguserInfo!=null){ map.put("code",ResponseCode.FAIL);
	 * map.put("desc","该手机号已经注册过用户"); return map; } UserInfoDto parentUserInfoDto =
	 * queryMyRecInfo.queryUserInfoByInvitNum(invite_num);//根据邀请码查询出此邀请码的用户信息
	 * List<String>
	 * provinceAbbreviationList=queryProvinceAbbreviation.getAllProvinceAbbreviation
	 * ();//获取省份简写信息 OrganizationStructureUtil organizationStructureUtil=new
	 * OrganizationStructureUtil(invite_num, parentUserInfoDto,
	 * provinceAbbreviationList, sequenceOper); //判断顶级机构是否已经存在
	 * if(organizationStructureUtil.isTopOrgUser()&&queryMyRecInfo.
	 * queryUserInfoByOrgCode(invite_num)!=null){ map.put("code",ResponseCode.FAIL);
	 * map.put("desc","机构编号已经存在"); return map; } OrganizationStructureResult
	 * organizationStructureResult=organizationStructureUtil.structure();//
	 * 解析验证并返回对应的机构信息 if(!organizationStructureResult.isSuccess()){ //未通过验证
	 * map.put("code",ResponseCode.FAIL);
	 * map.put("desc",organizationStructureResult.getErrorMsg()); return map; }
	 * String
	 * recCode=createRecCodeByOrgType(organizationStructureResult.getOrg_type());//
	 * 获取邀请码 System.out.println("======================酷酷酷酷酷酷 =recCode======"+
	 * recCode +"========================================="); RegUserCommand command
	 * = new RegUserCommand(0,phone,0,new
	 * MD5().getMD5(password),null,null,null,null,null,phone,0,recCode,new
	 * Date(),"insert");//新增用户信息
	 * command.setOrg_code(organizationStructureResult.getOrg_code());//设置用户机构编码
	 * command.setOrg_type(organizationStructureResult.getOrg_type());//设置用户机构等级
	 * command.setOrg_parent_code(organizationStructureResult.getOrg_parent_code());
	 * //设置用户上级机构编码 commandGateway.sendAndWait(command); if(recCode!=null){
	 * sequenceOper.addSequence(recCode, 1);//新增对应邀请码的序列 } RegUserCommand upcommand
	 * = new RegUserCommand(0, "", 0, "", "", "", "", "", "", phone, 0, "", new
	 * Date(), "updateId"); commandGateway.send(upcommand);
	 * System.out.println("invitenum:::::::::::::::"+invite_num);
	 * if(!"".equals(invite_num) && invite_num != null){ UserInfoDto userInfoDto =
	 * queryMyRecInfo.queryUserInfoByInvitNum(invite_num);//根据邀请码查询出此邀请码的用户信息
	 * if(userInfoDto != null){ int userNo = userInfoDto.getUser_no(); //获取推荐码用户的编号
	 * UserInfoDto ReguserInfo =
	 * queryMyRecInfo.queryUserInfoByUserName(phone);//查询出注册用户的用户编号 int ReguserNo =
	 * ReguserInfo.getUser_no();//获取注册用户编号 MyrecCommand command1 = new
	 * MyrecCommand(2121L,userNo,ReguserNo,1,0,new Date(),"inserRecUser");
	 * commandGateway.send(command1); } } map.put("code", ResponseCode.SUCCESS);
	 * map.put("desc",ResponseCode.SUCCESS_DESC); }catch (Exception e){
	 * e.printStackTrace(); map.put("code",ResponseCode.FAIL);
	 * map.put("desc",ResponseCode.FAIL_DESC); } return map; }
	 */

	/**
	 * 新增用户(5级机构)
	 */
	/*
	 * public Map<String , Object> validataRegUsers(String phone,String password,
	 * String invite_num){ Map<String , Object> map = new HashMap<>(); try {
	 * if(phone.equals("") || password.equals("")){
	 * map.put("code",ResponseCode.REG_FAIL_NULL);
	 * map.put("desc",ResponseCode.REG_FAIL_NULL_DESC); return map; }
	 * if(StringUtils.isBlank(invite_num)){ map.put("code",ResponseCode.FAIL);
	 * map.put("desc","邀请码不允许为空"); return map; } UserInfoDto databaseReguserInfo =
	 * queryMyRecInfo.queryUserInfoByUserName(phone);//查询出注册用户的用户编号
	 * if(databaseReguserInfo!=null){ map.put("code",ResponseCode.FAIL);
	 * map.put("desc","该手机号已经注册过用户"); return map; } UserInfoDto parentUserInfoDto =
	 * queryMyRecInfo.queryUserInfoByInvitNum(invite_num);//根据邀请码查询出此邀请码的用户信息（邀请人信息）
	 * List<String>
	 * provinceAbbreviationList=queryProvinceAbbreviation.getAllProvinceAbbreviation
	 * ();//获取省份简写信息 OrganizationStructureUtil organizationStructureUtil=new
	 * OrganizationStructureUtil(invite_num, parentUserInfoDto,
	 * provinceAbbreviationList, sequenceOper); //判断顶级机构是否已经存在
	 * if(organizationStructureUtil.isTopOrgUser() &&
	 * queryMyRecInfo.queryUserInfoByOrgCode(invite_num)!=null){
	 * map.put("code",ResponseCode.FAIL); map.put("desc","机构编号已经存在"); return map; }
	 * OrganizationStructureResult
	 * organizationStructureResult=organizationStructureUtil.structure();//
	 * 解析验证并返回对应的机构信息 if(!organizationStructureResult.isSuccess()){ //未通过验证
	 * map.put("code",ResponseCode.FAIL);
	 * map.put("desc",organizationStructureResult.getErrorMsg()); return map; }
	 * String
	 * recCode=createRecCodeByOrgType(organizationStructureResult.getOrg_type());//
	 * 获取邀请码 RegUserCommand command = new RegUserCommand(0,phone,0,new
	 * MD5().getMD5(password),null,null,null,null,null,phone,0,recCode,new
	 * Date(),"insert");
	 * command.setOrg_code(organizationStructureResult.getOrg_code());
	 * command.setOrg_type(organizationStructureResult.getOrg_type());
	 * command.setOrg_parent_code(organizationStructureResult.getOrg_parent_code());
	 * commandGateway.sendAndWait(command); if(recCode!=null){
	 * sequenceOper.addSequence(recCode, 1);//新增对应邀请码的序列 } RegUserCommand upcommand
	 * = new RegUserCommand(0, "", 0, "", "", "", "", "", "", phone, 0, "", new
	 * Date(), "updateId"); commandGateway.send(upcommand);
	 * if(!"".equals(invite_num) && invite_num != null){ UserInfoDto userInfoDto =
	 * queryMyRecInfo.queryUserInfoByInvitNum(invite_num);//根据邀请码查询出此邀请码的用户信息
	 * if(userInfoDto != null){ int userNo = userInfoDto.getUser_no(); //获取推荐码用户的编号
	 * UserInfoDto ReguserInfo =
	 * queryMyRecInfo.queryUserInfoByUserName(phone);//查询出注册用户的用户编号- int ReguserNo =
	 * ReguserInfo.getUser_no();//获取注册用户编号 MyrecCommand command1 = new
	 * MyrecCommand(2121L,userNo,ReguserNo,1,0,new Date(),"inserRecUser");
	 * commandGateway.send(command1); } } map.put("code", ResponseCode.SUCCESS);
	 * map.put("desc",ResponseCode.SUCCESS_DESC); }catch (Exception e){
	 * e.printStackTrace(); map.put("code",ResponseCode.FAIL);
	 * map.put("desc",ResponseCode.FAIL_DESC); } return map;
	 * 
	 * }
	 */

	// 目前只有顶级机构和经纪人可以生成推荐码
	private String createRecCodeByOrgType(Integer orgType) {
		if (orgType >= 3) {
			return null;
		}
		return creatRecCode().toUpperCase();
	}

	/** 生成推荐码 */
	public String creatRecCode() {
		String result = getCode();

		UserInfoDto userInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(result);
		// if(userInfoDto != null){
		// result = getCode();
		// }
		boolean ok = userInfoDto != null;
		while (ok) {
			result = getCode();
			userInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(result);
			ok = userInfoDto != null;
		}

		return result;
	}

	public String getCode() {

		String result = "";
		for (int i = 0; i < 7; i++) {
			int intVal = (int) (Math.random() * 26 + 97);
			result = result + (char) intVal;
		}
		return result;
	}

	/**
	 * 忘记用户密码
	 */
	public Map<String, Object> validataUpdatePwd(String username, String password) {
		Map<String, Object> result = new HashMap<>();
		List<InesvUserDto> list = queryUserNamePhoneInfo.getUserNamePhoneInfo(username);
		List<InesvUserDto> dtos = queryUserNamePhoneInfo.getUserNamePhoneInfo(username);
		if (dtos.size() > 0) {
			if (list.isEmpty()) {
				result.put("code", ResponseCode.FAIL);
				result.put("desc", ResponseCode.FAIL_DESC);
				result.put("result", "修改失败");
			} else {
				InesvUserDto user = list.get(0);
				Long id = user.getId();
				RegUserCommand command = new RegUserCommand(new Integer(id.intValue()), user.getUsername(),
						user.getUser_no(), new MD5().getMD5(password), user.getRegion(), user.getReal_name(),
						user.getCertificate_num(), user.getDeal_pwd(), user.getMail(), user.getPhone(), user.getState(),
						user.getInvite_num(), user.getDate(), "update");
				commandGateway.sendAndWait(command);
				result.put("code", ResponseCode.SUCCESS);
				result.put("desc", ResponseCode.SUCCESS_DESC);
			}
		} else {
			result.put("result", "不存在该号码");
			result.put("code", ResponseCode.FAIL);
			result.put("desc", ResponseCode.FAIL_DESC);
		}
		return result;
	}

	/**
	 * 校验短信验证码
	 */
	public Map<String, Object> validataCompare(String mobile, String code) {
		Map<String, Object> map = new HashMap<>();
		int smsNum = redisCode.getSms(mobile, 1);// 获取缓存里里面的验证码
		// int smsNum=725582;
		// 通用 手机号码验证
		if (code.equals(smsNum + "")) {
			// redisCode.delete(mobile, 1);//清除缓存
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);

		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 发送短信
	 *
	 * @param mobile
	 * @param type
	 * @return
	 */
	public Map<String, Object> validataSend(String mobile, int type) {
		int mCode = sendMsgUtil.getCode(mobile, type);
		InesvUserDto inesvUserDto = queryUser.loadUserByPhoneNumber(mobile);
		redisCode.setSms(mobile, type, mCode);
		MessageSetDto messageSetDto = queryMessageSet.getMessageSet();
		Map<String, Object> map = new HashMap<>();
		String  msgContent = null;
		try {
			if (messageSetDto != null) {
				int limitNumber = messageSetDto.getLimit_number();// 限制次数
				int limitDate = messageSetDto.getLimit_date();// 限制时间
				if (inesvUserDto != null) {
					List<MessageLogDto> messageLogs = queryUser.getMessageLogLimitTime(inesvUserDto.getUser_no(),
							limitDate);
					if (messageLogs != null && messageLogs.size() >= limitNumber) {// 超过次数
						map.put("code", ResponseCode.FAIL);
						map.put("desc", ResponseCode.FAIL_DESC);
						map.put("message", "限制发送短信");
						return map;
					}
				}
				msgContent = SmsUtil.sendMySms(mobile, mCode + "");
			} else {// 没有设置限制信息
				msgContent = SmsUtil.sendMySms(mobile, mCode + "");
			}
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		InesvPhoneCommand command = new InesvPhoneCommand(0, null, mobile, 1, mCode, "insert");
		commandGateway.sendAndWait(command);
		System.out.println("!StringUtil.isEmpty(msgContent):"+!StringUtil.isEmpty(msgContent));
		System.out.println("inesvUserDto != null:"+inesvUserDto != null);
		if (!StringUtil.isEmpty(msgContent)) {
			// 如果验证码发送成功则将发送短信日志写到表里面
			if (inesvUserDto != null) {
				modifyMessageLog(inesvUserDto, msgContent);
			}
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("validataCode", mCode);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("message", "发送失败");
		}
		return map;
	}

	/**
	 * 判断该用户是否已注册
	 * 
	 * @param phone
	 * @return
	 */
	public Map<String, Object> phoneIsUnique(String phone) {
		Map<String, Object> result = new HashMap<>();
		InesvUserDto user = queryUser.getPhoneIsUnique(phone);
		if (user != null) {// 不为说明该手机号已经存在了（已有该手机号对应的用户）
			result.put("code", ResponseCode.REG_FAIL_NOT_UNIQUE);
			result.put("desc", ResponseCode.REG_FAIL_NOT_UNIQUE_DESC);
		} else {
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return result;
	}

	/*
	 * 用户数据统计
	 */
	public Map<String, Object> validateUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		InesvUserDto dto = queryUser.queryUser();
		map.put("sumUser", dto.getUsername());
		map.put("dayUser", dto.getPhone());
		map.put("sumUser0", dto.getPassword());
		map.put("sumUser1", dto.getRegion());
		map.put("sumUser2", dto.getMail());
		map.put("sumUser3", dto.getAlipay());
		map.put("dayUser2", dto.getState());
		map.put("dayUser3", dto.getInvite_num());
		return map;
	}

	private void modifyMessageLog(InesvUserDto inesvUserDto, String smsContent) {
		MessageLogCommand command = new MessageLogCommand();
		command.setPhone_number(inesvUserDto.getPhone());
		command.setReceive_name(inesvUserDto.getReal_name());
		command.setSms_content(smsContent);
		command.setUpdate_time(new Date());
		command.setUser_id(inesvUserDto.getId());
		command.setOperation("insert");
		commandGateway.sendAndWait(command);
	}
}
