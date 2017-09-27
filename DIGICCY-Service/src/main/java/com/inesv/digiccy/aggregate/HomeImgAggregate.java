package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.HomeImgCommand;
import com.inesv.digiccy.event.CoinEvent;
import com.inesv.digiccy.event.HomeImgEvent;

public class HomeImgAggregate extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
	private int id;

	private HomeImgAggregate() {

	}

	@CommandHandler
	public HomeImgAggregate(HomeImgCommand command) {
		apply(new HomeImgEvent(command.getId(), command.getImgInfo(), command.getImgUrl(), command.getOperation()));
	}

    @EventHandler
    public void on(HomeImgEvent imgEvent){
        id = imgEvent.getId();
    }
}
