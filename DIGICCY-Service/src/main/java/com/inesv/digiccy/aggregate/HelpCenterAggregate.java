package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.HelpCenterCommand;
import com.inesv.digiccy.event.HelpCenterEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2017/06/05 0016.
 */
public class HelpCenterAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private Long id;

	public HelpCenterAggregate() {
	}

	@CommandHandler
	public HelpCenterAggregate(HelpCenterCommand helpConterCemmand) {
		apply(new HelpCenterEvent(helpConterCemmand.getId(),
				helpConterCemmand.getHelp_name(),
				helpConterCemmand.getHelp_grade(),
				helpConterCemmand.getHelp_parent(),
				helpConterCemmand.getHelp_remark(),
				helpConterCemmand.getOperation()));
	}

	@EventHandler
	public void on(HelpCenterEvent helpConterEvent) {
		id = helpConterEvent.getId();
	}

}
