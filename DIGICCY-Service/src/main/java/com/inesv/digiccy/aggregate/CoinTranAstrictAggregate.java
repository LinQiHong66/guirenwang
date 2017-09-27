package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.CoinCommand;
import com.inesv.digiccy.api.command.CoinTranAstrictCommand;
import com.inesv.digiccy.event.CoinEvent;
import com.inesv.digiccy.event.CoinTranAstrictEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
public class CoinTranAstrictAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Long id;

    public CoinTranAstrictAggregate() {}

    @CommandHandler
    public CoinTranAstrictAggregate(CoinTranAstrictCommand coinTranAstrictCommand){
        apply(new CoinTranAstrictEvent(coinTranAstrictCommand.getId(),
        		coinTranAstrictCommand.getCoin_no(),
        		coinTranAstrictCommand.getBuy_min_price(),
        		coinTranAstrictCommand.getBuy_max_price(),
        		coinTranAstrictCommand.getSell_min_price(),
        		coinTranAstrictCommand.getSell_max_price(),
        		coinTranAstrictCommand.getSingle_min_price(),
        		coinTranAstrictCommand.getSingle_max_price(),
        		coinTranAstrictCommand.getRose_astrict(),
        		coinTranAstrictCommand.getDrop_astrict(),
        		coinTranAstrictCommand.getBegin_date(),
        		coinTranAstrictCommand.getEnd_date(),
        		coinTranAstrictCommand.getState(),
        		coinTranAstrictCommand.getOperation()));
    }

    @EventHandler
    public void on(CoinTranAstrictEvent coinTranAstrictEvent){
        id = coinTranAstrictEvent.getId();
    }

}
