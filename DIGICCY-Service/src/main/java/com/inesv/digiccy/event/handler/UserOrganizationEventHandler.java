package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.InesvUserOrganizationDto;
import com.inesv.digiccy.event.UserOrganizationEvent;
import com.inesv.digiccy.persistence.user.UserOrganizationOperation;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class UserOrganizationEventHandler {

    @Autowired
    UserOrganizationOperation userOrganizationOperation;

    @EventHandler
    public void handle(UserOrganizationEvent event) throws Exception {
    	InesvUserOrganizationDto organizationDto = new InesvUserOrganizationDto();
    	organizationDto.setId(event.getId());
    	organizationDto.setUser_no(event.getUser_no());
    	organizationDto.setState(event.getState());
    	organizationDto.setOrg_type(event.getOrg_type());
    	organizationDto.setDate(event.getDate());
    	organizationDto.setAttr1(event.getAttr1());
    	organizationDto.setAttr2(event.getAttr2());
    	String operation=event.getOperation();
        switch (operation){
            case "addUserOrganization":
            	userOrganizationOperation.addUserOrganization(organizationDto);
                break;
            default:
                break;
        }
    }

}
