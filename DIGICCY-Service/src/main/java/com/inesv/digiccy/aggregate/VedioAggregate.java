package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.UserVoucherCommand;
import com.inesv.digiccy.api.command.VedioCommand;
import com.inesv.digiccy.event.UserVoucherEvent;
import com.inesv.digiccy.event.VedioEvent;

/**
 * 
 * @author Liukeling
 *
 */
public class VedioAggregate extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
	private int id;

	public VedioAggregate() {
	}

	@CommandHandler
	public VedioAggregate(VedioCommand command) {
		apply(new VedioEvent(command.getId(), command.getName(), command.getUrl(), command.getInfo(), command.getOperation()));
	}

	@EventHandler
	public void on(UserVoucherEvent event) {
		this.id = event.getId();
	}
}
