package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.event.FicWithdrawEvent;
import com.inesv.digiccy.persistence.finance.FicWithdrawPersistence;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
public class FicWithdrawEventHandler {

    @Autowired
    FicWithdrawPersistence ficWithdrawPersistence;

    @EventHandler
    public void handle(FicWithdrawEvent event){
        FicWithdrawDto ficWithdrawDto = new FicWithdrawDto();
        ficWithdrawDto.setId(event.getId());
        ficWithdrawDto.setUser_no(event.getUser_no());
        ficWithdrawDto.setCoin_no(event.getCoin_no());
        ficWithdrawDto.setCoin_sum(event.getCoin_sum());
        ficWithdrawDto.setAddress(event.getAddress());
        ficWithdrawDto.setPoundage(event.getPoundage());
        ficWithdrawDto.setActual_price(event.getActual_price());
        ficWithdrawDto.setSate(event.getSate());
        ficWithdrawDto.setDate(event.getDate());
        String operation = event.getOperation();
        switch(operation){
            case "insert":
                ficWithdrawPersistence.insertWithdrawInfo(ficWithdrawDto);
                break;
            case "update":
                break;
            case "delete":
                break;
            case "updateState":
            	ficWithdrawPersistence.updateStateWithdrawInfo(ficWithdrawDto);
                break;
            default:
                break;
        }
    }








}
