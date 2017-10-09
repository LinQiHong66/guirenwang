package com.inesv.digiccy.mission;

import com.inesv.digiccy.validata.RmbRechargeValidate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;

@Component
public class RechargeMission implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(RechargeMission.class);

    @Autowired
    private RmbRechargeValidate rmbRechargeValidate;

     /**
     * 定时处理虚拟币充值（每分钟执行一次）
     */
	@Override
	@Scheduled(cron="0 0/2 * * * ?")  //1分钟             
	public void run() {
		try {
			LOGGER.info("**********************定时器处理充值单发起成功**********************************:" + new Date());
			rmbRechargeValidate.validateRechargeInfo();
			LOGGER.info("**********************定时器处理充值单号成功**********************************:" + new Date());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("**********************定时器处理充值单号失败**********************************:" + new Date());
		}
	}
}
