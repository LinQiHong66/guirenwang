package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.StaticParamsDto;
import com.inesv.digiccy.event.StaticParamEvent;
import com.inesv.digiccy.persistence.param.StaticParamOperation;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JimJim on 2016/12/14 0014.
 */
public class StaticParamEventHandler {

    @Autowired
    StaticParamOperation staticParamOperation;

    @EventHandler
    public void handle(StaticParamEvent event) throws Exception {
        String operation = event.getOperation();
        StaticParamsDto staticParamsDto = new StaticParamsDto();
        staticParamsDto.setId(event.getStaticParamId());
        staticParamsDto.setParam(event.getParam());
        staticParamsDto.setValue(event.getValue());
        staticParamsDto.setCode(event.getCode());
        switch (operation){
            case "insert":
                staticParamOperation.addStaticParam(staticParamsDto);
                break;
            case "update":
                staticParamOperation.updateStaticParam(staticParamsDto);
                break;
            case "delete":
                staticParamOperation.deteleStaticParam(event.getStaticParamId());
                break;
            default:
                break;
        }
    }

}
