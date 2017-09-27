package com.inesv.digiccy.event.handler;


import com.inesv.digiccy.dto.CommandRedDto;
import com.inesv.digiccy.event.CommandRedEvent;
import com.inesv.digiccy.persistence.command.CommandRedOperation;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/06/06 0016.
 */
public class CommandRedEventHandler {
	
	@Autowired
	CommandRedOperation commandRedOperation;

    @EventHandler
    public void commandRedEvent(CommandRedEvent event) throws Exception {
    	String operation = event.getOperation();
    	CommandRedDto commandRedDto = new CommandRedDto();
    	commandRedDto.setId(event.getId());
    	commandRedDto.setCommand_no(event.getCommand_no());
    	commandRedDto.setCommand_name(event.getCommand_name());
    	commandRedDto.setCommand_prize_type(event.getCommand_prize_type());
    	commandRedDto.setCommand_name_price(event.getCommand_name_price());
    	commandRedDto.setCommand_number(event.getCommand_number());
    	commandRedDto.setCommand_remark(event.getCommand_remark());
    	commandRedDto.setState(event.getState());
    	commandRedDto.setDate(event.getDate());
    	switch (operation){
    		case "updateCommandRed":
    			commandRedOperation.updateCommandRed(commandRedDto);
    			break;
    		case "insertCommandRed" :
    			commandRedOperation.insertCommandRed(commandRedDto);
    			break;
    		case "deleteCommandRed" :
    			commandRedOperation.deleteCommandRed(commandRedDto);
    			break;
    		case "updateCommandRedState" :
    			commandRedOperation.updateCommandRedState(commandRedDto);
    			break;
    		default:
                break;
    	}
    }

}
