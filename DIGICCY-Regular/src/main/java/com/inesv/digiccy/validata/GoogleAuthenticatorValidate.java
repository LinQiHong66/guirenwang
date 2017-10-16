package com.inesv.digiccy.validata;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.CreateInesvUserCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.query.QuerySubCore;
import com.inesv.digiccy.validata.user.InesvUserValidata;
import com.inesv.digiccy.validata.util.GoogleAuthenticatorUtil;

/**
 * 
 * @author Liukeling
 *
 */
@Component
public class GoogleAuthenticatorValidate {
	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private QuerySubCore querySubCore;

	// 获取秘钥key和二维码
	// public Map<String, Object> getRetailKey(int userNo){
	// Map<String, Object> map = new HashMap<>();
	// InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo);
	// if(uid != null && uid.getValidate_pwdstate() == 1){
	// String keyandurl = uid.getValidate_pwd();
	// String[] kau = keyandurl.split(",");
	// String key = "";
	// String url = "";
	// for(int i = 0; i < kau.length; i ++){
	// if(i == 0){
	// key = kau[i];
	// }else{
	// url += kau[i];
	// }
	// }
	// map.put("result", new String[]{key, url});
	// map.put("code", ResponseCode.SUCCESS);
	// map.put("desc", ResponseCode.SUCCESS_DESC);
	// }else{
	// map.put("result", "未开启双重验证");
	// map.put("code", ResponseCode.FAIL);
	// map.put("desc", ResponseCode.FAIL_DESC);
	// }
	// return map;
	// }

	// 生成秘钥key
	public Map<String, Object> createRetailKey(String userName, int userNo) {
		Map<String, Object> map = new HashMap<>();
		String[] result = GoogleAuthenticatorUtil.genSecret(userName);
		if (result != null) {
			try {
				String mkey = result[0];
				String url = result[1];
				// 判断用户是否存在
				InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo, false);
				if (uid == null) {
					map.put("result", "用户不存在");
					map.put("code", ResponseCode.FAIL);
					map.put("desc", ResponseCode.FAIL_DESC);
					return map;
				}
				// 将秘钥保存
				CreateInesvUserCommand ciucValidatePwd = new CreateInesvUserCommand(userNo, null, null,
						mkey + "," + url, -1, null, null, "upValidatePwd");
				commandGateway.sendAndWait(ciucValidatePwd);
			} catch (Exception e) {
				map.put("result", "保存秘钥失败");
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
			}
			map.put("result", result);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("result", "获取秘钥失败");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	// 验证秘钥
	public Map<String, Object> inspectionKey(String key, int userNo) {
		Map<String, Object> map = new HashMap<>();
		boolean ok = false;
		try {
			InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo, false);
			if (uid.getValidate_pwdstate() != 1) {
				map.put("msg", "未开启双重验证");
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
				return map;
			} else {
				String code = uid.getValidate_pwd();
				code = code.split(",")[0];
				ok = GoogleAuthenticatorUtil.authcode(key, code);
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "验证出错");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		if (ok) {
			map.put("msg", "验证成功");
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("msg", "验证失败");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	// 查询是否开启双重验证
	public Map<String, Object> selectIsStart(int userNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo, false);
			int state = uid == null ? 0 : uid.getValidate_pwdstate();
			map.put("state", state);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", -1);
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**根据双重验证码重置双重验证*/
	public Map<String, Object> resetProingByCode(String key, int userNo){
		HashMap<String, Object> map = new HashMap<String, Object>();
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo, false);
		if (uid.getValidate_pwdstate() != 1) {
			map.put("msg", "未开启双重验证");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		} else {
			String code = uid.getValidate_pwd();
			code = code.split(",")[0];
			boolean ok = GoogleAuthenticatorUtil.authcode(key, code);
			if(ok){
				//清除秘钥
				CreateInesvUserCommand ciucValidatePwd = new CreateInesvUserCommand(userNo, null, null,
						"", 0, null, null, "upValidatePwd");
				commandGateway.sendAndWait(ciucValidatePwd);
				map.put("msg", "重置成功");
				map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
			}else{
				map.put("msg", "验证码错误");
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
			}
			return map;
		}
	}
	/**根据双重验证码确定开启*/
	public Map<String, Object> startProving(String key, int userNo){
		HashMap<String, Object> map = new HashMap<String, Object>();
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo, false);
		if (uid.getValidate_pwdstate() != 0) {
			map.put("msg", "双重验证无须开启");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		} else {
			String code = uid.getValidate_pwd();
			code = code.split(",")[0];
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(key+"____"+code);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			boolean ok = GoogleAuthenticatorUtil.authcode(key, code);
			if(ok){
				//清除秘钥
				try{
				CreateInesvUserCommand ciucValidatePwd = new CreateInesvUserCommand(userNo, null, null,
						"", 1, null, null, "upValidatePwd");
				commandGateway.sendAndWait(ciucValidatePwd);
				map.put("msg", "开启成功");
				map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
				}catch(Exception e){
					e.printStackTrace();
					map.put("msg", "开启失败");
					map.put("code", ResponseCode.FAIL);
					map.put("desc", ResponseCode.FAIL_DESC);
				}
			}else{
				map.put("msg", "双重验证失败");
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
			}
			return map;
		}
	}
}
