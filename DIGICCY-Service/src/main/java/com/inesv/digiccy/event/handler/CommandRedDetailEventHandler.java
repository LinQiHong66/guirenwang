package com.inesv.digiccy.event.handler;


import com.inesv.digiccy.dto.CommandRedDetailDto;
import com.inesv.digiccy.event.CommandRedDetailEvent;
import com.inesv.digiccy.persistence.command.CommandRedDetailsOperation;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class CommandRedDetailEventHandler {

	@Autowired
	CommandRedDetailsOperation commandRedDetailsOperation;

    @EventHandler
    public void commandRedDetailsEvent(CommandRedDetailEvent event) throws Exception {
    	String operation=event.getOperation();
    	CommandRedDetailDto commandRedDetailDtoDto = new CommandRedDetailDto();
    	commandRedDetailDtoDto.setId(event.getId());
    	commandRedDetailDtoDto.setUser_id(event.getUser_id());
    	commandRedDetailDtoDto.setCommand_id(event.getCommand_id());
    	commandRedDetailDtoDto.setCommand_number(event.getCommand_number());
    	commandRedDetailDtoDto.setCommand_name_price(event.getCommand_name_price());
    	commandRedDetailDtoDto.setState(event.getState());
    	commandRedDetailDtoDto.setDate(event.getDate());
    	commandRedDetailDtoDto.setAttr1(event.getAttr1());
    	commandRedDetailDtoDto.setAttr2(event.getAttr2());
    	switch (operation){
    		case "insertDetails":
    			commandRedDetailsOperation.insertDetails(commandRedDetailDtoDto);
    			break;
    		case "updateDetails":
    			commandRedDetailsOperation.updateDetails(commandRedDetailDtoDto);
    			break;
    		default:
                break;
    	}
    }

}
