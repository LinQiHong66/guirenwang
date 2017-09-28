package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.UserBalanceDetailCommand;
import com.inesv.digiccy.api.command.UserBasicCommand;
import com.inesv.digiccy.event.UserBalanceDetailEvent;
import com.inesv.digiccy.event.UserBasicInfoEvent;

public class UserBasicInfoAggregate extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
	private int id;

	public UserBasicInfoAggregate() {
	}

	@CommandHandler
	public UserBasicInfoAggregate(UserBasicCommand userBasicCommand) {
		apply(new UserBasicInfoEvent(userBasicCommand.getUserNo(), userBasicCommand.getNationality(),
				userBasicCommand.getJob(), userBasicCommand.getSex(), userBasicCommand.getBirthday(),
				userBasicCommand.getUserName(), userBasicCommand.getOpration()));
	}

	@EventHandler
	public void on(UserBasicInfoEvent userBasicInfoEvent) {
		id = userBasicInfoEvent.getUserNo();
	}
}
