package com.inesv.digiccy.event.handler;


import com.inesv.digiccy.dto.CrowdFundingDto;
import com.inesv.digiccy.event.CrowdFundingEvent;
import com.inesv.digiccy.persistence.crowd.CrowdFundingOperation;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/06/06 0016.
 */
public class CrowdFundingEventHandler {
	
	@Autowired
	CrowdFundingOperation crowdFundingOperation;

    @EventHandler
    public void crowdFundingEvent(CrowdFundingEvent event) throws Exception {
    	CrowdFundingDto crowdFundingDto = new CrowdFundingDto();
    	crowdFundingDto.setId(event.getId());
    	crowdFundingDto.setIco_no(event.getIco_no());
    	crowdFundingDto.setIco_name(event.getIco_name());
    	crowdFundingDto.setIco_photo(event.getIco_photo());
    	crowdFundingDto.setIco_target(event.getIco_target());
    	crowdFundingDto.setIco_current(event.getIco_current());
    	crowdFundingDto.setIco_price_type(event.getIco_price_type());
    	crowdFundingDto.setIco_price(event.getIco_price());
    	crowdFundingDto.setIco_sum_price(event.getIco_sum_price());
    	crowdFundingDto.setIco_status(event.getIco_status());
    	crowdFundingDto.setIco_state(event.getIco_state());
    	crowdFundingDto.setIco_remark(event.getIco_remark());
    	crowdFundingDto.setIco_explain(event.getIco_explain());
    	crowdFundingDto.setBegin_date(event.getBegin_date());
    	crowdFundingDto.setEnd_date(event.getEnd_date());
    	crowdFundingDto.setAttr1(event.getAttr1());
    	crowdFundingDto.setAttr2(event.getAttr2());
    	crowdFundingDto.setAttr3(event.getAttr3());
    	crowdFundingDto.setAttr4(event.getAttr4());
    	crowdFundingDto.setAttr5(event.getAttr5());
    	crowdFundingDto.setAttr6(event.getAttr6());
    	String operation=event.getOperation();
    	switch (operation){
    		case "updateCrowdFundingFront":
    			crowdFundingOperation.updateCrowdFundingFront(crowdFundingDto);
    			break;
    		case "updateCrowdFundingBack":
    			crowdFundingOperation.updateCrowdFundingBack(crowdFundingDto);
    			break;
    		case "updateCrowdFundingState" :
    			crowdFundingOperation.updateCrowdFundingState();
    			break;
    		case "insertCrowdFunding" :
    			crowdFundingOperation.insertCrowdFunding(crowdFundingDto);
    			break;
    		case "deleteCrowdFunding" :
    			crowdFundingOperation.deleteCrowdFunding(crowdFundingDto);
    			break;
    		default:
                break;
    	}
    }

}
