package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.BonusDetailDto;
import com.inesv.digiccy.event.BonusDetaillEvent;
import com.inesv.digiccy.persistence.bonus.BonusOperation;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by JimJim on 2016/12/7 0007.
 */
public class BonusDetailEventHandler {

    @Autowired
    BonusOperation bonusOperation;

    @EventHandler
    public void handle(BonusDetaillEvent event) throws Exception {
        String operation = event.getOperation();
        BonusDetailDto bonusDetailDto = new BonusDetailDto();
        bonusDetailDto.setId(event.getBonusDetailId());
        bonusDetailDto.setBonus_name(event.getBonus_name());
        bonusDetailDto.setCoin_type(event.getCoin_type());
        bonusDetailDto.setNum(event.getNum());
        bonusDetailDto.setDate(new Date());
        bonusDetailDto.setState(1);
        switch (operation){
            case "insert":
                bonusOperation.insertBonusDetailed(bonusDetailDto);
            case "update":
                bonusOperation.updateBonusDetailed(event.getBonusDetailId(),event.getBonus_name(),event.getCoin_type(),event.getNum());
            case "delete":
                bonusOperation.deteleBonusDetailed(event.getBonusDetailId());
            case "bonus":
                bonusOperation.doBonus(event.getBonus_name(),event.getCoin_type(),event.getNum());
            default:
                break;
        }
    }

}
