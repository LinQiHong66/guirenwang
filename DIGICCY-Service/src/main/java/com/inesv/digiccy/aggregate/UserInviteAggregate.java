package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.UserInvitrCommand;
import com.inesv.digiccy.event.UserInviteEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by yc on 2016/12/20 0020.
 */
public class UserInviteAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Long id;

    public UserInviteAggregate(){}

    @CommandHandler
    public UserInviteAggregate(UserInvitrCommand command){
        apply(new UserInviteEvent(command.getId(),command.getUser_no(),command.getInvite_num(),command.getDate(),command.getOperation()));
    }

    @EventHandler
    public void on(UserInviteEvent event){
        this.id = event.getId();
    }
 
}
