package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.event.AddressEvent;
import com.inesv.digiccy.persistence.address.AddressOperation;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
public class AddressEventHandler {

    @Autowired
    AddressOperation addressOperation;

    @EventHandler
    public void handler(AddressEvent event) throws Exception {
        AddressDto address = new AddressDto();
        address.setId(event.getId());
        address.setAddress(event.getAddress());
        address.setName(event.getName());
        address.setCard(event.getCard());
        address.setDate(event.getDate());
        address.setPhone(event.getPhone());
        address.setRemark_address(event.getRemark_address());
        address.setUser_no(event.getUser_no());
        String operation = event.getOperation();
        switch (operation){
            case "insert":
                addressOperation.insertAddress(address);
                break;
            case "update":
                addressOperation.updateAddress(address);
                break;
            case "updateByUser":
                addressOperation.updateAddressByUser(address);
                break;
            case "delete":
                addressOperation.deleteAddress(address);
                break;
            default:
                break;
        }
    }

}
