package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.UserBalanceCommand;
import com.inesv.digiccy.event.UserBalanceEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
public class UserBalanceAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long id1;

    public UserBalanceAggregate(){

    }

    @CommandHandler
    public UserBalanceAggregate(UserBalanceCommand command){
        apply(new UserBalanceEvent(command.getId(),command.getUser_no(),command.getCoin_type(),command.getEnable_coin(),command.getUnable_coin(),command.getTotal_price(),command.getWallet_address(),command.getDate(),command.getOperation()));
    }

    /*@CommandHandler
    public UserBalanceAggregate(UserBalanceCommand Command){
        apply(new UserBalanceEvent(Command.getId(),Command.getUser_no(),Command.getCoin_type(),Command.getWallet_address(),Command.getOperation()));
    }*/


    @EventHandler
    public void on(UserBalanceEvent userBalanceEvent){
        this.id1 = userBalanceEvent.getId();
    }




}
