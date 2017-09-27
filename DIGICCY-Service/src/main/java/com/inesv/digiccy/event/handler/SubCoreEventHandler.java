package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.SubCoreDto;
import com.inesv.digiccy.event.SubCoreEvent;
import com.inesv.digiccy.persistence.operation.SubCorePer;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class SubCoreEventHandler {

    @Autowired
    SubCorePer subCorePer;

    @EventHandler
    public void subRecordEvent(SubCoreEvent subCoreEvent) throws Exception {
        SubCoreDto subCoreDto = new SubCoreDto();
        subCoreDto.setId(subCoreEvent.getId());
        subCoreDto.setCoin_type(subCoreEvent.getCoin_type());
        subCoreDto.setPrice(subCoreEvent.getPrice());
        subCoreDto.setNum(subCoreEvent.getNum());
        subCoreDto.setAlready(subCoreEvent.getAlready());
        subCoreDto.setLimit_buy(subCoreEvent.getLimit_buy());
        subCoreDto.setDate(subCoreEvent.getDate());
        subCoreDto.setThaw_num(subCoreEvent.getThaw_num());
        subCoreDto.setCycle(subCoreEvent.getCycle());
        subCoreDto.setStatus(subCoreEvent.getStatus());
        subCoreDto.setSub_name(subCoreEvent.getSub_name());
        subCoreDto.setPhoto(subCoreEvent.getPhoto());
        String operation = subCoreEvent.getOperation();
        switch (operation){
            case "insert":
                subCorePer.addSubCore(subCoreDto);
                break;
            case "update":
                subCorePer.updateSubCore(subCoreDto);
                break;
            case "delete":
                subCorePer.deleteSubCore(subCoreDto);
                break;
            default:
                break;
        }
    }

}
