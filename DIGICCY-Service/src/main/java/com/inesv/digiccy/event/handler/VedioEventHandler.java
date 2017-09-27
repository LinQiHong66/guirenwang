package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.VedioDto;
import com.inesv.digiccy.event.VedioEvent;
import com.inesv.digiccy.persistence.other.VedioOperation;

public class VedioEventHandler {
	@Autowired
	VedioOperation vedioOperation;
	@EventHandler
	public void handle(VedioEvent event) {
		VedioDto dto = new VedioDto();
		dto.setId(event.getId());
		dto.setInfo(event.getInfo());
		dto.setName(event.getName());
		dto.setUrl(event.getUrl());
		String operation = event.getOperation();
		switch (operation) {
		case "insert":
			vedioOperation.insert(dto);
			break;
		case "insertHomeVedio":
			vedioOperation.insertHomeVedio(dto);
			break;
		case "update":
			vedioOperation.update(dto);
			break;
		case "delete":
			break;
		default:
			break;
		}
	}
}
