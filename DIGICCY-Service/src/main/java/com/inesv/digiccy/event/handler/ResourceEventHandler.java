package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.auth.ResourceDto;
import com.inesv.digiccy.event.ResourceEvent;
import com.inesv.digiccy.persistence.auth.ResourceOperation;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by JimJim on 2016/11/11 0011.
 */
public class ResourceEventHandler {

    @Autowired
    ResourceOperation resourceOperation;

    @EventHandler
    public void handle(ResourceEvent event) throws Exception {
        ResourceDto resource = new ResourceDto();
        resource.setId(event.getResourceId());
        resource.setValue(event.getValue());
        resource.setType(event.getType());
        resource.setParent(event.getParent());
        resource.setCommon(event.getCommon());
        String operation = event.getOperation();
        switch (operation){
            case "insert":
                resourceOperation.addResource(resource);
                break;
            case "update":
                resourceOperation.updateResource(resource);
                break;
            case "delete":
                resourceOperation.deleteResource(resource);
                break;
            default:
                break;
        }
    }

}
