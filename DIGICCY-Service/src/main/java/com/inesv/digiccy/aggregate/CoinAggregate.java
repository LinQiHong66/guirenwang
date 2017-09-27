package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.CoinCommand;
import com.inesv.digiccy.event.CoinEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
public class CoinAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private Long coinId;

	public CoinAggregate() {
	}

	@CommandHandler
	public CoinAggregate(CoinCommand coinCommand) {
		apply(new CoinEvent(coinCommand.getCoinId(), coinCommand.getCoinNo(), coinCommand.getCoinName(),
				coinCommand.getCoinCore(), coinCommand.getVote(), coinCommand.getState(), coinCommand.getAddress(),
				coinCommand.getIcon(), coinCommand.getDate(), coinCommand.getAttr1(), coinCommand.getAttr2(),
				coinCommand.getOperation(), coinCommand.getBuy_poundatge(), coinCommand.getSell_poundatge(),
				coinCommand.getBlock(), coinCommand.getSell_withdraw_poundatge_one(),
				coinCommand.getSell_withdraw_poundatge_twe(), coinCommand.getSell_withdraw_poundatge_three()));
	}

	@EventHandler
	public void on(CoinEvent coinEvent) {
		coinId = coinEvent.getCoinId();
	}

}
