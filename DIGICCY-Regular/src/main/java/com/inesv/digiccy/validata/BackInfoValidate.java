package com.inesv.digiccy.validata;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.PaymentBankCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.PaymentBankDto;
import com.inesv.digiccy.query.QueryPaymentBank;

/**
 * 
 * @author Administrator
 *
 */
@Component
public class BackInfoValidate {
	@Autowired
	QueryPaymentBank queryPaymentBank;
	@Autowired
	CommandGateway commandGateway;

	public Map<String, Object> getBankInfo() {
		HashMap<String, Object> map = new HashMap<>();
		PaymentBankDto dto = queryPaymentBank.getBankInfo();
		if (dto != null) {
			map.put("result", dto);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("result", "none");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public Map<String, Object> modifyBankInfo(int id, String name, String cardId, String backName, String remark) {
		PaymentBankCommand command = new PaymentBankCommand();
		command.setBankCardId(cardId);
		command.setId(id);
		command.setBankName(backName);
		command.setRemark(remark);
		command.setBankUserName(name);
		command.setOperation("update");
		HashMap<String , Object> map = new HashMap<>();
		try {
			commandGateway.send(command);
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
