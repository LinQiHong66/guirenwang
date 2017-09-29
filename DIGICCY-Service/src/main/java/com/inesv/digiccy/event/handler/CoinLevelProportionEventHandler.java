package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.CoinLevelProportionDto;
import com.inesv.digiccy.event.CoinLevelProportionEvent;
import com.inesv.digiccy.persistence.coinlevelproportion.CoinLevelProportionOper;

public class CoinLevelProportionEventHandler {
	
	@Autowired
	CoinLevelProportionOper oper;
	
	@EventHandler
	public void handler(CoinLevelProportionEvent event){
		CoinLevelProportionDto levelDto = new CoinLevelProportionDto();
		levelDto.setId(event.getId());
		levelDto.setCoin_no(event.getCoin_no());
		levelDto.setLevel_one(event.getLevel_one());
		levelDto.setLevel_two(event.getLevel_two());
		levelDto.setLevel_three(event.getLevel_three());
		levelDto.setLevel_four(event.getLevel_four());
		levelDto.setLevel_five(event.getLevel_five());
		levelDto.setLevel_type(event.getLevel_type());
		levelDto.setState(event.getState());
		levelDto.setAttr1(event.getAttr1());
		levelDto.setAttr2(event.getAttr2());
		String operation = event.getOperation();
		switch (operation) {
		case "insert":
			oper.insert(levelDto);
			break;
		case "update":
			oper.updateById(levelDto);
			break;
		case "delete":
			oper.delete(levelDto);
			break;
		default:
			break;
		}
	}
}
