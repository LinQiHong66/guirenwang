package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.CoinLevelProportionCommand;
import com.inesv.digiccy.event.CoinLevelProportionEvent;

public class CoinLevelProportionAggregate extends AbstractAnnotatedAggregateRoot{
	
	@AggregateIdentifier
	private Long id;
	
	public CoinLevelProportionAggregate(){
		
	}
	
	@CommandHandler
	public CoinLevelProportionAggregate(CoinLevelProportionCommand event){
		apply(new CoinLevelProportionEvent(event.getId(),event.getCoin_no(),event.getLevel_one(),event.getLevel_two(),event.getLevel_three(),event.getLevel_four(),event.getLevel_five(),
				event.getLevel_type(),event.getState(),event.getAttr1(),event.getAttr2(),event.getOperation()));
	}
	
	@EventHandler
	public void on(CoinLevelProportionEvent event){
		this.id = event.getId();
	}
}
