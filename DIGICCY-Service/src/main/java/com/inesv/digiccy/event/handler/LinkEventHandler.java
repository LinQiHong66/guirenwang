package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.LinkDto;
import com.inesv.digiccy.event.LinkEvent;
import com.inesv.digiccy.persistence.other.LinkOperation;

public class LinkEventHandler {
	
    @Autowired
    private LinkOperation linkOperation;
	
	@EventHandler
    public void handle(LinkEvent event) throws Exception {
        LinkDto dto = new LinkDto();
        dto.setId(event.getId());
        dto.setLinkName(event.getLinkName());
        dto.setLinkUrl(event.getLinkUrl());
        dto.setLinkType(event.getLinkType());
        String operation = event.getOperation();
        switch (operation) {
		case "insert":
			linkOperation.insert(dto);
			break;
		case "delete":
			linkOperation.delById(dto.getId());
			break;
		case "update":
			linkOperation.updateById(dto);
			break;
		default:
			break;
		}
    }
}
