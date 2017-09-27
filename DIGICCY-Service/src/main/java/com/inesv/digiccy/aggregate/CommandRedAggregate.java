package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.CommandRedCommand;
import com.inesv.digiccy.event.CommandRedEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2017/06/05 0016.
 */
public class CommandRedAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private Long id;

	public CommandRedAggregate() {
	}

	@CommandHandler
	public CommandRedAggregate(CommandRedCommand commandRedCommand) {
		apply(new CommandRedEvent(commandRedCommand.getId(),
				commandRedCommand.getCommand_no(),
				commandRedCommand.getCommand_name(),
				commandRedCommand.getCommand_prize_type(),
				commandRedCommand.getCommand_name_price(),
				commandRedCommand.getCommand_number(),
				commandRedCommand.getCommand_remark(),
				commandRedCommand.getState(),
				commandRedCommand.getDate(),
				commandRedCommand.getOperation()));
	}

	@EventHandler
	public void on(CommandRedEvent commandRedEvent) {
		id = commandRedEvent.getId();
	}

}
