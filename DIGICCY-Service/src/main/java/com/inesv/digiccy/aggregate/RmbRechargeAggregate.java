package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.RmbRechargeCommand;
import com.inesv.digiccy.event.RmbRechargeEvnet;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class RmbRechargeAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long id;

    public RmbRechargeAggregate(){
    }

    @CommandHandler
    public RmbRechargeAggregate(RmbRechargeCommand command){
        apply(new RmbRechargeEvnet(command.getId(),command.getUser_no(),command.getRecharge_type(),command.getRecharge_price(),command.getRecharge_order(),command.getActual_price(),
                command.getState(),command.getDate(),command.getOperating()));
    }

    @EventHandler
    public void on(RmbRechargeEvnet rmbRechargeEvnet){
        this.id = rmbRechargeEvnet.getId();
    }



}
