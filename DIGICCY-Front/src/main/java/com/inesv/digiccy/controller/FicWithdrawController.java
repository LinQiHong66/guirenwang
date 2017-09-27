package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.WalletLinkDto;
import com.inesv.digiccy.query.QueryWalletLinkInfo;
import com.inesv.digiccy.validata.FicWithdrawValidate;
import com.inesv.digiccy.wallet.BitcoinAPI;
import com.inesv.digiccy.wallet.EthcoinAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
@Controller
@RequestMapping("/virtualWith")
public class FicWithdrawController {

    @Autowired
    FicWithdrawValidate ficWithdrawValidate;

    @Autowired
    QueryWalletLinkInfo queryWalletLinkInfo;

    /**
     *账单记录
     */
    @RequestMapping(value = "getbill", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getbill(String start,String end,String date,String type,Integer userId) {
        Map<String,Object> map = ficWithdrawValidate.validateBill(start, end, date, type,userId);
        return map;
    }
    
    /**
     * 可用金额，提现地址，手续费列表，提现列表
     */
    @RequestMapping(value = "getWithdrawinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getWithdrawinfo(int userNo,int coinNo) {
        Map<String,Object> map = ficWithdrawValidate.validateWithdrawInfo(userNo,coinNo);
        return map;
    }

    /**
     * 查询相应币种的交易记录及可用余额，判断是否有钱包地址
     */
    @RequestMapping(value = "goVirtuaWith", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> goVirtuaWith(int userNo,int coinNo) {
        Map<String,Object> map = ficWithdrawValidate.validateVirtualWithdraw(userNo,coinNo);
        return map;
    }
    
    /**
     *当用户无钱包地址的时候用户手动填入钱包地址并校验
     */
    @RequestMapping(value = "checkAddress",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> checkAddress(String address,int userNo,int coinType){
        BitcoinAPI bitcoinAPI = getBitcoinAPI(coinType);
        Map<String,Object> map = ficWithdrawValidate.validateWalletAddress(address,userNo,coinType,bitcoinAPI);
        return map;
    }
 
	/**
	 * 确认提现
	 * @param address :提现地址
	 * @param bitcoinAPI
	 * @param userNo 
	 * @param dealPwd 
	 * @param coinNo
	 * @param fee ：手续费等级
	 * @param trueNum : 提现金额 
	 * @return
	 */
    @RequestMapping(value = "determineWithdraw",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> goDetermineWithdraw(String address,int userNo,int coinType,String dealPwd,int fee,String trueNum){
        Map<String,Object> map = null;
        try {
			map = ficWithdrawValidate.validateFirWithdraw(address,userNo,dealPwd,coinType,fee,trueNum);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
            map.put("desc", "虚拟币提现失败！");
		}
    	return map;
    }
    

    /**
     *获取手机验证码
     */
    @RequestMapping(value = "sendCodeCode",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> sendCodeCode(String mobile){
        Map<String,Object> map = ficWithdrawValidate.validatePhoneCode(1,mobile);
        return map;
    }

    /**
     *获取钱包接口
     */
    public BitcoinAPI getBitcoinAPI(int coinType){
        WalletLinkDto walletLinkDto = WalletLinkInfo(coinType);
        String host = walletLinkDto.getHost();
        String post = walletLinkDto.getPost();
        String walletName = walletLinkDto.getWallet_name();
        String walletPwd = walletLinkDto.getWallet_pwd();
        String lockPwd = walletLinkDto.getWallet_lockpwd();
        return new BitcoinAPI(walletName,walletPwd,host,post,lockPwd);
    }
    
    /**
     *获取钱包接口--以太坊
     */
    public EthcoinAPI getETHcoinAPI(int coinType){
        WalletLinkDto walletLinkDto = WalletLinkInfo(coinType);
        String host = walletLinkDto.getHost();
        String post = walletLinkDto.getPost();
        String walletName = walletLinkDto.getWallet_name();
        String walletPwd = walletLinkDto.getWallet_pwd();
        String lockPwd = walletLinkDto.getWallet_lockpwd();
        return new EthcoinAPI(walletName, walletPwd, host, post, lockPwd);
    }

    /**查询币种钱包链接信息*/
    public WalletLinkDto WalletLinkInfo(int coinType){
        WalletLinkDto walletLinkDto = queryWalletLinkInfo.queryLinkInfo(coinType);
        return walletLinkDto;
    }


}
