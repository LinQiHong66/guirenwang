package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.MyrecCommand;
import com.inesv.digiccy.event.MyRecEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by yc on 2016/12/26 0026.
 */
public class MyRecAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Long id;

    public MyRecAggregate(){}

    @CommandHandler
    public MyRecAggregate(MyrecCommand command){
        apply(new MyRecEvent(command.getId(),command.getUser_no(),command.getRec_user(),command.getRec_type(),
                command.getAuth(),command.getDate(),command.getOperation()));
    }

    @EventHandler
    public void on(MyRecEvent event){
        this.id = event.getId();
    }

}
