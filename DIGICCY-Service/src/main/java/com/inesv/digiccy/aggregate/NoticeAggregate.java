package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.NoticeCommand;
import com.inesv.digiccy.event.NoticeEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/12/13 0013.
 */
public class NoticeAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Long noticeId;

    public NoticeAggregate() {}

    @CommandHandler
    public NoticeAggregate(NoticeCommand command){
        apply(new NoticeEvent(command.getNoticeId(),command.getNotice_type(),command.getNotice_bz(),command.getPerson(),
                command.getDate(),command.getTitle(),command.getContext(),command.getNotice_nametype(),command.getOperation()));
    }

    @EventHandler
    public void on(NoticeEvent event){
        noticeId = event.getNoticeId();
    }


}
