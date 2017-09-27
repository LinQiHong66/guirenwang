package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.WalletAddressDto;
import com.inesv.digiccy.event.WalletAddressEvent;
import com.inesv.digiccy.persistence.finance.WalletAddressPersistence;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public class WalletAddressEventHandler {

    @Autowired
    WalletAddressPersistence walletAddressPersistence;

    @EventHandler
    public void handle(WalletAddressEvent event){
        WalletAddressDto dto = new WalletAddressDto();
        dto.setUser_no(event.getUser_no());
        dto.setCoin_no(event.getCoin_no());
        dto.setIdtf(event.getIdtf());
        dto.setAddress(event.getAddress());
        dto.setDate(event.getDate());
        String operation = event.getOperation();
        switch(operation){
            case "insertAddress":
                walletAddressPersistence.addWalletAddress(event.getUser_no(),event.getCoin_no(),event.getIdtf(),event.getAddress(),event.getDate());
                break;
            case "deleteAddress":
                walletAddressPersistence.deleteWalletAddress(event.getId());
                break;
            default:
                break;
        }
    }

}
