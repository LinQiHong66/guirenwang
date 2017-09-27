package com.inesv.digiccy.validata;

import com.aliyuncs.exceptions.ClientException;
import com.inesv.digiccy.api.command.InesvPhoneCommand;
import com.inesv.digiccy.api.command.PoundageCommand;
import com.inesv.digiccy.api.command.TranCommand;
import com.inesv.digiccy.api.command.UserBalanceCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.StaticParamsDto;
import com.inesv.digiccy.dto.TranDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.query.QueryStaticParam;
import com.inesv.digiccy.query.QueryTranInfo;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import com.inesv.digiccy.redis.RedisCodeImpl;
import com.inesv.digiccy.sms.SendMsgUtil;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.util.SmsUtil;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
@Component
public class TranValidate {

	@Autowired
	QueryUserBalanceInfo queryUserBalanceInfo;

	@Autowired
	QueryTranInfo queryTranInfo;

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	RedisCodeImpl redisCode;

	@Autowired
	SendMsgUtil sendMsgUtil;

	@Autowired
	QueryStaticParam queryStaticParam;

	/**
	 * 校验转账记录和可用金额
	 */
	public Map<String, Object> validateTran(int userNo, int coinType) {
		Map<String, Object> map = new HashMap();
		List<TranDto> list = queryTranInfo.queryTranInfo(userNo, coinType);// 查询用户转账记录
		UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, coinType);
		BigDecimal enableCoin = userBalanceDto.getEnable_coin();// 获取用户可用币种的余额
		if (!list.isEmpty()) {
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

	/* @@@@@@@@@@@@@@@@@@@@ */

	/**
	 * 确认充值
	 */
	public Map<String, Object> validateConfirmTransfer(int userNo, int coinType, String mobile, int tranuser,
			String coinNum, String code, String dealPwd) {
		Map<String, Object> map = new HashMap();

		double coin = Double.parseDouble(coinNum);// 获取转账金额
		UserInfoDto userInfoDto = queryUserBalanceInfo.queryDeaPSW(userNo);
		String userDealPwd = userInfoDto.getDeal_pwd();// 获取交易密码
		// int result = validataCompare(mobile,code);//校验验证码是否正确
		UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, coinType);
		BigDecimal enable = userBalanceDto.getEnable_coin();// 获取当前用户可用币种余额
		BigDecimal totalPrice = userBalanceDto.getTotal_price();// 获得用户总资产
		double coinEnble = enable.doubleValue();
		// UserBalanceDto userBalanceDto1 =
		// queryUserBalanceInfo.queryEnableCoin(tranuser,coinType);
		// BigDecimal tranEnable = userBalanceDto1.getEnable_coin();//获取转账用户可用币种余额
		List<UserInfoDto> list = queryTranInfo.queryUserInfo(userNo);// 校验是否存在此用户
		UserBalanceDto userBalanceDto2 = queryUserBalanceInfo.queryTranUserCoinType(tranuser, coinType);// 检验转账对象是否有此币种
		MD5 md5 = new MD5();
		String md5DealPwd = md5.getMD5(dealPwd);
		if (!list.isEmpty()) {
			if (1 == 1) {// ----------------------
				if (md5DealPwd.equals(userDealPwd)) {
					if (coin != 0 && coin > 0 && coin <= coinEnble && coin <= 10000) {
						if (userBalanceDto2 == null) {
							// 如果转账对象用户没有此币种则新增此用户相应币种
							UserBalanceCommand Trancommand = new UserBalanceCommand(12, tranuser, coinType,
									BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, "", new Date(),
									"insertTranCoinType");
							commandGateway.sendAndWait(Trancommand);
						}
						StaticParamsDto staticParamsDto = queryStaticParam.getStaticParamByParam("poundageRate");// 获取手续费
						BigDecimal Proces = staticParamsDto.getValue();// 获取手续费比例
						double doubleProces = Proces.doubleValue();// 将手续费转成double
						double procesCoin = doubleProces * coin;// 产生的手续费

						BigDecimal procesCoinB = BigDecimal.valueOf(procesCoin);

						double tureCoin = coin - procesCoin;// 实际转账金额
						String fa = new DecimalFormat("#.000").format(tureCoin);// 保留3位小数
						Double trNum = new Double(fa);
						BigDecimal tranCoin = BigDecimal.valueOf(trNum);
						TranCommand command = new TranCommand(522L, userNo, tranuser, coinType, tranCoin, procesCoinB,
								1, new Date(), "insert");
						commandGateway.send(command);// 新增记录
						BigDecimal nowEnble = enable.subtract(BigDecimal.valueOf(coin));// 转账后的用户可用金额

						BigDecimal nowTotalprice = totalPrice.subtract(tranCoin);// 转账后的用户总额
						UserBalanceCommand command1 = new UserBalanceCommand(5535L, userNo, coinType, nowEnble,
								nowTotalprice, new Date(), "updateEnble");// 更新用户可用金额及用户总额
						commandGateway.send(command1);
						PoundageCommand poundageCommand = new PoundageCommand(23231L, userNo, 4, coinType,
								BigDecimal.valueOf(procesCoin), new Date(), "insert");
						commandGateway.send(poundageCommand);// 新增手续费记录
						UserBalanceDto userBalanceDto1 = queryUserBalanceInfo.queryEnableCoin(tranuser, coinType); // 转账对象
						BigDecimal tranUserEnble = userBalanceDto1.getEnable_coin();// 获取转账对象可用币种余额
						BigDecimal tranTotalPrice = userBalanceDto1.getTotal_price();// 获取转账对象可用币种总额
						BigDecimal nowTranEnble = tranUserEnble.add(tranCoin);// 转账后转账对象用户的可用余额
						BigDecimal nowTranTotalPrice = tranTotalPrice.add(tranCoin);// 转账后转账对象用户
						UserBalanceCommand command2 = new UserBalanceCommand(357L, tranuser, coinType, nowTranEnble,
								nowTranTotalPrice, new Date(), "updateTranEnble");// 更新转入用户可用金额及用户总额
						commandGateway.send(command2);
						map.put("code", ResponseCode.SUCCESS);
						map.put("desc", ResponseCode.SUCCESS_DESC);
					} else {
						map.put("code", ResponseCode.FAIL_INPUT_COIN);
						map.put("desc", ResponseCode.FAIL_INPUT_COIN_DESC);// 请输入正确的金额
					}
				} else {
					map.put("code", ResponseCode.FAIL_DEALPWD);
					map.put("desc", ResponseCode.FAIL_DEALPWD_DESC);// 交易密码有误
				}
			} else {
				map.put("code", ResponseCode.FAIL_ERROR_CODE);
				map.put("desc", ResponseCode.FAIL_ERROR_CODE_DESC);// 验证码不正确
			}
		} else {
			map.put("code", ResponseCode.FAIL_EMPTY_USER);
			map.put("desc", ResponseCode.FAIL_EMPTY_USER_DESC);// 无此用户
		}

		return map;
	}

	/* @@@@@@@@@@@ @@@@@@@@@ */

	/**
	 * 发送短信验证码
	 */
	public Map<String, Object> validatePhoneCode(int type, String mobile) {

		int mCode = sendMsgUtil.getCode(mobile, type);

		redisCode.setSms(mobile, type, mCode);
		boolean ok = false;
		try {
			ok = SmsUtil.sendMySms(mobile, mCode + "");
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap();
		try {
			ok = SmsUtil.sendMySms(mobile, mCode + "");
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InesvPhoneCommand command = new InesvPhoneCommand(0, null, mobile, 1, mCode, "insert");
		commandGateway.send(command);
		if (ok) {
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
		int smsNum = redisCode.getSms(mobile, 1);// 获取缓存里里面的验证码
		// 通用手机号码验证
		if (code.equals(smsNum + "")) {
			return 1;
		}
		return 0;
	}

}
