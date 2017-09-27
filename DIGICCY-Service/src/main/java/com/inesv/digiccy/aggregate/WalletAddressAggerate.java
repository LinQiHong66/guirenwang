package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.WalletAddressCommand;
import com.inesv.digiccy.event.WalletAddressEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by yc on 2016/12/19 0019.
 */
public class WalletAddressAggerate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long id;

    public WalletAddressAggerate(){}

    @CommandHandler
    public WalletAddressAggerate(WalletAddressCommand command){
        apply(new WalletAddressEvent(command.getId(),command.getUser_no(),command.getCoin_no(),command.getIdtf(),
                command.getAddress(),command.getDate(),command.getOperation()));
    }

    @EventHandler
    public void on(WalletAddressEvent event){
        id = event.getId();
    }



}
