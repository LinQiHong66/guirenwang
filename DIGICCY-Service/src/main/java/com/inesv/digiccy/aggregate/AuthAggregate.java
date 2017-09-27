package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.AuthCommand;
import com.inesv.digiccy.event.AuthEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/11/16 0016.
 */
public class AuthAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer authId;

    public AuthAggregate() {}

    @CommandHandler
    public AuthAggregate(AuthCommand authCommand){
        apply(new AuthEvent(authCommand.getAuthId(),authCommand.getUserId(),authCommand.getName(),authCommand.getPassword(),
                authCommand.getRoleId(),authCommand.getResId(),authCommand.getOperation()));
    }

    @EventHandler
    public void on(AuthEvent authEvent){
        authId = authEvent.getAuthId();
    }
}
