package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.BillDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.event.BillEvent;
import com.inesv.digiccy.persistence.bill.BillOperation;
import com.inesv.digiccy.persistence.operation.BillPer;
import com.inesv.digiccy.query.QuerySubCore;

import javax.swing.SwingConstants;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class BillEventHandler {

    @Autowired
    private BillPer billPer;

    @Autowired
    private QuerySubCore querySubCore;

    @Autowired
    private BillOperation billOperation;

    /**
     * create by huguokai date:2016年11月16日15:17:21
     * 话费充值事件处理
     * @param billEvent
     * @throws Exception
     */
    @EventHandler
    public void billEvent(BillEvent billEvent) throws Exception {
       /* BillDto billDto = new BillDto(billEvent.getUser_no(),billEvent.getRecharge_phone(),billEvent.getRecharge_price(),billEvent.getPay_type(),billEvent.getPay_price(),billEvent.getHandle_date(),billEvent.getState(),billEvent.getDate());
        billPer.billRecharge(billDto);
        UserBalanceDto userBalanceDto = querySubCore.getUserBalance(billDto.getUser_no(),0);
        billPer.editUserBalance(userBalanceDto.getEnable_coin().subtract(billDto.getRecharge_price()),billDto.getUser_no(),0);*/
    	
    	BillDto bill=new BillDto();
    	bill.setUser_no(billEvent.getUser_no());
    	bill.setRecharge_phone(billEvent.getRecharge_phone());
        switch(billEvent.getOperation())
        {
        case "insert":
        	billOperation.addBill(bill);
            break;
        }
    }

}
