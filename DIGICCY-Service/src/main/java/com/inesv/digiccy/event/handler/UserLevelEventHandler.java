package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.UserLevelDto;
import com.inesv.digiccy.event.UserLevelEvent;
import com.inesv.digiccy.persistence.userlevel.UserLevelOper;

@Component
public class UserLevelEventHandler {
	@Autowired
	UserLevelOper userLevelOper;
	
	@EventHandler
	public void handler(UserLevelEvent userLevelEvent){
		switch (userLevelEvent.getOperation()) {
		case "updateLevel":
			userLevelOper.updateLevel(userLevelEvent.getStatus(),userLevelEvent.getLevel_id(),userLevelEvent.getLevel());
			break;
		case "addLevel":
			userLevelOper.addLevel(new UserLevelDto(userLevelEvent.getUser_id(),userLevelEvent.getLevel(),userLevelEvent.getStatus()));
			break;
		default:
			break;
		}
	}
	
}
