package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.PaymentBankDto;
import com.inesv.digiccy.event.MyRecEvent;
import com.inesv.digiccy.event.PaymentBankEvent;
import com.inesv.digiccy.persistence.finance.MyrecPersistence;
import com.inesv.digiccy.persistence.param.PaymentBankOperation;

public class PaymentBankEventHandler {
	@Autowired
	PaymentBankOperation paymentBankOperation;

	@EventHandler
	public void handle(PaymentBankEvent event) {
		String operation = event.getOperation();
		PaymentBankDto dto = new PaymentBankDto();
		dto.setId(event.getId());
		dto.setBankCardId(event.getBankCardId());
		dto.setBankName(event.getBankName());
		dto.setBankUserName(event.getBankUserName());
		dto.setRemark(event.getRemark());
		switch (operation) {
		case "update":
			paymentBankOperation.modifyPaymentBank(dto);
			break;

		default:
			break;
		}
	}
}
