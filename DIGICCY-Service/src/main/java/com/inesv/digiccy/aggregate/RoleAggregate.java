package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.RoleCommand;
import com.inesv.digiccy.event.RoleEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class RoleAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer roleId;

    public RoleAggregate(){}

    @CommandHandler
    public RoleAggregate(RoleCommand roleCommand){
        apply(new RoleEvent(roleCommand.getRoleId(),roleCommand.getName(), roleCommand.getDescription(),roleCommand.getOperation()));
    }

    @EventHandler
    public void on(RoleEvent roleEvent){
        roleId = roleEvent.getRoleId();
    }


}
