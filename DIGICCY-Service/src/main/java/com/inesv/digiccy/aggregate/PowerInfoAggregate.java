package com.inesv.digiccy.aggregate;

import java.util.ArrayList;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.AddressCommand;
import com.inesv.digiccy.api.command.PowerInfoCommand;
import com.inesv.digiccy.api.command.ProwerParamCommand;
import com.inesv.digiccy.event.AddressEvent;
import com.inesv.digiccy.event.PowerInfoEvent;
import com.inesv.digiccy.event.ProwerParamEvent;

public class PowerInfoAggregate extends AbstractAnnotatedAggregateRoot {

	@AggregateIdentifier
	private int id;

	public PowerInfoAggregate() {
	}

	@CommandHandler
	public PowerInfoAggregate(PowerInfoCommand command) {
		PowerInfoEvent event = new PowerInfoEvent();
		ArrayList<ProwerParamCommand> commParams = command.getParams();
		ArrayList<ProwerParamEvent> params = new ArrayList<>();
		if (commParams != null) {
			for (ProwerParamCommand paramc : commParams) {
				ProwerParamEvent parame = new ProwerParamEvent();
				parame.setId(paramc.getId());
				parame.setParamInfo(paramc.getParamInfo());
				parame.setParamName(paramc.getParamName());
				parame.setParamValue(paramc.getParamValue());
				parame.setPowerId(paramc.getPowerId());
				params.add(parame);
			}
		}
		event.setParams(params);
		event.setId(command.getId());
		event.setInfo(command.getInfo());
		event.setTime(command.getTime());
		event.setUrl(command.getUrl());
		event.setUserName(command.getUserName());
		event.setOpration(command.getOpration());
		apply(event);
	}

	@EventHandler
	public void on(PowerInfoEvent event) {
		id = event.getId();
	}

}
