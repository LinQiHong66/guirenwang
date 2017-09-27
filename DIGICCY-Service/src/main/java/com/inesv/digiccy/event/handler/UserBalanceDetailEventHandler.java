package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.UserBalanceDetailDto;
import com.inesv.digiccy.event.UserBalanceDetailEvent;
import com.inesv.digiccy.persistence.finance.UserBalanceDetailPersistence;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/06/06 0016.
 */
public class UserBalanceDetailEventHandler {
	
	@Autowired
	UserBalanceDetailPersistence userBalanceDetailOperation;

    @EventHandler
    public void userBalanceDetailEvent(UserBalanceDetailEvent event) throws Exception {
    	String operation = event.getOperation();
    	UserBalanceDetailDto userBalanceDetailDto = new UserBalanceDetailDto();
    	userBalanceDetailDto.setId(event.getId());
    	userBalanceDetailDto.setUser_no(event.getUser_no());
    	userBalanceDetailDto.setAdmin_no(event.getAdmin_no());
    	userBalanceDetailDto.setCoin_type(event.getCoin_type());
    	userBalanceDetailDto.setCoin_price(event.getCoin_price());
    	userBalanceDetailDto.setRemark(event.getRemark());
    	userBalanceDetailDto.setDate(event.getDate());
    	switch (operation){
    		case "insertDetail" :
    			userBalanceDetailOperation.insertDetail(userBalanceDetailDto);
    			break;
    		default:
                break;
    	}
    }

}
