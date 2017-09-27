package com.inesv.digiccy.validata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inesv.digiccy.api.command.FicRechargeCommand;
import com.inesv.digiccy.api.command.UserBalanceCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.*;
import com.inesv.digiccy.enums.RechargeState;
import com.inesv.digiccy.enums.SqlOperate;
import com.inesv.digiccy.persistence.finance.FicRechargePersistence;
import com.inesv.digiccy.query.QueryFicRechargeInfo;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import com.inesv.digiccy.query.QueryUserInfo;
import com.inesv.digiccy.query.QueryWalletLinkInfo;
import com.inesv.digiccy.wallet.EthcoinAPI;
import com.inesv.digiccy.wallet.TransactionResult;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Component
public class FicRechargeValidate {
	
	 public static Integer PUBLICMAP = 0; 
	 
	 @Autowired
	 QueryUserInfo queryUserInfo;
	
    @Autowired
    QueryFicRechargeInfo queryFicRechargeInfo;

    @Autowired
    QueryWalletLinkInfo queryWalletLinkInfo;

    @Autowired
    QueryUserBalanceInfo queryUserBalanceInfo;
    @Autowired
    RedisTemplate<String, Object>  redisTemplate;
    @Autowired
    private CommandGateway commandGateway;

    private static final Logger logger = LoggerFactory.getLogger(FicRechargeValidate.class);

    @Autowired
    FicRechargePersistence  ficRechargePersistence;
    
    /**
     * lqh
     * 获取用户钱包地址
     * 查询用户交易记录
     * 查询相应币种额度
     **/
    public Map<String, Object> validateVirtualRecharge(String username, int userNo, int coinType) {
    	Map<String, Object> map = new HashMap();
        WalletLinkDto walletLinkDto = WalletLinkInfo(coinType);
        String address ="";
        if(walletLinkDto != null){
            String host = walletLinkDto.getHost();//钱包ip地址
            String post = walletLinkDto.getPost();//钱包端口号
            String walletName = walletLinkDto.getWallet_name();//钱包用户名
            String walletPassword = walletLinkDto.getWallet_pwd();//钱包密码
            String lockPwd = walletLinkDto.getWallet_lockpwd();//钱包锁密码

			UserBalanceDto balance = queryUserBalanceInfo
					.queryUserBalanceInfoByUserNoAndCoinType(String.valueOf(userNo), String.valueOf(coinType));
			if (balance == null || balance.getWallet_address() == null||balance.getWallet_address().equals("")) // 没有钱包地址，创建钱包地址
           {
				 
				if (coinType == 20 || coinType == 40) // 以太坊|以太经典
				{
					EthcoinAPI eth = new EthcoinAPI(username, walletPassword, host, post, lockPwd);
					address = eth.newAccount(walletPassword); // 钱包密码
				} else { // 其他币
					
					TransactionResult transactionResult = new TransactionResult(host, post, walletName, walletPassword,lockPwd);// 建立连接
					address = transactionResult.getAccountAddress(username); // 获取相应币种的钱包地址
				}
				 //将获取的钱包地址存入用户资产表
	            UserBalanceCommand command = new UserBalanceCommand(8997, userNo, coinType, address, "update");
	            commandGateway.send(command);

			} else {
				address = balance.getWallet_address();
			}
    
            map.put("address", address);
        }else{
            map.put("address", "");
        }

        UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, coinType);
        if(userBalanceDto != null){
            BigDecimal enable = userBalanceDto.getEnable_coin();//获取可用的币种金额
            map.put("coin", enable);
        }else {
            map.put("coin", 0);
        }
        List<FicRechargeDto> list = queryFicRechargeInfo.queryFicRechargeInfo(userNo, coinType);//查询出充值记录
        if (!list.isEmpty()) {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
        	map.put("data", null);
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }

        return map;
    }

	
	
