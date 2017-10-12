package com.inesv.digiccy.mission;

import com.inesv.digiccy.validata.FicRechargeValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;

@Component
public class RechargeCoinMission implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(RechargeCoinMission.class);

    @Autowired
    private FicRechargeValidate ficRechargeValidate;

     /**
     * 定时处理虚拟币充值（每分钟执行一次）
     */
	@Override
	/*@Scheduled(cron="0 0/1 * * * ?")*/
	/*@Scheduled(cron="0 0/3 * * * ?")*/
	public void run() {
		 try {
			ficRechargeValidate.validateRechargeCoinNew();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        LOGGER.info("**********************定时器处理虚拟币充值**********************************:" + new Date()); 
	}
}
