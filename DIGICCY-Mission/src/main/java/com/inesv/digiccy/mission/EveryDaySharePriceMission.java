package com.inesv.digiccy.mission;

import com.inesv.digiccy.validata.TradeValidata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EveryDaySharePriceMission implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(EveryDaySharePriceMission.class);

    @Autowired
    private TradeValidata tradeValidata;

	@Override
	@Scheduled(cron = "0 0 0 * * ?")
	public void run() {
		tradeValidata.addDayMarket();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!每日股价更新成功!!!!!!!!!!!!!!!!!!!!" + new Date());
        LOGGER.info("**********************每日股价更新成功**********************************:" + new Date());
	}

}
