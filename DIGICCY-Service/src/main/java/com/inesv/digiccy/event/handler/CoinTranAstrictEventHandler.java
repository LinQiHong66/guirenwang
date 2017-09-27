package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinTranAstrictDto;
import com.inesv.digiccy.event.CoinEvent;
import com.inesv.digiccy.event.CoinTranAstrictEvent;
import com.inesv.digiccy.persistence.coin.CoinOperation;
import com.inesv.digiccy.persistence.coin.CoinTranAstrictOperation;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
public class CoinTranAstrictEventHandler {

    @Autowired
    CoinTranAstrictOperation coinTranAstrictOperation;

    @EventHandler
    public void handle(CoinTranAstrictEvent event) throws Exception {
        String operation = event.getOperation();
        CoinTranAstrictDto coinTranAstrictDto = new CoinTranAstrictDto();
        coinTranAstrictDto.setId(event.getId());
        coinTranAstrictDto.setCoin_no(event.getCoin_no());
        coinTranAstrictDto.setBuy_min_price(event.getBuy_min_price());
        coinTranAstrictDto.setBuy_max_price(event.getBuy_max_price());
        coinTranAstrictDto.setSell_max_price(event.getSell_max_price());
        coinTranAstrictDto.setSell_min_price(event.getSell_min_price());
        coinTranAstrictDto.setSingle_min_price(event.getSingle_min_price());
        coinTranAstrictDto.setSingle_max_price(event.getSingle_max_price());
        coinTranAstrictDto.setRose_astrict(event.getRose_astrict());
        coinTranAstrictDto.setDrop_astrict(event.getDrop_astrict());
        coinTranAstrictDto.setBegin_date(event.getBegin_date());
        coinTranAstrictDto.setEnd_date(event.getEnd_date());
        coinTranAstrictDto.setState(event.getState());

        switch (operation){
            case "insert":
            	coinTranAstrictOperation.addCoinTranAstrict(coinTranAstrictDto);
                break;
            case "update":
            	coinTranAstrictOperation.updateCoinTranAstrict(coinTranAstrictDto);
                break;
            case "delete":
            	coinTranAstrictOperation.deleteCoinTranAstrict(coinTranAstrictDto);
                break;
            default:
                break;
        }
    }

}
