package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.NewsCommand;
import com.inesv.digiccy.api.command.RmbRechargeCommand;
import com.inesv.digiccy.event.NewsEvnet;
import com.inesv.digiccy.event.RmbRechargeEvnet;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class NewsAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long id;

    public NewsAggregate(){
    }

    @CommandHandler
    public NewsAggregate(NewsCommand command){
        apply(new NewsEvnet(command.getId(),command.getNews_title(),command.getNews_content(), command.getNews_author(), command.getDate(),command.getType(), command.getOperating()));
    }

    @EventHandler
    public void on(NewsCommand newsCommand){
        this.id = newsCommand.getId();
    }



}
