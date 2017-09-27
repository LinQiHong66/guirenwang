package com.inesv.digiccy.test;

import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.wallet.BitcoinAPI;
import com.inesv.digiccy.wallet.TransactionResult;

/**
 * Created by SKINK on 2016/10/6.
 */
public class BaseJunit4Test {

    public static void main(String[] args) {
    	BitcoinAPI ba =  new BitcoinAPI("dhs", "dirhams!", "127.0.0.1", "9912", "asdasd");
    	/*String accountAddress = ba.getnewaddress("dsessse");*/
    	 String address=ba.getaddressesbyaccount("linqihong"); 
    	/*String balances = ba.getbalance();*/
    	 
    	  TransactionResult transactionResult = new TransactionResult( "127.0.0.1", "9912", "dhs", "dirhams!", "asdasd");//建立连接
    	  double amount = transactionResult.getAmount("linqihong");
    	  String trade = transactionResult.getTrans("linqihong");
    	  String trade2=transactionResult.getTransInfoToOneHundred();
    	System.out.println(trade2);
    	
    }
}
