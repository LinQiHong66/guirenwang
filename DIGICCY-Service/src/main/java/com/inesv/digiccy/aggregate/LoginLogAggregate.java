package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.LoginLogCommand;
import com.inesv.digiccy.event.LoginLogEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public class LoginLogAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Long id;

    public LoginLogAggregate() {}

    @CommandHandler
    public LoginLogAggregate(LoginLogCommand loginLogCommand){
        apply(new LoginLogEvent(loginLogCommand.getUser_no(),loginLogCommand.getOpt_type(),loginLogCommand.getOpt_remark(),loginLogCommand.getOpt_ip(),loginLogCommand.getOpt_address(),loginLogCommand.getState(),loginLogCommand.getDate()));
    }

    @EventHandler
    public void on(LoginLogEvent event){
        id = event.getId();
    }

}
