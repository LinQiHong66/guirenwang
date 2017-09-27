package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.dto.NoticeTypeDto;
import com.inesv.digiccy.event.NoticeEvent;
import com.inesv.digiccy.event.NoticeTypeEvent;
import com.inesv.digiccy.persistence.notice.NoticeOperation;
import com.inesv.digiccy.persistence.notice.NoticeTypeOperation;

public class NoticeTypeEventHandler {

    @Autowired
    private NoticeTypeOperation noticeTypeOperation;

    @EventHandler
    public void handle(NoticeTypeEvent event) throws Exception {
        String operation = event.getOpreation();
        NoticeTypeDto type = new NoticeTypeDto();
        type.setId(event.getId());
        type.setName(event.getName());
        switch (operation){
            case "insert":
            	noticeTypeOperation.addNoticeType(type);
                break;
            case "update":
                noticeTypeOperation.modifyNoticeType(type);
                break;
            case "delete":
            	noticeTypeOperation.delNoticeType(event.getId());
            	break;
            default:
                break;
        }
    }

}
