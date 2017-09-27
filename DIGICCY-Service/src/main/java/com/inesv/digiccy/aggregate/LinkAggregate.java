package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.LinkCommand;
import com.inesv.digiccy.event.LinkEvent;

public class LinkAggregate extends AbstractAnnotatedAggregateRoot{
	@AggregateIdentifier
    private int id;

    public LinkAggregate() {}

    @CommandHandler
    public LinkAggregate(LinkCommand linkCommand){
        apply(new LinkEvent(linkCommand.getId(), linkCommand.getLinkName(), linkCommand.getLinkUrl(),linkCommand.getLinkType(), linkCommand.getOperation()));
    }

    @EventHandler
    public void on(LinkEvent event){
        id = event.getId();
    }
}
