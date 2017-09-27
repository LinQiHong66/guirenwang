package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.MessageLogCommand;
import com.inesv.digiccy.event.MessageLogEvent;

public class MessageLogAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private Long id;

	public MessageLogAggregate() {
	}

	@CommandHandler
	public MessageLogAggregate(MessageLogCommand command) {
		apply(new MessageLogEvent(command.getId(), command.getPhone_number(), command.getReceive_name(),
				command.getSms_content(), command.getUpdate_time(), command.getUser_id(), command.getOperation()));
	}

	@EventHandler
	public void on(MessageLogEvent event) {
		id = event.getId();
	}

}
