package com.inesv.digiccy.validata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletResponse;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;
import com.inesv.digiccy.api.command.InesvPhoneCommand;
import com.inesv.digiccy.api.command.RmbWithdrawCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvBankInfo;
import com.inesv.digiccy.dto.RmbWithdrawDto;
import com.inesv.digiccy.dto.StaticParamsDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.persistence.bankinfo.OperationBankInfo;
import com.inesv.digiccy.persistence.finance.RmbWithdrawPersistence;
import com.inesv.digiccy.query.QueryBankInfo;
import com.inesv.digiccy.query.QueryRmbWithdrawInfo;
import com.inesv.digiccy.query.QueryStaticParam;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import com.inesv.digiccy.query.QueryWalletLinkInfo;
import com.inesv.digiccy.redis.RedisCodeImpl;
import com.inesv.digiccy.sms.SendMsgUtil;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.util.SmsUtil;
import com.inesv.digiccy.validata.util.ExcelUtils;

/**
 * Created by yc on 2016/12/12 0012.
 */
@Component
public class RmbWithdrawValidate {

	@Autowired
	QueryUserBalanceInfo queryUserBalanceInfo;

	@Autowired
	QueryRmbWithdrawInfo queryRmbWithdrawInfo;

	@Autowired
	QueryWalletLinkInfo queryWalletLinkInfo;

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	RedisCodeImpl redisCode;

	@Autowired
	SendMsgUtil sendMsgUtil;

	@Autowired
	QueryBankInfo queryBankInfo;

	@Autowired
	QueryStaticParam queryStaticParam;

	@Autowired
	OperationBankInfo operationBankInfo;

	@Autowired
	RmbWithdrawPersistence rmbWithdrawPersistence;

