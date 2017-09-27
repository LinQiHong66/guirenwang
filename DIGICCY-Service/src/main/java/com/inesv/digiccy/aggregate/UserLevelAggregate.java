package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.UserLevelCommand;
import com.inesv.digiccy.event.UserLevelEvent;

public class UserLevelAggregate extends AbstractAnnotatedAggregateRoot{
	
	@AggregateIdentifier
	private Long level_id;
	
	public void UserLevelAggregate(){}
	
	@CommandHandler
	public void UserLevelAggregate(UserLevelCommand command){
		apply(new UserLevelEvent(command.getLevel_id(), command.getUser_id(), command.getLevel(), command.getStatus(), command.getOperation()));
	}
	
	@EventHandler
	private void on(UserLevelEvent uEvent){
		this.level_id = uEvent.getLevel_id();
	}
}
