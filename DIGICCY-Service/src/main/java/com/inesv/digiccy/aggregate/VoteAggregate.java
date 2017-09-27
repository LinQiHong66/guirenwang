package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.VoteCommand;
import com.inesv.digiccy.event.VoteEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class VoteAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private long id;

    public VoteAggregate(){};

    @CommandHandler
    public VoteAggregate(VoteCommand voteCommand){
        apply(new VoteEvent(voteCommand.getUser_no(),voteCommand.getVote_type(),voteCommand.getCoin_type(),voteCommand.getDate()));
    }

    @EventHandler
    public void on(VoteEvent voteEvent){ id = voteEvent.getId(); }

}
