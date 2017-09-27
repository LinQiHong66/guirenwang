package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.event.NoticeEvent;
import com.inesv.digiccy.persistence.notice.NoticeOperation;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by JimJim on 2016/12/13 0013.
 */
public class NoticeEventHandler {

    @Autowired
    private NoticeOperation noticeOperation;

    @EventHandler
    public void handle(NoticeEvent event) throws Exception {
        String operation = event.getOperation();
        NoticeDto notice = new NoticeDto();
        notice.setId(event.getNoticeId());
        notice.setNotice_type(event.getNotice_type());
        notice.setNotice_bz(event.getNotice_bz());
        notice.setContext(event.getContext());
        notice.setTitle(event.getTitle());
        notice.setPerson(event.getPerson());
        notice.setDate(event.getDate());
        notice.setNotice_nametype(event.getNotice_nametype());
        switch (operation){
            case "insert":
                noticeOperation.addNotice(notice);
                break;
            case "update":
                noticeOperation.updateNotice(notice);
                break;
            case "delete":
                noticeOperation.deleteNotice(notice.getId());
                break;
            default:
                break;
        }
    }

}
