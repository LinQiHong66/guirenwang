package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.UserVoucherCommand;
import com.inesv.digiccy.event.UserVoucherEvent;

/**
 * 
 * @author Liukeling
 *
 */
public class UserVoucherAggregate extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
	private int id;

	public UserVoucherAggregate() {
	}

	@CommandHandler
	public UserVoucherAggregate(UserVoucherCommand command) {
		apply(new UserVoucherEvent(command.getId(), command.getCardId(), command.getCardType(), command.getImgUrl1(),
				command.getImgUrl2(), command.getImgUrl3(), command.getState(), command.getUserNo(),
				command.getRealName(), command.getOperating(), command.getMyvoucherType(), command.getStartDate(), command.getEndDate()));
	}

	@EventHandler
	public void on(UserVoucherEvent event) {
		this.id = event.getId();
	}

}
