package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.SubCoreCommand;
import com.inesv.digiccy.event.SubCoreEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class SubCoreAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long subCoreId;

    public SubCoreAggregate(){}

    @CommandHandler
    public SubCoreAggregate(SubCoreCommand subCoreCommand){
        apply(new SubCoreEvent(subCoreCommand.getId(),subCoreCommand.getSub_name(),subCoreCommand.getCoin_type(),subCoreCommand.getPrice(),subCoreCommand.getNum(),subCoreCommand.getAlready(),subCoreCommand.getLimit_buy(),
                subCoreCommand.getDate(),subCoreCommand.getThaw_num(),subCoreCommand.getCycle(),subCoreCommand.getStatus(),subCoreCommand.getPhoto(),subCoreCommand.getOperation()));
    }

    @EventHandler
    public void on(SubCoreEvent subCoreEvent){
        subCoreId = subCoreEvent.getId();
    }

}
