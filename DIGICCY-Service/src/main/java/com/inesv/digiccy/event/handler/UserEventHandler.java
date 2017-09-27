package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.event.UserEvent;
import com.inesv.digiccy.persistence.finance.ForgetDealPswPersistent;
import com.inesv.digiccy.persistence.user.UserOperation;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class UserEventHandler {

    @Autowired
    UserOperation userOperation;
    
    @Autowired
    ForgetDealPswPersistent forgetDealPswPersistent;


    @EventHandler
    public void handle(UserEvent event) throws Exception {
        String operation = event.getOperation();
        switch (operation){
            case "updateUserInfo":
               userOperation.updateUserInfo(event.getUsername(),event.getUser_no(),event.getReal_name(),
                       event.getMail(),event.getPhone(),event.getCertificate_num(),event.getAlipay());
                break;
            case "updateUserState":
                userOperation.updateUserState(event.getUser_no(),event.getState());
                break;
            case "updateUserPass":
                userOperation.updateUserPass(event.getUser_no(),event.getPassword(),event.getDeal_pwd());
                break;
            case "updateDealPsw":
            	forgetDealPswPersistent.updateDealPwd(event.getDeal_pwd(),event.getUsername());
            	break;
            	/*
            	 * 测试
            	 */
            case "updateUsers":
            	userOperation.updateUsers(event.getId(),event.getUsername(),event.getUser_no(),event.getPassword(),event.getReal_name(),event.getMail(),event.getPhone(),
            			event.getCertificate_num(),event.getDeal_pwd(),event.getAlipay(),event.getState(),event.getOperation());
            	break;
            	/*
            	 * 测试
            	 */
            case "addUsers":
            	userOperation.addUsers(event.getId(),event.getUsername(),event.getUser_no(),event.getPassword(),event.getReal_name(),event.getMail(),event.getPhone(),
            			event.getCertificate_num(),event.getDeal_pwd(),event.getAlipay(),event.getState(),event.getOperation());
            	break;
            	/*
            	 * 测试
            	 */
            case "deleteUsers":
            	userOperation.deleteUsers(event.getUser_no(),event.getOperation());
            	break;
            	/*
            	 * 测试
            	 */
            case "updateUsers1":
            	userOperation.updateUsers1(event.getId(),event.getUsername(),event.getUser_no(),event.getPassword(),event.getReal_name(),event.getMail(),event.getPhone(),
            			event.getCertificate_num(),event.getDeal_pwd(),event.getAlipay(),event.getState(),event.getOperation());
            	break;
            default:
                break;
        }
    }

}
