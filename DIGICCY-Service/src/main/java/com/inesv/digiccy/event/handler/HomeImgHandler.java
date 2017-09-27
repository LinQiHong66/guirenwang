package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.HomeImgDto;
import com.inesv.digiccy.event.HomeImgEvent;
import com.inesv.digiccy.persistence.other.HomeImgOperation;

public class HomeImgHandler {
	@Autowired
	HomeImgOperation homeImgOperation;

	@EventHandler
	public void handle(HomeImgEvent event) {

		HomeImgDto homeImgDto = new HomeImgDto();
		homeImgDto.setId(event.getId());
		homeImgDto.setImgDescription(event.getImgInfo());
		homeImgDto.setImgUrl(event.getImgUrl());
		String operation = event.getOperation();
		switch (operation) {
		case "insert":
			homeImgOperation.addHomeImg(homeImgDto);
			break;
		case "update":
			homeImgOperation.modifyHomeImg(homeImgDto);
			break;
		case "delete":
			homeImgOperation.delHomeImg(homeImgDto.getId());
			break;
		default:
			break;
		}
		
	}
}