	/**
	 * 查询出用户的提现信息
	 */
	public Map<String, Object> validateWithdrawInfo(int userNo, int coinType) {
		Map<String, Object> map = new HashMap();
		UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, coinType);
		List<InesvBankInfo> bankList = queryRmbWithdrawInfo.queryBankInfo(userNo);// 查询出用户的银行信息
		BigDecimal enableCoin = userBalanceDto.getEnable_coin();// 获取可用人民币
		List<RmbWithdrawDto> list = queryRmbWithdrawInfo.queryWithdrawInfo(userNo);// 查询人民币提现信息
		for (RmbWithdrawDto rmbwith : list) {
			int bankid = rmbwith.getBank();
			InesvBankInfo bank = operationBankInfo.queryBankInfo(bankid);
			rmbwith.setBankName(bank == null ? "" : bank.getBank_num());
		}
		// 查询用户银行信息
		if (enableCoin != null) {
			map.put("bankList", bankList);
			map.put("list", list);
			map.put("data", enableCoin);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 申请提现
	 * 
	 * @throws Exception
	 */
	/*
	 * public Map<String, Object> validateRmbWithdraw(int userNo, String price, int
	 * bank, String dealPwd, String moble, String code) throws Exception {
	 * Map<String, Object> map = new HashMap(); int result = validataCompare(moble,
	 * code);//校验手机验证码 UserInfoDto userInfoDto =
	 * queryUserBalanceInfo.queryDeaPSW(userNo); if (result != 0) { if (userInfoDto
	 * != null) { String dealPassword = userInfoDto.getDeal_pwd(); //获取用户的交易密码
	 * UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo,
	 * 0); if (userBalanceDto != null) { BigDecimal enableCoin = null; enableCoin =
	 * userBalanceDto.getEnable_coin();//获取用户可用金额 if (enableCoin == null) {
	 * enableCoin = BigDecimal.valueOf(0);//空时默认为0 } BigDecimal totalPrice = null;
	 * totalPrice = userBalanceDto.getTotal_price();//获取用户总额 if (totalPrice == null)
	 * { totalPrice = BigDecimal.valueOf(0); } BigDecimal unableCoin = null;
	 * unableCoin = userBalanceDto.getUnable_coin();//获取用户冻结金额 if (unableCoin ==
	 * null) { unableCoin = BigDecimal.valueOf(0);//空时默认为0 } double doubleEnble =
	 * enableCoin.doubleValue(); double doublePrice = Double.parseDouble(price);
	 * //提现金额 MD5 md5 = new MD5(); String md5Password =
	 * md5.getMD5(dealPwd);//将页面传进来的交易密码转换成md5格式进行比较
	 * 
	 * StaticParamsDto staticParamsDto =
	 * queryStaticParam.getStaticParamByParam("poundageRate"); //获取手续费 BigDecimal
	 * Proces = staticParamsDto.getValue();//获取手续费比例 double procePrice =
	 * Proces.doubleValue();//手续费比例 double needProce = doublePrice * procePrice;
	 * //所需手续费 double sumprice = doublePrice - needProce; //实际提现金额 BigDecimal
	 * bigsumprice = BigDecimal.valueOf(sumprice);//所需总费用 BigDecimal tureyProces =
	 * BigDecimal.valueOf(needProce);//所需手续费 BigDecimal withdrawPrice =
	 * BigDecimal.valueOf(doublePrice); //提现金额
	 * 
	 * if (md5Password.equals(dealPassword)) { if (sumprice <= doubleEnble) { if (0
	 * < doublePrice) { //----------充值事务-----------
	 * rmbWithdrawPersistence.applayToAccount(userNo, bank, withdrawPrice,
	 * tureyProces, new BigDecimal(0)); map.put("code", ResponseCode.SUCCESS);
	 * map.put("desc", ResponseCode.SUCCESS_DESC);//提现成功 } else { map.put("code",
	 * ResponseCode.FAIL_INPUT_COIN); map.put("desc",
	 * ResponseCode.FAIL_INPUT_COIN_DESC);//请输入正确的提现金额 } } else { map.put("code",
	 * ResponseCode.FAIL_BALANCE_NUM); map.put("desc",
	 * ResponseCode.FAIL_BALANCE_NUM_DESC);//余额不足 } } else { map.put("code",
	 * ResponseCode.FAIL_DEALPWD); map.put("desc",
	 * ResponseCode.FAIL_DEALPWD_DESC);//交易密码错误 } } } } else { map.put("code",
	 * ResponseCode.FAIL); map.put("desc", "验证码错误");//验证码错误 }
	 * 
	 * return map; }
	 */
	/**
	 * 申请提现
	 * 
	 * @throws Exception
	 */
	public Map<String, Object> validateRmbWithdraw(int userNo, String price, int bank, String dealPwd, String moble,
			String code) throws Exception {
		Map<String, Object> map = new HashMap();
		if (Double.valueOf(price) < 100) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "提现金额不能低于100，请见谅！");
			return map;
		}
		int result = validataCompare(moble, code);// 校验手机验证码
		if (result == 0) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "验证码错误");// 验证码错误
			return map;
		}
		UserInfoDto userInfoDto = queryUserBalanceInfo.queryDeaPSW(userNo);
		if (userInfoDto == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		String dealPassword = userInfoDto.getDeal_pwd(); // 获取用户的交易密码
		String md5Password = new MD5().getMD5(dealPwd);// 将页面传进来的交易密码转换成md5格式进行比较
		if (!md5Password.equals(dealPassword)) {
			map.put("code", ResponseCode.FAIL_DEALPWD);
			map.put("desc", ResponseCode.FAIL_DEALPWD_DESC);// 交易密码错误
			return map;
		}
		UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, 0);
		if (userBalanceDto == null) {// 用户资产
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		BigDecimal totalPrice = userBalanceDto.getTotal_price();// 获取用户总额
		if (totalPrice == null) {
			totalPrice = BigDecimal.valueOf(0);// 空时默认为0
		}
		BigDecimal enableCoin = userBalanceDto.getEnable_coin();// 获取用户可用金额
		if (enableCoin == null) {
			enableCoin = BigDecimal.valueOf(0);// 空时默认为0
		}
		BigDecimal unableCoin = userBalanceDto.getUnable_coin();// 获取用户冻结金额
		if (unableCoin == null) {
			unableCoin = BigDecimal.valueOf(0);// 空时默认为0
		}
		double doubleEnble = enableCoin.doubleValue(); // 可用金额
		double doublePrice = Double.parseDouble(price); // 提现金额
		if (doublePrice > doubleEnble) {
			map.put("code", ResponseCode.FAIL_BALANCE_NUM);
			map.put("desc", ResponseCode.FAIL_BALANCE_NUM_DESC);// 余额不足
			return map;
		}
		StaticParamsDto staticParamsDto = queryStaticParam.getStaticParamByParam("poundageRate"); // 获取手续费
		BigDecimal Proces = staticParamsDto.getValue();// 获取手续费比例
		double procePrice = Proces.doubleValue();// 手续费比例
		double needProce = doublePrice * procePrice; // 所需手续费
		// double sumprice = doublePrice - needProce; //实际提现金额
		// BigDecimal bigsumprice = BigDecimal.valueOf(sumprice);//所需总费用
		BigDecimal tureyProces = BigDecimal.valueOf(needProce);// 所需手续费
		BigDecimal withdrawPrice = BigDecimal.valueOf(doublePrice); // 提现金额
		Lock lock = new ReentrantLock();// 锁对象
		lock.lock();// 得到锁
		try {
			rmbWithdrawPersistence.applayToAccount(userNo, bank, withdrawPrice, tureyProces, new BigDecimal(0));
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);// 提现成功
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} finally {
			lock.unlock();// 释放锁
		}
		return map;
	}

	/**
	 * 发送短信验证码
	 */
	public Map<String, Object> validatePhoneCode(int type, String mobile) {
		Map<String, Object> map = new HashMap();
		// map = sendMsgUtil.sendMsg(mobile,type);
		// int code= Integer.parseInt(String.valueOf(map.get("code")));
		int code = sendMsgUtil.getCode(mobile, type);
		boolean ok = false;
		try {
			ok = SmsUtil.sendMySms(mobile, code + "");
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		InesvPhoneCommand command = new InesvPhoneCommand(0, null, mobile, 1, code, "insert");
		commandGateway.send(command);
		if (!map.get("code").equals(500)) {
			map.put("validataCode", code);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 校验短信验证码
	 */
	public int validataCompare(String mobile, String code) {
		int smsNum = redisCode.getSms(mobile, 1);// 获取缓存里面的验证码
		// 通用手机号码验证
		if (code.equals(smsNum + "")) {
			return 1;
		}
		return 0;
	}

	public Map<String, Object> validateQueryRecord(String userName, String state, String startDate, String endDate,
			int curPage, int pageItem) {
		Map<String, Object> map = new HashMap();
		List<RmbWithdrawDto> list = queryRmbWithdrawInfo.queryWithdrawInfo(userName, state, startDate, endDate, curPage,
				pageItem);
		long size = queryRmbWithdrawInfo.getSize(userName, state, startDate, endDate);
		if (list != null) {
			map.put("data", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("count", size);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public Map<String, Object> validateRmbWithdrawBack() {
		Map<String, Object> map = new HashMap();
		RmbWithdrawDto dto = queryRmbWithdrawInfo.queryWithdrawBack();
		if (dto != null) {
			map.put("sumPrice", dto.getPrice());
			map.put("dayPrice", dto.getActual_price());
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 校验提现到账
	 */
	public Map<String, Object> confirmToAccount(int id, int userNo, BigDecimal price) {
		Map<String, Object> map = new HashMap();
		try {
			rmbWithdrawPersistence.confirmToAccount(id, userNo, price.toString());
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public void getWithdrawExcel(HttpServletResponse response, String userName, String state, String startDate,
			String endDate) {
		long size = queryRmbWithdrawInfo.getSize(userName, state, startDate, endDate);
		List<RmbWithdrawDto> dtos = queryRmbWithdrawInfo.queryWithdrawInfo(userName, state, startDate, endDate, 1,
				Integer.parseInt(size + ""));
		Map<String, List<String>> contact = new HashMap<String, List<String>>();

		String title7 = "状态";
		String title6 = "日期";
		String title5 = "实际到账";
		String title4 = "手续费";
		String title3 = "提现金额";
		String title2 = "提现银行";
		String title1 = "用户名称";
		List<String> value1 = new ArrayList<String>();
		List<String> value2 = new ArrayList<String>();
		List<String> value3 = new ArrayList<String>();
		List<String> value4 = new ArrayList<String>();
		List<String> value5 = new ArrayList<String>();
		List<String> value6 = new ArrayList<String>();
		List<String> value7 = new ArrayList<String>();

		for (RmbWithdrawDto dto : dtos) {
			value1.add(dto.getAttr1());
			value2.add(dto.getAttr2());
			value3.add(dto.getPrice().toString());
			value4.add(dto.getPoundage().toString());
			value5.add(dto.getActual_price().toString());
			value6.add(dto.getDate().toString());
			value7.add(dto.getState() == 0 ? "未到账" : "已到账");
		}

		contact.put(title1, value1);
		contact.put(title2, value2);
		contact.put(title3, value3);
		contact.put(title4, value4);
		contact.put(title5, value5);
		contact.put(title6, value6);
		contact.put(title7, value7);
		ExcelUtils.export(response, contact);
	}
}
