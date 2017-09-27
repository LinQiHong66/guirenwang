package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.event.CoinLevelProportionEvent;
import com.inesv.digiccy.persistence.coinlevelproportion.CoinLevelProportionOper;

public class CoinLevelProportionEventHandler {
	
	@Autowired
	CoinLevelProportionOper oper;
	
	@EventHandler
	public void handler(CoinLevelProportionEvent event){
		switch (event.getOperation()) {
		case "insert":
			oper.insert(event.getCoin_no(), event.getLevel_one(), event.getLevel_two(), event.getLevel_three());
			break;
		case "updateLevelByCoinNo":
			oper.updateLevelByCoinNo(event.getLevel_one(), event.getLevel_two(), event.getLevel_three(), event.getId());
			break;
		default:
			break;
		}
	}
}
