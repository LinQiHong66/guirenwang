package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.event.AuthEvent;
import com.inesv.digiccy.persistence.auth.AuthUserOperation;
import com.inesv.digiccy.persistence.auth.ResourceOperation;
import com.inesv.digiccy.persistence.auth.RoleOperation;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Created by JimJim on 2016/11/16 0016.
 */
public class AuthEventHandler {

    @Autowired
    RoleOperation roleOperation;

    @Autowired
    ResourceOperation resourceOperation;

    @Autowired
    AuthUserOperation authUserOperation;

    @EventHandler
    public void handle(AuthEvent event) throws Exception {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        String passMd5 = md5.encodePassword(event.getPassword(),event.getName());
        String operation = event.getOperation();
        switch (operation){
            case "roleRes":
                resourceOperation.updateRoleResource(event.getRoleId(),event.getResId());
                break;
            case "addUser":
                authUserOperation.addAuthUser(event.getName(),passMd5,event.getRoleId());
                break;
            case "updateUser":
                authUserOperation.updateAuthUser(event.getUserId(),event.getName(),event.getPassword(),event.getRoleId());
                break;
            case "deleteUser":
                authUserOperation.deleteAuthUser(event.getUserId());
                break;
            default:
                break;
        }
    }

}
