package com.inesv.digiccy.event.handler;

import java.sql.SQLException;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.PushInfoDto;
import com.inesv.digiccy.event.PushInfoEvent;
import com.inesv.digiccy.persistence.push.PushInfoOperation;

public class PushInfoHandler {

	@Autowired
	PushInfoOperation pushInfoOperation;

	@EventHandler
	public void handle(PushInfoEvent event) throws Exception {
		PushInfoDto dto = new PushInfoDto();
		dto.setDriverToken(event.getDriverToken());
		dto.setId(event.getId());
		dto.setMaxPrice(event.getMaxPrice());
		dto.setMinPrice(event.getMinPrice());
		dto.setPush(event.isPush());
		dto.setUserName(event.getUserName());
		dto.setUserNo(event.getUserNo());
		String opration = event.getOpration();
		switch (opration) {
		case "updateDriverToken":
			pushInfoOperation.updateDriverToken(dto);
			break;
		case "setSection":
			pushInfoOperation.setSectionOfPush(dto);
			break;
		default:
			break;
		}
	}
}
