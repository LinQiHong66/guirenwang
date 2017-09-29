package com.inesv.digiccy.back.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.validata.EntrustDealValidate;
import com.inesv.digiccy.validata.InesvAddressValidata;
import com.inesv.digiccy.validata.TradeValidata;
import com.inesv.digiccy.validata.UserBalanceValidate;
import com.inesv.digiccy.validata.UserVoucherValidate;
import com.inesv.digiccy.validata.bank.InesvBankInfoValidata;
import com.inesv.digiccy.validata.user.InesvUserValidata;
import com.inesv.digiccy.validata.user.OpUserValidata;

/**
 * Created by JimJim on 2016/12/5 0005.
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	OpUserValidata userValidata;

	@Autowired
	UserBalanceValidate userBalanceValidate;

	@Autowired
	InesvBankInfoValidata bankInfoValidata;

	@Autowired
	EntrustDealValidate entrustDealValidate;

	@Autowired
	TradeValidata tradeValidata;

	@Autowired
	InesvUserValidata inesvUserValidata;

	@Autowired
	InesvAddressValidata inesvAddressValidata;

	@Autowired
	UserVoucherValidate userVoucherValidate;

	/*
	 * 测试
	 */
	@RequestMapping(value = "getUsers")
	@ResponseBody
	public Map<String, Object> getUsers() {
		Map<String, Object> map = userValidata.validateGetUsers();
		return map;
	}

	/*
	 * 测试
	 */
	@RequestMapping(value = "editUsers")
	@ResponseBody
	public Map<String, Object> editUsers(InesvUserDto userDto) {
		Map<String, Object> map = inesvUserValidata.updateUsers(userDto.getUser_no(), userDto.getReal_name());
		return map;
	}

	// 到管理用户实名认证的界面
	@RequestMapping(value = "gotovoucher", method = RequestMethod.GET)
	public String gotoVoucher() {
		return "/user/voucher";
	}

	// 到敏感信息的界面
	@RequestMapping(value = "sensitiveinformation", method = RequestMethod.GET)
	public String gotoSensitiveInformation() {
		return "/user/sensitiveinformation";
	}

	@RequestMapping(value = "gotoUser", method = RequestMethod.GET)
	public ModelAndView gotoUser() {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> crowdMap = userValidata.validateUser();
		map.put("sumUser", crowdMap.get("sumUser"));
		map.put("dayUser", crowdMap.get("dayUser"));
		map.put("sumUser0", crowdMap.get("sumUser0"));
		map.put("sumUser1", crowdMap.get("sumUser1"));
		map.put("sumUser2", crowdMap.get("sumUser2"));
		map.put("sumUser3", crowdMap.get("sumUser3"));
		map.put("dayUser2", crowdMap.get("dayUser2"));
		map.put("dayUser3", crowdMap.get("dayUser3"));
		return new ModelAndView("/user/user", map);
	}

	@RequestMapping(value = "gotoUserInfo", method = RequestMethod.GET)
	public ModelAndView gotoUserInfo(String id) {
		Map<String, Object> map = userValidata.validataGetUserInfoById(Long.valueOf(id));
		return new ModelAndView("/user/userInfo", map);
	}

	@RequestMapping(value = "getUserInfoById", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserInfoById(String id) {
		Map<String, Object> map = userValidata.validataGetUserInfoById(Long.valueOf(id));
		return map;
	}

	/**
	 * 敏感信息修改
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "gotoSensitiveinformation", method = RequestMethod.GET)
	public ModelAndView gotoSensitiveinformation(String id) {
		Map<String, Object> map = userValidata.validataGetUserInfoById(Long.valueOf(id));
		return new ModelAndView("/user/sensitiveinformationmotify", map);
	}

	@RequestMapping(value = "gotoBank", method = RequestMethod.GET)
	public String gotoBank() {
		return "/user/bank";
	}

	@RequestMapping(value = "gotoWallet", method = RequestMethod.GET)
	public String gotoWallet() {
		return "/user/wallet";
	}

	// 获取条件查询认证的用户
	@RequestMapping(value = "getallvoucher", method = RequestMethod.POST)
	@ResponseBody
	public Map getAllVoucher(String field, String value) {
		return userValidata.getAllVoucher(field, value);
	}

	// 更改用户认证状态
	@RequestMapping(value = "modifyvoucherstate", method = RequestMethod.POST)
	@ResponseBody
	public Map modifyVoucherState(int userNo, int state) {
		return userValidata.modifyVoucher(userNo, state);
	}

	@RequestMapping(value = "getAllUser", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllUser(String username, String phone, int state, float curpage, int pageItem) {
		int curPage_a = (curpage + "").contains(".")
				? Integer.parseInt((curpage + "").substring(0, (curpage + "").indexOf(".")))
				: Integer.parseInt(curpage + "");
		Map<String, Object> map = userValidata.validataGetAllUser(username, phone, state, curPage_a, pageItem);
		return map;
	}

	@RequestMapping(value = "getUserByPhone", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserByPhone(String phone) {
		Map<String, Object> map = userValidata.validataGetUserByPhone(phone);
		return map;
	}

	@RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserInfo(String no, String name, String real, String mail, String phone,
			String certificate, String alipay) {
		Map<String, Object> usermap = userValidata.validataGetUserInfoByNo(Integer.parseInt(no));
		InesvUserDto info = (InesvUserDto) usermap.get("data");
		Map<String, Object> map = new HashMap<>();
		if (real != null && certificate != null) {
			if (!real.equals(info.getReal_name()) || !certificate.equals(info.getCertificate_num())) {//输入的姓名或者身份证与数据库不一致才调用认证接口
				System.out.println("调用认证接口");
				// 判断身份证与名字是否一致
				Map<String, Object> map1 = userVoucherValidate.validateCardId(real, certificate);
				if ("100".equals(map1.get("code"))) {
					// 确认通过审核
					map = inesvUserValidata.updateUserInfo(name, Integer.valueOf(no), real, mail, phone, certificate,
							alipay);
				} else {
					map.put("code", ResponseCode.FAIL);
					map.put("desc", "用戶名和证件号不一致");
				}
			} else {
				System.out.println("不调用接口");
				map = inesvUserValidata.updateUserInfo(name, Integer.valueOf(no), real, mail, phone, certificate,
						alipay);
			}
		} else {// 如果输入的名字或者身份证为空则不调用身份验证接口
			System.out.println("名字或者身份证为空不调用接口");
			map = inesvUserValidata.updateUserInfo(name, Integer.valueOf(no), real, mail, phone, certificate, alipay);
		}

		return map;
	}

	@RequestMapping(value = "updateUserState", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserState(String no, String state) {
		Map<String, Object> map = inesvUserValidata.updateUserState(Integer.valueOf(no), Integer.valueOf(state));
		return map;
	}

	@RequestMapping(value = "updateUserPass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserPass(String no, String pass, String deal) {
		Map<String, Object> map = inesvUserValidata.updateUserPass(Integer.valueOf(no), pass, deal);
		return map;
	}

	@RequestMapping(value = "getBankInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getBankInfo() {
		Map<String, Object> map = bankInfoValidata.getAllBankInfo();
		return map;
	}

	@RequestMapping(value = "getWallet", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWallet(@RequestParam String condition, @RequestParam String value) {
		Map<String, Object> map = userBalanceValidate.validataQueryUserBalanceInfoByUserNoOrCoinType(condition, value);
		return map;
	}

	@RequestMapping(value = "getUserEntrust", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserEntrust(String userNo) {
		Map<String, Object> map = entrustDealValidate.validataEntrustRecordByUserNo(userNo);
		return map;
	}

	@RequestMapping(value = "getUserDeal", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> getUserDeal(String userNo) {
		Map<Object, Object> map = tradeValidata.validataDealDetailListByUserNo(userNo);
		return map;
	}

	@RequestMapping(value = "getUserAddress", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserAddress(String userNo) {
		Map<String, Object> map = inesvAddressValidata.getAddressByUser(userNo);
		return map;
	}

	@RequestMapping(value = "confirmEntrust", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> confirmEntrust(String id, String user, String icon, String type, String price,
			String num, String piundatge) {
		Map<String, Object> map = tradeValidata.confirmEntrust(id, user, icon, type, price, num, piundatge);
		return map;
	}

	@RequestMapping(value = "editAddress", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editAddress(String userNo, String name, String phone, String remarkAddress,
			String address, String card) {
		Map<String, Object> map = inesvAddressValidata.updateAddressByUser(userNo, name, phone, remarkAddress, address,
				card);
		return map;
	}

}
