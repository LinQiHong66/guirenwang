package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.event.CoinTranTypeEvent;
import com.inesv.digiccy.api.command.CoinTranTypeCommand;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
public class CoinTranTypeAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer id;

    public CoinTranTypeAggregate() {}

    @CommandHandler
    public CoinTranTypeAggregate(CoinTranTypeCommand coinTranTypeCommand){
        apply(new CoinTranTypeEvent(coinTranTypeCommand.getId(),
        		coinTranTypeCommand.getCoin_no(),
        		coinTranTypeCommand.getTran_coin_no(),
        		coinTranTypeCommand.getState(),
        		coinTranTypeCommand.getDate(),
        		coinTranTypeCommand.getAttr1(),
        		coinTranTypeCommand.getAttr2(),
        		coinTranTypeCommand.getOperation()));
    }

    @EventHandler
    public void on(CoinTranTypeEvent coinTradeTypeEvent){
        id = coinTradeTypeEvent.getId();
    }

}
