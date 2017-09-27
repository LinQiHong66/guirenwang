package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.UserCommand;
import com.inesv.digiccy.event.UserEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/12/9 0009.
 */
public class FrontUserAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private int user_no;

    public FrontUserAggregate(){}

    @CommandHandler
    public FrontUserAggregate(UserCommand userCommand){
        apply(new UserEvent(userCommand.getId(),userCommand.getUsername(),userCommand.getUser_no(),userCommand.getPassword(),userCommand.getReal_name(),
                userCommand.getMail(),userCommand.getPhone(),userCommand.getCertificate_num(),userCommand.getDeal_pwd(),userCommand.getAlipay(),
                userCommand.getState(),userCommand.getOperation()));
    }

    @EventHandler
    public void on(UserEvent userEvent){
        user_no = userEvent.getUser_no();
    }


}
