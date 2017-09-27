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


public class BitcoinAPI {
	private static Logger logger = LoggerFactory.getLogger(BitcoinAPI.class);

	private String rpcuser;
	private String rpcpassword;
	private String rpcurl;
	private String rpcport;
	private String walletpassphrase;

	/**
	 * 
	 */
	public BitcoinAPI() {
		
	}
	
	public BitcoinAPI(String rpcuser,String rpcpassword,String rpcurl,String rpcport,String walletpassphrase) {
		this.rpcuser = rpcuser;
		this.rpcpassword = rpcpassword;
		this.rpcurl = rpcurl;
		this.rpcport = rpcport;
		this.walletpassphrase = walletpassphrase;
	}

	/**
	 * @return
	 */
	public String getblockcount() {
		String returnAnswer = generateRequest("getblockcount");
		logger.info(returnAnswer);
		return returnAnswer;
	}

	/**
	 * @return
	 */
	public String getdifficulty() {
		String returnAnswer = generateRequest("getdifficulty");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getgenerate() {
		String returnAnswer = generateRequest("getgenerate");
		logger.info(returnAnswer);
		return returnAnswer;

	}
	
	public String getstakinginfo(){
		String returnAnswer = generateRequest("getstakinginfo");
		logger.info(returnAnswer);
		return returnAnswer;
	}

	/**
	 * @return
	 */
	public String getinfo() {
		String returnAnswer = generateRequest("getinfo");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getbestblockhash() {
		String returnAnswer = generateRequest("getrawchangeaddress");
		logger.info(returnAnswer);
		return returnAnswer;
 }

	/**
	 * @return
	 */
	public String getrawchangeaddress() {
		String returnAnswer = generateRequest("getrawchangeaddress");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getbalance() {
		String returnAnswer = generateRequest("getbalance");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String listreceivedbyaddress() {
		String returnAnswer = generateRequest("listreceivedbyaddress");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String openwallet() {
		String returnAnswer = generateRequest("walletpassphrase",
				this.walletpassphrase, 600);
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String closewallet() {
		String returnAnswer = this.generateRequest("walletlock");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getconnectioncount() {
		String returnAnswer = generateRequest("getconnectioncount");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String gethashespersec() {
		String returnAnswer = generateRequest("gethashespersec");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getmininginfo() {
		
		String returnAnswer = generateRequest("getmininginfo");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getnettotals() {
		
		String returnAnswer = generateRequest("getnettotals");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getpeerinfo() {
		
		String returnAnswer = generateRequest("getpeerinfo");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String listaddressgroupings() {
		
		String returnAnswer = generateRequest("listaddressgroupings");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String listlockunspent() {
		
		String returnAnswer = generateRequest("listlockunspent");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String ping() {
		
		String returnAnswer = generateRequest("ping");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String stop() {
		
		String returnAnswer = generateRequest("stop");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String gettxoutsetinfo() {
		
		String returnAnswer = generateRequest("gettxoutsetinfo");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getunconfirmedbalance() {
		String returnAnswer = generateRequest("getunconfirmedbalance");
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @param txid
	 * @return
	 */
	public String gettransaction(String txid) {
		logger.info(txid);
		String returnAnswer = generateRequest("gettransaction", txid);
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @param verbose
	 * @return
	 */
	public String getrawmempool(boolean... verbose) {
		String returnAnswer = "";
		if (verbose.length > 0) {
			logger.info(verbose.toString());
			returnAnswer = generateRequest("getrawmempool", verbose[0]);
		} else {
			returnAnswer = generateRequest("getrawmempool", false);
		}
		logger.info(returnAnswer);
		return returnAnswer;
	}

	/**
	 * @param txid
	 * @return
	 */
	public String getrawtransaction(String txid) {
		logger.info(txid);
		String returnAnswer = generateRequest("getrawtransaction", txid);
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @param hexstring
	 * @return
	 */
	public String decoderawtransaction(String hexstring) {
		logger.info(hexstring);
		String returnAnswer = generateRequest("decoderawtransaction", hexstring);
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @param hash
	 * @return
	 */
	public String getblock(String hash) {
		logger.info(hash);
		String returnAnswer = generateRequest("getblock", hash);
		logger.info(hash);
		return returnAnswer;

	}

	/**
	 * @param index
	 * @return
	 */
	public String getblockhash(int index) {
		logger.info(String.valueOf(index));
		String returnAnswer = this.generateRequest("getblockhash", index);
		logger.info(returnAnswer);
		return returnAnswer;
	}

	/**
	 * @param label
	 * @return
	 */
	public String getnewaddress(String label) {
		logger.info(label);
		String returnAnswer = generateRequest("getnewaddress", label);
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * 获取钱包地址
	 * @param account
	 * @return
	 */
	public String getaddressesbyaccount(String account) {
		logger.info(account);              
		String returnAnswer = generateRequest("getaddressesbyaccount", account);
		logger.info(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @param address
	 * @return
	 */
	public String dumpprivkey(String address) {
		logger.info(address);
		this.closewallet();
		this.openwallet();
		String privateKey = generateRequest("dumpprivkey", address);
		this.closewallet();
		logger.info(privateKey);
		return privateKey;
	}

	/**
	 * @param rawTransactionHex
	 * @return
	 */
	public String sendRawTransaction(String rawTransactionHex) {
		logger.info(rawTransactionHex);
		String returnAnswer = generateRequest("sendrawtransaction",
				rawTransactionHex);
		logger.info(returnAnswer);
		return returnAnswer;
	}

	// Done
	/**
	 * @param oldpassphrase
	 * @param newpassphrase
	 * @return
	 */
	public String walletpassphrasechange(String oldpassphrase, String newpassphrase) {
		
		String returnAnswer = generateRequest("walletpassphrasechange",
				oldpassphrase,newpassphrase);
		logger.info(returnAnswer);
		return returnAnswer;
	}
	
	// Dosn't work for some reason
	/**
	 * @param nrequired
	 * @param key
	 * @return
	 */
	public String createmultisig(int nrequired, String... key) {
		logger.info(String.valueOf(nrequired));

		JSONArray address = new JSONArray();
		if (key.length > 0) {
			for (int i = 0; i < key.length; i++) {
				address.put(key[i]);

			}
		}
		return generateRequest("createmultisig", nrequired, address);

	}

	// Dosn't work for some reason
	/**
	 * @param nrequired
	 * @param label
	 * @param key
	 * @return
	 */
	public String addmultisigaddress(int nrequired, String label, String... key) {
		logger.info(String.valueOf(nrequired));

		JSONArray address = new JSONArray();
		if (key.length > 0) {
			for (int i = 0; i < key.length; i++) {

				address.put(key[i]);
			}
		}
		if (label.equals(""))
			return generateRequest("addmultisigaddress", nrequired, address);
		else
			return generateRequest("addmultisigaddress", nrequired, address,
					label);

	}

	// Dosn't work for some reason
	/**
	 * @param txs
	 * @param sendAddresses
	 * @return
	 */
	public String createrawtransaction(JSONArray txs, JSONObject sendAddresses) {
		logger.info(sendAddresses.toString());
		String returnAnswer = generateRequest("createrawtransaction", txs,
				sendAddresses);
		logger.info(returnAnswer);
		return generateRequest("createrawtransaction", txs, sendAddresses);
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
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< requestBody： "+requestBody);
		if (method.equals("dumpprivkey"))
			logger.info("dumpprivkey so no logging");
		else if (method.equals("getblock"))
			logger.info("It's to long to log an entire block");
		else if (method.equals("walletpassphrasechange"))
			logger.info("walletpassphrasechange so no logging");
		else
			logger.info("请求参数："+requestBody);

		final PasswordAuthentication temp = new PasswordAuthentication(
				this.rpcuser, this.rpcpassword.toCharArray());
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return temp;
			}
		});
		String uri = "http://" + this.rpcurl + ":" + this.rpcport;
		logger.info("请求url:---------"+uri);

		String contentType = "application/json";
		HttpURLConnection connection = null;
		try {
			URL url = new URL(uri);
			connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", contentType);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Length",
			Integer.toString(requestBody.getBytes().length));
			connection.setUseCaches(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(6000);
			OutputStream out = connection.getOutputStream();
			out.write(requestBody.getBytes());
			out.flush();
			out.close();
		} catch (Exception ioE) {
			connection.disconnect();
			//ioE.printStackTrace();
			logger.info("connection error");
		}

		try {
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream is = connection.getInputStream();
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(is));
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
					logger.info("响应结果："+returnAnswer);
					
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
	 *返回交易ID 
	 */
	public String sendToAddress(String address,double amount){
		logger.info("SendToAddress Address:[{}],Amount:[{}]",address,amount);
		String returnAnswer = generateRequest("sendtoaddress", address,amount);
		logger.info(returnAnswer);
		return returnAnswer;
	}
	
	/**
	 *检查地址是否有效 
	 */
	public String validateaddress(String address){
		logger.info("validateaddress Address:[{}]",address);
		String returnAnswer = generateRequest("validateaddress",address);
		logger.info(returnAnswer);
		return returnAnswer;
	}
	
	/**
	 *查询某人交易记录 
	 */
	public String listtransactions(String username,int count,int from){
		logger.info("listtransactions username:[{}],Cout:[{}],From:[{}]",username,count,from);
		String returnAnswer = generateRequest("listtransactions",username,count,from);
		logger.info(returnAnswer);
		return returnAnswer;
	}
	
	

}
