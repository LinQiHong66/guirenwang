package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.event.UserBalanceEvent;
import com.inesv.digiccy.persistence.finance.UserBalancePersistence;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
public class UserBalanceEventHandler {

    @Autowired
    UserBalancePersistence userBalancePersistence;

    @EventHandler
    public void handler(UserBalanceEvent event) throws Exception{
        UserBalanceDto userBalanceDto = new UserBalanceDto();
        userBalanceDto.setId(event.getId());
        userBalanceDto.setUser_no(event.getUser_no());
        userBalanceDto.setCoin_type(event.getCoin_type());
        userBalanceDto.setEnable_coin(event.getEnable_coin());
        userBalanceDto.setUnable_coin(event.getUnable_coin());
        userBalanceDto.setTotal_price(event.getTotal_price());
        userBalanceDto.setWallet_address(event.getWallet_address());
        String operation = event.getOperation();

        switch(operation){
            case "insert":
            	userBalancePersistence.addUserBalance(userBalanceDto);
                break;
            case  "update":
                userBalancePersistence.updateWallteAddress(userBalanceDto);
                break;
            case "updateEnble":
                userBalancePersistence.updateUserenableCoin(userBalanceDto);
                break;
           case "updateEnblexn":
                userBalancePersistence.updateUserenableCoinxn(userBalanceDto);
                break;     
            case "updateTranEnble":
                userBalancePersistence.updateTranUserenableCoin(userBalanceDto);
                break;
            case "updateTranEnbles":
                userBalancePersistence.updateTranUserenableCoins(userBalanceDto);
                break;
            case "delete":
                break;
            case "insertTranCoinType":
                userBalancePersistence.insertTranCoinType(userBalanceDto);
                break;
            case "updateUnable":
                userBalancePersistence.updateUserUnableCoin(userBalanceDto);
                break;
            case "updateEnbleByfirwith":
                userBalancePersistence.updateEnbleByfirwith(userBalanceDto);
                break;    
                
            default:
                break;
        }
    }





}
