package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.MyRecDto;
import com.inesv.digiccy.event.MyRecEvent;
import com.inesv.digiccy.persistence.finance.MyrecPersistence;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yc on 2016/12/21 0021.
 */
public class MyRecEventHandler {

    @Autowired
    MyrecPersistence myrecPersistence;


    @EventHandler
    public void handle(MyRecEvent event){
        String operation = event.getOperation();
        System.out.println("++++++++++++++"+operation);
        switch (operation){
            case "inserRecUser":
                myrecPersistence.updateRec(event.getUser_no(),event.getRec_user(),event.getRec_type(),event.getAuth(),event.getDate());
                break;
            default:
                break;
        }
    }




}
