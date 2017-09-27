package com.inesv.digiccy.controller;

import com.inesv.digiccy.dto.WalletLinkDto;
import com.inesv.digiccy.query.QueryWalletLinkInfo;
import com.inesv.digiccy.validata.walletaddress.WalletAddressValidate;
import com.inesv.digiccy.wallet.BitcoinAPI;
import com.inesv.digiccy.wallet.EthcoinAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by yc on 2016/12/19 0019.
 * 安全中心钱包地址管理
 */
@Controller
@RequestMapping("/Safety")
public class WalletAddressController {

    @Autowired
    WalletAddressValidate walletAddressValidate;

    @Autowired
    QueryWalletLinkInfo queryWalletLinkInfo;

    /**获取用户钱包地址信息*/
    @RequestMapping(value = "/getAddress",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAddress(int userNo,int coinNo){
        Map<String,Object> map = walletAddressValidate.validateGetWalletAddressInfo(userNo,coinNo);
        return map;
    }
    
    /**新增钱包地址*/
    @RequestMapping(value = "/addAddress",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addAddress(int userNo,int coinType,String walletename,String address,String dealPwd){
        Map<String,Object> map = walletAddressValidate.validateAddWalletAddress(userNo,coinType,walletename,address,dealPwd);
        return map;
    }

    /**删除钱包地址*/
    @RequestMapping(value = "/deleteAddress",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteAddress(Long id){
        Map<String,Object> map = walletAddressValidate.validateDeleteAddress(id);
        return map;

    }



    /**查询币种钱包链接信息*/
    public WalletLinkDto WalletLinkInfo(int coinType){
        WalletLinkDto walletLinkDto = queryWalletLinkInfo.queryLinkInfo(coinType);
        return walletLinkDto;
    }




}
