package com.inesv.digiccy.event.handler;


import com.inesv.digiccy.dto.CrowdFundingDetailsDto;
import com.inesv.digiccy.event.CrowdFundingDetailsEvent;
import com.inesv.digiccy.persistence.crowd.CrowdFundingDetailsOperation;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class CrowdFundingDetailsEventHandler {

	@Autowired
	CrowdFundingDetailsOperation crowdFundingDetailsOperation;


    /**
     * 增加众筹信息事件处理
     */
    @EventHandler
    public void crowdFundingDetailsEvent(CrowdFundingDetailsEvent event) throws Exception {
    	CrowdFundingDetailsDto crowdFundingDetailsDto = new CrowdFundingDetailsDto();
    	crowdFundingDetailsDto.setId(event.getId());
    	crowdFundingDetailsDto.setIco_id(event.getIco_id());
    	crowdFundingDetailsDto.setUser_id(event.getUser_id());
    	crowdFundingDetailsDto.setIco_user_number(event.getIco_user_number());
    	crowdFundingDetailsDto.setIco_user_sumprice(event.getIco_user_sumprice());
    	crowdFundingDetailsDto.setDate(event.getDate());
    	String operation=event.getOperation();
    	switch (operation){
    		case "insertDetails":
    			crowdFundingDetailsOperation.insertDetails(crowdFundingDetailsDto);
    			break;
    		default:
                break;
    	}
    }

}
