package com.inesv.digiccy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by SKINK on 18/11/2016.
 */
@Component
public class OrderIDGenerate {
    private static Logger logger = LoggerFactory.getLogger(OrderIDGenerate.class);
    private static Random random = new Random();

    public static String getOrderId(){
        String orderId = DateTime.now().toDateTimeString(DateTime.DEFAULT_ORDER_DATE_TIME_FORMAT_PATTERN) + getRandomCode();
        logger.info("订单号生成器生成订单号:[{}]",orderId);
        return orderId;
    }

    private static String getRandomCode(){
        String randomCode = "";
        for(int i = 0; i < 4; i++){
            randomCode = randomCode + random.nextInt(10);
        }
        return randomCode;
    }
}
