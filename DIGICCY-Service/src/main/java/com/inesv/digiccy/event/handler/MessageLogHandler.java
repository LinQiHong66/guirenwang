package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.MessageLogDto;
import com.inesv.digiccy.event.MessageLogEvent;
import com.inesv.digiccy.persistence.param.MessageLogOperation;

public class MessageLogHandler {
	@Autowired
	MessageLogOperation messageLogOperation;

	@EventHandler
	public void handle(MessageLogEvent event) throws Exception {
		String operation = event.getOperation();
		MessageLogDto messageLogDto = new MessageLogDto();
		messageLogDto.setId(event.getId());
		messageLogDto.setPhone_number(event.getPhone_number());
		messageLogDto.setReceive_name(event.getReceive_name());
		messageLogDto.setSms_content(event.getSms_content());
		messageLogDto.setUpdate_time(event.getUpdate_time());
		messageLogDto.setUser_id(event.getUser_id());
		switch (operation) {
		case "insert":
			messageLogOperation.addMessageLog(messageLogDto);
			break;
		case "update":
			messageLogOperation.updateMessageLog(messageLogDto);
			break;
		case "delete":
			messageLogOperation.deteleMessageLog(event.getId());
			break;
		default:
			break;
		}
	}
}
