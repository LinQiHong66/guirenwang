package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.WalletLinkDto;
import com.inesv.digiccy.event.WalletLinkEvent;
import com.inesv.digiccy.persistence.wallet.WalletLinkOperation;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ben on 2016/12/20 0020.
 */

public class WalletLinkEventHandler {
    @Autowired
    WalletLinkOperation walletLinkOperation;

    @EventHandler
    public void handler(WalletLinkEvent event){
        String operation = event.getOperation();
        WalletLinkDto walletLinkDto = new WalletLinkDto();
        walletLinkDto.setId(event.getId());
        walletLinkDto.setCoin_no(event.getCoin_no());
        walletLinkDto.setHost(event.getHost());
        walletLinkDto.setPost(event.getPost());
        walletLinkDto.setWallet_name(event.getWallet_name());
        walletLinkDto.setWallet_pwd(event.getWallet_pwd());
        walletLinkDto.setWallet_lockpwd(event.getWallet_lockpwd());

        switch (operation){
            case "insert":
                walletLinkOperation.addWalletLink(walletLinkDto);
                break;
            case "update":
                walletLinkOperation.updateWalletLink(walletLinkDto);
                break;
            case "delete":
                walletLinkOperation.deleWalletLink(event.getCoin_no());
                break;
        }
    }
}
