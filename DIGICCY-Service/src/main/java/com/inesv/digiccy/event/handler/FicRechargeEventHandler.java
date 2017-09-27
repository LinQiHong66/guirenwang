package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.FicRechargeDto;
import com.inesv.digiccy.event.FicRechargeEvent;
import com.inesv.digiccy.persistence.finance.FicRechargePersistence;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class FicRechargeEventHandler {

    @Autowired
    FicRechargePersistence ficRechargePersistence;

    @EventHandler
    public void handle(FicRechargeEvent event){
        FicRechargeDto ficRechargeDto = new FicRechargeDto();
        ficRechargeDto.setId(event.getId());
        ficRechargeDto.setUser_no(event.getUser_no());
        ficRechargeDto.setCoin_no(event.getCoin_no());
        ficRechargeDto.setAddress(event.getAddress());
        ficRechargeDto.setActual_price(event.getActual_price());
        ficRechargeDto.setGive_price(event.getGive_price());
        ficRechargeDto.setSum_price(event.getSum_price());
        ficRechargeDto.setState(event.getState());
        ficRechargeDto.setDate(event.getDate());
        ficRechargeDto.setTixid(event.getTixid());
        String operation = event.getOperation();
        switch(operation){
            case "insert":
                ficRechargePersistence.addFicFecharge(ficRechargeDto);
                break;
            case "updateState":
                ficRechargePersistence.updateState(event.getUser_no(),event.getCoin_no(),event.getState(),event.getTixid());
                break;
            default:
                break;
        }



    }




}
