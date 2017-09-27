package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.PoundageDto;
import com.inesv.digiccy.event.PoundageEvent;
import com.inesv.digiccy.persistence.finance.PoundagePersistence;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/1/5 0005.
 */
public class PoundageEventHandler {

    @Autowired
    PoundagePersistence poundagePersistence;

    @EventHandler
    public void handle(PoundageEvent event){
        PoundageDto dto = new PoundageDto();
        dto.setId(event.getId());
        dto.setUser_no(event.getUser_no());
        dto.setOptype(event.getOptype());
        dto.setType(event.getType());
        dto.setMoney(event.getMoney());
        dto.setDate(event.getDate());
        String operation = event.getOperation();
        switch (operation){
            case "insert":
                poundagePersistence.insertPoundageInfo(dto);
                break;
            default:
                break;
        }


    }




}
