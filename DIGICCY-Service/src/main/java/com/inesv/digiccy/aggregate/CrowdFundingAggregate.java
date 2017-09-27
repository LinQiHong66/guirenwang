package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.CrowdFundingCommand;
import com.inesv.digiccy.event.CrowdFundingEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2017/06/05 0016.
 */
public class CrowdFundingAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private Long id;

	public CrowdFundingAggregate() {
	}

	@CommandHandler
	public CrowdFundingAggregate(CrowdFundingCommand crowdFundingCommand) {
		apply(new CrowdFundingEvent(crowdFundingCommand.getId(),
				crowdFundingCommand.getIco_no(),
				crowdFundingCommand.getIco_name(),
				crowdFundingCommand.getIco_photo(),
				crowdFundingCommand.getIco_target(),
				crowdFundingCommand.getIco_current(),
				crowdFundingCommand.getIco_status(),
				crowdFundingCommand.getIco_price_type(),
				crowdFundingCommand.getIco_price(),
				crowdFundingCommand.getIco_sum_price(),
				crowdFundingCommand.getIco_remark(),
				crowdFundingCommand.getIco_explain(),
				crowdFundingCommand.getIco_state(),
				crowdFundingCommand.getBegin_date(),
				crowdFundingCommand.getEnd_date(),
				crowdFundingCommand.getAttr1(),
				crowdFundingCommand.getAttr2(),
				crowdFundingCommand.getAttr3(),
				crowdFundingCommand.getAttr4(),
				crowdFundingCommand.getAttr5(),
				crowdFundingCommand.getAttr6(),
				crowdFundingCommand.getOperation()));
	}

	@EventHandler
	public void on(CrowdFundingEvent crowdFundingEvent) {
		id = crowdFundingEvent.getId();
	}

}
