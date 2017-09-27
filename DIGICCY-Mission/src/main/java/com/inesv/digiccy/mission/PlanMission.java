package com.inesv.digiccy.mission;

import java.sql.SQLException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.validata.PlanValidata;

@Component
public class PlanMission implements Runnable{
	private final static Logger LOGGER = LoggerFactory.getLogger(PlanMission.class);
	
	@Autowired
	PlanValidata planValidata;
	

	@Override
	/*@Scheduled(cron="0 0/3 * * * ?") */
	public void run() {
		/*LOGGER.info("**********************委托计划START**********************"+new Date());
		// TODO Auto-generated method stub
		try {
			planValidata.doTrade();
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!委托计划设置成功!!!!!!!!!!!!!!!!!!!!!!!!!!"+new Date());
			LOGGER.info("**********************委托计划设置成功**********************"+new Date());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!委托计划设置失败!!!!!!!!!!!!!!!!!!!!!!!!!!"+new Date());
			LOGGER.info("**********************委托计划设置失败**********************"+new Date());
		}*/
	}

}
