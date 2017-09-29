package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.UserBasicInfoDto;
import com.inesv.digiccy.event.UserBasicInfoEvent;
import com.inesv.digiccy.persistence.user.UserBasicInfoOperation;

public class UserBasicEventHandler {
	
	@Autowired
	UserBasicInfoOperation userBasicInfoOptation;
	
	@EventHandler
	public void handle(UserBasicInfoEvent event) throws Exception {
		UserBasicInfoDto dto = new UserBasicInfoDto();
		dto.setBirthday(event.getBirthday());
		dto.setJob(event.getJob());
		dto.setNationality(event.getNationality());
		dto.setSex(event.getSex());
		dto.setUserNo(event.getUserNo());
		String opration = event.getOpration();
		switch (opration) {
		case "insert":
			userBasicInfoOptation.addBasicInfo(dto);
			break;
		default:
			break;
		}
	}
}
