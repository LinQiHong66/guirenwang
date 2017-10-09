package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.EntrustCommand;
import com.inesv.digiccy.event.EntrustEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class EntrustManageAggregate extends AbstractAnnotatedAggregateRoot {
    @AggregateIdentifier
    private Long id;

    public EntrustManageAggregate() {}

    @CommandHandler
    public EntrustManageAggregate(EntrustCommand command){
        apply(new EntrustEvent(command.getId(),command.getUser_no(),command.getEntrust_coin(),command.getConvert_coin(),command.getConvert_price(),command.getEntrust_type(),
        		command.getEntrust_price(),command.getEntrust_num(),command.getDeal_num(),command.getPiundatge(),command.getState(),command.getDate(),command.getOperation()));
    }

    @EventHandler
    public void on(EntrustEvent event){id=event.getId();}
}
