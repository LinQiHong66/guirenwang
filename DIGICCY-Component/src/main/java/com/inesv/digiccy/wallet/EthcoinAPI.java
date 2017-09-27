/**
decodescript "hex"
dumpwallet "filename"
getaccount "bitcoinaddress"
getaccountaddress "account"
backupwallet "destination"
importwallet "filename"
validateaddress "bitcoinaddress"
settxfee amount
addnode "node" "add|remove|onetry"
createrawtransaction [{"txid":"id","vout":n},...] {"address":amount,...}
getaddednodeinfo dns ( "node" )
getbalance ( "account" minconf )
getblocktemplate ( "jsonrequestobject" )
getnetworkhashps ( blocks height )
getreceivedbyaccount "account" ( minconf )
getreceivedbyaddress "bitcoinaddress" ( minconf )
gettxout "txid" n ( includemempool )
getwork ( "data" )
help ( "command" )
importprivkey "bitcoinprivkey" ( "label" rescan )
keypoolrefill ( newsize )
listaccounts ( minconf )
listreceivedbyaccount ( minconf includeempty )
listsinceblock ( "blockhash" target-confirmations )
listtransactions ( "account" count from )
listunspent ( minconf maxconf ["address",...] )
lockunspent unlock [{"txid":"txid","vout":n},...]
move "fromaccount" "toaccount" amount ( minconf "comment" )
sendfrom "fromaccount" "tobitcoinaddress" amount ( minconf "comment" "comment-to" )
sendmany "fromaccount" {"address":amount,...} ( minconf "comment" )
sendtoaddress "bitcoinaddress" amount ( "comment" "comment-to" )
setaccount "bitcoinaddress" "account"
setgenerate generate ( genproclimit )
signmessage "bitcoinaddress" "message"
signrawtransaction "hexstring" ( [{"txid":"id","vout":n,"scriptPubKey":"hex","redeemScript":"hex"},...] ["privatekey1",...] sighashtype )
submitblock "hexdata" ( "jsonparametersobject" )
verifychain ( checklevel numblocks )
verifymessage "bitcoinaddress" "signature" "message"
 */
package com.inesv.digiccy.wallet;

 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

 
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;


import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
public class EthcoinAPI {
	private static Logger logger = LoggerFactory.getLogger(EthcoinAPI.class);

	private String rpcuser;
	private String rpcpassword;
	private String rpcurl;
	private String rpcport;
	private String walletpassphrase;

	/**
	 * 
	 */
	public EthcoinAPI() {

	}

	public EthcoinAPI(String rpcuser, String rpcpassword, String rpcurl, String rpcport, String walletpassphrase) {
		this.rpcuser = rpcuser;
		this.rpcpassword = rpcpassword;
		this.rpcurl = rpcurl;
		this.rpcport = rpcport;
		this.walletpassphrase = walletpassphrase;
	}

	private static final Charset QUERY_CHARSET = Charset.forName("ISO8859-1");

	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	private byte[] prepareRequest(final String method, final Object... params) {
		return JSON.stringify(new LinkedHashMap() {
			{
				put("jsonrpc", "1.0");
				put("id", "1");
				put("method", method);
				put("params", params);
			}
		}).getBytes(QUERY_CHARSET);
	}

	private String generateRequest(String method, Object... param) {

		String requestBody = new String(this.prepareRequest(method, param));
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< requestBody： " + requestBody);
		if (method.equals("dumpprivkey"))
			logger.info("dumpprivkey so no logging");
		else if (method.equals("getblock"))
			logger.info("It's to long to log an entire block");
		else if (method.equals("walletpassphrasechange"))
			logger.info("walletpassphrasechange so no logging");
		else
			logger.info("请求参数：" + requestBody);

		final PasswordAuthentication temp = new PasswordAuthentication(this.rpcuser, this.rpcpassword.toCharArray());
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return temp;
			}
		});
		String uri = "http://" + this.rpcurl + ":" + this.rpcport;
		logger.info("请求url:---------" + uri);

		String contentType = "application/json";
		HttpURLConnection connection = null;
		try {
			URL url = new URL(uri);
			connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", contentType);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Length", Integer.toString(requestBody.getBytes().length));
			connection.setUseCaches(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(6000);
			OutputStream out = connection.getOutputStream();
			out.write(requestBody.getBytes());
			out.flush();
			out.close();
		} catch (Exception ioE) {
			connection.disconnect();
			// ioE.printStackTrace();
			logger.info("connection error");
		}

		try {
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream is = connection.getInputStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				String line;
				StringBuffer response = new StringBuffer();
				while ((line = rd.readLine()) != null) {
					response.append(line);
					response.append('\r');
				}
				rd.close();

				String responseToString = response.toString();
				try {
					JSONObject json = new JSONObject(responseToString);
					String returnAnswer = json.get("result").toString();
					logger.info("响应结果：" + returnAnswer);

					if (method.equals("dumpprivkey"))
						logger.info("dumpprivkey so no logging");
					else if (method.equals("getblock"))
						logger.info("It's to long to log an entire block");
					else if (method.equals("walletpassphrasechange"))
						logger.info("walletpassphrasechange so no logging");
					else
						logger.info(returnAnswer);

					return returnAnswer;
				} catch (Exception e) {
					return "";
				}
			} else {
				System.out.println("Coudln't connet to Bitcoind!");
				logger.info("Coudln't connet to Bitcoind!");
				connection.disconnect();
			}
		} catch (Exception e) {
		}
		logger.info("Couldn't get a decent answer");
		return "";
	}

	/**
	 * 获取余额
	 * @param address : 地址
	 * @return
	 */
	public String getBalance(String address) {
		logger.info(address);
		String returnAnswer = generateRequest("eth_getBalance", address, "latest");
		logger.info(returnAnswer);
		return returnAnswer;
	}

	/**
	 * 新建账户
	 * @param pwd : 密码
	 * @return
	 */
	public String newAccount(String pwd) {
		logger.info(pwd);
		String returnAnswer = generateRequest("personal_newAccount", pwd);
		logger.info(returnAnswer);
		return returnAnswer;
	}

	/**
	 * 转账
	 * @param from ： 转入账户
	 * @param to ： 转出账户
	 * @param value ： 金额          
	 * @return
	 */
	public String sendTransaction(String from, String to, String value) {
		System.out.println("转账=====from:"+from);
		System.out.println("转账=====to:"+to);
		System.out.println("转账=====value:"+value);
		// 账户解锁
		String unlockAccountRes = generateRequest("personal_unlockAccount", from, this.rpcpassword);
		logger.info(unlockAccountRes);
		// 发送交易
		Map<String, String> reqMap = new HashMap<>();
		reqMap.put("from", from);
		reqMap.put("to", to);
		reqMap.put("value", value);
		String returnAnswer = generateRequest("eth_sendTransaction", reqMap);
		logger.info(returnAnswer);
		return returnAnswer;
	}
	
	/**
	 * 获取主账户
	 * @return
	 */
	public String getMainaccount() { 
		 
		String returnAnswer = generateRequest("eth_coinbase");
		logger.info(returnAnswer);
		return returnAnswer;
	}
	
	
	/**
	 * 获取所有账户
	 * @return
	 */
	public String getAllAccounts() { 
		 
		String res = generateRequest("eth_accounts");
		return res;
	}

}
