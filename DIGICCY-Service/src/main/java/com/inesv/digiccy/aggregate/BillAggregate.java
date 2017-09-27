package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.BillCommand;
import com.inesv.digiccy.event.BillEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class BillAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long id;

    public BillAggregate(){}

    @CommandHandler
    public BillAggregate(BillCommand billCommand){
        apply(new BillEvent(billCommand.getUser_no(),billCommand.getRecharge_phone(),
                billCommand.getRecharge_price(),billCommand.getPay_type(),
                billCommand.getPay_price(),billCommand.getHandle_date(),
                billCommand.getState(),billCommand.getDate(),billCommand.getOperation()));
    }

    @EventHandler
    public void on(BillEvent billEvent){
        id = billEvent.getId();
    }

}
