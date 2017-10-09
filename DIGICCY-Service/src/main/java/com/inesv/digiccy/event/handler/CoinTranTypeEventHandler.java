package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.CoinTranTypeDto;
import com.inesv.digiccy.event.CoinTranTypeEvent;
import com.inesv.digiccy.persistence.coin.CoinTranTypeOperation;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
public class CoinTranTypeEventHandler {

    @Autowired
    CoinTranTypeOperation coinTranTypeOperation;

    @EventHandler
    public void handle(CoinTranTypeEvent event) throws Exception {
        String operation = event.getOperation();
        CoinTranTypeDto coinTradeTypeDto = new CoinTranTypeDto();
        coinTradeTypeDto.setId(event.getId());
        coinTradeTypeDto.setCoin_no(event.getCoin_no());
        coinTradeTypeDto.setTran_coin_no(event.getTran_coin_no());
        coinTradeTypeDto.setState(event.getState());
        coinTradeTypeDto.setDate(event.getDate());
        coinTradeTypeDto.setAttr1(event.getAttr1());
        coinTradeTypeDto.setAttr2(event.getAttr2());
        switch (operation){
            case "insert":
            	coinTranTypeOperation.addCoinTradeType(coinTradeTypeDto);
                break;
            case "update":
            	coinTranTypeOperation.updateCoinTradeType(coinTradeTypeDto);
                break;
            case "delete":
            	coinTranTypeOperation.deleteCoinTradeType(coinTradeTypeDto);
                break;
            default:
                break;
        }
    }

}
