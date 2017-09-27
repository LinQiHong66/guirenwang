package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.AddressCommand;
import com.inesv.digiccy.event.AddressEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
public class AddressAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Long addressId;

    public AddressAggregate() {}

    @CommandHandler
    public AddressAggregate(AddressCommand command){
        apply(new AddressEvent(command.getId(),command.getUser_no(),command.getRemark_address(),command.getName(),command.getCard(),
                command.getPhone(),command.getAddress(),command.getDate(),command.getAttr1(),command.getAttr2(),command.getOperation()));
    }

    @EventHandler
    public void on(AddressEvent event){
        addressId = event.getId();
    }

}
