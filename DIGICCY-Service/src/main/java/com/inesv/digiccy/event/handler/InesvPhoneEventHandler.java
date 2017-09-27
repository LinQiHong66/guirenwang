package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.InesvPhoneDto;
import com.inesv.digiccy.event.InesvPhoneEvent;
import com.inesv.digiccy.persistence.reg.RegUserPersistence;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class InesvPhoneEventHandler {
    @Autowired
    RegUserPersistence regUserPersistence;

    @EventHandler
    public void handle(InesvPhoneEvent event) throws Exception {
        InesvPhoneDto inesvPhoneDto = new InesvPhoneDto();
        inesvPhoneDto.setUser_no(event.getUser_no());
        inesvPhoneDto.setPhone(event.getPhone());
        inesvPhoneDto.setState(event.getState());
        inesvPhoneDto.setCode(event.getCode());
        String operation = event.getOperation();
        switch (operation){
            case "insert":
                regUserPersistence.addPhone(inesvPhoneDto);
                break;
            default:
                break;
        }
    }

}