//    /**
//     * 校验确认虚拟币充值
//     * Return Map
//     */
//    public void validateRechargeCoin() {
//        List<CoinDto> Coinlist = queryFicRechargeInfo.queryAllCoinType();//查询出所有开启的币种信息
//        int coinNo = 0;
//        //遍历币种表
//        for (CoinDto coinDto : Coinlist) {
//            coinNo = coinDto.getCoin_no();
//            Map<String, Object> map = new HashMap();
//            WalletLinkDto walletLinkDto = WalletLinkInfo(coinNo);
//            String host = walletLinkDto.getHost();//钱包ip地址
//            String post = walletLinkDto.getPost();//钱包端口号
//            String walletName = walletLinkDto.getWallet_name();//钱包用户名
//            String walletPassword = walletLinkDto.getWallet_pwd();//钱包密码
//            String lockPwd = walletLinkDto.getWallet_lockpwd();//钱包锁密码
//            TransactionResult transactionResult = new TransactionResult(host, post, walletName, walletPassword, lockPwd);//建立钱包连接
//            //String address = transactionResult.getAccountAddress("*");//获取钱包地址
//            String trans = transactionResult.getTransInfoToOneHundred();//获取交易记录最近一百条交易记录
//            //判断返回记录是否为空
//            if (!"none".equals(trans)) {
//                List<Map<String, Object>> list = JSON.parseObject(trans, List.class);
//                for (Map<String, Object> listMap : list) {
//                    String account = (String) listMap.get("account");//获取交易的用户名
//                    if (account != null && !"".equals(account)) {
//                        UserInfoDto userInfoDto = queryFicRechargeInfo.queryUserINfoByUsername(account);//查询此用户名是否是本系统的用户名
//                        if (userInfoDto != null) {
//                            String address = (String) listMap.get("address");
//                            int userNo = userInfoDto.getUser_no();//获取用户的用户编号
//                            String tixId = (String) listMap.get("txid");//获取交易ID
//                            Double price = transactionResult.getResultCoin(listMap.get("amount"));//获取充值的额度
//                            BigDecimal bigPrice = BigDecimal.valueOf(price);//将额度转换为BigDecimal格式
//                            List<FicRechargeDto> TxidList = queryFicRechargeInfo.qurtytxid(tixId);//用交易ID查询用户是否已充值
//                            if (TxidList.isEmpty()) {
//                                FicRechargeCommand command = new FicRechargeCommand(351, userNo, coinNo, address, bigPrice, BigDecimal.ZERO, bigPrice, 0, new Date(), tixId, "insert");
//                                commandGateway.sendAndWait(command);//新增交易记录
//                                List<FicRechargeDto> listFicState = queryFicRechargeInfo.queryState(userNo, coinNo, 0);//确认时候有六条充值记录
//                                if (!listFicState.isEmpty() && listFicState != null) {
//                                    FicRechargeDto ficDto = queryFicRechargeInfo.querySumPrice(userNo, coinNo, 0);
//                                    BigDecimal actualPrice = ficDto.getActual_price();//获取大于六条充值金额的总额
//                                    UserBalanceDto userBalanceDto1 = queryUserBalanceInfo.queryEnableCoin(userNo, coinNo);
//                                    BigDecimal enable = userBalanceDto1.getEnable_coin();//获取当前用户可用币种余额
//                                    BigDecimal totalPrice = userBalanceDto1.getTotal_price();//获取用户可用总额
//                                    BigDecimal nowEnble = enable.add(actualPrice);//充值后币种可用金额
//                                    BigDecimal nowTotalprice = totalPrice.add(actualPrice);
//                                    UserBalanceCommand command1 = new UserBalanceCommand(5535L, userNo, coinNo, nowEnble, nowTotalprice, new Date(), "updateEnble");//充值后更新用户金额和总额
//                                    commandGateway.sendAndWait(command1);
//                                    FicRechargeCommand command2 = new FicRechargeCommand(545, userNo, coinNo, 1, "updateState");//修改状态
//                                    commandGateway.send(command2);
//                                    map.put("code", ResponseCode.SUCCESS);
//                                    map.put("desc", ResponseCode.SUCCESS_DESC);
//                                }
//
//                            }
//
//                        }
//                    }
//                }
//            }
//        }
//    }

    
    
 
    
    @Transactional
    public void validateRechargeCoinNew() throws SQLException{
    	System.out.println(".........开始.....validateRechargeCoinNew..............");
    	//添加一个 key 
        ValueOperations<String, Object> value = redisTemplate.opsForValue();
 
        
         
        logger.info("Mission COINRECHARGE==>START");
        List<CoinDto> coinList = queryFicRechargeInfo.queryAllCoinType();
        for(CoinDto coin : coinList){
            Integer coinNo = coin.getCoin_no();
            logger.info("coinname[{}],coinNo[{}]",coin.getCoin_name(),coin.getCoin_no());
            WalletLinkDto walletLinkInfo = WalletLinkInfo(coinNo);
            if (walletLinkInfo == null)continue;
            
            
            if(coin.getCoin_no()==20 || coin.getCoin_no()==40){ //以太坊|以太经典
            	
            	//redis存放执行的位置
            	if(value.get(coin.getCoin_core())==null ){
                	value.set(coin.getCoin_core(),0);
                 }
            	
            	/*EthcoinAPI eth=new EthcoinAPI(walletLinkInfo.getWallet_name(),walletLinkInfo.getWallet_pwd(),walletLinkInfo.getHost(),walletLinkInfo.getPost(),walletLinkInfo.getWallet_lockpwd());
            	String accounts = eth.getAllAccounts(); //获取所有以太坊账户
            	List<String> list = new ArrayList<String>();  
      	        list = JSONObject.parseArray(accounts, String.class);*/
            	List<String> list = queryUserBalanceInfo.getBalanceByCoinType(coin.getCoin_no()); // 获取所有以太坊账户（平台內）
            	System.err.println("======以太记录：====="+list.size());
         
            	List<String> listMC=new ArrayList<>();
            	
            	//每次只處理3條數據，防止頻繁請求被拒絕
            	int inti = (int)value.get(coin.getCoin_core()); 
                int sum=(int)value.get(coin.getCoin_core())+3;  
               
            	value.set(coin.getCoin_core(),sum);  
            	if(sum>=list.size()){  
            		sum=list.size();
            		value.set(coin.getCoin_core(),0);
            	}
            	for(int i=inti;i<sum;i++){
            		listMC.add(list.get(i));
            		 System.err.println("=========address"+i+":"+list.get(i));
            	}
            	
            	 System.err.println("=========inti===:"+inti);
            	 System.err.println("=========sum===:"+sum);
      	        for(String account : listMC){   //遍歷所有賬戶的充值信息，添加到充值表
      	        	 System.err.println("=========address 进行充值处理===:"+account);
      	        	List<Map<String, Object>> translist =new ArrayList<Map<String, Object>>();
      	         try{
      	        	translist = this.getTransactionByAccount(account); //获取账户的充值信息
      	         }catch(Exception e)
      	         {
      	        	System.err.println("=============請求被拒絕了===============");
      	        	 e.printStackTrace();
      	         }
      	        	for(Map<String, Object> m: translist){
      	        		String txid = m.get("hash").toString();
      	        		String amount = m.get("amount").toString();
      	        		BigDecimal amountBig=new BigDecimal(amount);
      	        		BigDecimal wei=new BigDecimal("1000000000000000000");
      	        		BigDecimal bigPrice = amountBig.divide(wei);
      	        		String fromaddress= m.get("recipient").toString();  //充值賬戶
      	        		System.out.println("========fromaddress:"+fromaddress+"======================");
      	        		UserBalanceDto userBalanceDto = queryUserBalanceInfo.getBalanceByAddressAndCoinType(account, coin.getCoin_no());
      	        		Integer userNo = userBalanceDto.getUser_no();
      	        		 FicRechargeDto rechargeInfo = queryFicRechargeInfo.qurtytxid(txid); //充值信息
      	        		if(rechargeInfo==null){ //没有充值记录，插入一条
      	        			 FicRechargeCommand ficRechargeCommand = new FicRechargeCommand(0, userNo, coinNo, fromaddress, bigPrice, BigDecimal.ZERO, bigPrice, RechargeState.RECHARGED.getValue(), new Date(), txid, SqlOperate.INSERT.getValue()); //充值完成
                             commandGateway.send(ficRechargeCommand);
                             
                             BigDecimal enable = userBalanceDto.getEnable_coin();
                             BigDecimal totalPrice = userBalanceDto.getTotal_price();
                             BigDecimal nowEnble = enable.add(bigPrice);
                             BigDecimal nowTotalprice = totalPrice.add(bigPrice);
                             /*UserBalanceCommand userBalanceCommand = new UserBalanceCommand(0, userNo, coinNo, nowEnble, nowTotalprice, new Date(), "updateEnblexn");//充值后更新用户金额和总额
                             commandGateway.sendAndWait(userBalanceCommand);*/
                             
                             //虚拟币充值
                             ficRechargePersistence.ficRechargeTransac(userNo, coinNo, fromaddress, bigPrice, RechargeState.RECHARGED.getValue(), txid, nowEnble, nowTotalprice);
      	        		}
      	        	}
      	        }
             
            	 continue;
            }
             //其他币
            TransactionResult transactionResult = new TransactionResult(walletLinkInfo.getHost(),walletLinkInfo.getPost(),walletLinkInfo.getWallet_name(),walletLinkInfo.getWallet_pwd(),walletLinkInfo.getWallet_lockpwd());
            String transJson = transactionResult.getTransInfoToOneHundred();   //获取所有交易记录

            if(!"none".equals(transJson)){
                List<Map<String,Object>> transList = JSON.parseObject(transJson,List.class);
                for(Map<String,Object> trans: transList){
                    if(!"receive".equals(trans.get("category")))continue;
                    String account = (String) trans.get("account");
                    if(account == null || "".equals(account)) continue;
                    UserInfoDto userInfo = queryFicRechargeInfo.queryUserINfoByUsername(account);
                    if (userInfo == null) continue;
                    logger.info("All check ok");
                    String address = (String) trans.get("address");
                    int userNo = userInfo.getUser_no();
                    String txid = (String) trans.get("txid");
                    Double price = transactionResult.getResultCoin(trans.get("amount"));
                    BigDecimal bigPrice = BigDecimal.valueOf(price);
                    FicRechargeDto rechargeInfo = queryFicRechargeInfo.qurtytxid(txid); //充值信息
                    Integer confirmations = trans.get("confirmations") == null ? 0 : (Integer) trans.get("confirmations");
                    if(rechargeInfo == null){  //没有充值记录，则插入一个
                        if(confirmations >= 6){
                          /*  FicRechargeCommand ficRechargeCommand = new FicRechargeCommand(0, userNo, coinNo, address, bigPrice, BigDecimal.ZERO, bigPrice, RechargeState.RECHARGED.getValue(), new Date(), txid, SqlOperate.INSERT.getValue()); //充值完成
                            commandGateway.send(ficRechargeCommand);*/
                            UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, coinNo);
                            BigDecimal enable = userBalanceDto.getEnable_coin();
                            BigDecimal totalPrice = userBalanceDto.getTotal_price();
                            BigDecimal nowEnble = enable.add(bigPrice);
                            BigDecimal nowTotalprice = totalPrice.add(bigPrice);
                            /*UserBalanceCommand userBalanceCommand = new UserBalanceCommand(0, userNo, coinNo, nowEnble, nowTotalprice, new Date(), "updateEnblexn");//充值后更新用户金额和总额
                            commandGateway.sendAndWait(userBalanceCommand);*/
                            //虚拟币充值
                            ficRechargePersistence.ficRechargeTransac(userNo, coinNo, address, bigPrice, RechargeState.RECHARGED.getValue(), txid, nowEnble, nowTotalprice);
                            
                        }else{
                            FicRechargeCommand ficRechargeCommand = new FicRechargeCommand(0, userNo, coinNo, address, bigPrice, BigDecimal.ZERO, bigPrice, RechargeState.RECHARGEING.getValue(), new Date(), txid, SqlOperate.INSERT.getValue()); //充值中
                            commandGateway.send(ficRechargeCommand);
                        }
                    }else{    
                        if(rechargeInfo.getState() == RechargeState.RECHARGED.getValue()){ 
                            continue;
                        }else{
                            if(confirmations >= 6){
                                UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, coinNo);
                                BigDecimal enable = userBalanceDto.getEnable_coin();
                                BigDecimal totalPrice = userBalanceDto.getTotal_price();
                                BigDecimal nowEnble = enable.add(bigPrice);
                                BigDecimal nowTotalprice = totalPrice.add(bigPrice);
                                UserBalanceCommand userBalanceCommand = new UserBalanceCommand(0, userNo, coinNo, nowEnble, nowTotalprice, new Date(), "updateEnblexn");//充值后更新用户金额和总额
                                commandGateway.sendAndWait(userBalanceCommand);
                                FicRechargeCommand ficRechargeCommand = new FicRechargeCommand(0, userNo, coinNo, RechargeState.RECHARGED.getValue(),txid, "updateState");//修改状态
                                commandGateway.send(ficRechargeCommand); 
                            }else{
                                logger.info("nothing change");
                            }
                        }
                    }


                }
                logger.info("Recharge Mission Finish!");
            } else {
                logger.info("no coinrecharge info");
            } 
        }
    }



    /**
     * 查询币种钱包链接信息
     */
    public WalletLinkDto WalletLinkInfo(int coinType) {
        WalletLinkDto walletLinkDto = queryWalletLinkInfo.queryLinkInfo(coinType);
        return walletLinkDto;
    }
    
    
    //获取账户的交易记录
    public List<Map<String,Object>> getTransactionByAccount(String account)
    {
    	HttpRequest request = HttpRequest.get("https://etherchain.org/api/account/"+account+"/tx/0");
		request.contentType("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	    HttpResponse response = request.send();
		String respJson = response.bodyText();
		Map<String, Object> respMap = JSON.parseObject(respJson, Map.class);
		
	    List<Map<String,Object>> dataList = (List<Map<String, Object>>) respMap.get("data");
    	return dataList;
    }
    
    
 
    
 

}






