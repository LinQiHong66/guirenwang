package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.LoginLogDto;
import com.inesv.digiccy.event.LoginLogEvent;
import com.inesv.digiccy.persistence.user.UserOperation;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public class LoginLogEventHandler {

    @Autowired
    private UserOperation userOperation;

    @EventHandler
    public void handle(LoginLogEvent event) throws Exception {
        LoginLogDto loginLogDto = new LoginLogDto(event.getUser_no(),event.getOpt_type(),event.getOpt_remark(),event.getOpt_ip(),event.getOpt_address(),event.getState(),event.getDate());
        userOperation.addIpAddRess(loginLogDto);
    }

}
