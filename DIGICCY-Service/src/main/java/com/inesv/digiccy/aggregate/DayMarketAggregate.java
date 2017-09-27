package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.DayMarketCommand;
import com.inesv.digiccy.event.DayMarketEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class DayMarketAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
    private Long id;

    public DayMarketAggregate(){}

    @CommandHandler
    public DayMarketAggregate(DayMarketCommand dayMarketCommand){
        apply(new DayMarketEvent(dayMarketCommand.getId(),dayMarketCommand.getCoin_type(),dayMarketCommand.getNewes_deal(),dayMarketCommand.getBuy_price(),dayMarketCommand.getSell_price(),dayMarketCommand.getDeal_num(),dayMarketCommand.getDeal_price(),dayMarketCommand.getDay_percent(),dayMarketCommand.getMax_price(),dayMarketCommand.getMin_price(),dayMarketCommand.getState(),dayMarketCommand.getDate(),dayMarketCommand.getOperation()));
    }

    @EventHandler
    public void on(DayMarketEvent dayMarketEvent){
        id = dayMarketEvent.getId();
    }

}
