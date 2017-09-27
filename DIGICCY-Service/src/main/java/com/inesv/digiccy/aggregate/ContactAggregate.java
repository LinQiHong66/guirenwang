package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.CoinCommand;
import com.inesv.digiccy.api.command.ContactCommand;
import com.inesv.digiccy.event.CoinEvent;
import com.inesv.digiccy.event.ContactEvent;

/**
 * 
 * @author Liukeling
 *
 */
public class ContactAggregate extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
	private int id;

	public ContactAggregate() {
	}

	@CommandHandler
	public ContactAggregate(ContactCommand command) {
		apply(new ContactEvent(command.getId(), command.getEmail(), command.getWeixin(), command.getWxqrcord(),command.getQqqrcord(),
				command.getQq(), command.getAddress(), command.getRemark(),command.getTelphone(), command.getOperation()));
	}

	@EventHandler
	public void on(ContactEvent event) {
		id = event.getId();
	}
}
