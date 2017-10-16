package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.SpeculativeFundDto;
import com.inesv.digiccy.event.SpeculativeFundEvent;
import com.inesv.digiccy.persistence.speculativeFund.SpeculativeFundOperation;

public class SpeculativeFundHandler {
	@Autowired
	SpeculativeFundOperation speculativeFundOperation;

	@EventHandler
	public void handle(SpeculativeFundEvent event) throws Exception {
		System.out.println("SpeculativeFundHandler:=-======================================");
		String operation = event.getOperation();
		switch (operation) {
		case "insertSpeculativeFund":
			SpeculativeFundDto speculativeFundDto = new SpeculativeFundDto(event.getId(), event.getUser_no(),
					event.getCoin_no(), event.getDeal_type(), event.getDeal_price(), event.getOptimum_price(),
					event.getMost_amount(), event.getPercent(), event.getDeal_num(), event.getSum_price(),
					event.getPoundage(), event.getDate(), event.getAttr1(), event.getAttr2(), event.getOperation());
			speculativeFundOperation.addSpeculativeFund(speculativeFundDto);
			break;
		default:
			break;
		}
	}

}
