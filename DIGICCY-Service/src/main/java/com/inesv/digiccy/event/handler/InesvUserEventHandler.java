package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.event.CreateInesvUserEvent;
import com.inesv.digiccy.persistence.finance.ForgetDealPswPersistent;
import com.inesv.digiccy.persistence.user.AuthenticationUser;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class InesvUserEventHandler {

    @Autowired
    AuthenticationUser authenticationUser;
    
    @Autowired
    ForgetDealPswPersistent forgetDealPswPersistent;

    @EventHandler
    public void createInesvUser(CreateInesvUserEvent createInesvUserEvent) {
        InesvUserDto inesvUserDto = new InesvUserDto();
        inesvUserDto.setUser_no(createInesvUserEvent.getUser_no());
        inesvUserDto.setPhoto(createInesvUserEvent.getPhoto());
        inesvUserDto.setPhoto_state(1);
        inesvUserDto.setPassword(createInesvUserEvent.getPassword());
        inesvUserDto.setDeal_pwd(createInesvUserEvent.getDeal_pwd());
        inesvUserDto.setPhone(createInesvUserEvent.getPhone());
        inesvUserDto.setPhone_state(1);
        inesvUserDto.setAlipay(createInesvUserEvent.getAlipay());
        inesvUserDto.setAlipay_state(1);
        inesvUserDto.setDeal_pwdstate(createInesvUserEvent.getDeal_pwdstate());
        inesvUserDto.setValidate_pwd(createInesvUserEvent.getValidate_pwd());
        inesvUserDto.setValidate_pwdstate(1);
        String opers = createInesvUserEvent.getOperation();
        switch (opers){
            case "update":
                authenticationUser.insert(inesvUserDto);
                break;
            case "uppassword":
                authenticationUser.updateUser(inesvUserDto);
                break;
            case  "upDealPwd":
                authenticationUser.updatePwd(inesvUserDto);
                break;
            case "upPhone":
                authenticationUser.updatePhone(inesvUserDto);
                break;
            case "upAlipay":
                authenticationUser.updateAlipay(inesvUserDto);
                break;
            case "upPwdState":
                authenticationUser.upDealPwdState(inesvUserDto);
                break;
            case "upValidatePwd":
                inesvUserDto.setValidate_pwdstate(createInesvUserEvent.getValidate_pwdstate());
                authenticationUser.upValidate_pwd(inesvUserDto);
                break;
            
            default:
                break;
        }

    }
}