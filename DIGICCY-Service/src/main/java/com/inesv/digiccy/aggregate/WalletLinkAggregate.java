package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.WalletLinkCommand;
import com.inesv.digiccy.event.WalletLinkEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/12/20 0020.
 */
public class WalletLinkAggregate extends AbstractAnnotatedAggregateRoot {
    @AggregateIdentifier
    private int id;

    public WalletLinkAggregate() {
    }

    @CommandHandler
    public WalletLinkAggregate(WalletLinkCommand walletLinkCommand){
        apply(new WalletLinkEvent(walletLinkCommand.getId(),walletLinkCommand.getCoin_no(),walletLinkCommand.getHost(),
                walletLinkCommand.getPost(),walletLinkCommand.getWallet_name(),walletLinkCommand.getWallet_pwd(),
                walletLinkCommand.getWallet_lockpwd(),walletLinkCommand.getOperation()));
    }

    @EventHandler
    public void on(WalletLinkEvent event){id=event.getId();}

}
