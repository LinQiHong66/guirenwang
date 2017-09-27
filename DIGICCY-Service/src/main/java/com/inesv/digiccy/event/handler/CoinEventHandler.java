package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.event.CoinEvent;
import com.inesv.digiccy.persistence.coin.CoinOperation;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
public class CoinEventHandler {

    @Autowired
    CoinOperation coinOperation;

   /* @Autowired
    WalletLinkOperation walletLinkOperation;*/

    @EventHandler
    public void handle(CoinEvent event) throws Exception {
        String operation = event.getOperation();
        CoinDto coinDto = new CoinDto();
        coinDto.setId(event.getCoinId());
        coinDto.setCoin_no(event.getCoinNo());
        coinDto.setAddress(event.getAddress());
        coinDto.setIcon(event.getIcon());
        coinDto.setCoin_name(event.getCoinName());
        coinDto.setCoin_core(event.getCoinCore());
        coinDto.setState(event.getState());
        coinDto.setDate(new Date());
        coinDto.setBuy_poundatge(event.getBuy_poundatge());
        coinDto.setSell_poundatge(event.getSell_poundatge());
        coinDto.setBlock(event.getBlock());
        coinDto.setWithdraw_poundatge_one(event.getSell_withdraw_poundatge_one());
        coinDto.setWithdraw_poundatge_three(event.getSell_withdraw_poundatge_three());
        coinDto.setWithdraw_poundatge_twe(event.getSell_withdraw_poundatge_twe());

        switch (operation){
            case "insert":
                coinOperation.addCoin(coinDto);
                break;
            case "update":
                coinOperation.updateCoin(coinDto);
                //walletLinkOperation.updateWalletLink(walletLinkDto);
                break;
            case "delete":
                coinOperation.deleteCoin(event.getCoinNo());
                break;
            case "state":
                coinOperation.changeState(event.getCoinId(),event.getState());
                break;
            case "vote":
                coinOperation.startVote(event.getCoinId(),event.getVote());
            case "icon":
                coinOperation.changeIcon(event.getCoinId(),event.getIcon());
                break;
            default:
                break;
        }
    }

}
