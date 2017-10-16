package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.SpeculativeFundCommand;
import com.inesv.digiccy.event.SpeculativeFundEvent;

public class SpeculativeFundAggregate extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
	private int id;

	public SpeculativeFundAggregate() {
	}

	@CommandHandler
	public SpeculativeFundAggregate(SpeculativeFundCommand speculativeFundCommand) {
		apply(new SpeculativeFundCommand(speculativeFundCommand.getId(), speculativeFundCommand.getUser_no(),
				speculativeFundCommand.getCoin_no(), speculativeFundCommand.getDeal_type(),
				speculativeFundCommand.getDeal_price(), speculativeFundCommand.getOptimum_price(),
				speculativeFundCommand.getMost_amount(), speculativeFundCommand.getPercent(),
				speculativeFundCommand.getDeal_num(), speculativeFundCommand.getSum_price(),
				speculativeFundCommand.getPoundage(), speculativeFundCommand.getDate(),
				speculativeFundCommand.getAttr1(), speculativeFundCommand.getAttr2(),
				speculativeFundCommand.getOperation()));
	}

	@EventHandler
	public void on(SpeculativeFundEvent event) {
		id = event.getId();
	}
}
