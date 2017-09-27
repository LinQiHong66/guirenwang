package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.CommandRedDetailCommand;
import com.inesv.digiccy.event.CommandRedDetailEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2017/06/05 0016.
 */
public class CommandRedDetailAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private Long id;

	public CommandRedDetailAggregate() {
	}

	@CommandHandler
	public CommandRedDetailAggregate(CommandRedDetailCommand commandRedDetailCommand) {
		apply(new CommandRedDetailEvent(commandRedDetailCommand.getId(),
				commandRedDetailCommand.getUser_id(),
				commandRedDetailCommand.getCommand_id(),
				commandRedDetailCommand.getCommand_number(),
				commandRedDetailCommand.getCommand_name_price(),
				commandRedDetailCommand.getState(),
				commandRedDetailCommand.getDate(),
				commandRedDetailCommand.getAttr1(),
				commandRedDetailCommand.getAttr2(),
				commandRedDetailCommand.getOperation()));
	}

	@EventHandler
	public void on(CommandRedDetailEvent commandRedDetailEvent) {
		id = commandRedDetailEvent.getId();
	}

}
