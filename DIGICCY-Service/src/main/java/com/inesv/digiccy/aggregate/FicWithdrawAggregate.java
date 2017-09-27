package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.FicWithdrawCommand;
import com.inesv.digiccy.event.FicRechargeEvent;
import com.inesv.digiccy.event.FicWithdrawEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
public class FicWithdrawAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer id;

    public FicWithdrawAggregate() {
    }

    @CommandHandler
    public FicWithdrawAggregate(FicWithdrawCommand command) {
        apply(new FicWithdrawEvent(command.getId(),command.getUser_no(),command.getCoin_no(),command.getCoin_sum(),command.getAddress(),command.getPoundage(),
                command.getActual_price(),command.getSate(),command.getDate(),command.getOperation()));
    }

    @EventHandler
    public void on(FicWithdrawEvent ficWithdrawEvent){
        this.id = ficWithdrawEvent.getId();
    }


}
