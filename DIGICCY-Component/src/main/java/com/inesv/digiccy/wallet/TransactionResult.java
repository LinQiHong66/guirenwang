package com.inesv.digiccy.wallet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionResult {
	
	/*// 钱包接口地址
	private String url = "192.168.10.242";
	// 钱包接口端口
	private String port = "40624";
	// 钱包接口用户名
	private String username = "user";
	// 钱包接口密码
	private String password = "wsh!";
	// 钱包锁密码
	private String walletpaps = "asdasd";*/

	// 钱包接口地址
	private String url;
	// 钱包接口端口
	private String port;
	// 钱包接口用户名
	private String username;
	// 钱包接口密码
	private String password;
	// 钱包锁密码
	private String walletpaps;
	
	private BitcoinAPI ba;

	private static Logger logger = LoggerFactory.getLogger(TransactionResult.class);


	public TransactionResult(String url, String port, String username,String password, String walletpaps) {
		super();
		this.url = url;
		this.port = port;
		this.username = username;
		this.password = password;
		this.walletpaps = walletpaps;
		ba = new BitcoinAPI(username, password, url, port, walletpaps);
		logger.info("url:"+url+"-port:"+port+"-user:"+username+"-pass:"+password+"-walletpass:"+walletpaps);
	}

    public BitcoinAPI creatBitcoin(){
        return ba;
    }


	/**
	 *获取交易记录 
	 */
	public String getTrans(String name) {
		String result = ba.getaddressesbyaccount(name);
		if (result == null || "".equals(result) || "[]".equals(result)) {
			result = ba.getnewaddress(name);
			return result;
		}
		String transactionResult = ba.listtransactions(name, 100, 0);
		List<Object> list = JSON.parseObject(result, List.class);
		String address = (String) list.get(0);// 返回钱包地址
		if (transactionResult == null || "".equals(transactionResult) || "[]".equals(transactionResult)) {
			String notransaction = "none";
			return notransaction;
		}
		return transactionResult;
	}

	
	/**获取最近交易一次的交易金额*/
	public double getAmount(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = ba.getaddressesbyaccount(name);
		if (result == null || "".equals(result) || "[]".equals(result)) {
			result = ba.getnewaddress(name);
		}
		String transactionResult = ba.listtransactions(name, 10, 0);
		List<Object> list = JSON.parseObject(result, List.class);
		String address = (String) list.get(0);// 返回钱包地址
		if (transactionResult == null || "".equals(transactionResult) || "[]".equals(transactionResult)) {
			String notransaction = "none";
			map.put("notransaction", notransaction);
		} else {
			List<Object> trResultList = JSON.parseObject(transactionResult,List.class);
			Map<String, Object> tr1 = (Map<String, Object>) trResultList.get(trResultList.size() - 1);
			Object amount = tr1.get("amount");
			Double coinAmount = getResultCoin(amount);
			return coinAmount;
		}
		return 0D;
	}

	//获取钱包地址
	public String getAccountAddress(String name){
		String result = ba.getaddressesbyaccount(name);
		if (result == null || "".equals(result) || "[]".equals(result)) {
			result = ba.getnewaddress(name);
		}

		if(result != null && !"".equals(result)){
			 
			List<Object> list = JSON.parseObject(result, List.class);
			String address = (String) list.get(0);// 返回钱包地址
			return address;
		}

		return "";
	}

	public Double getResultCoin(Object amount){
		Double coinAmount = 0D;
		if (amount instanceof Integer) {
			coinAmount = ((Integer) amount).doubleValue();
		} else if (amount instanceof Double) {
			coinAmount = (Double) amount;
		} else if (amount instanceof BigDecimal) {
			coinAmount = ((BigDecimal) amount).doubleValue();
		} else {
			System.out.println("无法判断类型");
		}
		return coinAmount;
	}

	/**
	 *获取钱包地址 
	 */
	public String getAddress(String name){
				String result = ba.getaddressesbyaccount(name);
				if (result == null || "".equals(result) || "[]".equals(result)) {
					result = ba.getnewaddress(name);
					return result;
				}
				List<Object> list = JSON.parseObject(result, List.class);
				String address = (String) list.get(0);// 返回钱包地址
				return address;
	}

	/**
	 *获取交易记录
	 */
	public String getTransInfoToOneHundred() {
		String transactionResult = ba.listtransactions("*", 100, 0);
		if (transactionResult == null || "".equals(transactionResult) || "[]".equals(transactionResult)) {
			String notransaction = "none";
			return notransaction;
		}
		return transactionResult;
	}

	
}
