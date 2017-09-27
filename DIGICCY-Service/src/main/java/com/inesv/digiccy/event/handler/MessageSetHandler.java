package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.MessageSetDto;
import com.inesv.digiccy.event.MessageSetEvent;
import com.inesv.digiccy.persistence.param.MessageSetOperation;

public class MessageSetHandler {

	@Autowired
	MessageSetOperation messageSetOperation;

	@EventHandler
	public void handle(MessageSetEvent event) throws Exception {
		String operation = event.getOperation();
		MessageSetDto messageSetDto = new MessageSetDto();
		messageSetDto.setId(event.getId());
		messageSetDto.setLimit_date(event.getLimit_date());
		messageSetDto.setLimit_number(event.getLimit_number());
		messageSetDto.setLimit_ip(event.getLimit_ip());
		messageSetDto.setLimit_name(event.getLimit_name());
		messageSetDto.setUpdate_time(event.getUpdate_time());
		switch (operation) {
		case "insert":
			messageSetOperation.addMessageSet(messageSetDto);
			break;
		case "update":
			messageSetOperation.updateMessageSet(messageSetDto);
			break;
		case "delete":
			messageSetOperation.deteleMessageSet(event.getId());
			break;
		default:
			break;
		}
	}
}
