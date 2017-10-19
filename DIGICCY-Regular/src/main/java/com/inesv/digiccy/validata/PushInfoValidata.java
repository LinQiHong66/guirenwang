package com.inesv.digiccy.validata;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.PushInfoCommand;
import com.inesv.digiccy.common.ResponseCode;

/**
 * 推送消息
 * 
 * @author Liukeling
 *
 */
@Component
public class PushInfoValidata {

	@Autowired
	private CommandGateway commandGateway;

	public Map<String, Object> setDriverToken(String driverToken, int userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		PushInfoCommand command = new PushInfoCommand();
		command.setUserNo(userNo);
		command.setDriverToken(driverToken);
		command.setOpration("updateDriverToken");
		try {
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public Map<String, Object> setSectionOfPush(int userNo, float maxPrice, float minPrice, Boolean isPush) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 调整数值
		if (maxPrice < minPrice) {
			float bufferPrice = maxPrice;
			maxPrice = minPrice;
			minPrice = bufferPrice;
		}
		PushInfoCommand command = new PushInfoCommand();
		command.setUserNo(userNo);
		command.setMaxPrice(maxPrice);
		command.setPush(isPush == null ? false : isPush);
		command.setMinPrice(minPrice);
		command.setOpration("setSection");
		try {
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
