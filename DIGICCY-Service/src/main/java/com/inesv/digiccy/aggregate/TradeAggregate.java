package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.EntrustCommand;
import com.inesv.digiccy.event.EntrustEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class TradeAggregate extends AbstractAnnotatedAggregateRoot {

	private static final long serialVersionUID = -414961613569229401L;
	
	@AggregateIdentifier
    private Long id;

    public TradeAggregate(){}

    @CommandHandler
    public TradeAggregate(EntrustCommand entrustCommand){
        apply(new EntrustEvent(entrustCommand.getId(),entrustCommand.getUser_no(),entrustCommand.getEntrust_coin(),entrustCommand.getConvert_coin(),
        		entrustCommand.getConvert_price(),entrustCommand.getEntrust_type(),entrustCommand.getEntrust_price(),entrustCommand.getEntrust_num(),
        		entrustCommand.getDeal_num(),entrustCommand.getPiundatge(),entrustCommand.getState(),entrustCommand.getDate(),entrustCommand.getOperation()));
    }

    @EventHandler
    public void on(EntrustEvent entrustEvent){
        id = entrustEvent.getId();
    }

}
