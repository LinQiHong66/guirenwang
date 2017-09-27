package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.CreateUserCommand;
import com.inesv.digiccy.api.command.UserCommand;
import com.inesv.digiccy.event.CreateUserEvent;
import com.inesv.digiccy.event.UserEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class UserAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long id;

    public UserAggregate(){}

    @CommandHandler
    public UserAggregate(CreateUserCommand userCommand){
        apply(new CreateUserEvent(userCommand.getId(),userCommand.getUsername(),userCommand.getPassword()));
    }
    
    /*
     * 测试
     */
    @CommandHandler
    public UserAggregate(UserCommand userCommand){
        apply(new UserEvent(userCommand.getId(),userCommand.getUsername(),userCommand.getUser_no(),userCommand.getPassword(),userCommand.getReal_name(),userCommand.getMail(),userCommand.getPhone(),userCommand.getCertificate_num(),userCommand.getDeal_pwd(),userCommand.getAlipay(),userCommand.getState(),userCommand.getOperation()));
    }

    @EventHandler
    public void on(CreateUserEvent userEvent){
        id = userEvent.getId();
    }



}
