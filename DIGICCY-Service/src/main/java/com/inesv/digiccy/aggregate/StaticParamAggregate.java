package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.StaticParamCommand;
import com.inesv.digiccy.event.StaticParamEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/12/14 0014.
 */
public class StaticParamAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer staticParamId;

    public StaticParamAggregate(){}

    @CommandHandler
    public StaticParamAggregate(StaticParamCommand command){
        apply(new StaticParamEvent(command.getStaticParamId(),command.getParam(),command.getValue(), command.getCode(), command.getOperation()));
    }

    @EventHandler
    public void on(StaticParamEvent event){
        staticParamId = event.getStaticParamId();
    }


}
