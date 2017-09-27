package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.CommandRedCommand;
import com.inesv.digiccy.api.command.UserBalanceDetailCommand;
import com.inesv.digiccy.event.CommandRedEvent;
import com.inesv.digiccy.event.UserBalanceDetailEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2017/06/05 0016.
 */
public class UserBalanceDetailAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private Long id;

	public UserBalanceDetailAggregate() {
	}

	@CommandHandler
	public UserBalanceDetailAggregate(UserBalanceDetailCommand userBalanceDetailCommand) {
		apply(new UserBalanceDetailEvent(userBalanceDetailCommand.getId(),
				userBalanceDetailCommand.getUser_no(),
				userBalanceDetailCommand.getAdmin_no(),
				userBalanceDetailCommand.getCoin_type(),
				userBalanceDetailCommand.getCoin_price(),
				userBalanceDetailCommand.getRemark(),
				userBalanceDetailCommand.getDate(),
				userBalanceDetailCommand.getOperation()));
	}

	@EventHandler
	public void on(UserBalanceDetailEvent userBalanceDetailEvent) {
		id = userBalanceDetailEvent.getId();
	}

}
