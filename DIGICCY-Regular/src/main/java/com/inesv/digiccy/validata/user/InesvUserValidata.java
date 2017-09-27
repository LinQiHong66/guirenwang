package com.inesv.digiccy.validata.user;

import com.inesv.digiccy.api.command.CreateInesvUserCommand;
import com.inesv.digiccy.api.command.RegUserCommand;
import com.inesv.digiccy.api.command.UserCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.dto.UserVoucherDto;
import com.inesv.digiccy.persistence.sequence.SequenceOper;
import com.inesv.digiccy.query.QueryMyRecInfo;
import com.inesv.digiccy.query.QueryProvinceAbbreviation;
import com.inesv.digiccy.query.QuerySubCore;
import com.inesv.digiccy.query.QueryUserVoucher;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.validata.util.organization.OrganizationStructureResult;
import com.inesv.digiccy.validata.util.organization.OrganizationStructureUtil;

import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
@Component
public class InesvUserValidata {
	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private QuerySubCore querySubCore;
	
	@Autowired
	QueryUserVoucher queryUserVoucher;
	
	@Autowired
    QueryMyRecInfo queryMyRecInfo;
    
    @Autowired
    SequenceOper sequenceOper;
    
    @Autowired
    QueryProvinceAbbreviation queryProvinceAbbreviation;

	/**
	 * 判斷交易密碼
	 * 
	 * @param pealpwd1
	 * @param pealpwd
	 * @return
	 */
	public Map<String, Object> isPealPwd( Integer userNo) {
		Map<String, Object> mac = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
		if (uid == null) {
			mac.put("code", "404");
			mac.put("desc", "不存在该用户");
			 return mac;
		}
		if(uid.getDeal_pwd()==null||uid.getDeal_pwd().trim().equals("")){
			mac.put("code", "200");
			mac.put("desc", "沒有設置交易密碼");
		}else{
			mac.put("code", ResponseCode.SUCCESS);
			mac.put("desc",ResponseCode.SUCCESS_DESC );
		}

		return mac;
	}
	
	/**
	 * 设置交易密码
	 * 
	 * @param pealpwd1
	 * @param pealpwd
	 * @return
	 */
	public Map<String, Object> setPealPwd(String pealpwd, Integer userNo) {
		Map<String, Object> mac = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
		if (uid == null) {
			mac.put("code", "404");
			mac.put("desc", "不存在该用户");
			return mac;
		}
		CreateInesvUserCommand ciuCommand = new CreateInesvUserCommand(userNo, null, null, new MD5().getMD5(pealpwd),
				null, null, null, null, "upDealPwd");
		commandGateway.sendAndWait(ciuCommand);
		mac.put("code", ResponseCode.SUCCESS);
		mac.put("desc", ResponseCode.SUCCESS_DESC);

		return mac;
	}
	
