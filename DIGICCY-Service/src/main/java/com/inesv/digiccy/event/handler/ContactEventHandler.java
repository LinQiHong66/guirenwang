package com.inesv.digiccy.event.handler;

import java.util.Date;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.ContactDto;
import com.inesv.digiccy.event.CoinEvent;
import com.inesv.digiccy.event.ContactEvent;
import com.inesv.digiccy.persistence.coin.CoinOperation;
import com.inesv.digiccy.persistence.other.ContactOperation;

/**
 * 
 * @author Liukeling
 *
 */
public class ContactEventHandler {
	 @Autowired
	  ContactOperation contactOperation;

	   /* @Autowired
	    WalletLinkOperation walletLinkOperation;*/

	    @EventHandler
	    public void handle(ContactEvent event) throws Exception {
	        String operation = event.getOperation();
	        ContactDto dto = new ContactDto();
	        dto.setAddress(event.getAddress());
	        dto.setEmail(event.getEmail());
	        dto.setId(event.getId());
	        dto.setQq(event.getQq());
	        dto.setWxqrcord(event.getWxqrcord());
	        dto.setQqqrcord(event.getQqqrcord());
	        dto.setRemark(event.getRemark());
	        dto.setWeixin(event.getWeixin());
	        dto.setTelphone(event.getTelphone());
	        
	        switch (operation){
	            case "insert":
	            	contactOperation.insertContact(dto);
	                break;
	            case "delete":
	            	contactOperation.delById(dto.getId());
	            	break;
	            case "modify":
	            	contactOperation.updateContact(dto);
	            	break;
	            default:
	                break;
	        }
	    }
}
