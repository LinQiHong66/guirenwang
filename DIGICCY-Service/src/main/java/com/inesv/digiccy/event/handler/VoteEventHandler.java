package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.VoteDto;
import com.inesv.digiccy.event.VoteEvent;
import com.inesv.digiccy.persistence.operation.VotePer;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class VoteEventHandler {

    @Autowired
    private VotePer votePer;

    @EventHandler
    public void handle(VoteEvent voteEvent){
        VoteDto voteDto = new VoteDto(voteEvent.getUser_no(),voteEvent.getVote_type(),voteEvent.getCoin_type(),voteEvent.getDate());
        votePer.addVoteInfo(voteDto);
    }

}
