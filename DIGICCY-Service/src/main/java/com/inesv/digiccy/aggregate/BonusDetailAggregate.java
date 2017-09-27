package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.BonusDetailCommand;
import com.inesv.digiccy.event.BonusDetaillEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/12/7 0007.
 */
public class BonusDetailAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer bonusDetailId;

    public BonusDetailAggregate() {}

    @CommandHandler
    public BonusDetailAggregate(BonusDetailCommand command){
        apply(new BonusDetaillEvent(command.getBonusDetailId(),command.getBonus_name(),command.getCoin_type(),
                command.getNum(),command.getDate(),command.getState(),command.getOperation()));
    }

    @EventHandler
    public void on(BonusDetaillEvent event){
        bonusDetailId = event.getBonusDetailId();
    }


}
