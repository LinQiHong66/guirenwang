package com.inesv.digiccy.event.handler;


import com.inesv.digiccy.dto.CommandRedDto;
import com.inesv.digiccy.dto.HelpCenterDto;
import com.inesv.digiccy.event.CommandRedEvent;
import com.inesv.digiccy.event.HelpCenterEvent;
import com.inesv.digiccy.persistence.command.CommandRedOperation;
import com.inesv.digiccy.persistence.helpCenter.HelpCenterOperation;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/06/06 0016.
 */
public class HelpCenterEventHandler {
	
	@Autowired
	HelpCenterOperation helpCenterOperation;

    @EventHandler
    public void helpCenterEvent(HelpCenterEvent event) throws Exception {
    	String operation = event.getOperation();
    	HelpCenterDto helpConterDto = new HelpCenterDto();
    	helpConterDto.setId(event.getId());
    	helpConterDto.setHelp_name(event.getHelp_name());
    	helpConterDto.setHelp_parent(event.getHelp_parent());
    	helpConterDto.setHelp_grade(event.getHelp_grade());
    	helpConterDto.setHelp_remark(event.getHelp_remark());
    	switch (operation){
    		case "updateHelpCenter":
    			helpCenterOperation.updateHelpCenter(helpConterDto);
    			break;
    		case "insertHelpCenter" :
    			helpCenterOperation.insertHelpCenter(helpConterDto);
    			break;
    		case "deleteHelpCenter" :
    			helpCenterOperation.deleteHelpCenter(helpConterDto);
    			break;
    		default:
                break;
    	}
    }

}
