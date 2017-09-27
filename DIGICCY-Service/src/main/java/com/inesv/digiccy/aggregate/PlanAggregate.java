package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.PlanCommand;
import com.inesv.digiccy.event.PlanEvent;

public class PlanAggregate extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
	private Long id;
	
	public PlanAggregate(){}
	
	@CommandHandler
	public PlanAggregate(PlanCommand planCommand){
		apply(new PlanEvent(planCommand.getId(),planCommand.getUser_id(),planCommand.getBill_id(),planCommand.getPlan_type(),planCommand.getTop_money_start(),planCommand.getLow_money_stop(),
				planCommand.getLow_money_start(),planCommand.getLow_money_stop(),planCommand.getPlan_status(),planCommand.getPlan_time(),planCommand.getRemark(),planCommand.getPlan_money(),planCommand.getOperation()));
	}
	
	@EventHandler
	public void on(PlanEvent planEvent){
		this.id=planEvent.getId();
	}
}
