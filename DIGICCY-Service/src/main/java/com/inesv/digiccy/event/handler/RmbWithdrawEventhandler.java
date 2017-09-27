package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.RmbWithdrawDto;
import com.inesv.digiccy.event.RmbWithdrawEvent;
import com.inesv.digiccy.persistence.finance.RmbWithdrawPersistence;

import java.sql.SQLException;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class RmbWithdrawEventhandler {

    @Autowired
    private RmbWithdrawPersistence rmbWithdrawPersistence;

    @EventHandler
    public void handle(RmbWithdrawEvent event) throws Exception{

        RmbWithdrawDto rmbWithdrawDto = new RmbWithdrawDto();
        rmbWithdrawDto.setId(event.getId());
        rmbWithdrawDto.setUser_no(event.getUser_no());
        rmbWithdrawDto.setBank(event.getBank());
        rmbWithdrawDto.setPrice(event.getPrice());
        rmbWithdrawDto.setPoundage(event.getPoundage());
        rmbWithdrawDto.setActual_price(event.getActual_price());
        rmbWithdrawDto.setDate(event.getDate());
        rmbWithdrawDto.setState(event.getState());

        String operation = event.getOperation();
        switch (operation){
            case "insert":
                rmbWithdrawPersistence.addWithdrawInfo(rmbWithdrawDto);
                break;
            case "confirm":
                rmbWithdrawPersistence.confirmToAccount(event.getId(),event.getUser_no(),event.getActual_price().toString());
                break;
            default:
                break;

        }

    }




}
