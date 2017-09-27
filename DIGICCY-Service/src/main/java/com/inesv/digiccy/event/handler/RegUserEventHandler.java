package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.event.RegUserEvent;
import com.inesv.digiccy.persistence.reg.RegUserPersistence;
import com.inesv.digiccy.persistence.userrelations.UserRelationsOper;
import com.inesv.digiccy.query.QueryUserInfo;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class RegUserEventHandler {
    @Autowired
    RegUserPersistence regUserPersistence;
    
//    @Autowired
//    QueryUserInfo queryUser;
    
    @Autowired
    UserRelationsOper relationsOpern;

    @EventHandler
    public void handle(RegUserEvent event) throws Exception {
        InesvUserDto inesvUserDto = new InesvUserDto();
        inesvUserDto.setId(event.getId());
        inesvUserDto.setUsername(event.getUsername());
        inesvUserDto.setUser_no(event.getUser_no());
        inesvUserDto.setPassword(event.getPassword());
        inesvUserDto.setRegion(event.getRegion());
        inesvUserDto.setReal_name(event.getReal_name());
        inesvUserDto.setCertificate_num(event.getCertificate_num());
        inesvUserDto.setDeal_pwd(event.getDeal_pwd());
        inesvUserDto.setMail(event.getMail());
        inesvUserDto.setPhone(event.getPhone());
        inesvUserDto.setState(event.getState());
        inesvUserDto.setInvite_num(event.getInvite_num());
        inesvUserDto.setDate(event.getDate());
        inesvUserDto.setOrg_code(event.getOrg_code());
        inesvUserDto.setOrg_type(event.getOrg_type()==null?0:event.getOrg_type());
        inesvUserDto.setOrg_parent_code(event.getOrg_parent_code());
        String operation = event.getOperation();
        switch (operation){
            /*case "insert":
                regUserPersistence.addUser(inesvUserDto);
                InesvUserDto inesvUserDto2 = new InesvUserDto(); 
                *//**
                 * 有邀请码自动生成联系用户
                 * *//*
                if(event.getInvite_num() != null && "".equals(event.getInvite_num())){
	                inesvUserDto2 = queryUser.queryUserByInviteNum(event.getInvite_num());
	                relationsOpern.insert(Long.valueOf(event.getUser_no()), Long.valueOf(inesvUserDto2.getUser_no()));
                }
                break;*/
            case "update":
                regUserPersistence.forgetPwd(inesvUserDto);
                break;
            case "updateId":
            	regUserPersistence.updateId(inesvUserDto.getPhone());
            	break;
            case "updateParentCode":
            	regUserPersistence.updateParentCode(inesvUserDto);
            	break;
            default:
                break;
        }
    }

}
