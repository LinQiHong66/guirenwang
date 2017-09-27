package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.auth.AuthRoleDto;
import com.inesv.digiccy.event.RoleEvent;
import com.inesv.digiccy.persistence.auth.RoleOperation;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class RoleEventHandler {

    @Autowired
    RoleOperation roleOperation;

    @EventHandler
    public void handle(RoleEvent event) throws Exception {
        AuthRoleDto role = new AuthRoleDto();
        role.setId(event.getRoleId());
        role.setName(event.getName());
        role.setDescription(event.getDescription());
        String operation = event.getOperation();
        switch (operation){
            case "insert":
                roleOperation.addRole(role);
                break;
            case "update":
                roleOperation.updateRole(role);
                break;
            case "delete":
                roleOperation.deleteRole(role);
                break;
            default:
                break;
        }
    }

}
