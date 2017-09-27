package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.ResourceCommand;
import com.inesv.digiccy.event.ResourceEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class ResourceAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer resourceId;

    public ResourceAggregate(){}

    @CommandHandler
    public ResourceAggregate(ResourceCommand resourceCommand){
        apply(new ResourceEvent(resourceCommand.getResourceId(),resourceCommand.getType(),resourceCommand.getValue(),
                resourceCommand.getParent(),resourceCommand.getOperation(),resourceCommand.getCommon()));
    }

    @EventHandler
    public void on(ResourceEvent resourceEvent){
        resourceId = resourceEvent.getResourceId();
    }


}
