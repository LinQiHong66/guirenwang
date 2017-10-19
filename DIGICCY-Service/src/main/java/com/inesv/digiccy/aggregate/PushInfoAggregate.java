package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.PushInfoCommand;
import com.inesv.digiccy.event.PushInfoEvent;

public class PushInfoAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private int id;

	public PushInfoAggregate() {
	}

	@CommandHandler
	public PushInfoAggregate(PushInfoCommand pushInfoCommand) {
		apply(new PushInfoEvent(pushInfoCommand.getId(), pushInfoCommand.getUserNo(), pushInfoCommand.getMaxPrice(),
				pushInfoCommand.getMinPrice(), pushInfoCommand.isPush(), pushInfoCommand.getDriverToken(),
				pushInfoCommand.getUserName(), pushInfoCommand.getOpration()));
	}

	@EventHandler
	public void on(PushInfoEvent pushInfoEvent) {
		id = pushInfoEvent.getId();
	}

}
