package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.CrowdFundingDetailsCommand;
import com.inesv.digiccy.event.CrowdFundingDetailsEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class CrowdFundingDetailsAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private int user_id;

	public CrowdFundingDetailsAggregate() {
	}

	@CommandHandler
	public CrowdFundingDetailsAggregate(
			CrowdFundingDetailsCommand crowdFundingDetailsCommand) {
		apply(new CrowdFundingDetailsEvent(
				crowdFundingDetailsCommand.getUser_id(),
				crowdFundingDetailsCommand.getIco_id(),
				crowdFundingDetailsCommand.getIco_user_number(),
				crowdFundingDetailsCommand.getIco_user_sumprice(),
				crowdFundingDetailsCommand.getDate(),
				crowdFundingDetailsCommand.getAttr1(),
				crowdFundingDetailsCommand.getAttr2(),
				crowdFundingDetailsCommand.getOperation()));
	}

	@EventHandler
	public void on(CrowdFundingDetailsEvent crowdFundingDetailsEvent) {
		user_id = crowdFundingDetailsEvent.getUser_id();
	}

}
