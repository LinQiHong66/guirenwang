package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.PoundageCommand;
import com.inesv.digiccy.event.PoundageEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by yc on 2017/1/5 0005.
 */
public class PoundageAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long id;

    public PoundageAggregate(){}

    @CommandHandler
    public PoundageAggregate(PoundageCommand command){
        apply(new PoundageEvent(command.getId(),command.getUser_no(),command.getUser_name(),command.getUser_code(),command.getOptype(),command.getType(),
        		command.getMoney(),command.getSum_money(),command.getDate(),command.getOperation()));
    }

    @EventHandler
    public void on(PoundageEvent event){
        this.id = event.getId();
    }

}
