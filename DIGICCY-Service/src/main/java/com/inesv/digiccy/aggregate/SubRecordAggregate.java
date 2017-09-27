package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.SubRecordCommand;
import com.inesv.digiccy.api.command.ThawCommand;
import com.inesv.digiccy.event.SubRecordEvent;
import com.inesv.digiccy.event.ThawEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by huguokai on 2016/11/9 0009.
 * 认购中心聚合根
 */
public class SubRecordAggregate extends AbstractAnnotatedAggregateRoot{

    @AggregateIdentifier
    private long id;

    public SubRecordAggregate(){}

    @CommandHandler
    public SubRecordAggregate(SubRecordCommand subRecordCommand){
        apply(new SubRecordEvent(subRecordCommand.getUser_no(),subRecordCommand.getCoin_no(),subRecordCommand.getSub_name(),subRecordCommand.getSub_price(),subRecordCommand.getSub_num(),subRecordCommand.getSum_price(),subRecordCommand.getThaw_num(),subRecordCommand.getThaw_time(),subRecordCommand.getSur_frozen(),subRecordCommand.getState(),subRecordCommand.getDate(),subRecordCommand.getThaw_sum()));
    }

    @CommandHandler
    public SubRecordAggregate(ThawCommand thawCommand){
        apply(new ThawEvent(thawCommand.getId(),thawCommand.getUser_no(),thawCommand.getCoin_no(),thawCommand.getSub_name(),thawCommand.getSub_price(),thawCommand.getSub_num(),thawCommand.getSum_price(),thawCommand.getThaw_num(),thawCommand.getThaw_time(),thawCommand.getSur_frozen(),thawCommand.getState(),thawCommand.getDate(),thawCommand.getThaw_sum()));
    }

    @EventHandler
    public void on(SubRecordEvent subRecordEvent){
        id = subRecordEvent.getId();
    }

    @EventHandler
    public void on(ThawEvent thawEvent){
        id = thawEvent.getId();
    }


}
