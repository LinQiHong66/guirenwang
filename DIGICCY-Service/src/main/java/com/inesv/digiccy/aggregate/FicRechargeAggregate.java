package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.FicRechargeCommand;
import com.inesv.digiccy.event.FicRechargeEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class FicRechargeAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer id;

    public FicRechargeAggregate(){}

    @CommandHandler
    public FicRechargeAggregate(FicRechargeCommand ficRechargeCommand){
        apply(new FicRechargeEvent(ficRechargeCommand.getId(),ficRechargeCommand.getUser_no(),ficRechargeCommand.getCoin_no(),ficRechargeCommand.getAddress(),ficRechargeCommand.getActual_price(),
                ficRechargeCommand.getGive_price(),ficRechargeCommand.getSum_price(),ficRechargeCommand.getState(),ficRechargeCommand.getDate(),ficRechargeCommand.getTixid(),ficRechargeCommand.getOperation()));
    }

    @EventHandler
    public void on(FicRechargeEvent ficRechargeEvent){
        this.id = ficRechargeEvent.getId();
    }








}
