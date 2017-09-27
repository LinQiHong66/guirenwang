package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.UserInvitrCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.query.QueryUserInfo;
import org.axonframework.commandhandling.gateway.CommandGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yc on 2016/12/19 0019. 校验推荐用户
 */
@Component
public class RecUserValidate {

	@Autowired
	QueryUserInfo queryUserInfo;

	@Autowired
	CommandGateway commandGateway;

	/**
	 * 查询或生成用户的推荐码
	 */
	public Map<String, Object> validateRechUser(int userNo) {
		Map<String, Object> map = new HashMap<>();
		InesvUserDto inesvUserDto = queryUserInfo.queryInviteNum(userNo);// 查询用户的邀请码
		String inviteCode = inesvUserDto.getInvite_num();
		if ("".equals(inviteCode)) {
			String incite = creatRecCode(userNo+"");
			UserInvitrCommand command = new UserInvitrCommand(654L, userNo, incite.toUpperCase(), new Date(),
					"updateInvite");
			commandGateway.send(command);
			map.put("inviteCode", incite.toUpperCase());
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else if (!"".equals(inviteCode)) {
			map.put("inviteCode", inviteCode);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**查询我的推荐人*/
	public Map<String, Object> getMyInesv(int userNo){
		Map<String, Object> map = new HashMap<>();
		try{
			List<InesvUserDto> inesvUser = queryUserInfo.getInesvByUserNo(userNo);
			map.put("result", (inesvUser==null || inesvUser.isEmpty())?"none":inesvUser);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}catch(Exception e){
			e.printStackTrace();
			map.put("result", "error");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/** 生成推荐码 */
	public String creatRecCode(String userNo) {
		int k = userNo.length();
		String result = "";
		for (int i = 0; i < 7-k; i++) {
			int intVal = (int) (Math.random() * 26 + 97);
			result = result + (char) intVal;
		}
		result += userNo;
		return result;
	}
}