	/*
	 * 测试
	 */
	public Map<String, Object> updateUsers(@RequestParam Integer user_no, @RequestParam String real_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(user_no);
		if (uid == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		try {
			UserCommand command = new UserCommand(0, "", user_no, "", real_name, "", "", "", "", "", 1, "updateUsers1");
			commandGateway.sendAndWait(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
		/*
		 * try { UserCommand command = new
		 * UserCommand(0,username,user_no,"",real_name,mail,phone,
		 * certificate_num,"",alipay,1,"updateUserInfo");
		 * commandGateway.sendAndWait(command);
		 * map.put("code",ResponseCode.SUCCESS);
		 * map.put("desc",ResponseCode.SUCCESS_DESC); }catch (Exception e){
		 * e.printStackTrace(); map.put("code",ResponseCode.FAIL);
		 * map.put("desc",ResponseCode.FAIL_DESC); } return map;
		 * 
		 * //判断用户是否存在 InesvUserDto uid =
		 * querySubCore.getInesvUserByUserNo(userNo); if(uid == null){
		 * map.put("code",ResponseCode.FAIL);
		 * map.put("desc",ResponseCode.FAIL_DESC); return map; } //判断登陆密码 if(new
		 * MD5().getMD5(password1).equals(uid.getPassword())){
		 * CreateInesvUserCommand ciuc = new CreateInesvUserCommand(userNo,new
		 * MD5().getMD5(password),null,null,null,null,null,null,"uppassword");
		 * commandGateway.sendAndWait(ciuc); map.put("code",
		 * ResponseCode.SUCCESS); map.put("desc", ResponseCode.SUCCESS_DESC);
		 * }else{ map.put("code", ResponseCode.FAIL_TRADE_LOGIN_PASSWORD);
		 * map.put("desc", ResponseCode.FAIL_TRADE_LOGIN_PASSWORD_DESC); }
		 * return map;
		 */
	}

	/**
	 * 修改登录密码
	 * 
	 * @param password1
	 * @param password
	 * @return
	 */
	public Map<String, Object> updateInesvUser(String password1, String password, Integer userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
		if (uid == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		// 判断登陆密码
		if (new MD5().getMD5(password1).equals(uid.getPassword())) {
			CreateInesvUserCommand ciuc = new CreateInesvUserCommand(userNo, new MD5().getMD5(password), null, null,
					null, null, null, null, "uppassword");
			commandGateway.sendAndWait(ciuc);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL_TRADE_LOGIN_PASSWORD);
			map.put("desc", ResponseCode.FAIL_TRADE_LOGIN_PASSWORD_DESC);
		}
		return map;
	}

	/**
	 * 修改交易密码
	 * 
	 * @param pealpwd1
	 * @param pealpwd
	 * @return
	 */
	public Map<String, Object> updateDealPwd(String pealpwd1, String pealpwd, Integer userNo) {
		Map<String, Object> mac = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNoWithPwd(userNo);
		if (uid == null) {
			mac.put("code", ResponseCode.FAIL);
			mac.put("desc", ResponseCode.FAIL_DESC);
			return mac;
		}
		System.out.println("----p1--"+new MD5().getMD5(pealpwd1)+"-------------p2--"+uid.getDeal_pwd());
		if (new MD5().getMD5(pealpwd1).equals(uid.getDeal_pwd())) {
			CreateInesvUserCommand ciuCommand = new CreateInesvUserCommand(userNo, null, null,
					new MD5().getMD5(pealpwd), null, null, null, null, "upDealPwd");
			commandGateway.sendAndWait(ciuCommand);
			mac.put("code", ResponseCode.SUCCESS);
			mac.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			mac.put("code", ResponseCode.FAIL);
			mac.put("desc", ResponseCode.FAIL_DESC);
		}
		return mac;
	}

	/**
	 * 绑定手机号
	 * 
	 * @param phone
	 * @return
	 */
	public Map<String, Object> updatePhone(String phone, Integer userNo) {
		Map<String, Object> mapPhone = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
		if (uid == null) {
			mapPhone.put("code", ResponseCode.FAIL);
			mapPhone.put("desc", ResponseCode.FAIL_DESC);
			return mapPhone;
		}
		CreateInesvUserCommand ciucPhone = new CreateInesvUserCommand(userNo, null, phone, null, null, null, null, 1,
				"upPhone");
		commandGateway.sendAndWait(ciucPhone);
		mapPhone.put("code", ResponseCode.SUCCESS);
		mapPhone.put("desc", ResponseCode.SUCCESS_DESC);
		mapPhone.put("userPhone", phone);
		return mapPhone;
	}

	/**
	 * 绑定支付宝账号
	 * 
	 * @param alipay
	 * @return
	 */
	public Map<String, Object> updateAlipay(String alipay, Integer userNo) {
		Map<String, Object> mapAlipay = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
		if (uid == null) {
			mapAlipay.put("code", ResponseCode.FAIL);
			mapAlipay.put("desc", ResponseCode.FAIL_DESC);
			return mapAlipay;
		} else {
			// 判断交易密码是否正确
			/*
			 * if(!uid.getDeal_pwd().equals(new MD5().getMD5(dealPwd))){
			 * mapAlipay.put("code", ResponseCode.FAIL_TRADE_PASSWORD);
			 * mapAlipay.put("desc", ResponseCode.FAIL_TRADE_PASSWORD_DESC);
			 * return mapAlipay; }
			 */
			CreateInesvUserCommand ciucAlipay = new CreateInesvUserCommand(userNo, null, null, null, null, alipay, null,
					null, "upAlipay");
			commandGateway.sendAndWait(ciucAlipay);
			mapAlipay.put("code", ResponseCode.SUCCESS);
			mapAlipay.put("desc", ResponseCode.SUCCESS_DESC);
			mapAlipay.put("userAlipay", alipay);
			return mapAlipay;
		}
	}

	/**
	 * 交易密码输入设置
	 * 
	 * @param pwdState
	 * @param dealPwd1
	 * @return
	 */
	public Map<String, Object> upPwdState(Integer pwdState, String dealPwd1, Integer userNo) {
		Map<String, Object> mapPwdState = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
		if (uid == null) {
			mapPwdState.put("code", ResponseCode.FAIL);
			mapPwdState.put("desc", ResponseCode.FAIL_DESC);
			return mapPwdState;
		}
		if (new MD5().getMD5(dealPwd1).equals(uid.getDeal_pwd())) {
			CreateInesvUserCommand ciucPwdState = new CreateInesvUserCommand(userNo, null, null, null, pwdState, null,
					null, null, "upPwdState");
			commandGateway.sendAndWait(ciucPwdState);
			mapPwdState.put("code", ResponseCode.SUCCESS);
			mapPwdState.put("code", ResponseCode.SUCCESS_DESC);
		} else {
			mapPwdState.put("code", ResponseCode.FAIL);
			mapPwdState.put("code", ResponseCode.FAIL_DESC);
		}
		return mapPwdState;
	}

	/**
	 * 设置双重认证
	 * 
	 * @param validate_pwd
	 * @return
	 */
	public Map<String, Object> upValidatePwd(String validate_pwd, Integer validatePwdState, Integer userNo) {
		Map<String, Object> mapPwdState = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
		if (uid == null) {
			mapPwdState.put("code", ResponseCode.FAIL);
			mapPwdState.put("desc", ResponseCode.FAIL_DESC);
			return mapPwdState;
		}
		CreateInesvUserCommand ciucValidatePwd = new CreateInesvUserCommand(userNo, null, null, validate_pwd,
				validatePwdState, null, null, "upValidatePwd");
		commandGateway.sendAndWait(ciucValidatePwd);
		mapPwdState.put("code", ResponseCode.SUCCESS);
		mapPwdState.put("code", ResponseCode.SUCCESS_DESC);
		return mapPwdState;
	}

	/**
	 * 修改用户资料
	 * 
	 * @param validate_pwd
	 * @param userNo
	 * @return
	 */
	public Map<String, Object> updateUserInfo(String username, int user_no, String real_name, String mail, String phone,
			String certificate_num, String alipay) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserCommand command = new UserCommand(0, username, user_no, "", real_name, mail, phone, certificate_num, "",
					alipay, 1, "updateUserInfo");
			commandGateway.sendAndWait(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public Map<String, Object> updateUserState(int user_no, int state) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserCommand command = new UserCommand(0, "", user_no, "", "", "", "", "", "", "", state, "updateUserState");
			commandGateway.sendAndWait(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public Map<String, Object> updateUserPass(int user_no, String pass, String deal) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			MD5 md5 = new MD5();
			String passMD5 = md5.getMD5(pass);
			String dealMD5 = md5.getMD5(deal);
			UserCommand command = new UserCommand(0, "", user_no, passMD5, "", "", "", "", dealMD5, "", 1,
					"updateUserPass");
			commandGateway.sendAndWait(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 判断绑定手机号与绑定支付宝账号的状态
	 * 
	 * @param userNo
	 * @return
	 */
	public Map<String, Object> phoneAndAlipayState(Integer userNo, String desc) {
		Map<String, Object> mapState = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
		if (uid == null) {
			mapState.put("code", ResponseCode.FAIL);
			mapState.put("desc", ResponseCode.FAIL_DESC);
			return mapState;
		}
		// 判断是否绑定手机号
		if (desc.equals("phone")) {
			if (uid.getPhone() == null || uid.getPhone().equals("")) {
				mapState.put("code", ResponseCode.FAIL_USER_PHONE_STATE_FALSE);
				mapState.put("desc", ResponseCode.FAIL_USER_PHONE_STATE_FALSE_DESC);
				return mapState;
			} else {
				mapState.put("code", ResponseCode.FAIL_USER_PHONE_STATE_TRUE);
				mapState.put("desc", ResponseCode.FAIL_USER_PHONE_STATE_TRUE_DESC);
				mapState.put("userPhone", uid.getPhone());
				return mapState;
			}
		}
		// 判断是否绑定支付宝号
		if (desc.equals("alipay")) {
			if (uid.getAlipay() == null || uid.getAlipay().equals("")) {
				mapState.put("code", ResponseCode.FAIL_USER_ALIPAY_STATE_FALSE);
				mapState.put("desc", ResponseCode.FAIL_USER_ALIPAY_STATE_FALSE_DESC);
				return mapState;
			} else {
				mapState.put("code", ResponseCode.FAIL_USER_ALIPAY_STATE_TRUE);
				mapState.put("desc", ResponseCode.FAIL_USER_ALIPAY_STATE_TRUE_DESC);
				mapState.put("userAlipay", uid.getAlipay());
				return mapState;
			}
		}
		// 判断是否算双重认证
		if (desc.equals("validatePwd")) {
			if (uid.getValidate_pwd() == null || uid.getValidate_pwd().equals("")) {
				mapState.put("code", ResponseCode.FAIL_USER_VALIDATEPWD_STATE_FALSE);
				mapState.put("desc", ResponseCode.FAIL_USER_VALIDATEPWD_STATE_FALSE_DESC);
				return mapState;
			} else {
				mapState.put("code", ResponseCode.FAIL_USER_VALIDATEPWD_STATE_TRUE);
				mapState.put("desc", ResponseCode.FAIL_USER_VALIDATEPWD_STATE_TRUE_DESC);
				mapState.put("userValidatePwdState", uid.getValidate_pwdstate());
				return mapState;
			}
		}
		return mapState;
	}

	/**
	 * 根据用户编号得到用户基本信息
	 * 
	 * @param userNo
	 * @return
	 */
	public Map<String, Object> getUserInfoByUserNo(Integer userNo) {
		Map<String, Object> mapUserInfo = new HashMap<>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
		UserVoucherDto voucherDto = queryUserVoucher.findByUserNo(userNo);
		if (uid == null) {
			mapUserInfo.put("code", ResponseCode.FAIL);
			mapUserInfo.put("desc", ResponseCode.FAIL_DESC);
			return mapUserInfo;
		}
		uid.setPassword("");
		uid.setDeal_pwd("");
		uid.setValidate_pwd("");
		String k = uid.getPhone();
		if(k != null && k.length() > 6){
			uid.setPhone(k.substring(0, 3)+"*****"+k.substring(k.length()-3));
		}
		k = uid.getCertificate_num();
		if(k != null && k.length() > 6){
			uid.setCertificate_num(k.substring(0, 3)+"*****"+k.substring(k.length()-3));
		}
		mapUserInfo.put("code", ResponseCode.SUCCESS);
		mapUserInfo.put("desc", ResponseCode.SUCCESS_DESC);
		mapUserInfo.put("userInfo", uid);
		UserVoucherDto nullDto = new UserVoucherDto();
		nullDto.setState(0);
		mapUserInfo.put("voucherinfo", voucherDto == null ? nullDto : voucherDto);
		return mapUserInfo;
	}
	
	/**
	 * 根据用户编号得到邀请用户信息
	 * 
	 * @param userNo
	 * @return
	 */
	public Map<String, Object> getUserInfoByOrgCode(Integer userNo,Integer pageSize,Integer lineSize) {
		Map<String, Object> map = new HashMap<>();
		List<InesvUserDto> uidList = querySubCore.getInesvUserByOrgCode(userNo,pageSize,lineSize);
		Long uidSize = querySubCore.getOrgSize(userNo);
		if (uidList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", uidSize);
			map.put("data", uidList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 得到机构编号
	 * 
	 * @param userNo
	 * @return
	 */
	public Map<String, Object> getUserInfoByOrgType() {
		Map<String, Object> map = new HashMap<>();
		List<InesvUserDto> uidList = querySubCore.getInesvUserByOrgType(0);
		if (uidList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", uidList.size());
			map.put("data", uidList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 手动添加
	 * 
	 * @param userNo
	 * @return
	 */
	public Map<String, Object> addUserInfoByOrgCode(Integer userNo,String parentCode) {
		Map<String, Object> map = new HashMap<>();
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
		if(StringUtils.isBlank(parentCode)) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "邀请码不能为空，请见谅！");
			return map;
		}
		if(!StringUtils.isBlank(uid.getOrg_parent_code())) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该用户已有上级用户！");
			return map;
		}
		if(uid.getOrg_type() == 0) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该用户已是顶级机构！");
			return map;
		}
		InesvUserDto parentUid = new InesvUserDto();
		if(uid.getOrg_type() == 1) {
			parentUid = querySubCore.getInesvUserByParentCode(parentCode);
			if(parentUid == null) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "该邀请码用户不存在");
				return map;
			}
			if(parentUid.getOrg_type() != 0) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "该邀请码用户不是机构，请查看！");
				return map;
			}
		}
		if(uid.getOrg_type() == 2) {
			parentUid = querySubCore.getInesvUserByParentCode(parentCode);
			if(parentUid == null) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "该邀请码用户不存在");
				return map;
			}
			if(parentUid.getOrg_type() != 1) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "该邀请码用户不是子机构，请查看！");
				return map;
			}
		}
		if(uid.getOrg_type() == 3) {
			parentUid = querySubCore.getInesvUserByParentCode(parentCode);
			if(parentUid == null) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "该邀请码用户不存在");
				return map;
			}
			if(parentUid.getOrg_type() != 2) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "该邀请码用户不是经纪人，请查看！");
				return map;
			}
		}
		RegUserCommand command = new RegUserCommand(0,"",userNo,"",null,null,null,null,null,"",0,"",new Date(),"updateParentCode");
        command.setOrg_parent_code(parentUid.getOrg_code());
        UserInfoDto parentUserInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(parentCode);//根据邀请码查询出此邀请码的用户信息
    	List<String> provinceAbbreviationList=queryProvinceAbbreviation.getAllProvinceAbbreviation();//获取省份简写信息
        OrganizationStructureUtil organizationStructureUtil=new OrganizationStructureUtil(parentCode, parentUserInfoDto, provinceAbbreviationList, sequenceOper);
    	OrganizationStructureResult organizationStructureResult=organizationStructureUtil.structure();//解析验证并返回对应的机构信息
        if(!organizationStructureResult.isSuccess()){
        	//未通过验证
        	map.put("code",ResponseCode.FAIL);
            map.put("desc",organizationStructureResult.getErrorMsg());
            return map;
        }
        command.setOrg_code(organizationStructureResult.getOrg_code());
        if(StringUtils.isBlank(uid.getInvite_num())) {
        	String recCode=createRecCodeByOrgType(organizationStructureResult.getOrg_type());//获取邀请码
        	command.setInvite_num(recCode);
        	if(recCode!=null){
            	sequenceOper.addSequence(recCode, 1);//新增对应邀请码的序列
            }
        }
        try {
        	commandGateway.sendAndWait(command);
        	InesvUserDto uidInfo = querySubCore.getInesvUserByUserNoWithPwd(userNo);
        	map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", uidInfo.getOrg_parent_code());
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "添加上级失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	//目前只有顶级机构和经纪人可以生成推荐码
    private String createRecCodeByOrgType(Integer orgType){
    	if(orgType>=3){
    		return null;
    	}
    	return creatRecCode().toUpperCase();
    }
    
    /** 生成推荐码 */
	public String creatRecCode() {
		String result = getCode();
		UserInfoDto userInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(result);
//		if(userInfoDto != null){
//			result = getCode();
//		}
		boolean ok = userInfoDto != null;
		while(ok){
			result = getCode();
			userInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(result);
			ok = userInfoDto != null;
		}
		return result;
	}
	public String getCode(){
		String result = "";
		for (int i = 0; i < 7; i++) {
			int intVal = (int) (Math.random() * 26 + 97);
			result = result + (char) intVal;
		}
		return result;
	}


}
