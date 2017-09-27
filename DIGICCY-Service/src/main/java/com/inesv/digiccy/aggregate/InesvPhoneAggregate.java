package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.InesvPhoneCommand;
import com.inesv.digiccy.event.InesvPhoneEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class InesvPhoneAggregate extends AbstractAnnotatedAggregateRoot {
    @AggregateIdentifier
    private Integer id;

    public InesvPhoneAggregate() {}

    @CommandHandler
    public InesvPhoneAggregate(InesvPhoneCommand inesvPhoneCommand){
        apply(new InesvPhoneEvent(inesvPhoneCommand.getId(),inesvPhoneCommand.getUser_no(),inesvPhoneCommand.getPhone(),
                inesvPhoneCommand.getState(),inesvPhoneCommand.getCode(),inesvPhoneCommand.getOperation()));
    }

    @EventHandler
    public void on(InesvPhoneEvent inesvPhoneEvent){
        id = inesvPhoneEvent.getId();
    }

}
