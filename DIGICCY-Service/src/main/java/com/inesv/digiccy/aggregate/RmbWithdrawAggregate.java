package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.RmbWithdrawCommand;
import com.inesv.digiccy.event.RmbWithdrawEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class RmbWithdrawAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer id;

    public RmbWithdrawAggregate() {
    }

    @CommandHandler
    public RmbWithdrawAggregate(RmbWithdrawCommand command){
        apply(new RmbWithdrawEvent(command.getId(),command.getUser_no(),command.getBank(),command.getPrice(),command.getPoundage(),command.getActual_price(),command.getDate(),
                command.getState(),command.getOperation()));
    }

    @EventHandler
    public void on(RmbWithdrawEvent rmbWithdrawEvent){
        id = rmbWithdrawEvent.getId();
    }
}
