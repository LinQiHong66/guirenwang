package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.event.UserInviteEvent;
import com.inesv.digiccy.persistence.finance.RecUserPersistence;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yc on 2016/12/20 0020.
 */
public class UserInviteEventHandler {

    @Autowired
    RecUserPersistence recUserPersistence;

    @EventHandler
    public void handle(UserInviteEvent event){
        String operation = event.getOperation();
        switch(operation){
            case "updateInvite":
                recUserPersistence.updateInvite(event.getUser_no(),event.getInvite_num());
                break;
        }
    }

}
