package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import com.inesv.digiccy.api.command.NoticeTypeCommand;
import com.inesv.digiccy.event.NoticeTypeEvent;

public class NoticeTypeAggregate extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
	private int id;

	public NoticeTypeAggregate() {}

	@CommandHandler
	    public NoticeTypeAggregate(NoticeTypeCommand command){
	        apply(new NoticeTypeEvent(command.getId(), command.getName(), command.getOpreation()));
	    }

	@EventHandler
	public void on(NoticeTypeEvent event) {
		id = event.getId();
	}
}
