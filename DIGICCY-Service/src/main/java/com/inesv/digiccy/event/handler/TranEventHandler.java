package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.TranDto;
import com.inesv.digiccy.event.TranEvent;
import com.inesv.digiccy.persistence.finance.TranPersistence;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class TranEventHandler {

    @Autowired
    TranPersistence tranPersistence;

    @EventHandler
    public void handle(TranEvent event){
        TranDto tranDto = new TranDto();
        tranDto.setId(event.getId());
        tranDto.setUser_no(event.getUser_no());
        tranDto.setTran_user(event.getTran_user());
        tranDto.setCoin_type(event.getCoin_type());
        tranDto.setTran_num(event.getTran_num());
        tranDto.setPoundage(event.getPoundage());
        tranDto.setState(event.getState());
        tranDto.setDate(event.getDate());
        String operation = event.getOperation();
        switch(operation){
            case "insert":
                tranPersistence.addTranInfo(tranDto);
                break;
            default:
                break;
        }
    }
}
