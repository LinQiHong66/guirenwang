package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.event.EntrustEvent;
import com.inesv.digiccy.persistence.plan.PlanOperation;
import com.inesv.digiccy.persistence.reg.RegUserPersistence;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class EntrustManageHandler {
    @Autowired
    RegUserPersistence regUserPersistence;
    @Autowired
    PlanOperation planOperation;

    @EventHandler
    public void handler (EntrustEvent event) throws Exception{
        EntrustDto entrustDto = new EntrustDto(event.getId(),event.getUser_no(),event.getEntrust_coin(),event.getConvert_coin(),event.getConvert_price(),event.getEntrust_type(),
                event.getEntrust_price(),event.getEntrust_num(),event.getDeal_num(),event.getPiundatge(),event.getState(),event.getDate());
        String opration = event.getOperation();
        switch(opration){
            case "updateState":
                regUserPersistence.updateEntrustState(entrustDto);
                break;
            case "updateState2":
                regUserPersistence.updateEntrustState2(event.getId());
                break;
            case "update": //撤销委托记录
                regUserPersistence.updateEntrustState(entrustDto);//修改委托状态
                break;
            default:
                break;
        }

    }

}
