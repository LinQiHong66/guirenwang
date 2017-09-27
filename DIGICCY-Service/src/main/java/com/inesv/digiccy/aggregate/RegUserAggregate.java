package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.RegUserCommand;
import com.inesv.digiccy.event.RegUserEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;


/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class RegUserAggregate extends AbstractAnnotatedAggregateRoot {

	private static final long serialVersionUID = -414961613569229401L;

	@AggregateIdentifier
    private Integer id;

    public RegUserAggregate(){}


    @CommandHandler
    public RegUserAggregate(RegUserCommand regUserCommand){
        apply(new RegUserEvent(regUserCommand.getId(),regUserCommand.getUsername(),regUserCommand.getUser_no(),
                regUserCommand.getPassword(),regUserCommand.getRegion(),regUserCommand.getReal_name(),
                regUserCommand.getCertificate_num(),regUserCommand.getDeal_pwd(),
                regUserCommand.getMail(),regUserCommand.getPhone(),regUserCommand.getState(),regUserCommand.getInvite_num(),
                regUserCommand.getDate(),regUserCommand.getOperation(),regUserCommand.getOrg_type(),regUserCommand.getOrg_code(),regUserCommand.getOrg_parent_code()));
    }

    @EventHandler
    public void on(RegUserEvent regUserEvent){
        id = regUserEvent.getId();
    }

}
