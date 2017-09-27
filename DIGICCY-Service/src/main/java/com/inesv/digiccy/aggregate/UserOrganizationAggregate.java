package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.UserOrganizationCommand;
import com.inesv.digiccy.event.UserOrganizationEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class UserOrganizationAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long id;

    public UserOrganizationAggregate(){}

    @CommandHandler
    public UserOrganizationAggregate(UserOrganizationCommand userCommand){
        apply(new UserOrganizationEvent(userCommand.getId(),userCommand.getUser_no(),userCommand.getState(),
        		userCommand.getOrg_type(),userCommand.getDate(),userCommand.getAttr1(),userCommand.getAttr2(),userCommand.getOperation()));
    }

    @EventHandler
    public void on(UserOrganizationEvent userOrganizationEvent){
        id = userOrganizationEvent.getId();
    }



}
