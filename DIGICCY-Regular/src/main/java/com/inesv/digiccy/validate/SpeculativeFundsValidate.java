package com.inesv.digiccy.validate;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.SpeculativeFundCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.SpeculativeFundsDto;
import com.inesv.digiccy.query.QuerySpeculativeFunds;

@Component
public class SpeculativeFundsValidate {
	@Autowired
	QuerySpeculativeFunds querySpeculativeFunds;
	@Autowired
	private CommandGateway commandGateway;

	/**
	 * 取得指定众筹项目
	 * 
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> validataAllSpeculativeFunds() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SpeculativeFundsDto> allSpeculativeFunds = querySpeculativeFunds.getAllSpeculativeFunds();
		if (allSpeculativeFunds != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", allSpeculativeFunds);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 取得指定用户
	 * 
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> validataSpeculativeFundById(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		SpeculativeFundsDto speculativeFundsDto = querySpeculativeFunds.getSpeculativeFundsById(id);
		if (speculativeFundsDto != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", speculativeFundsDto);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 修改用户资料
	 * 
	 * @param user_no 用户id
	 * @param coin_no 货币类型
	 * @param deal_type 买或者卖
	 * @param deal_price 价格
	 * @return
	 */
	public Map<String, Object> updateSpeculativeFund(int user_no, int coin_no, int deal_type, double deal_price) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SpeculativeFundCommand command = new SpeculativeFundCommand(0, user_no, coin_no, deal_type, deal_price, 0,
					0, 0, 0, deal_price, 0, new Date(), "", "", "insertSpeculativeFund");
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
}
