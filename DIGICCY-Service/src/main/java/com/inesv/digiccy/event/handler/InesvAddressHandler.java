package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.event.InesvAddressEvent;
import com.inesv.digiccy.persistence.inesvaddress.InesvAddressOper;

public class InesvAddressHandler {
	
	@Autowired
	InesvAddressOper inesvAddressOper;
	
	@EventHandler
	public void handler(InesvAddressEvent inesvAddressEvent){
		switch (inesvAddressEvent.getOperation()) {
		case "updateLevel":
			inesvAddressOper.updateLlevel(inesvAddressEvent.getAddress_level(), inesvAddressEvent.getId());
			break;

		default:
			break;
		}
	}

}
