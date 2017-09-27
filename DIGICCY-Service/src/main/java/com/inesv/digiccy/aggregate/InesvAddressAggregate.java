package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.InesvAddressCommand;
import com.inesv.digiccy.event.InesvAddressEvent;


public class InesvAddressAggregate extends AbstractAnnotatedAggregateRoot{

	@AggregateIdentifier
	private Long id;

	public InesvAddressAggregate() {
		
	}
	
	@CommandHandler
	public InesvAddressAggregate(InesvAddressCommand inesvAddressCommand) {
		apply(new InesvAddressEvent(inesvAddressCommand.getAddress_id(),inesvAddressCommand.getAddress(), inesvAddressCommand.getAddress_status(), inesvAddressCommand.getAddress_name(),
				inesvAddressCommand.getAddress_code(), inesvAddressCommand.getAddress_level(), inesvAddressCommand.getOperation()));
	}
	
	@EventHandler
	public void on(InesvAddressEvent inesvAddressEvent){
		id = inesvAddressEvent.getId();
	}
}
