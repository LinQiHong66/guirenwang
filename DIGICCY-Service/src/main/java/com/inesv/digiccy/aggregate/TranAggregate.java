package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.TranCommand;
import com.inesv.digiccy.event.TranEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class TranAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long id;

    public TranAggregate(){

    }

    @CommandHandler
    public TranAggregate(TranCommand command){
        apply(new TranEvent(command.getId(),command.getUser_no(),command.getTran_user(),command.getCoin_type(),command.getTran_num(),command.getPoundage(),
                command.getState(),command.getDate(),command.getOperation()));
    }

    @EventHandler
    public void on(TranEvent tranEvent){
        this.id = tranEvent.getId();
    }



}
