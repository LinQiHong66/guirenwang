package com.inesv.digiccy.validata.walletaddress;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inesv.digiccy.api.command.WalletAddressCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.UserAndWalletAndCoinDto;
import com.inesv.digiccy.dto.WalletAddressDto;
import com.inesv.digiccy.dto.WalletLinkDto;
import com.inesv.digiccy.query.QueryFinWithdrawInfo;
import com.inesv.digiccy.query.QueryWalletAddressInfo;
import com.inesv.digiccy.query.QueryWalletLinkInfo;
import com.inesv.digiccy.query.walletaddress.QueryWalletAddress;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.wallet.BitcoinAPI;
 
import com.inesv.digiccy.wallet.EthcoinAPI;
 
import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2017/1/3 0003.
 */
@Component
public class WalletAddressValidate {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private QueryWalletAddress queryWalletAddress;


    @Autowired
    QueryWalletAddressInfo queryWalletAddressInfo;

    @Autowired
    QueryWalletLinkInfo queryWalletLinkInfo;

    @Autowired
    QueryFinWithdrawInfo queryFinWithdrawInfo;


    /**
     * 根据用户名查询用户钱包信息
     * @return
     */
    public Map<String ,Object> getWalletAddressOfUser(Integer user_no){
        Map<String ,Object> map = new HashMap<>();
        WalletAddressDto walletAddressDto= queryWalletAddress.queryWalletAddressOfUser(user_no);
        if(walletAddressDto == null){
            map.put("code", ResponseCode.FAIL_BILL_INFO);
            map.put("desc",ResponseCode.FAIL_BILL_INFO_DESC);
        }else {
            map.put("data", walletAddressDto);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * 查询所有用户钱包信息
     * */
    public Map<String,Object> getAallWalletAddress(){

        Map<String,Object> map = new HashedMap();
        List<UserAndWalletAndCoinDto> walletAddressList = queryWalletAddress.queryAllWalletAddress();
        if(walletAddressList == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("total",walletAddressList.size());
            map.put("data",walletAddressList.subList(1,walletAddressList.size()));
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }
    /**
     * 根据币种查询用户钱包信息
     * */
    public Map<String ,Object> getWalletAddressOfCoin(Integer coin_no){
        Map<String ,Object> map = new HashMap<>();
        WalletAddressDto walletAddressDto= queryWalletAddress.queryWalletAddressOfCoin(coin_no);
        if(walletAddressDto == null){
            map.put("code", ResponseCode.FAIL_BILL_INFO);
            map.put("desc",ResponseCode.FAIL_BILL_INFO_DESC);
        }else {
            map.put("data", walletAddressDto);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**校验用户钱包地址信息*/
    public Map<String,Object> validateGetWalletAddressInfo(int userNo,int coinNo){
        Map<String,Object> map = new HashMap<>();
        List<WalletAddressDto> list = queryWalletAddressInfo.queryWalletAddressInfo(userNo,coinNo);
        if(!list.isEmpty()){
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }else{
            map.put("code", ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }
        return map;
    }
    
    /**校验新增钱包地址*/
    public Map<String,Object> validateAddWalletAddress(int userNo,int coinType,String walletename,String address,String dealPwd){
    	  try {
			walletename = new String(walletename.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Map<String,Object> map = new HashMap<>();
        if(coinType==20){
      
          String userDealPwd = queryFinWithdrawInfo.queryDeaPSW(userNo).getDeal_pwd();//获取用户交易密码
            String Md5DealPwd = new MD5().getMD5(dealPwd);
                             
           System.out.println("------------Md5DealPwd----"+Md5DealPwd+"----------- userDealPwd"+userDealPwd);
                if(Md5DealPwd.equals(userDealPwd)){
                    WalletAddressCommand command = new WalletAddressCommand(0,userNo,coinType,walletename,address,new Date(),"insertAddress");//Integer id, Integer userno, Integer coin_no, String idtf, String address, Date date, String atte1, String atte2, String operation
                    commandGateway.send(command);

                    map.put("code", ResponseCode.SUCCESS);
                    map.put("desc", ResponseCode.SUCCESS_DESC);
                }else{
                    map.put("code",200);
                    map.put("desc","交易密码有误");//交易密码有误
                }
           
        }else{
        	BitcoinAPI bitcoinAPI=this.getBitcoinAPI(coinType);
            String validateAddress = bitcoinAPI.validateaddress(address);//校验钱包地址是否合法
            Map<String, Object> resultMap =null;
            try{
                 resultMap = (Map<String, Object>) JSON.parseObject(validateAddress, Map.class);//校验地址的合法性
            }catch(Exception e){
            	 map.put("code", ResponseCode.FAIL);
                 map.put("desc",ResponseCode.FAIL_DESC);//钱包地址不合法
            	e.printStackTrace();
            	return map;
            	}
            String userDealPwd = queryFinWithdrawInfo.queryDeaPSW(userNo).getDeal_pwd();//获取用户交易密码
            String Md5DealPwd = new MD5().getMD5(dealPwd);
            if(((boolean) resultMap.get("isvalid")) == true){
                if(Md5DealPwd.equals(userDealPwd)){
                    WalletAddressCommand command = new WalletAddressCommand(0,userNo,coinType,walletename,address,new Date(),"insertAddress");//Integer id, Integer userno, Integer coin_no, String idtf, String address, Date date, String atte1, String atte2, String operation
                    commandGateway.send(command);

                    map.put("code", ResponseCode.SUCCESS);
                    map.put("desc", ResponseCode.SUCCESS_DESC);
                }else{
                    map.put("code", ResponseCode.FAIL);
                    map.put("desc",ResponseCode.FAIL_DESC);//交易密码有误
                }
            }else{
                map.put("code", ResponseCode.FAIL);
                map.put("desc",ResponseCode.FAIL_DESC);//钱包地址不合法
            }
        }
   
        return map;
    }


    /**查询币种钱包链接信息*/
    public WalletLinkDto WalletLinkInfo(int coinType){
        WalletLinkDto walletLinkDto = queryWalletLinkInfo.queryLinkInfo(coinType);
        return walletLinkDto;
    }


    /**
     *校验删除钱包地址
     */
    public Map<String,Object> validateDeleteAddress(Long id){
        Map<String,Object> map = new HashMap<>();
        WalletAddressCommand command = new WalletAddressCommand(id,"","deleteAddress");
        commandGateway.sendAndWait(command);
        map.put("code", ResponseCode.SUCCESS);
        map.put("desc", ResponseCode.SUCCESS_DESC);
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
     *获取以太坊钱包接口
     */
    public EthcoinAPI getEthcoinAPI(int coinType){
        WalletLinkDto walletLinkDto = WalletLinkInfo(coinType);
        String host = walletLinkDto.getHost();
        String post = walletLinkDto.getPost();
        String walletName = walletLinkDto.getWallet_name();
        String walletPwd = walletLinkDto.getWallet_pwd();
        String lockPwd = walletLinkDto.getWallet_lockpwd();
        return new EthcoinAPI(walletName,walletPwd,host,post,lockPwd);
    }
}
