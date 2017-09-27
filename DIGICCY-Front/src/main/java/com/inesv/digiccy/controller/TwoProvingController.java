package com.inesv.digiccy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.GoogleAuthenticatorValidate;

/**
 * 
 * @author Liukeling
 *
 */

@Controller
@RequestMapping("proving")
public class TwoProvingController {
	
	@Autowired
	GoogleAuthenticatorValidate googleAuthenticatorValidate;
	
	//生成秘钥key和二维码的接口
	@RequestMapping(value="createretailkey", method=RequestMethod.POST)
	@ResponseBody
	public Map getRetailKey(int userNo){
		HashMap<String, Object> map = new HashMap<>();
		map.putAll(googleAuthenticatorValidate.createRetailKey(userNo+"inesv", userNo));
		return map;
	}
//	获取秘钥key和二维码
//	@RequestMapping(value="getretailkey", method=RequestMethod.POST)
//	@ResponseBody
//	public Map getRetailkey(int userNo){
//		return googleAuthenticatorValidate.getRetailKey(userNo);
//	}
	//验证秘钥的接口
	@RequestMapping(value="inspectionkey", method=RequestMethod.POST)
	@ResponseBody
	public Map inspectionKey(String key, int userNo){
		return googleAuthenticatorValidate.inspectionKey(key, userNo);
	}
	
	/**查询是否开启双重验证*/

	@RequestMapping(value="isStartProving", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> provingIsStart(int userNo){
		HashMap<String, Object> map = new HashMap<>();
		map.putAll(googleAuthenticatorValidate.selectIsStart(userNo));
		return map;
	}
	
	/**根据双重验证码重置双重验证*/
	@RequestMapping(value="resetProvingByCode", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resetProvingByCode(int userNo, String key){
		return googleAuthenticatorValidate.resetProingByCode(key, userNo);
	}
	/**根据双重验证码开启双重验证*/
	@RequestMapping(value="startProving", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> startProving(int userNo, String key){
		return googleAuthenticatorValidate.startProving(key, userNo);
	}
}
