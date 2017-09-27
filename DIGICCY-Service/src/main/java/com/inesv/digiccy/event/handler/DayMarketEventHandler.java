package com.inesv.digiccy.event.handler;

import java.sql.SQLException;

import com.inesv.digiccy.dto.InesvDayMarket;
import com.inesv.digiccy.event.DayMarketEvent;
import com.inesv.digiccy.persistence.trade.DayMarketPersistence;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class DayMarketEventHandler {

    @Autowired
    DayMarketPersistence dayMarketPersistence;
    
	@EventHandler
	public void handle(DayMarketEvent event) throws SQLException{
		InesvDayMarket dayMarket = new InesvDayMarket(event.getId(),event.getCoin_type(),event.getNewes_deal(),event.getBuy_price(),event.getSell_price(),event.getDeal_num(),event.getDeal_price(),event.getDay_percent(),event.getMax_price(),event.getMin_price(),event.getState(),event.getDate(),"","");
	    String operation = event.getOperation();
		switch (operation) {
		case "insert":
			dayMarketPersistence.addDayMarket(dayMarket);
			break;
		default:
			break;
		}
	}
}
