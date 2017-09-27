package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.MessageSetCommand;
import com.inesv.digiccy.event.MessageSetEvent;

public class MessageSetAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private Integer id;

	public MessageSetAggregate() {
	}

	@CommandHandler
	public MessageSetAggregate(MessageSetCommand command) {
		apply(new MessageSetEvent(command.getId(), command.getLimit_date(), command.getLimit_number(),
				command.getLimit_ip(), command.getLimit_name(), command.getUpdate_time(), command.getOperation()));
		System.out.println("MessageSetAggregate");
	}

	@EventHandler
	public void on(MessageSetEvent event) {
		id = event.getId();
	}

}
