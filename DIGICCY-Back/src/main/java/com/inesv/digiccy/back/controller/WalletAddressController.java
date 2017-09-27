package com.inesv.digiccy.back.controller;

/**
 * Created by JimJim on 2016/12/27 0027.
 */

import com.inesv.digiccy.validata.walletaddress.WalletAddressValidate;
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
 * Created by JimJim on 2016/12/7 0007.
 */
@Controller
@RequestMapping("/walletAddress")
public class WalletAddressController {
    private static Logger logger = LoggerFactory.getLogger(WalletAddressController.class);

    @Autowired
    WalletAddressValidate walletAddressValidate;

    @RequestMapping(value = "gotoWalletAddress",method = RequestMethod.GET)
    public String gotoWalletAddress(){
        return "/user/walletAddress";
    }

    @RequestMapping(value = "getWalletAddressOfUser",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getWalletAddressOfUser(@RequestParam String user_no){
        Map<String,Object> map = walletAddressValidate.getWalletAddressOfUser(Integer.valueOf(user_no));
        return map;
    }
    @RequestMapping(value = "getWalletAddress",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getWalletAddress(){
        Map<String,Object> map = walletAddressValidate.getAallWalletAddress();
        return map;
    }
    @RequestMapping(value = "getWalletAddressOfCoin",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getWalletAddress(@RequestParam String coin_no){
        Map<String,Object> map = walletAddressValidate.getWalletAddressOfCoin(Integer.valueOf(coin_no));
        return map;
    }
}
