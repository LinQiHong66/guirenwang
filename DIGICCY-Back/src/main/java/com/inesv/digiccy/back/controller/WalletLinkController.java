package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.walletlink.WalletLinkdata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by JimJim on 2016/12/21 0021.
 */
@Controller
@RequestMapping("/WalletLink")
public class WalletLinkController {
    private static Logger logger = LoggerFactory.getLogger(WalletLinkController.class);

    @Autowired
    WalletLinkdata walletLinkdata;

    @RequestMapping(value = "gotoWalletLink",method = RequestMethod.GET)
    public String gotowalletlink(){
        return "/coin/coin";
    }

    @RequestMapping(value = "getAllCoin",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllWalletLink(){
        Map<String,Object> walletLinkMap = walletLinkdata.getAllWalletLink();
        return walletLinkMap;
    }

    @RequestMapping(value = "addWalletLink",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addWalletLink(@RequestParam int coin_no,@RequestParam String host,@RequestParam String post,
                                            @RequestParam String wallet_name,
                                            @RequestParam String wallet_pwd,@RequestParam String wallet_lockpwd){
        Map<String,Object> walletLinkMap = walletLinkdata.addWalletLink(coin_no,host,post,wallet_name,wallet_pwd,wallet_lockpwd);
        return walletLinkMap;
     }

    public Map<String,Object> updateWalletLink(@RequestParam int id,@RequestParam int coin_no,@RequestParam String host,@RequestParam String post,
                                               @RequestParam String wallet_name,
                                               @RequestParam String wallet_pwd,@RequestParam String wallet_lockpwd){
        Map<String,Object> walletLinkMap = walletLinkdata.updateWalletLink(id,coin_no,host,post,wallet_name,wallet_pwd,wallet_lockpwd);
        return walletLinkMap;
    }

    @RequestMapping(value = "deleteWalletLink",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteCoin(@RequestParam int coin_no){
        Map<String,Object> walletLinkMap = walletLinkdata.deleteWalletLink(coin_no);
        return walletLinkMap;
    }

}
