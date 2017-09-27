package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.PaymentBankCommand;
import com.inesv.digiccy.event.PaymentBankEvent;

public class PaymentBankAggregate extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
	private int id;

	public PaymentBankAggregate() {
	}

	@CommandHandler
	public PaymentBankAggregate(PaymentBankCommand command) {
		apply(new PaymentBankEvent(command.getId(), command.getBankName(), command.getBankCardId(),
				command.getBankUserName(), command.getRemark(), command.getOperation()));
	}

	@EventHandler
	public void on(PaymentBankEvent event) {
		id = event.getId();
	}
}
